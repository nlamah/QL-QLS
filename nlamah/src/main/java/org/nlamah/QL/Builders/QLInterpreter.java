package org.nlamah.QL.Builders;

import java.util.ArrayList;
import java.util.List;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.nlamah.QBase.Error.AmbiguityError;
import org.nlamah.QBase.Error.AttemptingFullContextError;
import org.nlamah.QBase.Error.ContextSensitivityError;
import org.nlamah.QBase.Error.QBaseError;
import org.nlamah.QBase.Error.QBaseException;
import org.nlamah.QBase.Error.QBaseWarning;
import org.nlamah.QBase.Error.SyntaxError;
import org.nlamah.QBase.Tools.AntlrTools;
import org.nlamah.QBase.Tools.SourceCodeTools;
import org.nlamah.QL.Model.Form.Form;
import org.nlamah.QL.TypeChecker.QLTypeChecker;

public class QLInterpreter implements ANTLRErrorListener 
{	
	private Form form;
	private boolean skipTypeChecking;
	
	private List<QBaseWarning> warnings;
	private List<QBaseError> errors;

	public QLInterpreter()
	{
		warnings = new ArrayList<QBaseWarning>();
		errors = new ArrayList<QBaseError>();
	}

	public Form interprete(String sourceCodePath) throws QBaseException
	{		
		try 
		{
			String sourceCode = SourceCodeTools.sourceCode(sourceCodePath);

			ParseTree tree = AntlrTools.createFormTreeFromSourceCode(sourceCode, this);

			RawFormBuilder rawFormBuilder = new RawFormBuilder();

			form = rawFormBuilder.buildForm(tree);

			errors.addAll(rawFormBuilder.errors());

			if (errors.size() > 0)
			{
				throw new QBaseException(errors);
			}

			if(!skipTypeChecking)
			{
				QLTypeChecker typeChecker = new QLTypeChecker();
			
				typeChecker.check(form);
				
				warnings.addAll(typeChecker.warnings());
			}
			
			return form;
		} 
		catch (QBaseException exception) 
		{
			throw new QBaseException(exception.warnings(), exception.errors());
		}
	}
	
	public void skipTypeChecking()
	{
		skipTypeChecking = true;
	}

	public List<QBaseWarning> warnings()
	{
		return warnings;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) 
	{
		errors.add(new SyntaxError(line, charPositionInLine, msg));
	}

	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) 
	{
		errors.add(new AmbiguityError(startIndex, stopIndex));
	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) 
	{
		errors.add(new AttemptingFullContextError(startIndex, stopIndex));
	}

	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) 
	{
		errors.add(new ContextSensitivityError(startIndex, stopIndex));
	}   
}