package org.parabot.randoms.soulsplit;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.soulsplit.api.methods.GroundItems;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.wrappers.GroundItem;
import org.soulsplit.api.wrappers.Item;

public class BrokenHatchet implements Random {
	
	private final int[] groundAxeHeads = {508, 510, 512, 514, 516, 518, 520, 6743};
	
	private final int[] inventoryAxeHeads = {509, 511, 513, 515, 517, 519, 521, 6744};
	
	private final long[] axeHead = {509, 511, 513, 515, 517, 519, 521, 6744};
	
	private final int[] axesId = {1350, 1352, 1354, 1356, 1358, 1360, 1362, 6740};
	
	private final long[] axeId = {1350, 1352, 1354, 1356, 1358, 1360, 1362, 6740};
	
	private final int axeHandle = 493;
	
	
    
	@Override
	public boolean activate() {
		GroundItem[] toPickup = GroundItems.getNearest(groundAxeHeads);
		return (toPickup.length > 0 && toPickup[0] != null && toPickup[0].distanceTo() < 7) || Inventory.getCount(axeHead) > 0;
	}

	@Override
	public void execute() {

		// Picks up head
		System.out.println("Completing Woodcutting Random...");

		final GroundItem[] toPickup = GroundItems.getNearest(groundAxeHeads);

		if (toPickup.length > 0 && !Inventory.isFull() && toPickup[0] != null) {
			System.out.println("Picking up axe head");
			Menu.sendAction(234, toPickup[0].getId(), toPickup[0].getX(), toPickup[0].getY());
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return toPickup[0] == null;
				}
			}, 1500);
		}

		// Unequips the handle
		if (Inventory.getCount(axeHead) > 0) {

			if (Inventory.getCount(axeHandle) < 1) {
				Menu.sendAction(632, axeHandle - 1, 3, 1688);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.getCount(axeHandle) > 0;
					}
				}, 5000);
			}

			if (Inventory.getCount(axeHandle) > 0) {
				combine(axeHandle, inventoryAxeHeads);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.getCount(Constants.AXE) > 0;
					}
				}, 5000);
			}
		}

		// Equips axe
		if (Inventory.getCount(axeId) > 0) {
			
			Item[] axe = Inventory.getItems(axesId);
			
			if (axe.length > 0 && axe[0] != null) {
				
				Menu.sendAction(454, (int) (axe[0].getId() - 1), axe[0].getSlot(), 3214);
				
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.getCount(axeId) < 1;
					}
				}, 3000);
			}
		}
	}
	
	/**
	 * Method written by Empathy.
	 * @param idOne the first id to combine
	 * @param idTwo the second id to combine
	 */
	public static void combine(int idOne, int...idTwo) {
		for (Item i : Inventory.getItems(idOne)) {
			for (Item j : Inventory.getItems(idTwo)) {
				if (i != null) {
					if (j != null) {
						Menu.sendAction(447, (int) (i.getId() - 1), i.getSlot(), 3214);
						Time.sleep(500);
						Menu.sendAction(870, (int) (j.getId() - 1), j.getSlot(), 3214);
						Time.sleep(500);
					}
				}
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
}
