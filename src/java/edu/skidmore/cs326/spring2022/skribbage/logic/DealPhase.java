//package edu.skidmore.cs326.spring2022.skribbage.logic;
//
//import java.util.ArrayList;
//
///**
// * Class acts as the DealPhase containing methods to discard cards to crib,
// * determine the dealer, deal cards, and removing a card from hand.
// * 
// * @author Dorjee
// */
//public class DealPhase {
//
//  private Game game;
//
//  /**
//   * Constructor ......
//   * 
//   * @param g
//   *          the current game passed through the params
//   */
//  public DealPhase(Game g) {
//    this.game = g;
//  }
//
//  /**
//   * This method executes the dealing phase of the game. A dealer is determined,
//   * cards are dealt, and cards are discarded to the crib.
//   */
//  public void dealPhase() {
//
//    game.getDeck().shuffle();
//
//    // select the dealer
//
//    // FRONT END INTERACTION
//    // for now, assume you got information from front end
//    // the information you need is the amount of cards to cut from the deck
//    // also assume there are two players
//
//    int amountToCutP1 = 8;
//    int amountToCutP2 = 8;
//
//    if (amountToCutP1 < 4 || amountToCutP1 > 26) {
//      // invalid number of cards to cut
//      // send that information to front end
//      // for now, print to console
//      System.out.println(
//          "The amount of cards to cut must be at least 4 and less than 26 for the first cut.");
//    }
//
//    if (amountToCutP2 < 4 || amountToCutP2 > 22) {
//      // invalid number of cards to cut
//      // send information to front end
//      // for now, print to console
//      System.out.println(
//          "The amount of cards to cut must be at least 4 and less than 22 for the second cut.");
//    }
//
//    determineDealer(amountToCutP1, amountToCutP2);
//
//    game.getDeck().shuffle();
//
//    dealCards();
//
//    // FRONT END INTERACTION
//    // assume you got the information from front end
//    Card[] toDiscardP1 = { new Card('0', Suit.DIAMONDS),
//        new Card('8', Suit.HEARTS) };
//    Card[] toDiscardP2 = { new Card('4', Suit.DIAMONDS),
//        new Card('7', Suit.DIAMONDS) };
//
//    discardToCrib(toDiscardP1, toDiscardP2);
//
//    // flip top card
//    // FRONT END INTERACTION to cut cut amount
//    // assume we have that information
//
//    int amountToCut = 6;
//
//    game.getDeck().moveToTop(amountToCut);
//    Card topCard = game.getDeck().getDeck().get(0);
//
//    // FRONT END INTERACTION: give information about the card on top of the deck
//
//  }
//
//  /**
//   * Given the assumption that there are only two players for now, this method
//   * discards two cards to the crib for each player.
//   * 
//   * @param toDiscardP1
//   *          the array of cards to discard for player 1
//   * @param toDiscardP2
//   *          the array of cards to discard for player 2
//   */
//  public void discardToCrib(Card[] toDiscardP1, Card[] toDiscardP2) {
//
//    for (int i = 0; i < toDiscardP1.length; i++) {
//      // search the player's hand for the card to discard and remove it from the
//      // hand
//      removeCardFromHand(toDiscardP1[i],
//          game.getPlayerList().get(0));
//
//      // add the card to the crib
//      game.getCrib().add(toDiscardP1[i]);
//    }
//
//    for (int j = 0; j < toDiscardP2.length; j++) {
//      // search the player's hand for the card and remove it
//      removeCardFromHand(toDiscardP2[j],
//          game.getPlayerList().get(1));
//
//      // add the card to the crib
//      game.getCrib().add(toDiscardP2[j]);
//
//    }
//
//  }
//
//  /**
//   * Searches for a given card in a specified player's hand and removes it.
//   * 
//   * @param cardToRemove
//   *          is the card to remove from the player's hand
//   * @param p
//   *          is the player
//   */
//  public void removeCardFromHand(Card cardToRemove, Player p) {
//
//    Hand theHand = p.getHand(); // need player phase
//
//    for (int i = 0; i < theHand.getHand().size(); i++) {
//      Card c = theHand.getHand().get(i);
//
//      // if (c.getSuit() == cardToRemove.getSuit() &&
//      // Character.compare(c.getCardIdentifier(),
//      // cardToRemove.getCardIdentifier())){
//      // theHand.remove(i);
//      // return;
//      // }
//
//      if (c.getSuit() == cardToRemove.getSuit()
//          && c.getIdentifier() == cardToRemove.getIdentifier()) {
//        theHand.getHand().remove(i);
//        return;
//      }
//
//    }
//  }
//
//  /**
//   * Deal cards to the players, assuming that there are two players. Each player
//   * will be dealt 6 cards.
//   */
//  public void dealCards() {
//
//    int idxOfDealer = game.getDealerIdx();
//
//    if (idxOfDealer == -1) {
//      return;
//    }
//
//    Hand dealerHand = new Hand();
//    Hand poneHand = new Hand();
//
//    for (int i = 0; i < 6; i++) {
//      poneHand.addCardToHand(game.getDeck().removeTopCard());
//      dealerHand.addCardToHand(game.getDeck().removeTopCard());
//    }
//
//    game.getPlayerList().get(idxOfDealer).setHand(dealerHand);
//
//    if (idxOfDealer == 1) {
//      game.getPlayerList().get(0).setHand(poneHand);
//    } else {
//      game.getPlayerList().get(1).setHand(poneHand);
//    }
//
//  }
//
//  /**
//   * Shuffles the deck, cuts the deck, and determines the dealer.
//   * 
//   * @param amountToCutP1
//   *          is the amount that player 1 wants to cut
//   * @param amountToCutP2
//   *          is the amount that player 2 wants to cut
//   */
//  public void determineDealer(int amountToCutP1, int amountToCutP2) {
//
//    game.getDeck().shuffle();
//
//    Card cardP1 = game.getDeck().cut(amountToCutP1);
//    Card cardP2 = game.getDeck().cut(amountToCutP2);
//
//    // assumption: player 2 is at index 1
//    // assumption: player 1 is at index 0
//
//    if (cardP1.getPointValue() < cardP2.getPointValue()) {
//      game.getPlayerList().get(0).isDealer = true;
//    } else if (cardP2.getPointValue() < cardP1.getPointValue()) {
//      game.getPlayerList().get(1).isDealer = true;
//    } else if (cardP2.getPointValue() == 10) {
//      // ordering is J, Q, K
//      // compare based on that ordering
//      char p1Id = cardP1.getIdentifier();
//      char p2Id = cardP2.getIdentifier();
//
//      if (p1Id == 'J') {
//        game.getPlayerList().get(0).isDealer = true;
//      } else if (p1Id == 'Q' && p2Id == 'K') {
//        game.getPlayerList().get(0).isDealer = true;
//      } else if (p2Id == 'J') {
//        game.getPlayerList().get(1).isDealer = true;
//      } else if (p2Id == 'Q' && p1Id == 'K') {
//        game.getPlayerList().get(1).isDealer = true;
//      } else {
//        determineDealer(amountToCutP1, amountToCutP2);
//      }
//    } else {
//      determineDealer(amountToCutP1, amountToCutP2);
//    }
//
//  }
//
//}
//package edu.skidmore.cs326.spring2022.skribbage.logic;
//
//import java.util.ArrayList;
//
///**
// * Class acts as the DealPhase containing methods to discard cards to crib,
// * determine the dealer, deal cards, and removing a card from hand.
// * 
// * @author Dorjee
// */
//public class DealPhase {
//  
//  private Game gameIns;
//  /**
//   * This method executes the dealing phase of the game. A dealer is determined,
//   * cards are dealt, and cards are discarded to the crib.
//   */
//  public void dealPhase() {
//
//    gameIns.getDeck().shuffle();
//
//    // select the dealer
//
//    // FRONT END INTERACTION
//    // for now, assume you got information from front end
//    // the information you need is the amount of cards to cut from the deck
//    // also assume there are two players
//
//    int amountToCutP1 = 8;
//    int amountToCutP2 = 8;
//
//    if (amountToCutP1 < 4 || amountToCutP1 > 26) {
//      // invalid number of cards to cut
//      // send that information to front end
//      // for now, print to console
//      System.out.println(
//          "The amount of cards to cut must be at least 4 and less than 26 for the first cut.");
//    }
//
//    if (amountToCutP2 < 4 || amountToCutP2 > 22) {
//      // invalid number of cards to cut
//      // send information to front end
//      // for now, print to console
//      System.out.println(
//          "The amount of cards to cut must be at least 4 and less than 22 for the second cut.");
//    }
//
//    determineDealer(amountToCutP1, amountToCutP2);
//
//    gameIns.getDeck().shuffle();
//
//    dealCards();
//
//    // FRONT END INTERACTION
//    // assume you got the information from front end
//    Card[] toDiscardP1 = { new Card('0', Suit.DIAMONDS),
//        new Card('8', Suit.HEARTS) };
//    Card[] toDiscardP2 = { new Card('4', Suit.DIAMONDS),
//        new Card('7', Suit.DIAMONDS) };
//
//    discardToCrib(toDiscardP1, toDiscardP2);
//
//    // flip top card
//    // FRONT END INTERACTION to cut cut amount
//    // assume we have that information
//
//    int amountToCut = 6;
//
//    gameIns.getDeck().moveToTop(amountToCut);
//    Card topCard = gameIns.getDeck().getDeck().get(0);
//
//    // FRONT END INTERACTION: give information about the card on top of the deck
//
//  }
//
//  /**
//   * Given the assumption that there are only two players for now, this method
//   * discards two cards to the crib for each player.
//   * 
//   * @param toDiscardP1
//   *          the array of cards to discard for player 1
//   * @param toDiscardP2
//   *          the array of cards to discard for player 2
//   */
//  public void discardToCrib(Card[] toDiscardP1, Card[] toDiscardP2) {
//
//    for (int i = 0; i < toDiscardP1.length; i++) {
//      // search the player's hand for the card to discard and remove it from the
//      // hand
//      removeCardFromHand(toDiscardP1[i],
//          gameIns.getPlayerList().get(0));
//
//      // add the card to the crib
//      gameIns.getCrib().add(toDiscardP1[i]);
//    }
//
//    for (int j = 0; j < toDiscardP2.length; j++) {
//      // search the player's hand for the card and remove it
//      removeCardFromHand(toDiscardP2[j],
//          gameIns.getPlayerList().get(1));
//
//      // add the card to the crib
//      gameIns.getCrib().add(toDiscardP2[j]);
//
//    }
//
//  }
//
//  /**
//   * Searches for a given card in a specified player's hand and removes it.
//   * 
//   * @param cardToRemove
//   *          is the card to remove from the player's hand
//   * @param p
//   *          is the player
//   */
//  public void removeCardFromHand(Card cardToRemove, Player p) {
//
//    Hand theHand = p.getHand(); // need player phase
//
//    for (int i = 0; i < theHand.getHand().size(); i++) {
//      Card c = theHand.getHand().get(i);
//
//      // if (c.getSuit() == cardToRemove.getSuit() &&
//      // Character.compare(c.getCardIdentifier(),
//      // cardToRemove.getCardIdentifier())){
//      // theHand.remove(i);
//      // return;
//      // }
//
//      if (c.getSuit() == cardToRemove.getSuit()
//          && c.getIdentifier() == cardToRemove.getIdentifier()) {
//        theHand.getHand().remove(i);
//        return;
//      }
//
//    }
//  }
//
//  /**
//   * Deal cards to the players, assuming that there are two players. Each player
//   * will be dealt 6 cards.
//   */
//  public void dealCards() {
//   
//    int idxOfDealer = gameIns.getDealerIdx();
//    
//    if (idxOfDealer == -1) {
//      return;
//    }
//    
//    Hand dealerHand = new Hand();
//    Hand poneHand = new Hand();
//
//    for (int i = 0; i < 6; i++) {
//      poneHand.addCardToHand(gameIns.getDeck().removeTopCard());
//      dealerHand.addCardToHand(gameIns.getDeck().removeTopCard());
//    }
//   
//    gameIns.getPlayerList().get(idxOfDealer).setHand(dealerHand);
//
//    if (idxOfDealer == 1) {
//        gameIns.getPlayerList().get(0).setHand(poneHand);
//    } else {
//        gameIns.getPlayerList().get(1).setHand(poneHand);
//    }
//
//  }
//
//  /**
//   * Shuffles the deck, cuts the deck, and determines the dealer.
//   * 
//   * @param amountToCutP1
//   *          is the amount that player 1 wants to cut
//   * @param amountToCutP2
//   *          is the amount that player 2 wants to cut
//   */
//  public void determineDealer(int amountToCutP1, int amountToCutP2) {
//
//      gameIns.getDeck().shuffle();
//
//    Card cardP1 = gameIns.getDeck().cut(amountToCutP1);
//    Card cardP2 = gameIns.getDeck().cut(amountToCutP2);
//
//    // assumption: player 2 is at index 1
//    // assumption: player 1 is at index 0
//
//    if (cardP1.getPointValue() < cardP2.getPointValue()) {
//        gameIns.getPlayerList().get(0).isDealer = true;
//    } else if (cardP2.getPointValue() < cardP1.getPointValue()) {
//        gameIns.getPlayerList().get(1).isDealer = true;
//    } else if (cardP2.getPointValue() == 10) {
//      // ordering is J, Q, K
//      // compare based on that ordering
//      char p1Id = cardP1.getIdentifier();
//      char p2Id = cardP2.getIdentifier();
//
//      if (p1Id == 'J') {
//          gameIns.getPlayerList().get(0).isDealer = true;
//      } else if (p1Id == 'Q' && p2Id == 'K') {
//          gameIns.getPlayerList().get(0).isDealer = true;
//      } else if (p2Id == 'J') {
//          gameIns.getPlayerList().get(1).isDealer = true;
//      } else if (p2Id == 'Q' && p1Id == 'K') {
//          gameIns.getPlayerList().get(1).isDealer = true;
//      } else {
//        determineDealer(amountToCutP1, amountToCutP2);
//      }
//    } else {
//      determineDealer(amountToCutP1, amountToCutP2);
//    }
//
//  }
//
//}
