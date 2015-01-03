package pszt.komiwojazer.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;




import org.junit.Test;

import pszt.komiwojazer.basic.Algorithm;
import pszt.komiwojazer.basic.Chromosome;
import pszt.komiwojazer.basic.Gene;

public class CrossoverFunctionTest {
	
	@Test
	public void crossoverFunctionTest() {
		List<Gene> genes1 = new ArrayList<Gene>(9);
		List<Gene> genes2 = new ArrayList<Gene>(9);
		List<Chromosome> chromosomes = new ArrayList<Chromosome>(2);
		Gene g1 = new Gene(0,1,1);
		Gene g2 = new Gene(0,2,1);
		/*Gene g3 = new Gene(0,3,1);
		Gene g4 = new Gene(0,4,1);
		Gene g5 = new Gene(0,5,1);
		Gene g6 = new Gene(0,6,1);
		Gene g7 = new Gene(0,7,1);
		Gene g8 = new Gene(0,8,1);
		Gene g9 = new Gene(0,9,1);*/
		genes1.add(g1);
		genes1.add(g2);
		/*genes1.add(g3);
		genes1.add(g4);
		genes1.add(g5);
		genes1.add(g6);
		genes1.add(g7);
		genes1.add(g8);
		genes1.add(g9);
		genes2.add(g9);
		genes2.add(g3);
		genes2.add(g7);
		genes2.add(g8);*/
		genes2.add(g2);
		/*genes2.add(g6);
		genes2.add(g5);*/
		genes2.add(g1);
		//genes2.add(g4);
		Chromosome c1 = new Chromosome();
		c1.setGenes(genes1);
		Chromosome c2 = new Chromosome();
		c2.setGenes(genes2);
		Algorithm algorithm = new Algorithm();
		Chromosome c3 = algorithm.crossover(c1, c2);
		Chromosome c4 = algorithm.crossover(c2, c1);
		assertEquals(g1, c3.getGenes().get(0));
		assertEquals(g2, c3.getGenes().get(1));
		assertEquals(g1, c4.getGenes().get(1));
		assertEquals(g2, c4.getGenes().get(0));

		/*assertEquals(g9, c3.getGenes().get(0));
		assertEquals(g3, c3.getGenes().get(1));
		assertEquals(g2, c3.getGenes().get(2));
		assertEquals(g4, c3.getGenes().get(3));
		assertEquals(g5, c3.getGenes().get(4));
		assertEquals(g6, c3.getGenes().get(5));
		assertEquals(g7, c3.getGenes().get(6));
		assertEquals(g1, c3.getGenes().get(7));
		assertEquals(g8, c3.getGenes().get(8));*/
	}

}
