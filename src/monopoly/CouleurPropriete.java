package monopoly;

import java.awt.Color;

public enum CouleurPropriete {
    bleuFonce((char)27+"[34m",Color.BLUE),
    orange((char)27+"[33m",Color.ORANGE),
    mauve((char)27+"[35m",Color.PINK),
    violet((char)27+"[95m",Color.MAGENTA),
    bleuCiel((char)27+"[94m",Color.CYAN),
    jaune((char)27+"[93m",Color.YELLOW),
    vert((char)27+"[32m",Color.GREEN),
    rouge((char)27+"[31m",Color.RED);
    
    private String ANSI_Color;
    private Color rgbColor;
    
    CouleurPropriete(String s, Color c) {
        this.ANSI_Color=s;
        this.rgbColor=c;
        
    }
    
    @Override
    public String toString() {
        return this.ANSI_Color;
    }

    public Color getColor() {
        return this.rgbColor;
    }
}
