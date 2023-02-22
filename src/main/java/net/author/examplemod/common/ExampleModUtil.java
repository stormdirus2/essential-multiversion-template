/*
 * -------------------------------------------------------------------
 * Example Mod
 * Copyright (c) ${year} Me!
 *
 * CC0 License 1.0 Universal
 * -------------------------------------------------------------------
 */

package net.author.examplemod.common;

import java.util.logging.Logger;

public class ExampleModUtil {
    public final static String MOD_ID = "examplemod";
    public final static Logger LOGGER = Logger.getLogger(MOD_ID);

    public static void sayHello() {
        LOGGER.info("Hello! I am a multi-version mod!");
    }
}
