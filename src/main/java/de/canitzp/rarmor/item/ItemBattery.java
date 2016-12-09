/*
 * This file ("ItemBattery.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.item;

import cofh.api.energy.ItemEnergyContainer;
import de.canitzp.rarmor.compat.Compat;
import de.canitzp.rarmor.compat.ItemTeslaWrapper;
import de.canitzp.rarmor.api.RarmorAPI;
import de.canitzp.rarmor.misc.CreativeTab;
import de.canitzp.rarmor.misc.Helper;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class ItemBattery extends ItemEnergyContainer{

    public ItemBattery(String name){
        super(500000, 500);
        this.setMaxStackSize(1);

        this.setRegistryName(RarmorAPI.MOD_ID, name);
        GameRegistry.register(this);

        this.setUnlocalizedName(RarmorAPI.MOD_ID+"."+name);
        this.setCreativeTab(CreativeTab.INSTANCE);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.add(TextFormatting.GOLD+I18n.format(RarmorAPI.MOD_ID+".storedEnergy")+":");
        tooltip.add(TextFormatting.YELLOW+"   "+this.getEnergyStored(stack)+"/"+this.getMaxEnergyStored(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack){
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack){
        double max = this.getMaxEnergyStored(stack);
        return (max-this.getEnergyStored(stack))/max;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound compound){
        return Compat.teslaLoaded ? new ItemTeslaWrapper(stack, this) : super.initCapabilities(stack, compound);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems){
        super.getSubItems(item, tab, subItems);

        ItemStack stack = new ItemStack(item);
        Helper.setItemEnergy(stack, this.getMaxEnergyStored(stack));
        subItems.add(stack);
    }
}
