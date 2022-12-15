package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardMatcherTest {

    @ParameterizedTest
    @CsvSource({"30122994494222,#### ###### ####,3012 299449 4222",
            "4444444444444444,#### #### #### ####,4444 4444 4444 4444",
            "6271999927660003301,###### #############,627199 9927660003301"})
    void shouldFormatNumberWithPattern(String cardNumber,
                                       String pattern,
                                       String expectedFormattedNumber) {
        //Given

        //When
        String formatted = CardMatcher.formatCardNumber(pattern, cardNumber);
        //Then
        assertEquals(expectedFormattedNumber, formatted);
    }

    @Test
    void shouldReturnListOfRecordsFromCsvFile() {
        //Given
        //When
        ArrayList<Card> list = CardMatcher.loadCardsFromFile();
        //Then
        assertEquals(64, list.size());
    }

    @ParameterizedTest
    @CsvSource({"30122994494222,Diners Club Carte Blanche"})
    void shouldFindMatchingCardType(String cardNumber,
                                    String cardName) {
        //Given
        ArrayList<Card> list = CardMatcher.loadCardsFromFile();

        //When
        Card card = CardMatcher.findMatchingCardType(list, cardNumber);

        //Then
        assertEquals(cardName, card.Name);
    }

}