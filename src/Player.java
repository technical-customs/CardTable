
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

class Player{
    private int points;
    private String name;
    final private List<Card> cardsInHand = new ArrayList<>();
    final private List<Card> removed = new ArrayList<>();
    private boolean northsouth = true;
    
    private int x, y, width = 20, height = 20;

    public Player(){
        
    }
    public Player(String name){
        this.name = name;
    }
    public void setNorthsouth(boolean northsouth){
        this.northsouth = northsouth;
    }
    public boolean getNorthsouth(){
        return this.northsouth;
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
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
     public void setPoints(int points){
        this.points = points;
    }
    public int getPoints(){
        return this.points;
    }

    public void addCardToHand(Card card){
        this.cardsInHand.add(card);
    }
    public List<Card> getCardsInHand(){
        return this.cardsInHand;
    }
    public List<Card> getRemoved(){
        return this.removed;
    }
    
    public boolean removeCard(Card card){
        Iterator<Card> cardIter = cardsInHand.iterator();
        while(cardIter.hasNext()){
            Card c = cardIter.next();
            
            if(c.equals(card)){
                
                cardIter.remove();
                return removed.add(c);
            }
        }
        return false;
    }
    public void clearHand(){
        this.cardsInHand.clear();
        //this.removed.clear();
    }
    
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillOval(this.x,this.y, this.width, this.height);
        g2.drawString(name, this.x+this.width, this.y+ this.height/2);
    }
    
    @Override
    public String toString(){
        return name + " - Cards: " + cardsInHand.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.points;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.cardsInHand);
        return hash;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!Player.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Player other = (Player) obj;
        if( this.getPoints() != other.getPoints()) {
            return false;
        }
        if( !this.getName().equalsIgnoreCase(other.getName())) {
            return false;
        }
        if( !this.getCardsInHand().containsAll(other.getCardsInHand())) {
            return false;
        }
        if( this.getPoints() != other.getPoints()) {
            return false;
        }
        return true;
    }
}