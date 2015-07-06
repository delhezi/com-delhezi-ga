/**
 * @(#)GeneticAlgorithm.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.crossover.factory.CrossoverFactory;
import com.delhezi.ga.crossover.factory.CrossoverOperatorType;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.drivers.ScriptEngineDriver;
import com.delhezi.ga.fitnessfunction.FitnessFunctionOption;
import com.delhezi.ga.mutation.factory.MutationFactory;
import com.delhezi.ga.mutation.factory.MutationOperatorType;
import com.delhezi.ga.selection.factory.SelectionFactory;
import com.delhezi.ga.selection.factory.SelectionMethodType;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
//import java.util.logging.Logger;

/**
 * <code>GeneticAlgorithm</code>: Fasada.
 * @version 1.0 2011-01-25
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class GeneticAlgorithm {
    /** Logger object. */
    // private static final Logger LOGGER =
    //     Logger.getLogger(GeneticAlgorithm.class.getName());

    /** Delhezi Error Code. */
    // private static final String DERC = "1-3-";

    private Population population;

    private State stateError;
    private State stateInitialized;
    private State stateRunning;
    private State stateStopped;
    private State state;

    /**
     * Konstruktor.
     * @since 1.0
     */
    public GeneticAlgorithm() {
        stateError = new StateError(this);
        stateInitialized = new StateInitialized(this);
        stateRunning = new StateRunning(this);
        stateStopped = new StateStopped(this);
        state = null;
    }

    /**
     * Licznik pokoleń - zwraca numer aktualnego pokolenia.
     * @return Numer aktualnego pokolenia.
     * @since 1.0
     */
    public int getGeneration() {
        return getPopulation().getGeneration();
    }

    /**
     * Zwraca wartość wskaźnika przystosowania najlepszego chromosomu.
     * @return Wartość wskaźnika przystosowania najlepszego chromosomu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public double getTopChromosomeCost() throws GeneticAlgorithmException {
        return getPopulation().getTopChromosome().getFitness();
    }

    /**
     * Zwraca numer pokolenia w którym znaleziono najlepszy chromosom.
     * @return Numer pokolenia.
     * @since 1.0
     */
    public int getTopChromosomeGenerationFound() {
        return getPopulation().getTopChromosomeGenerationFound();
    }

    /**
     * Zwraca typ populacji.
     * @return Typ populacji.
     * @since 1.0
     */
    public PopulationType getPopulationType() {
        PopulationType popType = null;
        if (getPopulation().getClass().getName().equals("com.delhezi.ga.PopulationChangeableSize")) {
            popType = PopulationType.valueOf("PopulationChangeableSize");
        } else if (getPopulation().getClass().getName().equals("com.delhezi.ga.PopulationConstantSize")) {
            popType = PopulationType.valueOf("PopulationConstantSize");
        }
        return popType;
    }

    /**
     * Przekszta populację na populację o stałej liczebności;
     * Funkcja określona dla populacji o zmiennej liczebności;
     * W przypadku użycia dla populacji o stałej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param selectionMethod Typ funkcji celu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationChangeableSize.
     * @since 1.0
     */
    public void toPopulationConstantSize(SelectionMethodType selectionMethod) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationChangeableSize) {
            setPopulation(((com.delhezi.ga.PopulationChangeableSize)getPopulation()).toPopulationConstantSize(selectionMethod));
        } else {
            throw new GeneticAlgorithmException("Funkcja określona dla PopulationType.PopulationChangeableSize");
        }
    }

    /**
     * Przekształca populację na populację o zmiennej liczebności;
     * Funkcja określona dla populacji o stałej liczebności;
     * W przypadku użycia dla populacji o zmiennej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param maxLT Maksymalny czas życia dopuszczalny dla chromosomu.
     * @param minLT Minimalny czas życia dopuszczalny dla chromosomu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationConstantSize.
     * @since 1.0
     */
    public void toPopulationChangeableSize(int maxLT,
                                           int minLT) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationConstantSize) {
            setPopulation(((com.delhezi.ga.PopulationConstantSize)getPopulation()).toPopulationChangeableSize(maxLT,
                                                                                                              minLT));
        } else {
            throw new GeneticAlgorithmException("Funkcja określona dla PopulationType.PopulationConstantSize");
        }
    }

    /**
     * Zwraca maksymalny czas życia dopuszczalny dla chromosomu;
     * Funkcja określona dla populacji o zmiennej liczebności;
     * W przypadku użycia dla populacji o stałej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @return Maksymalny czas życia dopuszczalny dla chromosomu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationChangeableSize.
     * @since 1.0
     */
    public int getMaxLT() throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationChangeableSize) {
            return ((com.delhezi.ga.PopulationChangeableSize) getPopulation()).getMaxLT();
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationChangeableSize");
        }
    }

    /**
     * Ustawia maksymalny czas życia dopuszczalny dla chromosomu;
     * Funkcja określona dla populacji o zmiennej liczebności;
     * W przypadku użycia dla populacji o stałej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param maxLT Maksymalny czas życia dopuszczalny dla chromosomu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationChangeableSize.
     * @since 1.0
     */
    public void setMaxLT(int maxLT) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationChangeableSize) {
            ((com.delhezi.ga.PopulationChangeableSize) getPopulation()).setMaxLT(maxLT);
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationChangeableSize");
        }
    }

    /**
     * Zwraca minimalny czas życia dopuszczalny dla chromosomu;
     * Funkcja określona dla populacji o zmiennej liczebności;
     * W przypadku użycia dla populacji o stałej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @return Minimalny czas życia dopuszczalny dla chromosomu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationChangeableSize.
     * @since 1.0
     */
    public int getMinLT() throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationChangeableSize) {
            return ((com.delhezi.ga.PopulationChangeableSize) getPopulation()).getMinLT();
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationChangeableSize");
        }
    }

    /**
     * Ustawia minimalny czas życia dopuszczalny dla chromosomu;
     * Funkcja określona dla populacji o zmiennej liczebności;
     * W przypadku użycia dla populacji o stałej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param minLT Minimalny czas życia dopuszczalny dla chromosomu.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationChangeableSize.
     * @since 1.0
     */
    public void setMinLT(int minLT) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationChangeableSize) {
            ((com.delhezi.ga.PopulationChangeableSize) getPopulation()).setMinLT(minLT);
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationChangeableSize");
        }
    }

    /**
     * Zwraca typ funkcji selekcji;
     * Funkcja określona dla populacji o stałej liczebności;
     * W przypadku użycia dla populacji o zmiennej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @return Typ funkcji selekcji.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationConstantSize.
     * @since 1.0
     */
    public SelectionMethodType getSelectionMethod() throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationConstantSize) {
            return SelectionFactory.getSelectionMethodType(((com.delhezi.ga.PopulationConstantSize) getPopulation()).getSelect());
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationConstantSize");
        }
    }

    /**
     * Ustawia funkcję selekcji;
     * Funkcja określona dla populacji o stałej liczebności;
     * W przypadku użycia dla populacji o zmiennej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param selectionMethod Typ funkcji selekcji.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationConstantSize.
     * @since 1.0
     */
    public void setSelectionMethod(SelectionMethodType selectionMethod) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationConstantSize) {
            ((com.delhezi.ga.PopulationConstantSize) getPopulation()).setSelect(selectionMethod);
        } else {
            throw new GeneticAlgorithmException("Parametr zdefiniowany dla PopulationType.PopulationConstantSize");
        }
    }

    /**
     * Zwraca typ operatora krzyżowania.
     * @return Typ operatora krzyżowania.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public CrossoverOperatorType getCrossoverOperator() throws GeneticAlgorithmException {
        return CrossoverFactory.getCrossoverOperatorType(getPopulation().getCrossover());
    }

    /**
     * Ustawia operator krzyżowania.
     * @param crossoverOperator Typ operatora krzyżowania.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setCrossoverOperator(CrossoverOperatorType crossoverOperator) throws GeneticAlgorithmException {
        getPopulation().setCrossover(crossoverOperator);
    }

    /**
     * Zwraca prawdopodobieństwo krzyżowania.
     * @return Prawdopodobieństwo krzyżowania.
     * @since 1.0
     */
    public double getCrossoverProbability() {
        return getPopulation().getCrossoverProbability();
    }

    /**
     * Ustawia prawdopodobieństwo krzyżowania.
     * @param crossoverProbability Prawdopodobieństwo krzyżowania.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setCrossoverProbability(double crossoverProbability) throws GeneticAlgorithmException {
        getPopulation().setCrossoverProbability(crossoverProbability);
    }

    /**
     * Zwraca typ operatora mutacji.
     * @return Typ operatora mutacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public MutationOperatorType getMutationOperator() throws GeneticAlgorithmException {
        return MutationFactory.getMutationOperatorType(getPopulation().getMutation());
    }

    /**
     * Ustawia operator mutacji.
     * @param mutationOperator Typ operatora mutacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setMutationOperator(MutationOperatorType mutationOperator) throws GeneticAlgorithmException {
        getPopulation().setMutation(mutationOperator);
    }

    /**
     * Zwraca prawdopodobieństwo mutacji.
     * @return Prawdopodobieństwo mutacji.
     * @since 1.0
     */
    public double getMutationProbability() {
        return getPopulation().getMutationProbability();
    }

    /**
     * Ustawia prawdopodobieństwo mutacji.
     * @param mutationProbability Prawdopodobieństwo mutacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setMutationProbability(double mutationProbability) throws GeneticAlgorithmException {
        getPopulation().setMutationProbability(mutationProbability);
    }

    /**
     * Zwraca informację czy zastosowano elitaryzm.
     * @return Elitaryzm.
     * @since 1.0
     */
    public boolean getElitism() {
        return getPopulation().getElitism();
    }

    /**
     * Ustawia elitaryzm.
     * @param elitism Elitaryzm.
     * @since 1.0
     */
    public void setElitism(boolean elitism) {
        getPopulation().setElitism(elitism);
    }

    /**
     * Zwraca bezwzględną ścieżkę do katalogu w którym składowane są skrytpy
     * funkcji celu.
     * @return Ścieżka do katalogu.
     * @since 1.0
     */
    public String getFitnessFunctionScriptPath() {
        return ((ScriptEngineDriver) getPopulation().getChromosomeProperties().getFitnessFunction().getScriptEngineDriver()).getScriptPath();
    }

    /**
     * Zwraca nazwę pliku z używanym skrytem funkcji celu.
     * @return Nazwa pliku ze skrytem funkcji celu.
     * @since 1.0
     */
    public String getFitnessFunctionScriptFile() {
        return ((ScriptEngineDriver) getPopulation().getChromosomeProperties().getFitnessFunction().getScriptEngineDriver()).getScriptFile();
    }

    /**
     * Zwraca nazwę silnika skryptów.
     * @return Nazwa silnika skryptów.
     * @since 1.0
     */
    public String getFitnessFunctionEnginName() {
        return ((ScriptEngineDriver) getPopulation().getChromosomeProperties().getFitnessFunction().getScriptEngineDriver()).getScriptEnginName();
    }

    /**
     * Zwraca informację o maksymalizacji/maksymalizacji funkcji celu.
     * @return Informacja o maksymalizacji/maksymalizacji funkcji celu.
     * @since 1.0
     */
    public FitnessFunctionOption getFitnessFunctionOption() {
        return (getPopulation().getChromosomeProperties().getFitnessFunction().isMaximisation() ==
                true) ? FitnessFunctionOption.maximisation :
               FitnessFunctionOption.minimisation;
    }

    /**
     * Ustawia informację o maksymalizacji/maksymalizacji funkcji celu.
     * @param fitnessFunctionOption Maksymalizacja/maksymalizacja funkcji celu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setFitnessFunctionOption(FitnessFunctionOption fitnessFunctionOption) throws GeneticAlgorithmException {
        FitnessFunction fitnessFunction =
            getPopulation().getChromosomeProperties().getFitnessFunction();
        if (fitnessFunctionOption == FitnessFunctionOption.minimisation) {
            fitnessFunction.setMaximisation(false);
        } else if (fitnessFunctionOption == FitnessFunctionOption.maximisation) {
            fitnessFunction.setMaximisation(true);
        }
    }

  /**
     * Ustawia funkcję celu.
     * @param fitnessFunctionEnginName Nazwa silnika skryptów. Przykładowe
     * wartości: [js, rhino, JavaScript, javascript, ECMAScript, ecmascript].
     * @param fitnassFunctionScriptsPath Bezwzględna ścieżkę do katalogu w
     * którym składowane są skrytpy funkcji celu.
     * @param fitnessFunctionScriptFile Nazwa pliku ze skrytem funkcji celu.
     * @param fitnessFunctionOption Maksymalizacja/maksymalizacja funkcji celu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setFitnessFunction(String fitnessFunctionEnginName,
                                   String fitnassFunctionScriptsPath,
                                   String fitnessFunctionScriptFile,
                                   FitnessFunctionOption fitnessFunctionOption) throws GeneticAlgorithmException {
        IFitnessFunctionDriver fitnessFunctionDriver =
            FitnessFunctionDriverFactory.getFitnessFunctionEngineDriver(fitnessFunctionEnginName,
                                                                        fitnassFunctionScriptsPath,
                                                                        fitnessFunctionScriptFile);
        FitnessFunction fitnessFunction =
            new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
        if (fitnessFunctionOption == FitnessFunctionOption.minimisation) {
            fitnessFunction.setMaximisation(false);
        } else if (fitnessFunctionOption == FitnessFunctionOption.maximisation) {
            fitnessFunction.setMaximisation(true);
        }
        getPopulation().getChromosomeProperties().setFitnessFunction(fitnessFunction);
    }

    /**
     * Zwraca wielkość populacji.
     * @return Wielkość populacji.
     * @since 1.0
     */
    public int getPopulationSize() {
        return getPopulation().getPopulationSize();
    }

    /**
     * Ustawia wielkość populacji;
     * Funkcja określona dla populacji o stałej liczebności;
     * W przypadku użycia dla populacji o zmiennej liczbności zwraca wyjątek GeneticAlgorithmException.
     * @param populationSize Wielkość populacji.
     * @throws GeneticAlgorithmException If getPopulationType() != PopulationType.PopulationConstantSize.
     * @since 1.0
     */
    public void setPopulationSize(int populationSize) throws GeneticAlgorithmException {
        if (getPopulationType() == PopulationType.PopulationConstantSize) {
            ((com.delhezi.ga.PopulationConstantSize) getPopulation()).changePopulationSize(populationSize);
        } else {
            throw new GeneticAlgorithmException("Metoda zdefiniowana dla PopulationType.PopulationConstantSize");
        }
    }

    /**
     * Uruchamia algorytm genetyczny.
     * @throws GeneticAlgorithmException xxx
     */
    public void run() throws GeneticAlgorithmException {
        state.run();
    }

    /**
     * Zatrzymuje działanie algorytmu genetycznego.
     * @throws GeneticAlgorithmException xxx
     */
    public void stop() throws GeneticAlgorithmException {
        state.stop();
    }

    /**
     * Warunki końca.
     * opcjonalne parametry - inicjowane wartościami 0.
     * Parametry uzwględniane w sterowaniu jeśli ich wartość jest większa niż 0.
     */
    private int maxGenerationCount = 0;
    private int lastGenerationTopChromosomeFind = 0;

    /**
     * Warunek końca; Zwraca maksymalną ilość przewidzianych generacji.
     * @return Maksymalna ilość przewidzianych generacji.
     */
    public int getMaxGenerationCount() {
        return this.maxGenerationCount;
    }

    /**
     * Warunek końca; Ustawia maksymalną ilość przewidzianych generacji.
     * @param maxGenerationCount Maksymalna ilość przewidzianych generacji.
     */
    public void setMaxGenerationCount(int maxGenerationCount) {
        this.maxGenerationCount = maxGenerationCount;
    }

    /**
     * Warunek końca; Zwraca maksymalną ilość przewidzianych generacji od
     * chwili znalezienia ostatniego najlepszego chromosomu.
     * @return Maksymalna ilość przewidzianych generacji.
     */
    public int getLastGenerationTopChromosomeFind() {
        return this.lastGenerationTopChromosomeFind;
    }

    /**
     * Warunek końca; Ustawia maksymalną ilość przewidzianych generacji od
     * chwili znalezienia ostatniego najlepszego chromosomu.
     * @param lastGenerationTopChromosomeFind Maksymalna ilość przewidzianych generacji.
     */
    public void setLastGenerationTopChromosomeFind(int lastGenerationTopChromosomeFind) {
        this.lastGenerationTopChromosomeFind = lastGenerationTopChromosomeFind;
    }

    /**
     * Zwraca stan algorytmu genetycznego.
     * @return Stan.
     * @since 1.0
     */
    public GeneticAlgorithmState getState() {
        return state.getState();
    }

    /**
     * Ustawia stan algorytmu genetycznego.
     * @param state Stan.
     * @since 1.0
     */
    protected void setState(final GeneticAlgorithmState state) {
        switch (state) {
        case ERROR:
            this.state = stateError;
            break;
        case STOPPED:
            this.state = stateStopped;
            break;
        case RUNNING:
            this.state = stateRunning;
            break;
        case INITIALIZED:
            this.state = stateInitialized;
            break;
        }
    }

    /**
     * Zwraca referencję do populacji.
     * @return Referencja do populacji.
     * @since 1.0
     */
    protected Population getPopulation() {
        return this.population;
    }

    /**
     * Ustawia populację.
     * @param population Populacja.
     * @since 1.0
     */
    protected void setPopulation(Population population) {
        this.population = population;
    }
}
