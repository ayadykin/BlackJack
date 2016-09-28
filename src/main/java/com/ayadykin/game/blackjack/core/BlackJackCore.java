package com.ayadykin.game.blackjack.core;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class BlackJackCore implements Serializable {

	@EJB
	private BlackJackRules blackJackRules;

	public void checkBlackJack(Player player, Dealer dealer) {

		int playerPoints = countPoints(player.getCards());
		int dealerPoints = countPoints(dealer.getCards());
		if (blackJackRules.isBlackJack(playerPoints)) {
			dealerOpenHiddenCard(dealer);
			dealerPoints = countPoints(dealer.getCards());
			if (blackJackRules.isBlackJack(dealerPoints)) {
				// return BlackJackResponce.NEXT_STEP;
			} else {
				// return BlackJackResponce.NEXT_STEP;
			}
		}
		// return BlackJackResponce.NEXT_STEP;

	}

	public boolean playerStep(Player player, Card card) {
		player.addCard(card);
		int points = countPoints(player.getCards());
		if (blackJackRules.isBust(points)) {
			player.setPlayerResult(PlayerResult.BUST);
			return false;
		}
		return true;
	}

	public boolean dealerStep(Dealer dealer) {
		dealerOpenHiddenCard(dealer);
		int points = countPoints(dealer.getCards());
		if (blackJackRules.dealerStep(points)) {
			dealer.addCard();
			points = countPoints(dealer.getCards());
			if (blackJackRules.isBust(points)) {
				dealer.setPlayerResult(PlayerResult.BUST);
				return false;
			} else if (blackJackRules.dealerStep(points)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void getGameResult(List<Player> players, Dealer dealer) {
		int dealerPoints = dealer.getPoints();

		for (Player player : players) {
			if (player.getPlayerResult() == PlayerResult.BUST && dealer.getPlayerResult() == PlayerResult.BUST) {
				player.setPlayerResult(PlayerResult.PUSH);
			} else if (player.getPlayerResult() == PlayerResult.BUST) {
				player.setPlayerResult(PlayerResult.LOSE);
			} else if (dealer.getPlayerResult() == PlayerResult.BUST) {
				player.setPlayerResult(PlayerResult.WIN);
			} else {
				int playerPoints = player.getPoints();
				if (playerPoints == dealerPoints) {
					player.setPlayerResult(PlayerResult.PUSH);
				} else if (playerPoints > dealerPoints) {
					player.setPlayerResult(PlayerResult.WIN);
				} else {
					player.setPlayerResult(PlayerResult.LOSE);
				}
			}
		}
	}

	public void countBet(List<Player> players) {
		blackJackRules.countBet(players);
	}

	private void dealerOpenHiddenCard(Dealer dealer) {
		dealer.getCards().get(1).setHidden(false);
	}

	public int countPoints(List<Card> ñards) {
		int points = 0;

		for (Card card : ñards) {
			points += card.getNominal().getPoints();
		}
		return points;
	}
}
