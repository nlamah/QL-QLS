form TaxForm {

    question hasSoldHouse "Did you sell your house in 2014?"
    answer boolean is (false)

    if hasSoldHouse {
        question totalHousesSold "How many houses did you sell in 2014?"
        answer number

        if totalHousesSold == 1 {
            question priceHouseSold "At what price did you sell your house?"
            answer number
        } else {
            if totalHousesSold == 2 {
                question priceHouseSold1 "At what price did you sell your first house?"
                answer number

                question priceHouseSold2 "At what price did you sell your second house?"
                answer number
            }
        }
    }
}