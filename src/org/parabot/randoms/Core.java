package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public class Core {
    private ArrayList<Random> randoms = new ArrayList<Random>();

    public void init(String server){
        randoms.add(new TestOne());
        randoms.add(new TestTwo());
        for (Random random : randoms){
            if (random.getServer().toLowerCase().equalsIgnoreCase(server.toLowerCase())){
                Context.getInstance().getRandomHandler().addRandom(random);
            }
        }
    }
}
