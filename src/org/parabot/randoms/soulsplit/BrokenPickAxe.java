package org.parabot.randoms.soulsplit;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.soulsplit.api.methods.GroundItems;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.wrappers.GroundItem;
import org.soulsplit.api.wrappers.Item;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 15-10-2014
 * Time: 20:14
 */
public class BrokenPickAxe implements Random {

    private int[] HEADS = {480, 482, 484, 4876, 488, 490};


    @Override
    public boolean activate() {
        GroundItem head = getHead();
        if (Inventory.getCount(467) > 0) {
            if (head != null) {
                return true;
            }

            if (Inventory.getCount(head.getId() + 1) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {

        try {
            final GroundItem head = getHead();

            if (head != null) {
                System.out.println("Taking Head");
                head.take();
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(head.getId() + 1) > 0;
                    }
                }, 2500);
            }

            if (Inventory.getCount(head.getId() + 1) > 0 && Inventory.getCount(467) > 0) {
                Menu.sendAction(447, 466, getItem(467).getSlot(), 3214);
                Time.sleep(50);
                Menu.sendAction(870, head.getId(), getItem(head.getId() + 1).getSlot(), 3214);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(491) == 0;
                    }
                }, 2500);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private GroundItem getHead() {

        for (GroundItem head : GroundItems.getNearest(490)) {
            if (head != null) {
                return head;
            }
        }
        return null;

    }

    private Item getItem(int id) {
        for (Item item : Inventory.getItems()) {
            if (item != null && item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Broken Pickaxe handeler";
    }

    @Override
    public String getServer() {
        return "soulsplit";
    }
}
