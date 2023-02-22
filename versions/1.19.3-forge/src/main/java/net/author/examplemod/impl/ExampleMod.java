/*
 * -------------------------------------------------------------------
 * Example Mod
 * Copyright (c) ${year} Me!
 *
 * CC0 License 1.0 Universal
 * -------------------------------------------------------------------
 */

package net.author.examplemod.impl;

import net.minecraftforge.fml.common.Mod;
import net.author.examplemod.common.ExampleModUtil;

@Mod(
    //#if MC<=11202
    //$$ modid =
    //#endif
        ExampleModUtil.MOD_ID
)
public class ExampleMod {
    public ExampleMod() {
        ExampleModUtil.sayHello();
    }
}
