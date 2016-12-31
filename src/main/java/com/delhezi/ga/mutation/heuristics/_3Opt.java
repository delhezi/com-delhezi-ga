/**
 * @(#)_3Opt.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.heuristics;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import java.util.ListIterator;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.genes.PointGene;
import com.delhezi.ga.mutation.IMutation;
//import java.util.logging.Logger;

/**
 * Klasa <code>_3Opt</code>: Algorytm 3-opt;
 *
 * Algorytm lokalnego poszukiwania. Dedykowany dla TSP;

 * Losowo wybieramy 3 krawędzie. Jeśli długość najlepszego z cyklu
 * uzyskanego po wymianie jest mniejsza niż przed, krawędzie są zamieniane
 * (wybierany jest taka konfiguracja, która daje najlepszy cykl);
 * W innym przypadku przeszukiwana jest dostępna pula rozwiązań
 * (iterakcyjnie wybieramy kolejne krawędzie) w celu znalezienia
 * pierwszego wystąpienia cyklu lepszego.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class _3Opt<GENE_TYPE> implements IMutation<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(_3Opt.class.getName());

    /** Random. */
    private static Random random = new Random();
    
    /**
     * Funkcja mutation wykorzystuje algorytm 3-opt.
     * @param chromosome Chromosom.
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

        int edge[] = new int[3];
        // Losuje liczny z zakresu od 0 do chromosomeSize i dodaje 1 gdyż
        // krawęd 0 jest niepoprawna.
        // Pierwsza krawędź edge1=2 dla xx-xxxxxxxx.
        for (int i = 0; i < 3; i++) {
            edge[i] = (int) (random.nextDouble() * chromosomeSize + 1);

            // Korekta wyniku.
            // Istnieje bardzo małe prawdopodobienstwo wylosowania liczby o
            // wartosci chromosomeSize (poza zakresem), np. 4.999999999999999
            // zaokraglane jest do 4, ale sprawdzę:
            if (edge[i] > chromosomeSize) {
                edge[i] = chromosomeSize;
            }
        }

        this.mutation(chromosome, edge);
        }

    private final void mutation(final Chromosome<GENE_TYPE> chromosome, final int edge[]) {
        /** Licznik ilości podjętych prób. */
        int counter = 70;
/*
        assert chromosome != null : "Illegal argument chromosome: null";
        
        for (int i = 0; i < edge.length; i++) {
            System.out.println(" edge" + i + "=" + edge[i]);
        }
        
        List<GENE_TYPE> list = chromosome.getGenesAsLinkedList();
        
        System.out.println("list.get(2)=" + list.get(2));
        
        ListIterator<GENE_TYPE> iter1 = (ListIterator<GENE_TYPE>) list.listIterator(edge[0]);
        System.out.println("================");
        GENE_TYPE edge1a = (GENE_TYPE) iter1;
        System.out.println("aaa1="+edge1a);
        if(iter1.hasNext()){ 
            iter1.next();
            System.out.println("bbb="+iter1);
        }
        System.out.println("aaa2="+edge1a);
        
        System.out.println("================");
        
        //ListIterator<GENE_TYPE> edge1b = (ListIterator<GENE_TYPE>) list.listIterator(edge1a).next();
        //ListIterator<GENE_TYPE> edge1b = (ListIterator<GENE_TYPE>) list.listIterator(edge[0]).next();

        GENE_TYPE edge2a = list.get(edge[0]);
        
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        } 
*/
    }
    
    // np. krawędź edge1=2 dla xx-xxxxxxxx.
    ////12-34-56-
    private Chromosome<GENE_TYPE>[] reconnectionCases(final Chromosome<GENE_TYPE> chromosome, final int edge[]) {
/*
        assert chromosome != null : "Illegal argument chromosome: null";

        Chromosome<GENE_TYPE> chromosome1 = chromosome.clone(); //1256341
        LinkedList<GENE_TYPE> genesLinkedList = chromosome1.getGenesAsLinkedList();
        GENE_TYPE geneRef1 = genesLinkedList.get(edge[0]-1);
        GENE_TYPE geneRef2;
        
        if (genesLinkedList.size() > edge[0])
            geneRef2 = genesLinkedList.get(edge[0]);
        else
            geneRef2 = genesLinkedList.getFirst();
        
        System.out.println("geneRef1="+geneRef1.toString());
        System.out.println("geneRef2="+geneRef2.toString());
        
        GENE_TYPE geneRef3 = genesLinkedList.get(edge[1] - 1);
        GENE_TYPE geneRef4;
        if (genesLinkedList.size() > edge[1])
            geneRef4 = genesLinkedList.get(edge[1]);
        else
            geneRef4 = genesLinkedList.getFirst();
        
        System.out.println("geneRef3="+geneRef3.toString());
        System.out.println("geneRef4="+geneRef4.toString());
        
        GENE_TYPE geneRef5 = genesLinkedList.get(edge[2] - 1);
        GENE_TYPE geneRef6;
        if (genesLinkedList.size() > edge[2])
            geneRef6 = genesLinkedList.get(edge[2]);
        else
            geneRef6 = genesLinkedList.getFirst();
        
        System.out.println("geneRef5="+geneRef5.toString());
        System.out.println("geneRef6="+geneRef6.toString());
        
       // ListIterator<GENE_TYPE> it = genesLinkedList.listIterator();
       // if (it.hasNext()) {
       //     GENE_TYPE s1 = it.next();
       //     System.out.println(s1);
       // }

        Node<GENE_TYPE> cur  = genesLinkedList.getFirst();
       
        
*/
        Chromosome<GENE_TYPE> chromosome2 = chromosome.clone(); //1265341
        Chromosome<GENE_TYPE> chromosome4 = chromosome.clone(); //1256431
        Chromosome<GENE_TYPE> chromosome6 = chromosome.clone(); //1243651

        
        Chromosome<GENE_TYPE> chromosomes[] = new Chromosome[4];
        for (int i = 0; i < 8; i++) {

        }

        return chromosomes;

    }

}
