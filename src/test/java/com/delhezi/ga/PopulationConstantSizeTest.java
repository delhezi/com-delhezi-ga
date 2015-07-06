package com.delhezi.ga;

import org.junit.Test;


public class PopulationConstantSizeTest {
    public PopulationConstantSizeTest() {
    }

    /**
     * @see Population#generation()
     */
    @Test
    public void testGeneration1() {
/**
          String scriptEngineDriverPath = "";
          //File dir1 = new File (".");//D:\sun\NetBeansProjects\GA
          File dir2 = new File(".."); //D:\sun\NetBeansProjects
          try {
              //System.out.println ("Current dir : " + dir1.getCanonicalPath());
              //System.out.println ("Parent  dir : " + dir2.getCanonicalPath());
              //scriptEngineDriverPath = "D:/sun/NetBeansProjects/com-delhezi-lib/com-delhezi-ga_scripts/";
              scriptEngineDriverPath = dir2.getCanonicalPath()+"/com-delhezi-ga/scripts/";
          } catch (Exception e) {
              e.printStackTrace();
          }

          ScriptEngineDriver driv = new ScriptEngineDriver("javascript", scriptEngineDriverPath, "fitnessFunction_TSP.js");
          FitnessFunction fitnessFunction = new FitnessFunction(driv, "fitnessFunction");
          fitnessFunction.setMaximisation(false);
          //fitnessFunction.setMaximisation(true);
          
          ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
          chromosomeProperties.setFitnessFunction(fitnessFunction);
          

      Point[] genes = {new Point(101,120), new Point(102,220),
                       new Point(103,220), new Point(104,220),
                       new Point(105,220), new Point(106,220),
                       new Point(107,120), new Point(108,220),
                       new Point(109,220), new Point(110,220),
                       new Point(111,220), new Point(112,220),
                       new Point(113,120), new Point(114,220),
                       new Point(115,220), new Point(116,220),
                       new Point(117,220), new Point(118,220),
                       new Point(119,120), new Point(120,220),
                       new Point(121,220), new Point(122,220),
                       new Point(123,220), new Point(124,220)};

      LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
      Point[] chromosome_i;
      for(int i=0; i< genes.length; i++){
        ShuffleList.shuffle(genes);
        chromosome_i = new Point[genes.length];
        System.arraycopy(genes, 0, chromosome_i, 0, genes.length);
        chromosomes.add(new Chromosome(chromosome_i, chromosomeProperties));
      }
      
      
          IMutation mutation = new _2Opt();
          //IMutation mutation = new InversionMutation();
          //IMutation mutation = new SwapMutation();

          ICrossover crossover = new PartiallyMappedCrossover();
          //ICrossover crossover = new OrderCrossover();

          ISelect select = new RouletteWheelElementaryImplementation();
          //ISelect select = new RouletteWheelEffectiveImplementation();
          //ISelect select = new Tournament();


      boolean elitism = true;
      //boolean elitism = false;
      
      PopulationConstantSize genetic = null;
      
      try {
          genetic = PopulationConstantSize.newPopulationConstantSize(
                  select, 
                  chromosomes,
                  crossover,
                  0.00,   //crossProbability
                  mutation,
                  0.90,  //mutationProbability
                  chromosomeProperties);
      } catch (GeneticAlgorithmException ex) {
        fail("Wystapil wyjatek GeneticAlgorithmException");
      }
      genetic.setElitism(elitism);
      
        //System.out.println(genetic.getChromosomeProperties().getFitnessFunction().isMaximisation());
        //LinkedList<Chromosome> linkedList = genetic.getChromosomes();
        //Collections.sort(linkedList);
        //System.out.println("++++++++++++++++++++++++++++" );
        //  for (Chromosome ch : genetic.getChromosomes()) {
        //           System.out.println(ch);
        //         }
        // UWAGA. LISTA POSORTOWANA JEST W ODWROTNEJ KOLEJNOŚCI
        // CHYAB JAKIŚ BŁĄD W JAVA
        // System.out.println("++++++++++++++++++++++++++++" );
        // System.out.println(genetic.getChromosome(0));
         //System.out.println(genetic.getChromosome(1));
         //System.out.println(genetic.getChromosome(0).compareTo(genetic.getChromosome(1)));


        try {
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
          genetic.generation();
        } catch (GeneticAlgorithmException e) {
          fail("Wystapil wyjatek GeneticAlgorithmException");
          }
*/
        }
    
    }

