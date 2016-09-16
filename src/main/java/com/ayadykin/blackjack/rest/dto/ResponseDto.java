package com.ayadykin.blackjack.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.BlackJackTable;

/**
 * Created by Andrey Yadykin on 14 бер. 2016 р.
 */

public class ResponseDto implements Serializable {
    private List<Player> players;
    private Dealer dealer;
    private BlackJackResponce blackJackResponce;

    public ResponseDto() {

    }

    public ResponseDto(BlackJackTable blackJackTable) {
        this.dealer = blackJackTable.getDealer();
        this.players = blackJackTable.getPlayers();
        this.blackJackResponce = blackJackTable.getBlackJackFlow().getBlackJackResponce();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public BlackJackResponce getBlackJackResponce() {
        return blackJackResponce;
    }

    public void setBlackJackResponce(BlackJackResponce blackJackResponce) {
        this.blackJackResponce = blackJackResponce;
    }

}
