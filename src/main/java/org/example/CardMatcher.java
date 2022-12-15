package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CardMatcher {
    private static final String CSV_FILE = "src/main/resources/conf4.csv";
    private static final String DELIMITER = ",";

    public static String formatCardNumber(String pattern, String cardNumber) {
        String formatted = "";
        for (int i = 0, j = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '#') {
                formatted += cardNumber.charAt(j++);
            } else {
                formatted += ' ';
            }
        }
        return formatted;
    }

    private static ArrayList<String> readCSV() {
        ArrayList<String> records = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(DELIMITER);
                records.add(String.join(DELIMITER, columns));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return records;
    }

    public static Card findMatchingCardType(ArrayList<Card> cards, String cardNumber) {
        return cards.stream()
                .filter(r -> r.Pan == cardNumber.length())
                .filter(r -> r.RangeStart <= Integer.parseInt(cardNumber.substring(0, r.PrefLength)))
                .filter(r -> r.RangeEnd >= Integer.parseInt(cardNumber.substring(0, r.PrefLength)))
                .findFirst()
                .get();

    }

    public static ArrayList<Card> loadCardsFromFile() {
        ArrayList<String> records = readCSV();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 1; i < records.size(); i++) {
            String[] record = records.get(i).split(",");
            Card card = new Card(record);
            cards.add(card);
        }
        return cards;
    }
}
