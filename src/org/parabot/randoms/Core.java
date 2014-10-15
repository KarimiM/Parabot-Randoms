package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;
import org.parabot.randoms.pkhonor.Jail;
import org.parabot.randoms.pkhonor.MysteriousOldMan;
import org.parabot.randoms.pkhonor.SandwichLady;
import org.parabot.randoms.pkhonor.TriangleSandwich;
import org.parabot.randoms.soulsplit.BrokenPickAxe;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public class Core {
    private ArrayList<Random> randoms = new ArrayList<Random>();

    public void init(String server){
        randoms.add(new Jail());
        randoms.add(new TriangleSandwich());
        randoms.add(new SandwichLady());
        randoms.add(new MysteriousOldMan());
        randoms.add(new BrokenPickAxe());

        for (Random random : randoms){
            if (random.getServer().toLowerCase().equalsIgnoreCase(server.toLowerCase())){
                Context.getInstance().getRandomHandler().addRandom(random);
            }
        }
    }
}
