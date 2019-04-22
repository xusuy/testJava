package com.util;

import java.util.UUID;

public class UUIDGenerator {
    private static final Object lock = new Object();

    private static long lastTime;
    private static long clockSequence = 0;

    @Deprecated
    public static String create() {
        return sequentialUUIDString().replace("-", "");
    }

    public static UUID sequentialUUID() {
        long currentTimeMillis = System.currentTimeMillis();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            if (currentTimeMillis > lastTime) {
                lastTime = currentTimeMillis;
                clockSequence = 0;
            } else {
                clockSequence++;
            }
        }

        UUID randomUUID = UUID.randomUUID();

        long mostSignificantBits = (currentTimeMillis << 20) | ((clockSequence & 0x0F) << 16) | (randomUUID.getMostSignificantBits() & 0xFFFF);

        return new UUID(mostSignificantBits, randomUUID.getLeastSignificantBits());
    }

    public static String sequentialUUIDString() {
        return sequentialUUID().toString().toUpperCase();
    }
}
