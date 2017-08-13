
import java.util.List;

class PokerRules{
    
    public PokerRules(){
        
    }
    
    
    public void checkForRoyalFlush(List cards){
        
    }
    
    public void checkForFourOfAKind(List cards){
        
    }
    public void checkForFullHouse(List cards){
        
    }
    public void checkForStraightFlush(List cards){
        
    }
    public void checkForFlush(List cards){
        
    }
    public void checkForTwoPair(List cards){
        
    }
    public void checkForPair(List cards){
        
    }
    public void checkForHighCard(List<Card> cards){
        Card high = null;
        for(Card card: cards){
            if(high == null){
                high = card;
                continue;
            }
            high = Card.compareCards(high, card);
        }
    }
    
    
}