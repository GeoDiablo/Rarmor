/*
 * This file ("Compat.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.compat;

import net.minecraftforge.fml.common.Loader;

public final class Compat{

    public static boolean teslaLoaded;

    public static void preInit(){
        teslaLoaded = Loader.isModLoaded("tesla") || Loader.isModLoaded("Tesla");
    }

}
