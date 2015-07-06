/**
 * fitnessFunction_template.js
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */

//importPackage(com.delhezi.ga.genes);
//importClass (com.delhezi.ga.genes.Point);


/**
 * Funkcja celu. Wyznaczenie wskażnika przystosowania.
 * Uwaga! Wymagane kodowanie pliku UTF-8.
  * 
 * param genesList  Argumentem funcji jest lista genów pojedynczego chromosomu
 *                  (lista obiektów typu GENE_TYPE).
 *                  GENE_TYPE to obiekt dowolnego typu dostępny w java (integer, string, tablica...)
 *                  w szczególnosci obiekt zdefiniowany w com.delhezi.ga.genes.*
 *
 * return cost Wyznaczony wskażnik przystosowania. Zwracana wartość powinna typu double.
 *
 * author: <a href="mailto:xxx">xxx</a>
 * version: x.x
 * since: x.x
 */
function fitnessFunction(genesList){

var genes=gensList.toArray();
var index;
var cost = null;

  for(index in genes){
      //Jeśli GENE_TYPE jest typu prostego - wartości genów dostępne są 
      //  bezpośrednio przez odwołanie do zmiennej: genes[index].
      //Jeśli GENE_TYPE jest obiektem złożonym to genes[index] zawiera
      // wskaźnik do obiektu a wartości genów dostępne są przez
      // wywołanie funkcji tego obiektu: genes[index].funkcja().
      cost = cost + 1; //Wyznacz wskażnik przystosowania.
  }
  return cost; //Wyznaczony wskażnik przystosowania (Uwaga! Wartość powinna być liczbą).
}