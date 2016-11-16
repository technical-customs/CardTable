
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class PlaceMat {
    private Rectangle[] placeHolderLocations = new Rectangle[7];
    
    
    final private List<Card> cards;
    private Player player;
    private int x;
    private int y;
    private int width;
    private int height;

    public PlaceMat(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        cards = new ArrayList<>();
        
        
        
        
    }

    private void initiateMat(boolean northsouth){
        if(northsouth){
            //northsouth
            for(int xx = 0; xx < placeHolderLocations.length; xx++){
                int px = 0;
                int py = 0;

                if(xx == 0){
                    px = this.x + 2;
                }else{
                    px = (this.x + (Card.CARDWIDTH * xx));
                }
                py = (this.y + (this.height)/2) - (Card.CARDHEIGHT/2);

                placeHolderLocations[xx] = new Rectangle(px,py,Card.CARDWIDTH,Card.CARDHEIGHT);
            }
        }else{
            //eastwest
        
            for(int xx = 0; xx < placeHolderLocations.length; xx++){
                int px = 0;
                int py = 0;

                if(xx == 0){
                    py = this.y + 2;
                }else{
                    py = (this.y + (Card.CARDWIDTH * xx));
                }
                px = (this.x + (this.width)/2) - (Card.CARDHEIGHT/2);


                placeHolderLocations[xx] = new Rectangle(px,py,Card.CARDHEIGHT,Card.CARDWIDTH);
            }
        }
    }
    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void addPlayer(Player player){
        this.player = player;
        initiateMat(player.getNorthsouth());
    }
    public Player getPlayer(){
        return this.player;
    }
    public void addCard(Card card, boolean northsouth){
        //start in middle, then split and add that way, or add from left to right
        try{
            if(cards.size() >= 7 || card == null){
                return;
            }
            if(northsouth){
                //normal orietation
                int addx = 0;

                if(cards.size() >= 1){
                    addx = (this.x + (card.getWidth() * cards.size()));
                }else if(cards.isEmpty()){
                    addx = (this.x + 2);
                }

                int addy = (this.y + (this.height)/2) - (card.getHeight()/2);

                card.setX(addx);
                card.setY(addy);
                cards.add(card);

            }else{
                int temp = card.getWidth();
                card.setWidth(card.getHeight());
                card.setHeight(temp);

                int addy = 0;

                if(cards.size() >= 1){
                    addy = (this.y + (card.getHeight() * cards.size()));
                }else if(cards.isEmpty()){
                    addy = (this.y + 2);
                }

                int addx = (this.x + (this.width)/2) - (card.getWidth()/2);

                card.setX(addx);
                card.setY(addy);
                cards.add(card);
            }
            
            
        }catch(Exception ex){
            
            System.out.println("No More Cards");
        }
        
        
        

    }
    public void clearMat(){
        cards.clear();
    }


    public synchronized void syncCards(){
        //new Thread(new Runnable(){

            //@Override
            //public void run(){
                //while(true){

                    if(player == null){
                        return;
                    }
                    
                    if(!player.getCardsInHand().isEmpty()){
                        
                        Iterator<Card> handIter = player.getCardsInHand().iterator();
                        while(handIter.hasNext()){
                            Card card = handIter.next();
                            addCard(card,player.getNorthsouth());
                        }
                    }else{
                        //if(!cards.isEmpty())
                        clearMat();
                    }
                //}
            //}
        //}).start();

    }
    public synchronized void draw(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(this.x, this.y, this.width, this.height);
        
        
        
        if(player != null){
            
            for(Rectangle p: placeHolderLocations){
                g2.setColor(Color.white);
                g2.fillRect(p.x, p.y, p.width, p.height);
                g2.setColor(Color.black);
                g2.drawRect(p.x, p.y, p.width, p.height);
            }
            player.draw(g2);
            Iterator<Card> cardIter = player.getCardsInHand().iterator();
            while(cardIter.hasNext()){
                Card card = cardIter.next();
                card.draw(g2);
            }
            
        }
        
    }
}