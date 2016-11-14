
import java.awt.Color;
import java.awt.Graphics2D;

class Card{
    final private CardSuit suit;
    final private String value;
    private boolean show = false;

    private int x=0,y=0,width=25,height=30;

    public Card(String value, CardSuit suit){
        this.value = value;
        this.suit = suit;
    }
    
    public void show(boolean show){
        this.show = show;
    }
    public boolean getShow(){
        return show;
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
        this.width = 25;
        this.height = 30;
    }
    public synchronized void draw(Graphics2D g2){
        if(show){
            g2.setColor(Color.white);
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
}