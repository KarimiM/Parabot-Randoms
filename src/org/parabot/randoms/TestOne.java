package org.parabot.randoms;

import org.parabot.environment.scripts.randoms.Random;

/**
 * @author JKetelaar
 */
public class TestOne implements Random {
    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public String getName() {
        return "TestOne";
    }

    @Override
    public String getServer() {
        return "ikov";
    }
}
