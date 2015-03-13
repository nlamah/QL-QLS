package interpreter

import (
	"time"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/qlang/interpreter/ast"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/qlang/interpreter/ast/typechecker"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/qlang/interpreter/plumbing"
	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/qlang/interpreter/symboltable"
)

func typecheck(q *ast.QuestionaireNode) {
	tc, symboltable := typechecker.New()
	symboltable.SetWatchError(true)

	tc.QuestionaireNode(q)

	symboltable.ShowWarn()
	symboltable.PanicErr()
}

func openChannels() (toFrontend, fromFrontend chan *plumbing.Frontend) {
	toFrontend = make(chan *plumbing.Frontend)
	fromFrontend = make(chan *plumbing.Frontend)
	return toFrontend, fromFrontend
}

func (v *interpreter) drawLoop(redraw bool) bool {
	v.send <- &plumbing.Frontend{
		Type: plumbing.ReadyP,
	}

drawLoop:
	for {
		select {
		case r := <-v.receive:
			switch r.Type {
			case plumbing.ReadyT:
				v.draw.QuestionaireNode(v.questionaire)
				v.send <- &plumbing.Frontend{Type: plumbing.Flush}
				break drawLoop
			}
		}
	}

	if redraw {
		redraw = false
		go func(receive chan *plumbing.Frontend) {
			receive <- &plumbing.Frontend{Type: plumbing.ReadyT}
		}(v.receive)
	}
	v.execute.QuestionaireNode(v.questionaire)
	v.send <- &plumbing.Frontend{Type: plumbing.Flush}
	return redraw
}

func (v *interpreter) mainLoop(redraw bool) {
	ticker := time.Tick(100 * time.Millisecond)
mainLoop:
	for {
		select {
		case r := <-v.receive:
			switch r.Type {

			case plumbing.Answers:
				for identifier, answer := range r.Answers {
					q := v.symbols.Read(identifier)
					q.(symboltable.StringParser).From(answer)
					v.symbols.Update(identifier, q)
					v.execute.QuestionaireNode(v.questionaire)
					v.send <- &plumbing.Frontend{Type: plumbing.Flush}
				}

			case plumbing.ReadyT:
				v.execute.QuestionaireNode(v.questionaire)
				v.send <- &plumbing.Frontend{Type: plumbing.Flush}

			case plumbing.Redraw:
				redraw = true
				break mainLoop

			}

		case <-ticker:
			v.send <- &plumbing.Frontend{Type: plumbing.FetchAnswers}
		}
	}
}
