
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class PokerRules{
    
    public PokerRules(){
        
    }
    
    
    public static void checkForRoyalFlush(List<Card> cards){
        
    }
    
    public static void checkForStraightFlush(List<Card> cards){
        
    }
    
    
    public static boolean checkForFullHouse(List<Card> cards){
        List<Card> cc = cards;
        Card[] three = checkForThreeOfAKind(cc);
        System.out.println("Three: " + Arrays.toString(three));
        
        if(three != null){
            for(Card card: three){
                cc.remove(card);
            }
            
            System.out.println("Remaining " + cc.toString());
            
            if(checkForPair(cc) != null){
                return true;
            }
        }
        return false;
    }
    public static boolean checkForFlush(List<Card> cards){
        CardSuit suit = null;
        
        for(Card card: cards){
            if(suit == null){
                suit = card.getSuit();
                continue;
            }
            
            if(!suit.equals(card.getSuit())){
                return false;
            }
        }
        return true;
    }
    public static Card[] checkForFourOfAKind(List<Card> cards){
        List<List<Card>> lcards = new LinkedList<>();
        
        for(Card card: cards){
            if(lcards.isEmpty()){
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
                continue;
            }
            
            boolean added = false;
            for(List<Card> cardlist: lcards){
                if(cardlist.get(0).getValue().equals(card.getValue())){
                    cardlist.add(card);
                    added = true;
                }
            }
            
            if(added){
                added = false;
            }else{
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
            }
        }
        for(List<Card> c: lcards){
            
            if(c.size() == 4){
                
                Card[] cc = new Card[c.size()];
                cc = c.toArray(cc);
                return  cc;
            }
        }
        return null;
    }
    public static Card[] checkForThreeOfAKind(List<Card> cards){
        List<List<Card>> lcards = new LinkedList<>();
        
        for(Card card: cards){
            if(lcards.isEmpty()){
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
                continue;
            }
            
            boolean added = false;
            for(List<Card> cardlist: lcards){
                if(cardlist.get(0).getValue().equals(card.getValue())){
                    cardlist.add(card);
                    added = true;
                }
            }
            
            if(added){
                added = false;
            }else{
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
            }
        }
        for(List<Card> c: lcards){
            
            if(c.size() == 3){
                
                Card[] cc = new Card[c.size()];
                cc = c.toArray(cc);
                return  cc;
            }
        }
        return null;
    }
    public static Card[] checkForTwoPair(List<Card> cards){
    List<List<Card>> lcards = new LinkedList<>();
        
        for(Card card: cards){
            if(lcards.isEmpty()){
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
                continue;
            }
            
            boolean added = false;
            for(List<Card> cardlist: lcards){
                if(cardlist.get(0).getValue().equals(card.getValue())){
                    cardlist.add(card);
                    added = true;
                }
            }
            
            if(added){
                added = false;
            }else{
                LinkedList<Card> ll = new LinkedList<>();
                ll.add(card);
                lcards.add(ll);
            }
        }
        
        List<List<Card>> l2 = new LinkedList<>();
        
        for(List<Card> c: lcards){
            
            if(c.size() == 2){
                l2.add(c);
            }
        }
        
        
        List<Card> cl = new LinkedList<>();
        if(l2.size() == 2){
            
            int x = 0;
            for(List<Card> l: l2){
                cl.addAll(l);
            }
            
            Card[] cc = new Card[cl.size()];
            cc = cl.toArray(cc);
            
            return cc;
        }
        return null;
    }
    public static Card[] checkForPair(List<Card> cards){
        Card c = null;
        Card pair = null;
        
        for(int x = 0; x < cards.size(); x++){
            c = cards.get(x);
            
            for(int y = x+1; y < cards.size(); y++){
                if(c.getValue().equals(cards.get(y).getValue())){
                    pair = cards.get(y);
                    
                    return new Card[]{c,pair};
                }
            }
        }
        return null;
    }
    public static Card checkForHighCard(List<Card> cards){
        return Card.compareCards(cards);
    }
    
    
}