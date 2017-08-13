
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;

class Card{
    final public static int CARDWIDTH = 25, CARDHEIGHT = 30;
    final private Rectangle cardRect;
    final private CardSuit suit;
    final private Object value;
    private boolean show = false;
    private boolean highlight = false;

    private int x=0,y=0,width=25,height=30;

    public Card(Object value, CardSuit suit){
        this.value = value;
        this.suit = suit;
        cardRect = new Rectangle(this.x,this.y,this.width,this.height);
        
    }
    
    public Rectangle getCardRect(){
        return cardRect;
    }
    public Object getValue(){
        return this.value;
    }
    public CardSuit getSuit(){
        return this.suit;
    }
    public void show(boolean show){
        this.show = show;
    }
    public boolean getShow(){
        return show;
    }
    
    public void highlight(boolean highlight){
        this.highlight = highlight;
    }
    public boolean getHighlight(){
        return highlight;
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

    public void reset(){
        highlight = false;
        this.width = 25;
        this.height = 30;
    }
    public synchronized void draw(Graphics2D g2){
        cardRect.setBounds(this.x, this.y, this.width, this.height);
        
        if(show){
            if(highlight){
                g2.setColor(Color.cyan);
            }else{
                g2.setColor(Color.white);
            }
            
            g2.fillRect(this.x, this.y,this.width, this.height);
            g2.setColor(Color.black);
            g2.drawRect(this.x, this.y,this.width, this.height);

            if(suit.getSuitname().equalsIgnoreCase("spade") || suit.getSuitname().equalsIgnoreCase("club")){
                g2.setColor(Color.black);
            }else if(suit.getSuitname().equalsIgnoreCase("heart") || suit.getSuitname().equalsIgnoreCase("diamond")){
                g2.setColor(Color.red);
            }
            g2.drawString(value + "" + suit.getSuitname().toUpperCase().charAt(0), this.x + 1 , (this.y + (this.height/2)));
        }else{
            g2.setColor(Color.gray);
            g2.fillRect(this.x, this.y,this.width, this.height);
            g2.setColor(Color.black);
            g2.drawRect(this.x, this.y,this.width, this.height);
        }
        
        
    }
    
    @Override
    public String toString(){
        return value + " " + suit.getSuitname();
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!Card.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Card other = (Card) obj;
        if((this.getSuit().getSuitname() == null) ? (other.getSuit().getSuitname() != null) : !this.getSuit().equals(other.getSuit())) {
            return false;
        }
        if((this.getValue() == null) ? (other.getValue() != null) : !this.getValue().equals(other.getValue())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.cardRect);
        hash = 89 * hash + Objects.hashCode(this.suit);
        hash = 89 * hash + Objects.hashCode(this.value);
        hash = 89 * hash + (this.show ? 1 : 0);
        hash = 89 * hash + (this.highlight ? 1 : 0);
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + this.width;
        hash = 89 * hash + this.height;
        return hash;
    }
    
    
    public static Card compareCards(Card a, Card b){
        //return high card
        
        Object aValue = a.getValue();
        Object bValue = b.getValue();
        
        if(aValue.getClass().equals(Integer.class)){
            
            if(bValue.getClass().equals(Integer.class)){
                if((int)aValue == (int)bValue){
                    return null;
                }
                return (int)aValue > (int)bValue?a:b;
                
            }else if(bValue.getClass().equals(String.class)){
                return b;
            }
            
        }else if(aValue.getClass().equals(String.class)){
            
            if(bValue.getClass().equals(Integer.class)){
                return a;
            }else if(bValue.getClass().equals(String.class)){
                String as = (String)aValue;
                String bs = (String)bValue;
                
                if(as.equalsIgnoreCase("A") && bs.equalsIgnoreCase("A")){
                   return null; 
                }
                if(as.equalsIgnoreCase("A") && bs.equalsIgnoreCase("K")){
                   return a; 
                }
                if(as.equalsIgnoreCase("A") && bs.equalsIgnoreCase("Q")){
                   return a; 
                }
                if(as.equalsIgnoreCase("A") && bs.equalsIgnoreCase("J")){
                   return a; 
                }
                
                if(as.equalsIgnoreCase("K") && bs.equalsIgnoreCase("A")){
                   return b; 
                }
                if(as.equalsIgnoreCase("K") && bs.equalsIgnoreCase("K")){
                   return null; 
                }
                if(as.equalsIgnoreCase("K") && bs.equalsIgnoreCase("Q")){
                   return a; 
                }
                if(as.equalsIgnoreCase("K") && bs.equalsIgnoreCase("J")){
                   return a; 
                }
                
                if(as.equalsIgnoreCase("Q") && bs.equalsIgnoreCase("A")){
                   return b; 
                }
                if(as.equalsIgnoreCase("Q") && bs.equalsIgnoreCase("K")){
                   return b; 
                }
                if(as.equalsIgnoreCase("Q") && bs.equalsIgnoreCase("Q")){
                   return null; 
                }
                if(as.equalsIgnoreCase("Q") && bs.equalsIgnoreCase("J")){
                   return a; 
                }
                
                if(as.equalsIgnoreCase("J") && bs.equalsIgnoreCase("A")){
                   return b; 
                }
                if(as.equalsIgnoreCase("J") && bs.equalsIgnoreCase("K")){
                   return b; 
                }
                if(as.equalsIgnoreCase("J") && bs.equalsIgnoreCase("Q")){
                   return b; 
                }
                if(as.equalsIgnoreCase("J") && bs.equalsIgnoreCase("J")){
                   return null; 
                }
            }
        }
        return null;
    }
    public static Card compareSuits(Card a, Card b){
        //spades high
        
        
        CardSuit aSuit = a.getSuit();
        CardSuit bSuit = b.getSuit();
        
        if(aSuit.getClass().equals(Spade.class)){
            //aSuit is spade
            
            if(bSuit.getClass().equals(Spade.class)){
                //bSuit is spade, compare cards
                return compareCards(a,b);
            }else{
                //bSuit is not spades
                return a;
            }
        }else{
            //aSuit is not spade
            
            if(bSuit.getClass().equals(Spade.class)){
                //bSuit is spade, return b
                return b;
                
            }else{
                //bSuit is not spades, compare
                return compareCards(a,b);
            }
        }
        
    }
}