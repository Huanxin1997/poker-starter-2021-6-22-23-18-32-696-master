package com.thoughtworks.refactor;

public class Hands {
    private final String hands;

    public Hands(String hands) {
        this.hands = hands;
    }

    public String getHands() {
        return hands;
    }

    Category getCategory() {
        return new Category(PokerUtil.judgeHandCategory(this));
    }

    int[] getDescendingNumbers() {
        return PokerUtil.getDescendingHandsNumbers(this);
    }

    int[] getDistinctDescendingNumbers() {
        return PokerUtil.getDistinctDescendingHandsNumbers(getDescendingNumbers());
    }

    int[] getDescendingRepeatNumbers() {
        return PokerUtil.getDescendingRepeatNumbers(getDescendingNumbers());
    }

    int[] getDescendingNoRepeatNumbers() {
        return PokerUtil.getDescendingNoRepeatNumbers(getDescendingNumbers());
    }
}
