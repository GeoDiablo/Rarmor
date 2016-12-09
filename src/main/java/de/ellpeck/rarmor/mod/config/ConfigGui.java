/*
 * This file ("GuiConfig.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.ellpeck.rarmor.mod.config;

import de.ellpeck.rarmor.api.RarmorAPI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.Collections;

public class ConfigGui extends GuiConfig{

    public ConfigGui(GuiScreen parentScreen){
        super(parentScreen, Collections.<IConfigElement>singletonList(new ConfigElement(Config.config.getCategory(Configuration.CATEGORY_GENERAL))), RarmorAPI.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
    }
}
