package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Inventory;
import java.lang.Override;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 11-9-2014
 * Time: 22:29
 */
public class TriangleSandwich implements Random {
    @Override
    public boolean activate() {
        return Inventory.getCount(6963) > 0;
    }

    @Override
    public void execute() {
        //Drop Triangle Sandwich
        Inventory.getItems(6963)[0].drop();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(6963) == 0;
            }
        },1500);

        @Override
        public String getName() {
            return "Triangle Sandwich Handeler";
        }

        @Override
        public String getServer() {
            return "pkhonor";
        }
    }
}
