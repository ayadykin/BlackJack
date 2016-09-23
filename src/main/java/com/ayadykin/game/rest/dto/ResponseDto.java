package com.ayadykin.game.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.BlackJackTable;

/**
 * Created by Andrey Yadykin on 14 бер. 2016 р.
 */

public class ResponseDto implements Serializable {
    private List<Player> players;
    private Dealer dealer;

    public ResponseDto() {

    }

    public ResponseDto(BlackJackTable blackJackTable) {
        this.dealer = blackJackTable.getDealer();
        this.players = blackJackTable.getPlayers();
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

}
