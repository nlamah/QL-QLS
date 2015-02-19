package ast

import "strconv"

// IntQuestion stores the answer of question which type is integer numeric
type IntQuestion int

const IntQuestionType = "int"

// From takes the input from Frontend and stores locally - Int
func (s *IntQuestion) From(str string) error {
	val, err := strconv.Atoi(str)
	*s = IntQuestion(val)
	return err
}

// String prints in human form the content of the question - Int
func (s IntQuestion) String() string {
	return strconv.Itoa(int(s))
}

// Type returns "int", therefore indicating this question type name.
func (s IntQuestion) Type() string {
	return IntQuestionType
}

// Value converts underlying int into primitive int
func (s IntQuestion) Value() int {
	return int(s)
}