package com.otto;

/**
 * Created by mac on 2017/11/7.
 */

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
