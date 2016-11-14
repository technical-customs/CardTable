class CardController{
    
    final private CardCalc model;
    final private CardGui gui;
    final private KeyControl keyControl;
    public CardController(CardCalc model, CardGui gui){
        this.model = model;
        this.gui = gui;
        this.keyControl = new KeyControl(model,gui);
        
        gui.addKeyControls(keyControl);
        gui.setFocusable(true);
        gui.requestFocus();
    }
    
    //addCardsPlayed to the placeholder
    
    
    
}