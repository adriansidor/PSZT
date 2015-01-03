package pszt.komiwojazer.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Klasa zawiera implementacje metod operacji na chromosomach
 * Na razie w takiej formie dopoki ktos nie wymysli czegos lepszego
 * @author adr
 *
 */

public class Algorithm {
	/*
	 * Liczba punktów dostawy
	 */
	private static int N = 2;


	/*
	 * Krzyzowanie - PMX 
	 */
	public Chromosome crossover(Chromosome c1, Chromosome c2) {
		Random r = new Random();
		Chromosome child = new Chromosome();
		Gene[] childGenes = new Gene[N];
		int start = r.nextInt(N-1); //TEST 3	
		int end = r.nextInt(N-start-1)+start+1; //TEST 6
				
		ArrayList<Gene> copiedSegment = (ArrayList<Gene>) copy(c1.getGenes().subList(start, end+1));
		ArrayList<Gene> correspondingSegment = (ArrayList<Gene>) copy(c2.getGenes().subList(start, end+1));
		
		for (int i = start; i<end+1; ++i)
			childGenes[i] = c1.getGenes().get(i);
		
		for(int i = start; i<end+1; ++i) {
			Gene gene = c2.getGenes().get(i);
			if(!copiedSegment.contains(gene))
			{
				int k = i; //4
				while(true)
				{
					int idx = c2.getGenes().indexOf(c1.getGenes().get(k));

					if (!correspondingSegment.contains(c1.getGenes().get(k)))
					{
						childGenes[idx] = gene;
						break;
					}
					else
						k = idx;
				}
			}
		}
		
		/*
		 * Kopiowanie pozostałych wierzchołków z drugiego rodzica
		 */
		for (int i = 0; i<N; ++i) {
			if (childGenes[i] == null) {
				childGenes[i] = c2.getGenes().get(i);
			}
		}
		
		child.setGenes(Arrays.asList(childGenes));
		
		return child;
	}
	
	/*
	 * Selekcja
	 */
	public List<Chromosome> selection(List<Chromosome> chromosomes, int resultSize) {
		List<Chromosome> result = new ArrayList<Chromosome>( );
				
		/*
		 * Prawdopodobieństwo wylosowania danego osobnika jest proporcjonalne do
		 * 1/(A+i) oraz przeskalowane tak, aby suma prawdopodobieństw wynosiła 1
		 * A - stała (selectionCoefficient), i - pozycja osobnika na liście rankingowej
		 */
		final double selectionCoefficient = 5.0;
		
		double sum = 0.0;
		HashMap<Chromosome, Double> mapping = new HashMap<Chromosome, Double>();
		Random r = new Random();

		Collections.sort(chromosomes);

		for (int i = 0; i<chromosomes.size(); ++i)
		{
			mapping.put(chromosomes.get(i), 1.0/(selectionCoefficient+i));
			sum += 1.0/(selectionCoefficient+i);
		}
		for( int i = 0; i<resultSize; ++i)
		{
			double random = r.nextDouble()*sum;
			for (Entry<Chromosome, Double> entry : mapping.entrySet()) {
				random -= entry.getValue();
				if (random < 0)
				{
					result.add(entry.getKey());
					sum -= entry.getValue();
					mapping.remove(entry.getKey()); // usunąć w przypadku możliwości powielania osobnika przy selekcji
					break;
				}
			}
		}
		return result;
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
