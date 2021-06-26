package com.thoughtworks.refactor;

import java.util.*;

public class Hands {
    private final String hands;

    public Hands(String hands) {
        this.hands = hands;
    }

    public String getHands() {
        return hands;
    }

    public int[] getDescendingHandsNumbers() {
        return PokerUtil.getDescendingHandsNumbers(this.hands);
    }

    public Catetgory getCategory() {
        return new Catetgory(PokerUtil.judgeHandCategory(this.hands));
    }

    public int[] getDistinctDescendingHandsNumbers() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] number = getDescendingHandsNumbers();
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
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    int[] getDescendingNoRepeatNumbers() {
        int[] descendingHandsNumbers = getDescendingHandsNumbers();
        return PokerUtil.noOrRepeatNumber(descendingHandsNumbers, 1);
    }

    int[] getDescendingRepeatNumbers() {
        int[] descendingHandsNumbers = getDescendingHandsNumbers();
        return PokerUtil.noOrRepeatNumber(descendingHandsNumbers, 0);
    }
}
