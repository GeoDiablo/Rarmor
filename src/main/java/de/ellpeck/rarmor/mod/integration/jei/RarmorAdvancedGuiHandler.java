/*
 * This file ("RarmorAdvancedGuiHandler.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.ellpeck.rarmor.mod.integration.jei;

import de.ellpeck.rarmor.mod.inventory.gui.GuiRarmor;
import de.ellpeck.rarmor.mod.inventory.gui.button.TabButton;
import mezz.jei.api.gui.IAdvancedGuiHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RarmorAdvancedGuiHandler implements IAdvancedGuiHandler<GuiRarmor>{

    @Nonnull
    @Override
    public Class<GuiRarmor> getGuiContainerClass(){
        return GuiRarmor.class;
    }

    @Nullable
    @Override
    public List<Rectangle> getGuiExtraAreas(GuiRarmor gui){
        List<Rectangle> list = new ArrayList<Rectangle>();

        for(TabButton button : gui.tabButtons){
            if(button.visible){
                list.add(new Rectangle(button.xPosition, button.yPosition, button.width, button.height));
            }
        }

        return list;
    }
}
