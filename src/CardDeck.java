
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class CardDeck {
    private int numofdecks;
    private ArrayList<Card> cards;
    final private List<Card> dealtCards;
    
    final private CardValues values;
    final private CardSuit[] suits;
    
    private int x,y,width,height;
    
    public CardDeck(){
        cards = new ArrayList<>();
        dealtCards = new ArrayList<>();
        values = new CardValues();
        suits = new CardSuit[4];
        suits[0] = new Spade();
        suits[1] = new Heart();
        suits[2] = new Diamond();
        suits[3] = new Club();
    }
    
    private void addCardToDeck(Card card){
        cards.add(card);
    }
    
    public void putDeckInOrder(Card[] cards){
        for(Card card: cards){
            addCardToDeck(card);
        }
    }
    public void collectDeck(){
        cards.addAll(dealtCards);
        dealtCards.clear();
    }
    public void makeDeck(int num){
        //System.out.println("Cards size " + cards.size());
        collectDeck();
        cards.clear();
        //System.out.println("Cards size " + cards.size());
        
        numofdecks = num;
        for(int xx = 0; xx < num; xx++){
            for(CardSuit suit: suits){
                for(Object value: values.getValues()){
                    addCardToDeck(new Card(value,suit));
                }
            }
        }
        //System.out.println("Cards size " + cards.size());
        
    }
    public void shuffleDeck(){
        Collections.shuffle(cards);
        Collections.shuffle(cards);
    }
    public ArrayList<Card> getCards(){
        return cards;
    }
    public List<Card> getDealtCards(){
        return dealtCards;
    }
    public Card dealCard(){
        try{
            Card card = cards.get(0);
            dealtCards.add(card);
            cards.remove(card);
            return card;
        }catch(Exception ex){
            return null;
        }
        
    }
    
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public synchronized void draw(Graphics2D g2){
        //draws the back of card
        if(!cards.isEmpty()){
            
            Iterator<Card> cardIter = cards.iterator();
            while(cardIter.hasNext()){
                Card card = cardIter.next();
                card.setX(this.x);
                card.setY(this.y);
                card.draw(g2);
            }
            
        }
    }
    
    @Override
    public String toString(){
        return "Cards: " + cards.toString();
    }
    
    public static void main(String[] args) throws InterruptedException{
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        Player p4 = new Player("P4");
        
        List<List<Card>> playerCards = new ArrayList();

        CardDeck cd = new CardDeck();
        cd.makeDeck(1);
        cd.shuffleDeck();
        
        for(int x = 0; x < 5; x++){
            
            p1.addCardToHand(cd.dealCard());
            System.out.println(p1.toString());
            Thread.sleep(1000);
            p2.addCardToHand(cd.dealCard());
            System.out.println(p2.toString());
            Thread.sleep(1000);
            p3.addCardToHand(cd.dealCard());
            System.out.println(p3.toString());
            Thread.sleep(1000);
            p4.addCardToHand(cd.dealCard());
            System.out.println(p4.toString());
            Thread.sleep(1000);
            System.out.println();
            Thread.sleep(1000);
            
        }
        playerCards.add(p1.getCardsInHand());
        playerCards.add(p2.getCardsInHand());
        playerCards.add(p3.getCardsInHand());
        playerCards.add(p4.getCardsInHand());
        
        //System.out.println(p1.toString());
        //System.out.println(p2.toString());
        //System.out.println(p3.toString());
        //System.out.println(p4.toString());
        
        List<Card> comphands = Arrays.asList(PokerRules.compareHands(playerCards));
        
        if(comphands == null){
           System.out.println("Tie - ");
           
           
           System.out.println(p1.getName() + " - " + PokerRules.getHandValue(p1.getCardsInHand()));
           System.out.println(p2.getName() + " - " + PokerRules.getHandValue(p2.getCardsInHand()));
           System.out.println(p3.getName() + " - " + PokerRules.getHandValue(p3.getCardsInHand()));
           System.out.println(p4.getName() + " - " + PokerRules.getHandValue(p4.getCardsInHand()));
        }else if(p1.getCardsInHand().containsAll(comphands)){
            System.out.println("Winner is " + p1.getName() + " - " + PokerRules.getHandValue(p1.getCardsInHand()));
            p1.setPoints(p1.getPoints()+ PokerRules.checkHand(p1.getCardsInHand()).getA());
            System.out.println(p1.getName() + " Points:" + p1.getPoints());
        }else if(p2.getCardsInHand().containsAll(comphands)){
            System.out.println("Winner is " + p2.getName() + " - " + PokerRules.getHandValue(p2.getCardsInHand()));
            p2.setPoints(p2.getPoints()+ PokerRules.checkHand(p2.getCardsInHand()).getA());
            System.out.println(p2.getName() + " Points:" + p2.getPoints());
        }else if(p3.getCardsInHand().containsAll(comphands)){
            System.out.println("Winner is " + p3.getName() + " - " + PokerRules.getHandValue(p3.getCardsInHand()));
            p3.setPoints(p3.getPoints()+ PokerRules.checkHand(p3.getCardsInHand()).getA());
            System.out.println(p3.getName() + " Points:" + p3.getPoints());
        }else if(p4.getCardsInHand().containsAll(comphands)){
            System.out.println("Winner is " + p4.getName() + " - " + PokerRules.getHandValue(p4.getCardsInHand()));
            p4.setPoints(p4.getPoints()+ PokerRules.checkHand(p4.getCardsInHand()).getA());
            System.out.println(p4.getName() + " Points:" + p4.getPoints());
        }
    }
}