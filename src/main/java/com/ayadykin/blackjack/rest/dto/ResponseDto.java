package com.ayadykin.blackjack.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.model.Person;

/**
 * Created by Andrey Yadykin on 14 бер. 2016 р.
 */

public class ResponseDto implements Serializable{
    private List<Person> players;
    private BlackJackResponce blackJackResponce;

    public ResponseDto(){
    	
    }
    public ResponseDto(List<Person> players, BlackJackResponce blackJackResponce) {
        this.players = players;
        this.blackJackResponce = blackJackResponce;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }

    public BlackJackResponce getBlackJackResponce() {
        return blackJackResponce;
    }

    public void setBlackJackResponce(BlackJackResponce blackJackResponce) {
        this.blackJackResponce = blackJackResponce;
    }

}
