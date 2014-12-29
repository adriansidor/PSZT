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
	private int x;
	
	@Getter
	@Setter
	private int y;
	
	@Getter
	@Setter
	private float weight;
	
	public Gene(int x, int y, float weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
	
	
	
}
