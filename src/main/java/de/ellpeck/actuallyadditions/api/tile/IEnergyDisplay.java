/*
 * This file ("IEnergyDisplay.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense/
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.api.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Extending this will cause a block to show "getEnergy()/maxEnergy() RF" when hovering over it in-world
 */
public interface IEnergyDisplay{

    @SideOnly(Side.CLIENT)
    int getEnergy();

    @SideOnly(Side.CLIENT)
    int getMaxEnergy();
}
