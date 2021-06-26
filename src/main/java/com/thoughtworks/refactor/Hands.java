package com.thoughtworks.refactor;

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
}
