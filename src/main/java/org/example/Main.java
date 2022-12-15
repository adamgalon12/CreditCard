package org.example;

import java.util.*;

import static org.example.CardMatcher.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String cardNumber = scanner.next();


        System.out.println("Card number : " + cardNumber);
        ArrayList<Card> cards = loadCardsFromFile();

        try {
            Card oneCard = findMatchingCardType(cards, cardNumber);
            System.out.println(String.format("%s | %s",
                    oneCard.Name,
                    formatCardNumber(oneCard.Pattern, cardNumber)));
        } catch (Exception e) {
            System.out.println("Number is not matching any type of card.");
        }
    }
}
