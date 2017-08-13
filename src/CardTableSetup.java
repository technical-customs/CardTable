class CardTableSetup{
    
    
    public static void main(String[] args){
        CardCalc model = new CardCalc();
        CardGui gui = new CardGui();
        CardController controller = new CardController(model, gui);
        
    }
}