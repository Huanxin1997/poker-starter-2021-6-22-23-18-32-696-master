package com.thoughtworks.refactor;

import java.util.*;

public class Poker {

    public static final String[] CATEGORIES = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackHands, String whiteHands) {
        String winResult = "";

        final Hands blackHandsObj = new Hands(blackHands);
        int[] blackDescendingHandsNumbers = blackHandsObj.getDescendingHandsNumbers();
        final Catetgory blackHandsCategory = blackHandsObj.getCategory();
        int blackHandsCategoryRanking = blackHandsCategory.judgeHandsCategoryRanking();

        int[] blackDistinctDescendingHandsNumbers = blackHandsObj.getDistinctDescendingHandsNumbers();
        int[] blackRepeatNumbers = blackHandsObj.getDescendingRepeatNumbers();
        int[] blackNoRepeatNumbers = blackHandsObj.getDescendingNoRepeatNumbers();

        final Hands whiteHandsObj = new Hands(whiteHands);
        int[] whiteDescendingHandsNumbers = whiteHandsObj.getDescendingHandsNumbers();
        final Catetgory whiteHandsCategory = whiteHandsObj.getCategory();
        int whiteHandsCategoryRanking = whiteHandsCategory.judgeHandsCategoryRanking();

        int[] whiteDistinctDescendingHandsNumbers = whiteHandsObj.getDistinctDescendingHandsNumbers();
        int[] whiteRepeatNumbers = whiteHandsObj.getDescendingRepeatNumbers();
        int[] whiteNoRepeatNumbers = whiteHandsObj.getDescendingNoRepeatNumbers();

        if (blackHandsCategoryRanking < whiteHandsCategoryRanking) {
            winResult = "black wins - " + CATEGORIES[blackHandsCategoryRanking];
        } else if (blackHandsCategoryRanking > whiteHandsCategoryRanking) {
            winResult = "white wins - " + CATEGORIES[whiteHandsCategoryRanking];
        } else {
            if (blackHandsCategoryRanking == 0) { // Straight Flush
                if (blackDescendingHandsNumbers[0] < whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackDescendingHandsNumbers[0] > whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(blackDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRanking == 1) { // Four Of A Kind
                if (blackDistinctDescendingHandsNumbers[0] < whiteDistinctDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 2) { // Full House
                if (blackDistinctDescendingHandsNumbers[0] < whiteDistinctDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 3) { // Flush
                for (int i = 0; i < 5; i++) {
                    if (blackDescendingHandsNumbers[i] < whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(whiteDescendingHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackDescendingHandsNumbers[i] > whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(blackDescendingHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRanking == 4) { // Straight
                if (blackDescendingHandsNumbers[0] < whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackDescendingHandsNumbers[0] > whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(blackDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRanking == 5) { // Three Of A Kind
                if (blackRepeatNumbers[0] < whiteRepeatNumbers[0]) {
                    String sig = intNumber(whiteRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 6) { // Two Pair
                for (int i = 0; i < 2; i++) {
                    if (blackRepeatNumbers[i] < whiteRepeatNumbers[i]) {
                        String sig = intNumber(whiteRepeatNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackRepeatNumbers[i] > whiteRepeatNumbers[i]) {
                        String sig = intNumber(blackRepeatNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult == "") {
                    if (blackNoRepeatNumbers[0] < whiteNoRepeatNumbers[0]) {
                        String sig = intNumber(whiteNoRepeatNumbers[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackNoRepeatNumbers[0] > whiteNoRepeatNumbers[0]) {
                        String sig = intNumber(blackNoRepeatNumbers[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRanking == 7) { // One Pair
                if (blackRepeatNumbers[0] < whiteRepeatNumbers[0]) {
                    String sig = intNumber(whiteRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackRepeatNumbers[0] > whiteRepeatNumbers[0]) {
                    String sig = intNumber(blackRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackNoRepeatNumbers[i] < whiteNoRepeatNumbers[i]) {
                            String sig = intNumber(whiteNoRepeatNumbers[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackNoRepeatNumbers[i] > whiteNoRepeatNumbers[i]) {
                            String sig = intNumber(blackNoRepeatNumbers[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { // High Card
                for (int i = 0; i < 5; i++) {
                    if (blackDescendingHandsNumbers[i] < whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(whiteDescendingHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackDescendingHandsNumbers[i] > whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(blackDescendingHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            }
        }
        return winResult;
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

}
