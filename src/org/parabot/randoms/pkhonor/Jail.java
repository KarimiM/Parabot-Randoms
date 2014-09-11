package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 11-9-2014
 * Time: 22:22
 */

public class Jail implements Random {

    Npc[] jailer;

    @Override
    public boolean activate() {
        try {
            if (jailer.length > 0 && jailer[0] != null) {
                jailer = Npcs.getNearest(201);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void execute() {
        try {

            SceneObject rock = SceneObjects.getClosest(2093, 2092);

            //Check if we got an Pickaxe
            if (Inventory.getCount(1266, 1268, 1270, 1272, 1274, 1276, 14605, 14608) > 0) {

                //Check if we can min the ores
                if (!Inventory.isFull()) {
                    if (rock != null) {
                        if (Players.getMyPlayer().getAnimation() == -1) {
                            rock.interact(0);
                            Time.sleep(new SleepCondition() {
                                @Override
                                public boolean isValid() {
                                    return Players.getMyPlayer().getAnimation() != -1;
                                }
                            }, 2000);
                        }
                    }

                    //Inventory is full depositting ores
                } else {
                    jailer[0].interact(0);

                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return !Inventory.isFull();
                        }
                    }, 5000);
                    Time.sleep(2500);
                }

                //getting Pickaxe
            } else {
                jailer[0].interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(1266) > 0;
                    }
                }, 5000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
