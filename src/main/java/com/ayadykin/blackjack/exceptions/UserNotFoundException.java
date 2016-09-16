package com.ayadykin.blackjack.exceptions;

/**
 * Created by Yadykin Andrii Sep 14, 2016
 *
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
