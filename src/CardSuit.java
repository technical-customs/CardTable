
import java.util.Objects;

class CardSuit{
    protected String suitname;

    public String getSuitname(){
        return suitname;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!CardSuit.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final CardSuit other = (CardSuit) obj;
        return !((this.getSuitname() == null) ? (other.getSuitname() != null) : !this.getSuitname().equalsIgnoreCase(other.getSuitname()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.suitname);
        return hash;
    }
}