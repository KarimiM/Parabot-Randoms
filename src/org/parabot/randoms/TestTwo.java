package org.parabot.randoms;

import org.parabot.environment.scripts.randoms.Random;

/**
 * @author JKetelaar
 */
public class TestTwo implements Random {
    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public String getName() {
        return "TestTwo";
    }

    @Override
    public String getServer() {
        return "ikov";
    }
}
