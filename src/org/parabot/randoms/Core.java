package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public class Core {
    private ArrayList<Random> randoms = new ArrayList<Random>();

    public void init(){
        randoms.add(new TestOne());
        randoms.add(new TestTwo());
        Context.getInstance().getRandomHandler().setRandoms(this.randoms);
    }
}
