package pszt.komiwojazer.basic;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa odwzorowuje trase dostawy drona
 * @author adr
 *
 */
public class Chromosome {
	
	@Getter
	@Setter
	private List<Gene> genes;
	
	/*
	 * Zużycie paliwa (na 1 km) bez obciazenia
	 */
	private static double S = 0.1;
	
	/*
	 * Wspolczynnik spalania
	 */
	private static double P = 0.01;
	
	/**
	 * Oblicza wartosc przystosowania
	 * @return
	 */
	public double fitnessFunction() {
		double F = 0;
		for(int i = 0; i<genes.size()-1; i++) {
			F += distance(genes.get(i), genes.get(i+1)) * S * (1 + P*weight(i+1));
		}
		return F;
	}
	
	/**
	 * Zwraca sume przenoszonego ladunku w zaleznosci od punktu
	 * @param nr
	 * @return
	 */
	private double weight(int nr) {
		double weight = 0;
		for(int i = nr; i<genes.size(); i++) {
			weight += genes.get(i).getWeight();
		}
		return weight;
	}
	
	/**
	 * Zwraca odleglosc miedzy punktami
	 * @param g1
	 * @param g2
	 * @return
	 */
	private double distance(Gene g1, Gene g2) {
		double a = Math.pow((g2.getX()-g1.getX()), 2) + Math.pow((g2.getY()-g1.getY()), 2);
		return Math.sqrt(a);
	}

}
