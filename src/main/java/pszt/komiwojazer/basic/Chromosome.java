package pszt.komiwojazer.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa odwzorowuje trase dostawy drona
 * @author adr
 *
 */
public class Chromosome implements Comparable<Chromosome> {
	
	@Getter
	@Setter
	private List<Gene> genes;

	/*
	 * Wartość funkcji przystosowania - aby nie było konieczne obliczanie wartości każdorazowo
	 */
	private double fitness;

	
	/*
	 * Zużycie paliwa (na 1 km) bez obciazenia
	 */
	public static double S = 0.1;
	
	/*
	 * Wspolczynnik spalania
	 */
	public static double P = 0.01;
	
	/**
	 * Oblicza wartosc przystosowania
	 * @return
	 */
	private double fitnessFunction() {
		double F = 0;
		List<Gene> genes = new ArrayList<Gene>();
		genes.add(new Gene(0,0,0)); //dodanie punktu startowego
		genes.addAll(this.genes);
		genes.add(new Gene(0,0,0)); //dodanie punktu koncowego
		for(int i = 0; i<genes.size()-1; i++) { 
			F += distance(genes.get(i), genes.get(i+1)) * S * (1 + P*weight(i));
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
	
	/*
	 * Mutacja
	 */
	public void mutation() {
		Random r = new Random();
		
		int start = r.nextInt(genes.size()-1);
		int end = r.nextInt(genes.size()-start-1)+start+1;
		
		Collections.reverse(genes.subList(start, end+1)); 
	}

	public double getFitness() {
		if(fitness == 0.0)
			fitness = fitnessFunction();
		return fitness;
	}

	public int compareTo(Chromosome chromosome) {
		return Double.compare( this.getFitness(), chromosome.getFitness() );
	}
}
