
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class CardDeck {
    private int numofdecks;
    final private ArrayList<Card> cards;
    final private List<Card> dealtCards;
    
    final private CardValues values;
    final private CardSuit[] suits;
    
    private int x,y,width,height;
    
    class CardValues{
        private final String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        
        public String[] getValues(){
            return values;
        }
    }
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
    public void collectDeck(){
        cards.addAll(dealtCards);
        dealtCards.clear();
    }
    public void makeDeck(int num){
        numofdecks = num;
        for(int xx = 0; xx < num; xx++){
            for(CardSuit suit: suits){
                for(String value: values.getValues()){
                    addCardToDeck(new Card(value,suit));
                }
            }
        }
        
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
    
    public static void main(String[] args){
        CardDeck cd = new CardDeck();
        
        System.out.println(cd.toString());
        cd.makeDeck(2);
        System.out.println(cd.toString());
        cd.shuffleDeck();
        System.out.println(cd.toString());
        
    }
}