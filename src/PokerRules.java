
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class PokerRules{
    
    public PokerRules(){
        
    }
    
    public static int checkHand(List<Card> cards){
        if(checkForRoyalFlush(cards)){
            
            System.out.println("ROYAL FLUSH");
            return 10;
        }else if(checkForStraightFlush(cards)){
            System.out.println("STRAIGHT FLUSH");
            return 9;
        }else if(checkForFourOfAKind(cards) != null){
            System.out.println("FOUR OF A KIND");
            return 8;
        }else if(checkForFullHouse(cards)){
            System.out.println("FULL HOUSE");
            return 7;
        }else if(checkForFlush(cards)){
            System.out.println("FLUSH");
            return 6;
        }else if(checkForStraight(cards)){
            System.out.println("STRAIGHT");
            return 5;
        }else if(checkForThreeOfAKind(cards) != null){
            System.out.println("THREE OF A KIND");
            return 4;
        }else if(checkForTwoPair(cards) != null){
            System.out.println("TWO PAIR");
            return 3;
        }else if(checkForPair(cards) != null){
            System.out.println("PAIR");
            return 2;
        }else if(checkForHighCard(cards) != null){
            System.out.println("HIGH CARD");
            return 1;
        }
        return 0;
    }
    public static List<Card> compareHands(List<Card> cardsa, List<Card> cardsb){
        return (checkHand(cardsa) > checkHand(cardsb)) ? cardsa : cardsb;
    }
    
    public static boolean checkForRoyalFlush(List<Card> cards){
        return (checkForHighCard(cards).getValue().equals("A") && checkForFlush(cards) && checkForStraight(cards));    
    }
    public static boolean checkForStraightFlush(List<Card> cards){
        return (checkForFlush(cards) && checkForStraight(cards));
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
    public static boolean checkForFullHouse(List<Card> cards){
        List<Card> cc = cards;
        Card[] three = checkForThreeOfAKind(cc);
        
        if(three != null){
            for(Card card: three){
                cc.remove(card);
            }
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
    public static boolean checkForStraight(List<Card> cards){
        List<Object> lowSt = Arrays.asList("A",2,3,4,5);
        
        List<Object> values = new LinkedList();
        for(Card card: cards){
            values.add(card.getValue());
        }
        
        if(values.containsAll(lowSt)){
            return true;
        }
        
        Card high = checkForHighCard(cards);
        int index = Integer.MAX_VALUE;
        
        for(int x = 0; x < CardValues.getValues().length; x++){
            if(high.getValue() == CardValues.getValues()[x]){
                index = x;
            }
        }
        if((index-4) < 0){
            return false;
        }
        
        List<Object> st = new LinkedList();
        for(int y = index; y >= index - 4; y--){
            st.add(CardValues.getValues()[y]);
        }
        return values.containsAll(st);
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