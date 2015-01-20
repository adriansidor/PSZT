package pszt.komiwojazer.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;




import org.junit.Test;

import pszt.komiwojazer.basic.Algorithm;
import pszt.komiwojazer.basic.Chromosome;
import pszt.komiwojazer.basic.Gene;

public class AlgorithmTest {
	
	@Test
	public void fitnessFunctionTest() {
		List<Gene> genes = new ArrayList<Gene>(3);
		Gene g1 = new Gene(4,3,1);
		Gene g2 = new Gene(-4,1.5,1);
		Gene g3 = new Gene(-6,1,1);
		genes.add(g1);
		genes.add(g2);
		genes.add(g3);
		Gene g4 = new Gene(-5,-2,0);
		Gene g5 = new Gene(-3,-2,0);
		Gene g6 = new Gene(-1,-3,0);
		genes.add(g4);
		genes.add(g5);
		genes.add(g6);
		
		Algorithm algorithm = new Algorithm( );
		
		/*
		 * genes - lista punktów dostawy
		 * fuelConsumption - spalanie pojazdu [l/km] - domyślnie 0,1 (domyślne wartości - wyświetlane początkowo w UI)
		 * loadFactor - współczynnik zwiększania spalania spowodowanego załadunkiem – domyślnie 0,01 (1/kg)
		 * populationSize - liczebność początkowej populacji – domyślnie równa liczbie punktów dostawy^2
		 * maxIterationsWithoutImprovement - warunek końcowy - maksymalna liczba iteracji bez poprawy najlepszego rozwiązania – domyślnie 30
		 * mutationProbability - prawdopodobieństwo mutacji – domyślnie 0,02
		 * parentPart - część rodziców brana pod uwagę przy reprodukcji – domyślnie 0.8
		 */

		Chromosome best = algorithm.findSolution(genes, 0.1, 0.01, 10, 20, 0.01, 0.8);
		System.out.println("\nNajlepsza znaleziona ścieżka: ");
		for (int i = 0; i<best.getGenes().size();++i)
		{
			System.out.println((i+1)+ ". " + best.getGenes().get(i).getX() + " " + best.getGenes().get(i).getY());
		}
		System.out.println("Spalone paliwo: " + best.getFitness() + " litrow.");
			
		/*	NAJLEPSZE ROZWIĄZANIE
		  	1. 4.0 3.0
			2. -4.0 1.5
			3. -6.0 1.0
			4. -5.0 -2.0
			5. -3.0 -2.0
			6. -1.0 -3.0
			Spalone paliwo: 2.6094990142784322 litrow.
			*/
		}

}
