package pszt.komiwojazer.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa zawiera implementacje metod operacji na chromosomach
 * Na razie w takiej formie dopoki ktos nie wymysli czegos lepszego
 * @author adr
 *
 */
public class Algorithm {

	/*
	 * Krzyzowanie
	 */
	public void crossover(Gene c1, Gene c2) {
		//TODO implement me!
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/*
	 * Mutacja
	 */
	public void mutation() {
		//TODO implement me!
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/*
	 * Selekcja
	 */
	public void selection() {
		//TODO implement me!
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/**
	 * Pomocnicza funkcja kopiujaca liste genow
	 * @param genes
	 * @return
	 */
	private List<Gene> copy(List<Gene> genes) {
		List<Gene> copy = new ArrayList<Gene>(genes.size());
		for(int i = 0; i<genes.size(); i++) {
			Gene gene = genes.get(i);
			copy.add(new Gene(gene.getX(), gene.getY(), gene.getWeight()));
		}
		return copy;
	}
	
	/**
	 * Tworzenie populacji na podstawie listy genow
	 * @param genes lista genow
	 * @return
	 */
	public List<Chromosome> createPopulation(List<Gene> genes) {
		int n = (int) Math.pow(genes.size(), 2);
		List<Chromosome> population = new ArrayList<Chromosome>(n);
		Random r = new Random();
		for(int a = 0; a<n; a++) {
			Chromosome chromosome = new Chromosome();
			List<Gene> copy = copy(genes);
			List<Gene> newGenes = new ArrayList<Gene>(copy.size());
			
			for(int i = genes.size(); i>0; i--) {
				int b = r.nextInt(i);
				newGenes.add(copy.remove(b));
			}
			chromosome.setGenes(newGenes);
			population.add(chromosome);
		}

		return population;
	}
}
