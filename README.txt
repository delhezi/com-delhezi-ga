                          com-delhezi-ga
Opis
------------------------------
  com-delhezi-ga stanowi implementację algorytmów genetycznych w środowisku java.

Dokumentacja 
------------------------------
  Aktualna dokumentacja została dołączona do dystrybucji i znajduje się w folderze /target/.
  Informacje nie ujęte w dokumentacji opublikowane są na stronie http://delhezi.com/ga/.

Lista zmian
------------------------------
  Pełna lista zmian dostępna jest pod adresem http://delhezi.com/ga/release-notes.html.

Wymagania dotyczące kompilacji biblioteki
------------------------------
  Poprawne zbudowanie biblioteki wymaga dostępności
    JDK 1.7 (http://docs.oracle.com/javase/7/docs/)

  Uwaga. Skrypty stworzone do budowy aplikacji bazują na zmiennych środowiskowych.
  DELHEZI_HOME, JAVA_HOME, M2_HOME.
  Poprawne zbudowanie aplikacji wymaga ustawienia powyższych zmiennych środowiskowych.

Kompilacja kodu z wykorzystaniem Mavena
------------------------------
  1. Kompilacja kodu oraz kolejne czynności opisane w tym dokumencie wymagają zainstalowania środowiska Mavena.
     http://maven.apache.org/
     W projekcie wykorzystano apache-maven-3.3.3.

     mvn clean

     Dozwolone fazy cyklu życia Mmaven'a dla projektu typu jar:
     mvn compile   	- kompiluje kod źródłowy. (lub mvn cobertura:check dla kompilacji uwarunkowanej spełnieniem wymogów minimalnego pokrycia kodu testami)
     mvn test-compile   - kompiluje testy jednostkowe
     mvn test		- wykonuje testy jednostkowe, wyniki umieszcza w katalogu target/surefire-reports
     mvn package	- pakuje kod do pliku jar
     mvn install	- instaluje pakiet w lokalnym repozytorium mavena
     mvn deploy		- wdraża pakiet do zdalnego repozytorium

     Wdrażenie projekty do lokalnego repozytorium Mavena:
     mvn install:install-file -DgroupId=com.delhezi.ga -DartifactId=com-delhezi-ga -Dversion=1.0.0 -Dpackaging=jar -Dfile=target/com-delhezi-ga-1.0.0.jar


Budowanie dokumentacji
------------------------------
   Diagramy UML w javadoc
     1. Uwaga!! UMLGraph wymaga zainstalowania w systemie oprogramowania graphviz (http://www.graphviz.org/)
        W projekcie wykorzystano graphviz-2.26.3.

     2. Wymagana instalacja biblioteki UMLGraph.jar. W projekcie wykorzystano UMLGraph-5.3.
         http://www.umlgraph.org/doc.html
         http://www.umlgraph.org/doc/index.html
         Na stronach składnia komnetarzy do generowania diagramów + przykłady.

     Polecenie budujące witrynę Maven prjektu:
        mvn site
     
     Polecenia budujące raporty składowe (wykonane w ramach mvn site):
        mvn checkstyle:checkstyle
        mvn cobertura:cobertura
        mvn javadoc:javadoc
        mvn pmd:pmd
        mvn pmd:cpd
        mvn surefire-report:report
        mvn jxr:jxr

Licencja
---------
  Zobacz plik LICENSE.TXT

URLS
----------
  Home Page:      http://delhezi.com/ga/
  Downloads:      http://delhezi.com/ga/downloads.html
  Source Code:    http://subversion.assembla.com/svn/com-delhezi-ga
  Wiki:           http://com-delhezi-ga.assembla.com/wiki/show/com-delhezi-ga/

