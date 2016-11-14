

//Tries to guess the card in your hand

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


class CardCalc {
    final private int MAXPLAYERS = 4, MAXHAND = 7;
    final private CardDeck deck;
    final private List<Card> cardsPlayed;
    final private List<Card> cardsInPlay;
    final private Player[] players;
    
    
    
    public CardCalc(){
        deck = new CardDeck();
        cardsPlayed = new ArrayList<>();
        cardsInPlay = new ArrayList<>();
        players = new Player[MAXPLAYERS];
        
    }
    
    public Player addPlayer(String name){
        Player player = new Player();
        player.setName(name);
        
        for(int x = 0; x < MAXPLAYERS; x++){
            if(players[x] == null){
                players[x] = player;
                return player;
            }
        }
        System.out.println("MAX PLAYERS REACHED");
        return null;
    }
    public Player getPlayer(String name){
        for(Player player: players){
            if(player.getName().equalsIgnoreCase(name)){
                return player;
            }
        }
        return null;
    }
    public Player[] getPlayers(){
        return players;
    }
    
    public CardDeck getDeck(){
        return deck;
    }
    public List<Card> calcDeck(){
        List<Card> preceivedDeck = (ArrayList) deck.getCards().clone();
        for(Card card: cardsPlayed){
            preceivedDeck.remove(card);
        }
        for(Card card: cardsInPlay){
            preceivedDeck.remove(card);
        }
        Collections.shuffle(preceivedDeck);
        Collections.shuffle(preceivedDeck);
        
        return preceivedDeck;
    }
    public List<Card> getCardsInPlay(){
        return cardsInPlay;
    }
    public void setCardsInPlay(){
        //gets cards on field
        cardsInPlay.clear();
        
        for(Player player: players){
            if(player == null){
                continue;
            }
            for(Card card: player.getCardsInHand()){
                cardsInPlay.add(card);
            }
        }
        
    }
    public List<Card> getCardsPlayed(){
        return cardsPlayed;
    }
    public void dealCards(int num){
        try{
            if(num <= 0 || num > MAXHAND){
                num = MAXHAND;
            }
            for(int x = 0; x < num; x++){
                for(Player player: players){
                    if(player == null){
                        continue;
                    }
                    if(player.getCardsInHand().size() == MAXHAND){
                        continue;
                    }
                    if(deck.getCards().isEmpty()){
                        break;
                    }
                    player.addCardToHand(deck.dealCard());
                }
            }
        }catch(Exception ex){
            System.err.println("Deal Exception ex: " + ex);
        }finally{
            setCardsInPlay();
        }
        
    }
    public void emptyField(){
        //sends all cards in play and hand to played
        for(Player player: players){
            if(player == null){
                continue;
            }
            player.clearHand();
        }
        
        for(Card card: cardsInPlay){
            cardsPlayed.add(card);
        }
        cardsInPlay.clear();
        
        
    }
    public void recollectDeck(){
        emptyField();
        cardsPlayed.clear();
        deck.collectDeck();
        for(Card card: deck.getCards()){
            card.show(false);
            card.reset();
        }
    }
    
    @Override
    public String toString(){
        for(Player player: players){
            if(player == null){
                continue;
            }
            System.out.println(player.toString());
        }
        return "";
    }
    
    public static void main(String[] args){
        CardCalc cc = new CardCalc();
        cc.getDeck().makeDeck(1);
        cc.getDeck().shuffleDeck();
        
        cc.addPlayer("Joe");
        cc.addPlayer("Chris");
        cc.addPlayer("Steve");
        cc.addPlayer("Will");
        
        System.out.println("Cards in play: " + cc.getCardsInPlay().toString());
        System.out.println("In Deck: " + cc.calcDeck().toString());
        System.out.println("Cards Played: " + cc.getCardsPlayed().toString());
        System.out.println();
        
        System.out.println(cc.toString());
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CardCalc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cc.dealCards(1);
        System.out.println(cc.toString());
        
        System.out.println("Cards in play: " + cc.getCardsInPlay().toString());
        System.out.println("In Deck: " + cc.calcDeck().toString());
        System.out.println("Cards Played: " + cc.getCardsPlayed().toString());
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CardCalc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cc.dealCards(1);
        System.out.println(cc.toString());
        System.out.println("Cards in play: " + cc.getCardsInPlay().toString());
        System.out.println("In Deck: " + cc.calcDeck().toString());
        System.out.println("Cards Played: " + cc.getCardsPlayed().toString());
        System.out.println();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CardCalc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cc.emptyField();
        System.out.println(cc.toString());
        System.out.println("Cards in play: " + cc.getCardsInPlay().toString());
        System.out.println("In Deck: " + cc.calcDeck().toString());
        System.out.println("Cards Played: " + cc.getCardsPlayed().toString());
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CardCalc.class.getName()).log(Level.SEVERE, null, ex);
        }
        cc.recollectDeck();
        System.out.println("In Deck: " + cc.calcDeck().toString());
    }
}