/**
 * @(#)ScriptEngineDriver.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.utility;

import com.delhezi.ga.exception.GeneticAlgorithmException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.Reader;
import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <code>FitnessFunction</code>: Silnik skryptów.
 * @version 1.0 2009-12-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class ScriptEngine {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(ScriptEngine.class.getName());

    /** Delhezi Error Code. */
    private static final String DERC = "1-9-1-";

    /** */
    private Invocable invokeEngine;

    /** */
    private String scriptEnginName;

    /** */
    private String scriptPath;

    /** */
    private String scriptFile;


    /**
     * Konstruktor.
     * @param scriptEnginName Nazwa silnika skryptów. Przykładowe wartości:
     *                        [js, rhino, JavaScript, javascript,
     *                        ECMAScript, ecmascript].
     * @param scriptPath  Bezwzględna ścieżka zakończona "/" do katalogu w
     * którym składowane są skrytpy.
     * @param scriptFile  Naza pliku ze skrytem.
     * @throws GeneticAlgorithmException DERC-1-9-1-1, DERC-1-9-1-2, DERC-1-9-1-3
     * @since 1.0
     */
    public ScriptEngine(final String scriptEnginName,
                        final String scriptPath,
                        final String scriptFile)
    throws GeneticAlgorithmException {
        this.scriptEnginName = scriptEnginName;
        this.scriptPath = scriptPath.replaceAll("\\\\", "/");
        this.scriptFile = scriptFile;
        
        /*
        System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxx1::" + this.scriptPath + scriptFile);
        try {
            ScriptEngineManager factory2 = new ScriptEngineManager();
            javax.script.ScriptEngine engine2 = factory2.getEngineByName("JavaScript");
            engine2.eval("function hi(){\n"+
            		"var a = 'PROSPER'.toLowerCase(); \n"+
            		"middle(); \n"+
            		"print('Live long and' + a)}\n"+
            		"function middle(){\n"+
            		"var b = 1; for(var i=0, max = 5; i<max;i++){\nb++;\n}\n print('b is '+b);}hi();");
            } catch (ScriptException ex) {
                    //...
                }
                */

            ScriptEngineManager factory3 = new ScriptEngineManager();
            javax.script.ScriptEngine engine = factory3.getEngineByName("JavaScript");
            try {
				engine.eval("load(\"" + this.scriptPath  + this.scriptFile + "\");");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
/*    
        
        ScriptEngineManager manager = new ScriptEngineManager();
        javax.script.ScriptEngine engine =
            manager.getEngineByName(scriptEnginName);
        
        if (engine == null) {
            String errMessage =
                    "DERC-" + DERC + "4: Cannot load " + scriptEnginName + " engine.";
            throw new GeneticAlgorithmException(errMessage);
        }
        

        Reader reader;
        try {
            reader = new FileReader(this.scriptPath + scriptFile);
        } catch (FileNotFoundException e) {
            String errMessage =
                "DERC-" + DERC + "1: The script " + this.scriptPath
                + scriptFile
                + " does not exist, is a directory rather than a regular file,"
                + " or for some other reason cannot be opened for reading.";
            LOGGER.log(Level.WARNING, errMessage, e);
            throw new GeneticAlgorithmException(errMessage);
        }
        
        try {
            System.out.print("ccccccccccccccccccccccccccccccccc1::" + this.scriptPath + scriptFile);
            engine.eval(reader);
            System.out.print("ccccccccccccccccccccccccccccccccc2::" + this.scriptPath + scriptFile);
        } catch (ScriptException e) {
            String errMessage =
                "DERC-" + DERC + "2: Error occurrs in script "
                + this.scriptPath + scriptFile + ".";
            LOGGER.log(Level.WARNING, errMessage, e);
            throw new GeneticAlgorithmException(errMessage);
        }

        try {
            reader.close();
        } catch (IOException e) {
            String errMessage =
                "DERC-" + DERC + "3: Nie udało się zamknąć uchwytu do pliku.";
            LOGGER.log(Level.WARNING, errMessage, e);
            throw new GeneticAlgorithmException(errMessage);
        }
*/
        invokeEngine = (Invocable) engine;
    }

    /**
     * Wywołuje funkcję zdefiniowaną w skrypcie.
     * @param functionName Nazwa funkcji zdefiniowanej w skrypcie.
     * @param args Argumenty do przekazania do wywoływanej funkcji.
     * @return Wartość zwrócona przez wywoływaną funkcję.
     * @throws GeneticAlgorithmException DERC-1-9-1-4, DERC-1-9-1-5
     * @since 1.0
     */
    public Object invoke(final String functionName,
                         final Object... args) throws GeneticAlgorithmException {
        Object o = null;
        try {
            o = invokeEngine.invokeFunction(functionName, args);
        } catch (ScriptException e) {
            String errMessage =
                "DERC-" + DERC + "4: Error occurrs during invocation of the method.";
            LOGGER.log(Level.WARNING, errMessage, e);
            throw new GeneticAlgorithmException(errMessage);
        } catch (NoSuchMethodException e) {
            String errMessage =
                "DERC-" + DERC + "5: Method with given name or matching argument types cannot be found.";
            LOGGER.log(Level.WARNING, errMessage, e);
            throw new GeneticAlgorithmException(errMessage);
        }
        return o;
    }

    /**
     * Zwraca nazwę silnika skryptów.
     * @return Nazwa silnika skryptów.
     * @since 1.0
     */
    public final String getScriptEnginName() {
        return this.scriptEnginName;
    }

    /**
     * Zwraca bezwzględną ścieżkę do katalogu w którym składowane są skrytpy.
     * @return Ścieżka do katalogu.
     */
    public final String getScriptPath() {
        return this.scriptPath;
    }

    /**
     * Zwraca nazwę pliku z wykonywanym skrytem.
     * @return Nazwa pliku z wykonywanym skrytem.
     * @since 1.0
     */
    public final String getScriptFile() {
        return this.scriptFile;
    }
}
