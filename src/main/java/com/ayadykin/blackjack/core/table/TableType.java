package com.ayadykin.blackjack.core.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface TableType {
    Type value();

    enum Type {
        BLACK_JACK, POCKER
    };
}
