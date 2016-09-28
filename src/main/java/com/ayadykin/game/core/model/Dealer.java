package com.ayadykin.game.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.blackjack.core.BlackJackCore;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.cards.CardDeck;
import com.ayadykin.game.core.deal.DealCards;

/**
 * Created by Yadykin Andrii Sep 7, 2016
 *
 */

public class Dealer {

	private CardDeck cardDeck;
	private List<Card> cards = new ArrayList<>();
	private int points;	
	private PlayerResult playerResult = PlayerResult.NONE;
	@Inject
	@Named("blackJackDeal")
	private DealCards blackJackDeal;
	@EJB
	private BlackJackCore blackJackCore;

	public Dealer() {

	}

	public void startGame(List<Player> players) {
		// Create new card deck
		cardDeck = new CardDeck();
		// Deal cards
		blackJackDeal.dealCards(players, this);

		// Set points for
		for (Player player : players) {
			player.setPoints(blackJackCore.countPoints(player.getCards()));
		}
		// Set points for
		setPoints(blackJackCore.countPoints(getCards()));

		// TODO except if no players
		// Set player status
		players.get(0).setPlayerStatus(PlayerStatus.STEP);

		///
		for (Player player : players) {
			blackJackCore.checkBlackJack(player, this);
		}
	}
	
	public boolean playerStep(Player player) {
        return blackJackCore.playerStep(player, getCard());
    }
   
    public void dealerStep(List<Player> players) {

        while (blackJackCore.dealerStep(this));

        // Game result
        blackJackCore.getGameResult(players, this);
        // Count bet
        blackJackCore.countBet(players);
    }
	public Card getCard() {
		return cardDeck.getCard().setHidden(false);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean addCard() {
		return cards.add(getCard());
	}

	public boolean addCard(Card card) {
		return cards.add(card);
	}

	public PlayerResult getPlayerResult() {
		return playerResult;
	}

	public void setPlayerResult(PlayerResult playerResult) {
		this.playerResult = playerResult;
	}

}
