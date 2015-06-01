package monopoly;

public enum CouleurPropriete {
    bleuFonce,
    orange,
    mauve,
    violet,
    bleuCiel,
    jaune,
    vert,
    rouge;
    
    @Override
    public String toString() {
        switch(this.ordinal()) {
            case 0:
                return (char)27+"[34m";
            case 1:
                return (char)27+"[33m";
            case 2:
                return (char)27+"[35m";
            case 3:
                return (char)27+"[95m";
            case 4:
                return (char)27+"[94m";
            case 5:
                return (char)27+"[93m";
            case 6:
                return (char)27+"[32m";
            case 7:
                return (char)27+"[31m";
            default:
                return (char)27+"[39m";
        }
    }
}
