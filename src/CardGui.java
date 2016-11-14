

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class CardGui extends JPanel{
    private int ww = 480, hh = 480;
    private JFrame frame;
    
    public Card testcard1 = new Card("A", new Spade());
    public Card testcard2 = new Card("K", new Spade());
    public Card testcard3 = new Card("Q", new Spade());
    public Card testcard4 = new Card("J", new Spade());
    public Card testcard5 = new Card("10", new Spade());
    public Card testcard6 = new Card("9", new Spade());
    public Card testcard7 = new Card("8", new Spade());
    
    
    private CardDeck deck;
    private List<Card> played;
    private TableTop tt;
    private PlaceMat north;
    private PlaceMat south;
    private PlaceMat east;
    private PlaceMat west;
    
    public CardGui(){
        super();
        played = new ArrayList<>();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                setupGui();
            }
        });
    }
    private void setupGui(){
        frame = new JFrame("Cards");
        
        this.setPreferredSize(new Dimension(ww,hh));
        this.setMinimumSize(new Dimension(ww,hh));
        this.setMaximumSize(new Dimension(ww,hh));
        this.setLayout(null);
        
        int baseSize = 180;
        tt = new TableTop((ww/2) - (baseSize/2) , (hh/2) - (baseSize/2) ,baseSize,baseSize);
        north = new PlaceMat((ww/2) - (baseSize/2),   (hh/2)  - (baseSize/2) - (baseSize/2) - 2 ,baseSize,baseSize/2);
        //north.render();
        south = new PlaceMat((ww/2) - (baseSize/2), (hh/2) + (baseSize/2) + 2 ,baseSize,baseSize/2);
        //south.render();
        east = new PlaceMat((ww/2) - (baseSize/2) - (baseSize/2) - 2, (hh/2) - (baseSize/2) ,baseSize/2,baseSize);
        //east.render();
        west = new PlaceMat((ww/2) + (baseSize/2) + 2, (hh/2) - (baseSize/2) ,baseSize/2,baseSize);
        //west.render();
        
        //east.addCard(testcard1, false);
        //testcard1.show(true);
        //east.addCard(testcard2, false);
        //east.addCard(testcard3, false);
        //testcard3.show(true);
        //east.addCard(testcard4, false);
        //east.addCard(testcard5, false);
        //east.addCard(testcard6, false);
        //east.addCard(testcard7, false);
        
        
        frame.getContentPane().add(this);
        frame.pack();
        
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void setupDeck(CardDeck deck){
        this.deck = deck;
        this.deck.setX(tt.getDeckPlaceHolderLocation().x);
        this.deck.setY(tt.getDeckPlaceHolderLocation().y);
    }
    public CardDeck getDeck(){
        return this.deck;
    }
    
    public List<Card> getPlayed(){
        return played;
    }
    public TableTop getTableTop(){
        return tt;
    }
    public PlaceMat getNorthMat(){
        return north;
    }
    public PlaceMat getSouthMat(){
        return south;
    }
    public PlaceMat getEastMat(){
        return east;
    }
    public PlaceMat getWestMat(){
        return west;
    }
            
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.blue);
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        
        draw(g2);
        repaint();
        g2.dispose();
    }
    
    class TableTop {
        private int x;
        private int y;
        private int width;
        private int height;
        
        private Point deckPlaceHolderLocation, playedPlaceHolderLocation;
        
        public TableTop(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            
        }
        
        public Point getDeckPlaceHolderLocation(){
            return deckPlaceHolderLocation;
        }
        public Point getPlayedPlaceHolderLocation(){
            return playedPlaceHolderLocation;
        }
        public synchronized void draw(Graphics2D g2){
            g2.setColor(Color.orange);
            g2.fillRect(this.x, this.y, this.width, this.height);
            
            
            //deck placeholder
            deckPlaceHolderLocation = new Point((this.x+(this.width/2))-(25)-(25/2), (this.y+(this.height/2))-(25/2));
            g2.setColor(Color.white);
            g2.fillRect(deckPlaceHolderLocation.x, deckPlaceHolderLocation.y,25,30);

            //played placeholder
            playedPlaceHolderLocation = new Point((this.x+(this.width/2))+(25/2), (this.y+(this.height/2))-(25/2));
            g2.setColor(Color.white);
            g2.fillRect(playedPlaceHolderLocation.x,playedPlaceHolderLocation.y,25,30);
            
        }
    }
    
    private synchronized void draw(Graphics2D g2){
        //table top
        
        tt.draw(g2);
        if(deck != null){
            deck.draw(g2);
        }
        if(played != null){
            Iterator<Card> cardIter = played.iterator();
            while(cardIter.hasNext()){
                Card card = cardIter.next();
                card.draw(g2);
            }
        }
        //mat north
        north.draw(g2);
        
        //mat south
        south.draw(g2);
        
        //mat east
        east.draw(g2);
        
        //mat west
        west.draw(g2);
        
        
    }
    
    public void addKeyControls(KeyAdapter keycontrol){
        this.addKeyListener(keycontrol);
    }
    public static void main(String[] args){
        CardGui gui = new CardGui();
    }
}