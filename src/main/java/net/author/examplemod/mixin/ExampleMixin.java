/*
 * -------------------------------------------------------------------
 * Example Mod
 * Copyright (c) ${year} Me!
 *
 * CC0 License 1.0 Universal
 * -------------------------------------------------------------------
 */

package net.author.examplemod.mixin;

import net.author.examplemod.common.ExampleModUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//#if MC>=11701
import net.minecraft.client.gui.screen.TitleScreen;
//#elseif FABRIC==1
//$$ import net.minecraft.client.gui.screen.TitleScreen;
//#endif


@Mixin(
    //#if MC<11701
    //#if FORGE==1
    //#if MC>11202
    //$$ net.minecraft.client.gui.screen.MainMenuScreen
    //#else
    //$$ net.minecraft.client.gui.GuiMainMenu
    //#endif
    //#else
    //$$ TitleScreen
    //#endif
    //#else
    TitleScreen
    //#endif
            .class
)
public class ExampleMixin {
    @Inject(at = @At("TAIL"), method = "<init>*")
    private void init(CallbackInfo info) {
        ExampleModUtil.LOGGER.info("This line is printed by a cross-version example mod mixin!");
    }
}
