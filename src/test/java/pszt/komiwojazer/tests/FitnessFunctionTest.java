package pszt.komiwojazer.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;



import org.junit.Test;

import pszt.komiwojazer.basic.Chromosome;
import pszt.komiwojazer.basic.Gene;

public class FitnessFunctionTest {
	
	@Test
	public void fitnessFunctionTest() {
		List<Gene> genes = new ArrayList<Gene>(3);
		//Gene g4 = new Gene(0,0,0);
		Gene g1 = new Gene(0,3,1);
		Gene g2 = new Gene(0,6,1);
		Gene g3 = new Gene(0,9,1);
		//Gene g5 = new Gene(0,0,0);
		//genes.add(g4);
		genes.add(g1);
		genes.add(g2);
		genes.add(g3);
		//genes.add(g5);
		Chromosome c = new Chromosome();
		c.setGenes(genes);
		double f = c.fitnessFunction();
		assertEquals(1.818, f, 0);
	}

}
