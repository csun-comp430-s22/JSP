package parser;

public class IfExp implements Exp {
   

    

    public boolean equals(final Object other) {
        if (other instanceof IfExp) {
            return (true);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (25);
    }

    public String toString() {
        return ("IfExp(" +")");
    }
}