package pszt.komiwojazer.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa odwzorowuje punkt dostawy
 * @author adr
 *
 */
public class Gene {

	@Getter
	@Setter
	private double x;
	
	@Getter
	@Setter
	private double y;
	
	@Getter
	@Setter
	private double weight;
	
	public Gene(double x, double y, double weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
	
	  public boolean equals(Object otherObiekt) {
		    if (this == otherObiekt) return true;
		    if (!( this.getClass().equals(otherObiekt.getClass()) )) return false;
		    
		    Gene otherGene = (Gene) otherObiekt;
		    return ( otherGene.weight == this.weight && otherGene.x == this.x && otherGene.y == this.y );
	  }
}
