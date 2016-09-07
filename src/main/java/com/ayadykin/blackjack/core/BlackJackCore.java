package com.ayadykin.blackjack.core;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.model.Person;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Stateless
public class BlackJackCore {

	private static final int BLACK_JACK = 21;

	public BlackJackResponce checkBlackJack(Table table) {

		Player player = table.getPlayer();
		Dealer dealer = table.getDealer();
		if (isBlackJack(player)) {
			dealerOpenHiddenCard(dealer);
			if (isBlackJack(dealer)) {
				return BlackJackResponce.PUSH;
			} else {
				return BlackJackResponce.BLACK_JACK;
			}
		}
		return BlackJackResponce.NEXT_MOVE;

	}

	public BlackJackResponce playerHit(Table table) {
		Player player = table.getPlayer();
		player.addCard(table.getCard());

		if (isBust(player)) {
			return BlackJackResponce.LOSE;
		}

		return BlackJackResponce.NEXT_MOVE;
	}

	public BlackJackResponce dealerStep(Table table) {
		Dealer dealer = table.getDealer();

		dealerOpenHiddenCard(dealer);

		int points = countPoints(dealer);
		dealer.setPoints(points);
		while (points < 17) {
			Card card = table.getCard();
			dealer.getCards().add(card);
			points = countPoints(dealer);
			dealer.setPoints(points);
		}
		if (isBust(dealer)) {
			return BlackJackResponce.WIN;
		}

		return getGameResult(table);
	}

	private void dealerOpenHiddenCard(Dealer dealer) {
		dealer.getCards().get(1).setHidden(false);
		int points = countPoints(dealer);
		dealer.setPoints(points);
	}

	private BlackJackResponce getGameResult(Table table) {
		int dealerPoints = table.getDealer().getPoints();
		int playerPoints = table.getPlayer().getPoints();

		if (playerPoints == dealerPoints) {
			return BlackJackResponce.PUSH;
		} else if (playerPoints > dealerPoints) {
			return BlackJackResponce.WIN;
		} else {
			return BlackJackResponce.LOSE;
		}
	}

	private int countPoints(Person player) {
		int points = 0;
		for (Card card : player.getCards()) {
			points += card.getNominal().getPoints();
		}
		System.out.println(points);
		return points;
	}

	private boolean isBlackJack(Person player) {
		int points = countPoints(player);
		if (points == BLACK_JACK) {
			return true;
		}
		return false;
	}

	private boolean isBust(Person player) {
		int points = countPoints(player);
		if (points > BLACK_JACK) {
			return true;
		}
		return false;
	}
}
