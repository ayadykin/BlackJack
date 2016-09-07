package com.ayadykin.blackjack.exceptions;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */

public class BlackJackException extends RuntimeException {
    public BlackJackException() {

    }

    public BlackJackException(String message) {
        super(message);
    }
}
