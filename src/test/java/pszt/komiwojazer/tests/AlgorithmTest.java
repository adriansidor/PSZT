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
		Gene g1 = new Gene(0,1,0);
		Gene g2 = new Gene(0,2,10);
		Gene g3 = new Gene(0,3,0);
		genes.add(g1);
		genes.add(g2);
		genes.add(g3);
		Gene g4 = new Gene(1,1,0);
		Gene g5 = new Gene(1,2,0);
		Gene g6 = new Gene(1,3,0);
		genes.add(g4);
		genes.add(g5);
		genes.add(g6);
		
		Algorithm algorithm = new Algorithm( );
		Chromosome best = algorithm.findSolution(genes, 0.1, 0.01, 100, 20, 0.01, 0.5);
		System.out.println("Najlepsza znaleziona ścieżka: ");
		System.out.println(best.getGenes());
		for (int i = 0; i<best.getGenes().size();++i)
		{
			System.out.println((i+1)+ ". " + best.getGenes().get(i).getX() + " " + best.getGenes().get(i).getY());
		}
		System.out.println("Spalone paliwo: " + best.getFitness() + " litrow.");
			
		//assertEquals(best, f, 0);
	}

}
