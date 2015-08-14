/**
 * fitnessFunction_TSP.js
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */

 /**
 * Funkcja celu stworzona dla rozwiązania problemu komiwojażera
 * (TSP - ang. traveling salesman problem).
 */

//importPackage(com.delhezi.ga.genes);
importClass (com.delhezi.ga.genes.Point);

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
 * author: <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 * version: 1.0
 * since: 1.0
 */
function fitnessFunction(genesList){

var genes=genesList.toArray();
var index;
var cost = null;

  for(index in genes)
    if(index > 0)
      cost =cost + distance(genes[index-1].getx(),genes[index-1].gety(),genes[index].getx(),genes[index].gety());

  //Powrót do miejsca wyjścia.
  cost =cost + distance(genes[0].getx(),genes[0].gety(),genes[genes.length-1].getx(),genes[genes.length-1].gety());
  return cost;
}

/**
 * Odległość pomiędzy dwoma punktami w układzie współrzędnych
 * (wzór na odległość Euklidesową pomiędzy dwoma punktami).
 *
 * param x1 Wspłórzędna x pierwszego punktu.
 * param y1 Wspłórzędna y pierwszego punktu.
 * param x2 Wspłórzędna x drugiego punktu.
 * param y2 Wspłórzędna y drugiego punktu.
 * return Odległość.
 */
function distance(x1, y1, x2, y2) {
    var xdiff = x1 - x2;
    var ydiff = y1 - y2;
    return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
}
