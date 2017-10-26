/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import support.MindView;
import support.NativeUtils;
import SoarBridge.SoarBridge;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import support.SimulationController;

/**
 *
 * @author Danilo
 */
public class ExperimentMain
{
    Logger logger = Logger.getLogger(ExperimentMain.class.getName());
        
    private void SilenceLoggers() {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.jsoar")).setLevel(ch.qos.logback.classic.Level.OFF);
        Logger.getLogger("Simulation").setLevel(Level.SEVERE);
    }

    public ExperimentMain() {
        SilenceLoggers();
        try
        {
            //NativeUtils.loadFileFromJar("/Users/du/Downloads/DemoJSOAR/rules/soar-rules.soar");
            String soarRulesPath = "/Users/du/Downloads/DemoJSOAR/rules/soar-rules.soar";

            //Start enviroment data
            Environment e = new Environment(Boolean.TRUE);
            SoarBridge soarBridge = new SoarBridge(e,soarRulesPath,true);
            MindView mv = new MindView(soarBridge);
            mv.setVisible(true);

            // Run Simulation until some criteria was reached
            Thread.sleep(3000);

            // Create and Populate SimulationController
            SimulationController simulationController = new SimulationController("SimulationController");
            simulationController.setCreature(e.c);
            simulationController.StartTimer();
            //=================================

            while(true)
            {
                if (mv.getDebugState() == 0) {
                   soarBridge.step();
                   mv.set_input_link_text(soarBridge.input_link_string);
                   mv.set_output_link_text(soarBridge.output_link_string);
                }
                else e.c.updateState();
                Thread.sleep(100);                   
            }

        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
            logger.severe("Unknown error"+ex);
        }
    }

    public static void main(String[] args)
    {
        ExperimentMain m = new ExperimentMain();
    }


}
