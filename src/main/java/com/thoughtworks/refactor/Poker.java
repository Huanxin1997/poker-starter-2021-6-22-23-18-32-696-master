package com.thoughtworks.refactor;

import java.util.*;

public class Poker {

    public static final String[] CARD_TYPES = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackCard, String whiteCard) {
        String winResult = "";
        String blackCardType = judgeCardType(blackCard);
        String whiteCardType = judgeCardType(whiteCard);
        String[] cardTypes = CARD_TYPES;
        int[] blackCardDescendingNumbers = getDescendingCardNumbers(blackCard);
        int[] whiteCardDescendingNumbers = getDescendingCardNumbers(whiteCard);
        int blackCardTypeIndex = judgeCardTypeIndex(blackCardType);
        int whiteCardTypeIndex = judgeCardTypeIndex(whiteCardType);
        int[] blackDistinctDescendingCardNumbers = getDistinctDescendingCardNumbers(blackCardDescendingNumbers);
        int[] whiteDistinctDescendingCardNumbers = getDistinctDescendingCardNumbers(whiteCardDescendingNumbers);
        int[] blackDescendingRepeatNumbers = getRepeatNumbers(blackCardDescendingNumbers);
        int[] whiteDescendingRepeatNumbers = getRepeatNumbers(whiteCardDescendingNumbers);
        int[] blackDescendingNoRepeatNumbers = getNoRepeatNumbers(blackCardDescendingNumbers);
        int[] whiteDescendingNoRepeatNumbers = getNoRepeatNumbers(whiteCardDescendingNumbers);
        if (blackCardTypeIndex < whiteCardTypeIndex) {
            winResult = "black wins - " + cardTypes[blackCardTypeIndex];
        } else if (blackCardTypeIndex > whiteCardTypeIndex) {
            winResult = "white wins - " + cardTypes[whiteCardTypeIndex];
        } else {
            if (blackCardTypeIndex == 0) { // Straight Flush
                if (blackCardDescendingNumbers[0] < whiteCardDescendingNumbers[0]) {
                    String sig = intNumber(whiteCardDescendingNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackCardDescendingNumbers[0] > whiteCardDescendingNumbers[0]) {
                    String sig = intNumber(blackCardDescendingNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackCardTypeIndex == 1) { // Four Of A Kind
                if (blackDistinctDescendingCardNumbers[0] < whiteDistinctDescendingCardNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingCardNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingCardNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardTypeIndex == 2) { // Full House
                if (blackDistinctDescendingCardNumbers[0] < whiteDistinctDescendingCardNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingCardNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingCardNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardTypeIndex == 3) { // Flush
                for (int i = 0; i < 5; i++) {
                    if (blackCardDescendingNumbers[i] < whiteCardDescendingNumbers[i]) {
                        String sig = intNumber(whiteCardDescendingNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackCardDescendingNumbers[i] > whiteCardDescendingNumbers[i]) {
                        String sig = intNumber(blackCardDescendingNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackCardTypeIndex == 4) { // Straight
                if (blackCardDescendingNumbers[0] < whiteCardDescendingNumbers[0]) {
                    String sig = intNumber(whiteCardDescendingNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackCardDescendingNumbers[0] > whiteCardDescendingNumbers[0]) {
                    String sig = intNumber(blackCardDescendingNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackCardTypeIndex == 5) { // Three Of A Kind
                if (blackDescendingRepeatNumbers[0] < whiteDescendingRepeatNumbers[0]) {
                    String sig = intNumber(whiteDescendingRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDescendingRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardTypeIndex == 6) { // Two Pair
                for (int i = 0; i < 2; i++) {
                    if (blackDescendingRepeatNumbers[i] < whiteDescendingRepeatNumbers[i]) {
                        String sig = intNumber(whiteDescendingRepeatNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackDescendingRepeatNumbers[i] > whiteDescendingRepeatNumbers[i]) {
                        String sig = intNumber(blackDescendingRepeatNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult == "") {
                    if (blackDescendingNoRepeatNumbers[0] < whiteDescendingNoRepeatNumbers[0]) {
                        String sig = intNumber(whiteDescendingNoRepeatNumbers[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackDescendingNoRepeatNumbers[0] > whiteDescendingNoRepeatNumbers[0]) {
                        String sig = intNumber(blackDescendingNoRepeatNumbers[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackCardTypeIndex == 7) { // One Pair
                if (blackDescendingRepeatNumbers[0] < whiteDescendingRepeatNumbers[0]) {
                    String sig = intNumber(whiteDescendingRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackDescendingRepeatNumbers[0] > whiteDescendingRepeatNumbers[0]) {
                    String sig = intNumber(blackDescendingRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackDescendingNoRepeatNumbers[i] < whiteDescendingNoRepeatNumbers[i]) {
                            String sig = intNumber(whiteDescendingNoRepeatNumbers[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackDescendingNoRepeatNumbers[i] > whiteDescendingNoRepeatNumbers[i]) {
                            String sig = intNumber(blackDescendingNoRepeatNumbers[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { // High Card
                for (int i = 0; i < 5; i++) {
                    if (blackCardDescendingNumbers[i] < whiteCardDescendingNumbers[i]) {
                        String sig = intNumber(whiteCardDescendingNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackCardDescendingNumbers[i] > whiteCardDescendingNumbers[i]) {
                        String sig = intNumber(blackCardDescendingNumbers[i]);
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

    private int[] getNoRepeatNumbers(int[] cardNumbers) {
        return noOrRepeatNumber(cardNumbers, 1);
    }

    private int[] getRepeatNumbers(int[] cardNumbers) {
        return noOrRepeatNumber(cardNumbers, 0);
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

    private int[] getDistinctDescendingCardNumbers(int[] cardNumbers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < cardNumbers.length; i++) {
            if (map.get(cardNumbers[i]) != null) {
                map.put(cardNumbers[i], map.get(cardNumbers[i]) + 1);
            } else {
                map.put(cardNumbers[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    // First get the number of occurrences of each element in the array,
    // then calculate the number of occurrences greater than 1 and the number of occurrences equal to 1.
    private int[] noOrRepeatNumber(int[] number, int flag) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < number.length; i++) {
            if (map.get(number[i]) != null) {
                map.put(number[i], map.get(number[i]) + 1);
            } else {
                map.put(number[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] repeatnumber = new int[list.size()];
        int[] norepeatnumber = new int[list.size()];
        int i = 0;
        if (flag == 0) {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() > 1) {
                    repeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() == 1) {
                    norepeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (flag == 0) {
            for (i = 0; i < repeatnumber.length; i++) {
                hashSet.add(repeatnumber[i]);
            }
        } else {
            for (i = 0; i < norepeatnumber.length; i++) {
                hashSet.add(norepeatnumber[i]);
            }
        }
        hashSet.remove(0);
        int[] result = new int[hashSet.size()];
        i = 0;
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        int[] reResult = new int[result.length];
        for (i = 0; i < result.length; i++) {
            reResult[i] = result[result.length - i - 1];
        }
        return reResult;
    }

    private int judgeCardTypeIndex(String cardType) {
        int index = -1;
        String[] type = CARD_TYPES;
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(cardType)) {
                index = i;
            }
        }
        return index;
    }

    // judge the type of card
    private String judgeCardType(String card) {
        String type = "";
        String[] strArray = card.split("");
        int[] number = getDescendingCardNumbers(card);
        int i;
        String[] color = new String[5];
        for (i = 0; i < 5; i++) {
            color[i] = strArray[i * 3 + 1];
        }
        HashSet<Integer> hashSetNumber = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            hashSetNumber.add(number[i]);
        }
        HashSet<String> hashSetType = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            hashSetType.add(color[i]);
        }
        if (hashSetNumber.size() == 5) {
            if ((number[0] - number[4] == 4) && (hashSetType.size() == 1) && (hashSetNumber.size() == 5)) { // five adjacent numbers with same color - Straight Flush
                type = "StraightFlush";
            } else if (number[0] - number[4] == 4 && (hashSetNumber.size() == 5)) { // five adjacent numbers - Straight
                type = "Straight";
            } else if (hashSetType.size() == 1) { // same color - Flush
                type = "Flush";
            } else { // five non-adjacent numbers - High Card
                type = "HighCard";
            }
        } else if (hashSetNumber.size() == 4) { // two numbers are one pair, the other three are different - One Pair
            type = "OnePair";
        } else if (hashSetNumber.size() == 3) {
            if ((number[0] == number[1] && number[2] == number[3]) || (number[1] == number[2] && number[3] == number[4]) || (number[0] == number[1] && number[3] == number[4])) { // Two Pair
                type = "TwoPair";
            } else { // three same numbers, the other two are different - Three Of A Kind
                type = "ThreeOfAKind";
            }
        } else {
            if (number[0] != number[1] || number[3] != number[4]) { // three same numbers, the other two are a pair - Full House
                type = "FourOfAKind";
            } else { // four same numbers - Four Of A Kind
                type = "FullHouse";
            }
        }
        return type;
    }

    // Convert to numbers and sort them from largest to smallest
    private int[] getDescendingCardNumbers(String card) {
        int[] number = new int[5];
        String[] strArray = card.split("");
        int i;
        for (i = 0; i < 5; i++) {
            String c = strArray[i * 3];
            switch (c) {
                case "T":
                    number[i] = 10;
                    break;
                case "J":
                    number[i] = 11;
                    break;
                case "Q":
                    number[i] = 12;
                    break;
                case "K":
                    number[i] = 13;
                    break;
                case "A":
                    number[i] = 14;
                    break;
                default:
                    number[i] = Integer.valueOf(c);
                    break;
            }
        }

        Arrays.sort(number);
        int[] renumber = new int[number.length];
        for (i = 0; i < number.length; i++) {
            renumber[i] = number[number.length - i - 1];
        }
        return renumber;
    }
}
