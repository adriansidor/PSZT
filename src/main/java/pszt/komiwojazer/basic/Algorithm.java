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
	 * genes - lista punktów dostawy
	 * fuelConsumption - spalanie pojazdu [l/km] - domyślnie 0,1 (domyślne wartości - wyświetlane początkowo w UI)
	 * loadFactor - współczynnik zwiększania spalania spowodowanego załadunkiem – domyślnie 0,01 (1/kg)
	 * populationSize - liczebność początkowej populacji – domyślnie równa liczbie punktów dostawy
	 * maxIterationsWithoutImprovement - warunek końcowy - maksymalna liczba iteracji bez poprawy najlepszego rozwiązania – domyślnie 5
	 * mutationProbability - prawdopodobieństwo mutacji – domyślnie 0,02
	 * parentPart - część rodziców brana pod uwagę przy reprodukcji – domyślnie 0.8 – całość
	 */
	public Chromosome findSolution(List <Gene> genes, double fuelConsumption, double loadFactor, int populationSize, int maxIterationsWithoutImprovement, double mutationProbability, double parentPart  ) {
		List<Chromosome> population = createPopulation(genes, populationSize);
		int endConditionCounter = 0;
		int i = 0;
		Chromosome bestChromosome = Collections.min(population);
System.out.println("Najlepszy na początku: " + bestChromosome.getFitness());
		while (endConditionCounter < maxIterationsWithoutImprovement)
		{
			++i;
			population = selection(population, populationSize);
			List<Pair<Chromosome, Chromosome>> pairs = createParents(population, parentPart);
			for (Pair<Chromosome, Chromosome> pair : pairs)
			{
				Chromosome child = crossover(pair.getFirst(), pair.getSecond());
				Random r = new Random();
				if (r.nextDouble() < mutationProbability )
					child.mutation();
				population.add(child);
			}
			
			Chromosome newBest = Collections.min(population);
			if(bestChromosome.getFitness() > newBest.getFitness())
			{
				bestChromosome = newBest;
				endConditionCounter = 0;
				System.out.println("Najlepszy po " + i + "iteracji : " + bestChromosome.getFitness());
			}
			else 
				++endConditionCounter;
		}
		return bestChromosome;
	}

	/*
	 * Krzyzowanie - PMX 
	 */
	public Chromosome crossover(Chromosome c1, Chromosome c2) {
		Random r = new Random();
		Chromosome child = new Chromosome();
		int N = c1.getGenes().size();

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
	public List<Chromosome> createPopulation(List<Gene> genes, int populationSize) {
		List<Chromosome> population = new ArrayList<Chromosome>(populationSize);
		Random r = new Random();
		for(int a = 0; a<populationSize; a++) {
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

	/**
	 * dobór rodziców w pary 
	 */
	public List<Pair<Chromosome, Chromosome>> createParents(List<Chromosome> chromosomes, double parentPart) {

		Collections.shuffle( chromosomes );
		List<Pair<Chromosome, Chromosome>> result = new ArrayList<Pair<Chromosome, Chromosome>>( );
		for (int i = 0; i < 0.5*chromosomes.size()*parentPart; ++i)
			result.add(new Pair<Chromosome, Chromosome>(chromosomes.get(2*i), chromosomes.get(2*i+1)));

		return result;
	}

	
}
