form SomeForm {
	"1 - Who said the logic is the cement of our civilization with which we ascended from chaos using reason as our guide?"
	questionOne   string

	"2 - What's the answer to life the universe and everything?"
	questionTwo   integer

	"3 - Are you happy today?"
	questionThree bool

	if (questionThree) {
		"4 - Why are you happy today?" questionFour string
		"5 - Grade your happiness?"    questionFive integer
	}

	if (questionTwo > 5) {
		"6 - Question 2 is bigger than 5?" questionSix bool
	}

	if (questionTwo < 5) {
		"7 - Question 2 is less than 5?" questionSeven bool
	}

	if (questionTwo >= 5) {
		"8 - Question 2 is equal to or bigger than 5?" questionEight bool
	}

	if (questionTwo <= 5) {
		"9 - Question 2 is equals to or less than 5?" questionNine bool
	}

	if (questionTwo == 0) {
		"10 - (If-Else) Question 2 is equals to 0?" questionTen bool
	} else {
		"11 - (If-Else) Question 2 is not equals to 0?" questionTenElse bool
	}

	if (questionTwo == 0) {
		"12 - (IF-ElseIf-Else) Question 2 is equals to 0?" questionTenAndHalf bool
	} else if (questionTwo == 1) {
		"13 - (IF-ElseIf-Else) Question 2 is equals to 1?" questionTenAndHalfElseIf bool
	} else {
		"14 - (IF-ElseIf-Else) Question 2 is not equals to 0?" questionTenAndHalfElse bool
	}

	if (1+1 == 2) {
		"15 - is 1+1=2?" questionEleven bool
	}

	if (1-1 == 0) {
		"16 - is 1-1=0?" questionTwelve bool
	}

	if (1*2 == 2) {
		"17 - is 1*2=2?" questionThirteen bool
	}

	if (4/2 == 2) {
		"18 - is 4/2=2?" questionFourteen bool
	}

	// if (questionOne == "surak"){
	// 	"19 - How do you feel?" questionFifteen string
	// }

	"20 - Calculated field" questionSixteen computed = questionTwo * 2
}