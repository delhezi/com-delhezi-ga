/**
 * @(#)_2Opt.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.heuristics;


import com.delhezi.ga.Chromosome;
import com.delhezi.ga.genes.PointGene;
import com.delhezi.ga.mutation.IMutation;
import java.util.Random;
//import java.util.logging.Logger;

/**
 * Klasa <code>_2Opt</code>: Algorytm 2-opt;
 *
 * Algorytm lokalnego przeszukiwania; Wersja zrandomizowana, ilość prób
 * podjętych do znalezienia lepszego rozwiązania okrela wartość counter;
 * Przydatny w rozwiązaniu symetrycznego problemu komiwojażera
 * (symmetric travelling salesman problem: STSP) – w którym dla każdego
 * miasta istnieje połączenie do wszystkich pozostałych miast, oraz
 * odległości pomiędzy miastami w obydwu kierunkach są sobie równe;
 * Dla pary miast (węzłów) istnieje tylko jeden łuk o określonej długości;
 *
 * Losowo wybieramy 2 krawędzie; Jeśli długość cyklu po wymianie jest
 * mniejsza niż przed, krawędzie są zamieniane; W innym przypadku
 * przeszukiwana jest dostępna pula rozwiązań (iterakcyjnie wybieramy
 * kolejne krawędzie) w celu znalezienia pierwszego wystąpienia
 * cyklu lepszego.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class _2Opt<GENE_TYPE> implements IMutation<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(_2Opt.class.getName());

    /** Random. */
    private static Random random = new Random();

    /**
     * Implementacja funkcji mutacji 2-opt.
     * @param chromosome Chromosom podlegający mutacji.
     * @since 1.0
     */
    @Override
    public final void mutation(final Chromosome<GENE_TYPE> chromosome) {

        if (chromosome == null) {
            throw new NullPointerException("Chromosome is null.");
            }
        int chromosomeSize = chromosome.size();
        //Dla chromosomeSize <= 3 dowolne połączenie jest optymalne
        if (chromosomeSize <= 3) {
            return;
            }

        //Losuje liczny z zakresu od 0 do chromosomeSize i dodaje 1 gdyż
        //krawęd 0 jest niepoprawna.
        //Pierwsza krawędź edge1=2 dla xx-xxxxxxxx.
        int edge1 = (int) (random.nextDouble() * chromosomeSize + 1);
        //Druga krawędź edge2=5 dla xxxxx-xxxxx.
        int edge2 = (int) (random.nextDouble() * chromosomeSize + 1);

        //Korekta wyniku.
        //Istnieje bardzo małe prawdopodobienstwo wylosowania liczby o
        //wartosci chromosomeSize (poza zakresem), np. 4.999999999999999
        //zaokraglane jest do 4, ale sprawdzę:
        if (edge1 > chromosomeSize) {
            edge1 = chromosomeSize;
            }
        if (edge2 > chromosomeSize) {
            edge2 = chromosomeSize;
            }

        this.mutation(chromosome, edge1, edge2);
        }

    /**
     * Implementacja funkcji mutacji 2-opt.Szukamy lepszego zarwiązania
     * zaczynając od odwrócenia fragmentu genów wskazanych
     * krawędziami edge1 i edge2. W przypadku niepowodzenia funkcja
     * deterministycznie, metodycznie (inkrementuje edge1 i edge2),
     * poszukuje lepszego rozwiązania.
     * Uwaga;
     * Ostania krawędź edge=chromosomeSize łączy ostatni gen z pierwszym
     * Przykład: edge=8 xxxxxxxx-
     * @param chromosome Chromosom podlegający mutacji.
     * @param edge1 Pierwsza krawędź, wartość z zakresu <1, chromosomeSize>.
     * Przykład: edge1=4 dla xxxx-xxxx.
     * @param edge2 Druga krawędź, wartość z zakresu <1, chromosomeSize>.
     * Przykład: edge2=8 dla xxxxxx-xx.
     * @since 1.0
     */
    protected final void mutation(final Chromosome<GENE_TYPE> chromosome, final int edge1,
                                  int edge2) {
        /** Licznik ilości podjętych prób.*/
        int counter = 70;

        //Chromosom nie może być wartością null.
        assert chromosome != null : "Illegal argument chromosome: null";

        int chromosomeSize = chromosome.size();

        //Krawędź edge1 powininna zawierać się w obszare chromosomu.
        assert (edge1 >= 1 && edge1 <= chromosomeSize)
            : "Illegal argument edge1: " + edge1;
        //Krawędź edge2 powininna zawierać się w obszare chromosomu.
        assert (edge2 >= 1 && edge2 <= chromosomeSize)
            : "Illegal argument edge2: " + edge2;

        //Dla chromosomeSize <= 3 dowolne połączenie jest optymalne
        if (chromosomeSize <= 3) {
            return;
            }

        //Szukamy deterministycznie, wiec przestawiamy krawędzie
        if (edge1 == edge2) {
            edge2 = (edge2 == chromosomeSize) ? 1 : ++edge2;
        }

        int edge1iter = edge1;
        int edge2iter = edge2;
        boolean koniec = false;
        int tmpEdge1IterNext;

        //Sprawdź czy edge2iter nie jest bezpośrednio po edge1iter lub
        //edge2iter nie pokrywa się z edge1iter.
        //Jeśli tak to zmiana krawędzi nie zmieni porządku genów w chromosomie,
        //więc od razu przestawiamy edge2iter do kolejnego przypadku.
        tmpEdge1IterNext = (edge1iter == chromosomeSize) ? 1 : edge1iter + 1;
        if (tmpEdge1IterNext == edge2iter) {
            edge2iter = (edge2iter == chromosomeSize) ? 1 : edge2iter + 1;
        }

        //Poszukuj iterakcyjnie lepszego cyklu dla ścieżki.
        //Kręć PETLA 2 i zwiekszaj w każdym obrocie edge2iter+1 (lub
        //przejdź na początek jeśli edge2iter dojdzie do końca tablicy).
        //Jeśli edge2 zatoczyło koło to zwiększ edge1+1 i ponownie
        //kręć PETLA 2 zwiększając w każdej iterakcji edge2+1
        //do wyczerpania wszystkich możliwości.
        //Pomiń pary ścieżek powtarzające się w edge1 i edge2.
        for (int i1 = 0; i1 < chromosomeSize - 2; i1++) { //PETLA 1

        // TU SĄ ZAWSZE PRZYGOTOWANE ZMIENNE edge1iter, edge1, edge2iter, edge2.

            //----------- PETLA 2
            koniec = false;
            while (!koniec) { //Zakończ jeśli zatoczysz pełne koło
                                      //z edge2.
                                //Możlie wyjście przez break wówczas idziemy
                                //do pętli zewnętrznej do GOTO2

                //Licznik ilości podjętych prób.
                if (counter == 0) {
                    return;
                }
                counter--;

                //Sprawdź, czy po zmianie uzyskamy lepszy cykl.
                if (selectEdge(chromosome, edge1iter, edge2iter)) {
                    //System.out.println("ZNALAZLEM. Odcinki edge1=" + edge1 +
                    //", edge2=" + edge2 + " należy usunąć.");
                    changeEdge(chromosome, edge1iter, edge2iter);
                    return;
                }

                //Ustaw edge2iter o jeden dalej.
                edge2iter = (edge2iter == chromosomeSize) ? 1 : edge2iter + 1;

                //ZAŁOŻENIE. edge1 nigdy nie jest rowne edge2
                if (edge2iter == edge2) {
                    //WYJŚCIE Z PETLI2
                    //USTAW edge2iter do następnej petli.
                    edge2iter = (edge2iter == chromosomeSize) ? 1 : edge2iter + 1;
                    koniec = true;

                    //Tu sprawdzamy czy po przesunieciu edge2iter wchodzi
                    //na edge1
                    //Nie musimy sprawdzać czy edge2 jest wewnątrz ścieżki
                    //"edge1-edge1iter", bo był przed ścieżką.
                      if (edge2iter == edge1) {
                        edge2iter = (edge1iter == chromosomeSize) ? 1
                            : edge1iter + 1;
                      }
                } else { //(edge2iter != edge2)

                  //Sprawdź czy edge2iter wchodzi na edge1.
                  //Jeśli tak to przeskocz nim za ścieżką "edge1-edge1iter".
                  if (edge2iter == edge1) {
                      //USTAW edge2iter za ścieżką "edge1-edge1iter"
                      edge2iter = (edge1iter == chromosomeSize) ? 1
                          : edge1iter + 1;

                      //edge2 jest we wnetrzu "edge1-edge1iter"
                      //WYJŚCIE Z PETLI2
                      if ((edge2 <= edge1iter) && (edge2 >= edge1)) {
                            koniec = true;
                            break; //GOTO2
                      } else {
                          //Sprawdź czy po przeskoczeniu ścieżki nie trafisz
                          //edge2iter == edge2. Nie musisz sprawdzać, czy dalej
                          //nie ma "edge1-edge1iter", bo już była.
                          if (edge2iter == edge2) {
                              //USTAW edge2iter do następnej petli !!!
                              edge2iter = (edge2iter == chromosomeSize) ? 1 : edge2iter + 1;
                              break; //GOTO2
                              }
                          }
                      } //if (edge2iter == edge1)
                    } //koniec //(edge2iter != edge2)
                } //----------- koniec PETLA 2

            //Tu wychodzimy z GOTO2

            edge1iter = (edge1iter == chromosomeSize) ? 1 : edge1iter + 1;

            //Ewentualna korekta edge2iter
            if (edge2iter == edge1iter) {
              edge2iter = (edge2iter == chromosomeSize) ? 1 : edge2iter + 1;
            }
        }
    }

    /**
     * Jeśli znaleziono lepszy cykl; Zamień "krzyżowo" ścieżki;
     * Przykład:
     * edge1=ab, edge2=cd
     * Zawsze odwracamy odcinek bc.
     * 1) dxxa-b12c-, edge1=ab=4, edge2=cd=8  --  wynik dxxa-c21b-
     * 2) xxa-b12c-dxx, edge1=ab=3, edge2=cd=7 -- wynik xxa-c21b-dxx
     * @param chromosome Chromosom.
     * @param edge1 Pierwsza krawędź, wartość z zakresu <1, chromosomeSize>
     *              np. edge1=4 dla xxxx-xxxx.
     * @param edge2 Druga krawędź, wartość z zakresu <1, chromosomeSize>
     *              np. edge2=8 dla xxxxxx-xx.
     * @since 1.0
     */
    protected final void changeEdge(Chromosome<GENE_TYPE> chromosome,
                                    final int edge1,
                                    final int edge2) {
        int chromosomeSize = chromosome.size();
        int pA;
        int pB;
        int pC;
        int pD;

        //Zawsze odwracamy punkty pomiędzy B a C.

            if (edge1 == chromosomeSize) { //bxxxxxxxxa-
               pA = edge1 - 1; //geny licza sie od 0
               pB = 0;
            } else { //xxxxxa-bxxx
               pA = edge1 - 1; //geny licza sie od 0
               pB = edge1;
            }

            if (edge2 == chromosomeSize) { //dxxxxxxxxc-
               pC = edge2 - 1; //geny licza sie od 0
               pD = 0;
            } else { //xxxxxc-dxxx
               pC = edge2 - 1; //geny licza sie od 0
               pD = edge2;
            }

        //Liczba genów podlegających inwersji.
        int inversionGenes = (pB < pC) ? pC - pB + 1
            : (chromosomeSize - pB) + pC + 1;

        //tmpGenes - sekcja inwersji po operacji inwersji.
        @SuppressWarnings("unchecked")
        GENE_TYPE[] invGenes = (GENE_TYPE[]) new Object[inversionGenes];
        int ii;
        if (pB < pC) {
            ii = pC;
            for (int i = 0; i < invGenes.length; i++) {
                invGenes[i] = chromosome.getGene(ii);
                ii--;
            }
        } else {
            ii = 0;
            for (int i = pC; i >= 0; i--) {
                invGenes[ii++] = chromosome.getGene(i);
            }

            for (int i = chromosomeSize - 1; i >= pB; i--) {
                invGenes[ii++] = chromosome.getGene(i);
            }
        }

        int inv = 0;
        if (edge1 < edge2) {
            for (int i = edge1; i < edge2; i++) {
                chromosome.setGene(i, invGenes[inv]);
                inv++;
            }
        } else {
            for (int i = edge1; i < chromosomeSize; i++) {
                chromosome.setGene(i, invGenes[inv]);
                inv++;
            }
            for (int i = 0; i < edge2; i++) {
                chromosome.setGene(i, invGenes[inv]);
                inv++;
            }
        }
      chromosome.changed();
    }

    /**
     * Funkcja sprawdza, czy udało sie wybrać właściwe krawędzie do
     * usunięcia; Cykl po usunięciu wybraych krawędzi (edge1, edge2) oraz
     * po utworzeniu nowych "na krzyż" powinien być badziej optymalny;
     * Przykład:
     * dxxa-bxxc-, edge1=ab=4, edge2=cd=8
     * Jeśli długości odcinków ac+bd jest mniejsza niż ab+cd to
     * znaleziono cykl lepszy (zwróć true).
     * @param chromosome Chromosom.
     * @param edge1 Pierwsza krawędź, wartość z zakresu <1, chromosomeSize>
     *              np. edge1=4 dla xxxx-xxxx.
     * @param edge2 Druga krawędź, wartość z zakresu <1, chromosomeSize>
     *              np. edge2=8 dla xxxxxx-xx.
     * @return true, w przypadku kiedy usuniecie wybranych odcinków i
     * utworzenie nowych skraca cykl.
     * @since 1.0
     */
    protected final boolean selectEdge(Chromosome<GENE_TYPE> chromosome,
                                       final int edge1,
                                       final int edge2) {

        int chromosomeSize = chromosome.size();
        double sizeAB;
        double sizeCD;
        double sizeAC;
        double sizeDB;
        PointGene pA, pB, pC, pD;

            if (edge1 == chromosomeSize) { //bxxxxxxxxa-
               pA = (PointGene) chromosome.getGene(edge1 - 1); //geny licza sie od 0
               pB = (PointGene) chromosome.getGene(0);
            } else { //xxxxxa-bxxx
               pA = (PointGene) chromosome.getGene(edge1 - 1); //geny licza sie od 0
               pB = (PointGene) chromosome.getGene(edge1);
            }
            sizeAB = distance(pA.getx(), pA.gety(), pB.getx(), pB.gety());

            if (edge2 == chromosomeSize) { //dxxxxxxxxc-
               pC = (PointGene) chromosome.getGene(edge2 - 1); //geny licza sie od 0
               pD = (PointGene) chromosome.getGene(0);
            } else { //xxxxxc-dxxx
               pC = (PointGene) chromosome.getGene(edge2 - 1); //geny licza sie od 0
               pD = (PointGene) chromosome.getGene(edge2);
            }
            sizeCD = distance(pC.getx(), pC.gety(), pD.getx(), pD.gety());

            sizeAC = distance(pA.getx(), pA.gety(), pC.getx(), pC.gety());
            sizeDB = distance(pD.getx(), pD.gety(), pB.getx(), pB.gety());

            //if ac+bd<ab+cd ZANLEZIONO lepszy cykl
            if (sizeAC + sizeDB < sizeAB + sizeCD) {
                 return true;
            } else {
                return false;
            }
    }

    /**
     * Odległość pomiędzy dwoma punktami w układzie współrzędnych
     * (wzór na odległość Euklidesową pomiędzy dwoma punktami).
     * @param x1 Wspłórzędna x pierwszego punktu.
     * @param y1 Wspłórzędna y pierwszego punktu.
     * @param x2 Wspłórzędna x drugiego punktu.
     * @param y2 Wspłórzędna y drugiego punktu.
     * @return Odległość.
     * @since 1.0
     */
    private double distance(final int x1, final int y1, final int x2,
                            final int y2) {
        int xdiff = x1 - x2;
        int ydiff = y1 - y2;
        return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    }
}
