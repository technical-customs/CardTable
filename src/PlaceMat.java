
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class PlaceMat {
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
        render();
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


    private synchronized void render(){
        new Thread(new Runnable(){

            @Override
            public void run(){
                while(true){

                    if(player == null){
                        continue;
                    }
                    
                    if(!player.getCardsInHand().isEmpty()){
                        Iterator<Card> handIter = player.getCardsInHand().iterator();
                        while(handIter.hasNext()){
                            Card card = handIter.next();
                            addCard(card,player.getNorthsouth());
                        }
                    }else{
                        clearMat();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CardGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }
    public synchronized void draw(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(this.x, this.y, this.width, this.height);

        if(player != null){
            player.draw(g2);
            Iterator<Card> cardIter = cards.iterator();
            while(cardIter.hasNext()){
                Card card = cardIter.next();
                card.draw(g2);
            }
            
        }
        
    }
}