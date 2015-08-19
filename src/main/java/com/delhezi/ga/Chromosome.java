/**
 * @(#)Chromosome.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.mutation.IMutation;
//import java.util.logging.Logger;

/**
 * <code>Chromosome</code>: Klasa chromosomu.
 * @param <GENE_TYPE> typ obiektu charakteryzujący gen np.: Integer, Double lub objekt klasy com.delhezi.genes.*.
 * Uwaga; Genami nie mogą być typy proste np. int, double.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class Chromosome<GENE_TYPE> implements Cloneable,
                                   Comparable<Chromosome<GENE_TYPE>> {
    /** Logger object. */
    //private static final Logger LOGGER = Logger.getLogger(Chromosome.class.getName());

    /**
     * Konstruktor.
     * @param genes Tablica genów.
     * @param chromosomeProperties Referencja do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej instancji populacji.
     * @since 1.0
     */
    public Chromosome(final GENE_TYPE[] genes,
                      final ChromosomeProperties chromosomeProperties) {
        //W konstruktorze i metodzie clone inicjuj bezposrednio
        //wartości zmiennych lub wywołuj tylko metody final.
        this.genes = genes;
        this.chProperties = chromosomeProperties;
        this.changed = true;
    }

    /**
     * Porównanie dwóch chromosomów;
     * Wykorzystywane przy sortowaniu.
     * W przypadku maksymalizacji sortowanie od najwiekszej do najmniejszej wartości fitnes (najlepsze osobniki na początku listy).
     * W przypadku minimalizacji sortowanie od najmniejszej do najwiekszej wartości fitnes (najlepsze osobniki na początku listy).
     * @param chromosome Chromosom do porównania.
     * @return 0 jeśli wartości wskażników przystosowania chromosomów
     * porównywanych są równe, 1 lub -1  jeśli jeden większy od drugiego.
     */
    public final int compareTo(final Chromosome<GENE_TYPE> chromosome) {
        try {
            if (this.chProperties.getFitnessFunction().isMaximisation()) { //MAKSYMALIZACJA
                if (this.getFitness() < chromosome.getFitness()) {
                    return 1;
                }
                if (this.getFitness() > chromosome.getFitness()) {
                    return -1;
                }
            } else { // MINIMALIZACJA
                if (this.getFitness() > chromosome.getFitness()) {
                    return 1;
                }
                if (this.getFitness() < chromosome.getFitness()) {
                    return -1;
                }
            }
        } catch (GeneticAlgorithmException e) {
        }
        return 0; //Chromosomy są równe.
    }


    /**
     * Zwraca parametr określający maksymalizację/minimalizację funkcji celu.
     * @return true -  maksymalizacja funkcji celu (najlepszym jest osobnik o
     *                 najwiekszej wartości wskaźnika przystosowania)
     *         false - minimalizacja funkcji celu (najlepszym jest osobnik o
     *                 najmniejszej wartości wskaźnika przystosowania)
     */
    public final boolean isFitnessMaximisation() {
        return this.chProperties.getFitnessFunction().isMaximisation();
    }


    /**
     * Mutacja.
     * @param mutation Funkcja mutacji
     * @since 1.0
     */
  public final void mutation(final IMutation<GENE_TYPE> mutation) {
      mutation.mutation(this);
      //this.changed=true; Infomacja o zmianie chromosomu
                           //ustawiana w funkcji mutation.mutation(..)
  }

  /**
   * Zwraca ilość genów w chromosomie.
   * @return Ilość genów w chromosomie.
   * @since 1.0
   */
  public final int size() {
      if (this.genes == null) {
        return 0;
      } else {
        return this.genes.length;
      }
  }

  /**
   * Wylicza  i zwraca wartość wskaźnika przystosowania.
   * @return Wartość wskaźnika przystosowania.
   * @throws GeneticAlgorithmException xxx
   * @since 1.0
   */
  public final double getFitness() throws GeneticAlgorithmException {
      if (this.changed) {
          this.fitness = this.chProperties.getFitnessFunction()
              .calculateFitness(genes);
          this.changed = false;
      }
      return this.fitness;
  }

  /**
   * Funkcja zmienia status chromosomu na "zmodyfikowany";
   * Status chromosomu należy zmienić po operacjach:
   * - bezpośrednio na tabeli genów po uzyskaniu referencji
   * do tabeli, jeśli zmiana odbyła się z pominięciem
   * funkcji setGenes() lub setGene();
   * - przy zmianie referencji do IMutation, jeśli zmiana
   * odbyła się z pominięciem funkcji setMutation();
   * - przy zmianie referencji do IFitnessFunction, jeśli zmiana
   * odbyła się z pominięciem funkcji setFitnessFunction().
   */
  public final void changed() {
      this.changed = true;
  }

  /**
   * Zwraca gen ze wskazanej pozycji (locus).
   * @param locus Pozycja genu (locus).
   * @return Gen określonego typu.
   *
   * @since 1.0
   */
  public final GENE_TYPE getGene(final int locus) {
          return this.genes[locus];
  }

  /**
   * Wstawia gen na wskazaną pozycję (locus).
   * @param locus Locus pod którym ma być wstawiony gen.
   * @param gene Wstawiany gen określonego typu.
   *
   * @since 1.0
   */
  public final void setGene(final int locus, final GENE_TYPE gene) {
          this.genes[locus] = gene;
          this.changed = true;
  }

  /**
   * Zwraca referencję do tablicy genów;
   * Uwaga;
   * Zwracana jest referencja do tablicy a nie jej kopia;
   * Jeśli za pomocą otrzymanej referencji dojdzie do modyfikacji wartości
   * tablicy należy wykonać funkcję changed() klasy Chromosome w celu
   * ponownego wyznaczenia wskaźnika przystosowania chromosomu.
   * @return Referencja do tablicy genów.
   *
   * @since 1.0
   */
  public final GENE_TYPE[] getGenes() {
          return this.genes;
  }

  /**
   * Wstawia referencję do tablicy genów;
   * Uwaga;
   * Wstawiana jest referencja do tablicy a nie jej kopia;
   * Jeśli po wstawieniu referencji dojdzie do modyfikacji watrości
   * tablicy należy wykonać funkcję changed() klasy Chromosome w celu
   * ponownego wyznaczenia wskaźnika przystosowania chromosomu.
   * @param genes Referencja do tablicy genów.
   *
   * @since 1.0
   */
  public final void setGenes(final GENE_TYPE[] genes) {
          this.genes = genes;
          this.changed = true;
  }

  /**
   * Kopiuje chromosom. Ze względu na konieczność skopiowania tablicy
   * genów wymagane jest "głębokie kopiowanie";
   *
   * SPITOLONA FUNCKJA WYMAGA UOGÓLNIENIA DO PRZERÓBKI;
   *
   * @return Głęboka kopia chromosomu.
   * @since 1.0
   */
  @Override
  public final Chromosome<GENE_TYPE> clone() {
      try {
          //super.clone() - Metoda klasy Object - zapewnia płytkie kopiowanie
          //klasy, kopiuje objekt bit po bicie (kopiuje referencje).
          //UWAGA w przypadku kiedy klasa zawiera referencje do objektów
          //ZMIENIALNYCH (np. Date, lub w przypadku tablicy skopiuje się
          //tylko refencja do tablicy) bo kopiowane są referecnje
          //do objektów.
          //W przypadku kiedy klasa zawiera referencje do obiektów
          //NIEZMIENIALNYCH typu String lub Integer nie ma to znaczenia.
          //W przypadku typów prostych - kopiowane sa wartości.
          Chromosome<GENE_TYPE> result = (Chromosome<GENE_TYPE>) super.clone();

          //Wsytarczy płytkie kopiowanie bo tablica chromosomów jest pusta.
          if (genes.length < 1) {
              return result;
          }

          //Wymagane GLEBOKIE kopiowanie.
          //Ze względu na możliwe mutacje objekt gene jest ZMIENIALY.
          if (genes[0] instanceof Cloneable) {
          result.genes = (GENE_TYPE[]) new Object[genes.length];
          for (int i = 0; i < genes.length; i++) {
              //if(((String) genes.getClass().getName()).equals(
              //"[Lcom.delhezi.ga.genes.KlasaObjektu;"))
              ////result.genes[i]=((KlasaObjektu) genes[i]).clone();

              //TU TRZEBA DOPISAĆ KOD
              }
          } else {
              result.genes.clone(); //Zakładamy, że elementami tablicy są
                                    //objekty NIEZMIENIALNE.
             //Wystarczy tylko skopiować referencje do nich.
          }

          return result;
      } catch (CloneNotSupportedException ex) {
          throw new AssertionError(); //Błąd JVM.
                                      //Nie powinien się zdarzyć.
      }
  }

  /**
   * String charakteryzujący chromosom.
   * @return String charakteryzujący chromosom.
   * @since 1.0
   */
  @Override
  public final String toString() {
  String str = "CHROMOSOME [fitness=" + this.fitness
               + ", size=" + this.size() + ", genes:";
  for (int i = 0; i < this.size(); i++) {
      str += "[" + this.genes[i].toString() + "]";
      }
  str += "]";
  return str;
  }

  /** Wskaźnik przystosowania. */
  private double fitness;

  /** Tablica genów składająca się na pojedynczy chromosom. */
  private GENE_TYPE[] genes;

  /** Parametr określający, czy geny w chromosomie zostały zmienione. */
  private boolean changed = true;

  /**
   * Referencja do obiektu przechowującego parametry wspólne dla
   * wszystkich chromosomów w ramach jednej instancji populacji.
   */
  private ChromosomeProperties chProperties;
}
