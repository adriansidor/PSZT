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
	
	
	
}
