package monopoly;

public class Groupe {
	private int prixAchatMaison;
	private int prixAchatHotel;
	private CouleurPropriete couleur;
	private java.util.ArrayList<ProprieteAConstruire> proprietes;

        public Groupe(CouleurPropriete c) {
            couleur = c;
        }
        
	public CouleurPropriete getCouleur() {
		return this.couleur;
	}
        
        public void addPropriete(ProprieteAConstruire p) {
            proprietes.add(p);
        }
}