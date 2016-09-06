package com.ayadykin.blackjack.actions;

/**
 * Created by Andrey Yadykin on 14 бер. 2016 р.
 */

public enum GameStatus {
    NEW, START, BET,  END;

    static {
        NEW.transitsTo(START);
        START.transitsTo(END);
    }

    private GameStatus[] transitions;

    private void transitsTo(GameStatus... transitions) {
        this.transitions = transitions;
    }

    public boolean canTransitTo(GameStatus status) {
        if (transitions != null) {
            for (int i = 0; i < transitions.length; i++) {
                if (transitions[i] == status)
                    return true;
            }
        }
        return false;
    }
}
