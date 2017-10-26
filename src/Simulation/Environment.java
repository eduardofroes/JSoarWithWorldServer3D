/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import java.util.Random;
import java.util.logging.Logger;

import ws3dproxy.CommandUtility;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;
import ws3dproxy.model.World;
import ws3dproxy.util.Constants;

/**
 * @author Danilo
 */
public class Environment {
    Logger logger = Logger.getLogger(Environment.class.getName());
    WS3DProxy proxy = null;
    //SoarBridge soarBridge = null;
    Creature c = null;
    World w = null;
    public int width = 800;
    public int height = 600;
    public Thing deliverySpot = null;

    public Environment(Boolean prepareEnviromentAndStartGame) {
        proxy = new WS3DProxy();
        try {

            if (prepareEnviromentAndStartGame) {
                w = World.getInstance();
                w.reset();

                c = proxy.createCreature(100, 450, 0);

                Random rand = new Random();

                World.createFood(rand.nextInt(2), rand.nextInt(width), rand.nextInt(height));
                World.createFood(rand.nextInt(2), rand.nextInt(width), rand.nextInt(height));
                World.createFood(rand.nextInt(2), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createJewel(rand.nextInt(6), rand.nextInt(width), rand.nextInt(height));
                World.createDeliverySpot(rand.nextInt(width), rand.nextInt(height));

                deliverySpot = World.getWorldEntities().stream().filter(x -> x.getCategory() == Constants.categoryDeliverySPOT).findFirst().get();

                int x = rand.nextInt(width);
                int y = rand.nextInt(height);
                World.createBrick(4, x, y, x + 20, y + 20);

                x = rand.nextInt(width);
                y = rand.nextInt(height);
                World.createBrick(4, x, y, x + 20, y + 20);



                c.start();

            }
        } catch (Exception e) {
            logger.severe("Error in starting the Environment ");
            e.printStackTrace();
        }
    }

    public Creature getCreature() {
        return (c);
    }


}
