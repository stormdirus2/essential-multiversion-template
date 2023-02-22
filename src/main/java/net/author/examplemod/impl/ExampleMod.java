/*
 * -------------------------------------------------------------------
 * Example Mod
 * Copyright (c) ${year} Me!
 *
 * CC0 License 1.0 Universal
 * -------------------------------------------------------------------
 */

package net.author.examplemod.impl;

import net.author.examplemod.common.ExampleModUtil;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleModUtil.sayHello();
    }
}
