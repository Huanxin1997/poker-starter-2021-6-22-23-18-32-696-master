package com.thoughtworks.refactor;

public class Catetgory {

    private final String catetgory;

    public Catetgory(String catetgory) {
        this.catetgory = catetgory;
    }

    public String getCatetgory() {
        return catetgory;
    }


    public int judgeHandsCategoryRanking() {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(this.catetgory)) {
                index = i;
            }
        }
        return index;
    }
}
