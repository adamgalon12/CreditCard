package org.example;

public class Card {
    String Name;
     int Pan;
    int PrefLength;
    int RangeStart;
    int RangeEnd;
     String Pattern;

    public Card(String[] record) {
        Name = record[0];
        Pan = Integer.parseInt(record[1]);
        PrefLength = Integer.parseInt(record[2]);
        RangeStart = Integer.parseInt(record[3]);
        RangeEnd = Integer.parseInt(record[4]);
        Pattern = record[5];
    }
}
