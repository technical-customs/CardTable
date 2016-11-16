
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;

class Card{
    final public static int CARDWIDTH = 25, CARDHEIGHT = 30;
    final private Rectangle cardRect;
    final private CardSuit suit;
    final private String value;
    private boolean show = false;
    private boolean highlight = false;

    private int x=0,y=0,width=25,height=30;

    public Card(String value, CardSuit suit){
        this.value = value;
        this.suit = suit;
        cardRect = new Rectangle(this.x,this.y,this.width,this.height);
        
    }
    
    public Rectangle getCardRect(){
        return cardRect;
    }
    public String getValue(){
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
}