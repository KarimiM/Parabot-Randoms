import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.randoms.Random;
import org.soulsplit.api.methods.Game;
import org.soulsplit.api.methods.GroundItems;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.methods.Skill;
import org.soulsplit.api.wrappers.GroundItem;
import org.soulsplit.api.wrappers.Item;

public class BrokenHatchet implements Random, Paintable {
	
	static int[] INVENTORY_HEAD_IDS = {509, 511, 513, 515, 517, 519, 521, 6744};
	static int[] GROUND_HEAD_IDS = {508, 510, 512, 514, 516, 518, 520, 6743};	
	static int[] AXES = { 1352, 1350, 1356, 1358, 1360, 6740 };
	static int[] LOGS = { 1512, 1522, 1520, 1518, 1514, 1516, 10809 };
	
	public static boolean headAvailable() {
      GroundItem[] Head = GroundItems.getNearest(GROUND_HEAD_IDS);
          if (Head.length > 0
              && Head[0] != null
              && Head[0].distanceTo() < 8) {
          return true;
      }
      return false;
  }
	
	public Item getHandle() {
        for (Item handle : Inventory.getItems(467)) {
            if (handle != null) {
                return handle;
            }
        }
        return null;
    }

    public Item getInventoryHead() {
        for (Item head : Inventory.getItems(INVENTORY_HEAD_IDS)) {
            if (head != null) {
                return head;
            }
        }
        return null;
    }
    
    public Item getAxe() {
        for (Item axe : Inventory.getItems(AXES)) {
            if (axe != null) {
                return axe;
            }
        }
        return null;
    }
    
    public Item getLogs() {
        for (Item logs : Inventory.getItems(LOGS)) {
            if (logs != null) {
                return logs;
            }
        }
        return null;
    }
    
    private GroundItem getHead() {
        for (GroundItem head : GroundItems.getNearest(GROUND_HEAD_IDS)) {
            if (head != null) {
                return head;
            }
        }
        return null;
    }
    
	@Override
	public boolean activate() {
		return headAvailable() || getInventoryHead() != null && Game.isLoggedIn();
	}

	@Override
	public void execute() {

		//Picks up head
		if(getInventoryHead() == null
				&& headAvailable()) {
			if(getHead() != null && Inventory.getCount() <= 26) {
				System.out.println("Picking up head");
	            getHead().take();
	            Time.sleep(800);
			}
			if(getHead() != null && Inventory.getCount() > 26  && getInventoryHead() == null) {
				while(getLogs() != null && Inventory.getCount() > 26) {
					getLogs().drop();
					Time.sleep(1500);
				}
			}
		}
		
		//Unequips the handle
		if(getHandle() == null
				&& getInventoryHead() != null
				&& headAvailable() == false) {
			if(Inventory.getCount() <= 27) {
				//unequips handle
				Menu.sendAction(632, 466, 3, 1688);
				Time.sleep(2500);
			}
		}
		
		//Uses handle on head
		if(getHandle() != null
				&& getInventoryHead() != null 
				&& headAvailable() == false) {
				while(getHandle() != null && getInventoryHead() != null) {
			        Menu.sendAction(447, (int) getHandle().getId() - 1, getHandle().getSlot(), 3214);
			        Time.sleep(3500);
			        Menu.sendAction(870, (int) getInventoryHead().getId() - 1, getInventoryHead().getSlot(), 3214);
			        Time.sleep(3500);
				}
		}
		
		//Wielding Process
		Item[] B_AXE = Inventory.getItems(1352);
		Item[] I_AXE = Inventory.getItems(1350);
		Item[] M_AXE = Inventory.getItems(1356);
		Item[] A_AXE = Inventory.getItems(1358);
		Item[] R_AXE = Inventory.getItems(1360);
		Item[] D_AXE = Inventory.getItems(6740);
		
		if(getAxe() != null) {
			if(Inventory.getCount(6740) > 0 && Skill.ATTACK.getLevel() >= 60) {
				Menu.sendAction(454, (int) (D_AXE[0].getId() - 1), D_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
			if(Inventory.getCount(1360) > 0 && Skill.ATTACK.getLevel() >= 40) {
				Menu.sendAction(454, (int) (R_AXE[0].getId() - 1), R_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
			if(Inventory.getCount(1358) > 0 && Skill.ATTACK.getLevel() >= 30) {
				Menu.sendAction(454, (int) (A_AXE[0].getId() - 1), A_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
			if(Inventory.getCount(1356) > 0 && Skill.ATTACK.getLevel() >= 20) {
				Menu.sendAction(454, (int) (M_AXE[0].getId() - 1), M_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
			if(Inventory.getCount(1352) > 0) {
				Menu.sendAction(454, (int) (B_AXE[0].getId() - 1), B_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
			if(Inventory.getCount(1350) > 0) {
				Menu.sendAction(454, (int) (I_AXE[0].getId() - 1), I_AXE[0].getSlot(), 3214);
				Time.sleep(3000);
			}
		}
	}

	@Override
	public String getName() {
		return "BrokenHatchet";
	}

	@Override
	public String getServer() {
		return "Soulsplit";
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		g.drawString("Random Activated: BrokenHatchet - Created by Bears ", 10, 275);
		
	}

}
