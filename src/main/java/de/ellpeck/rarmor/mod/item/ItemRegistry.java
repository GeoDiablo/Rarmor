/*
 * This file ("ItemRegistry.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.ellpeck.rarmor.mod.item;

import de.ellpeck.rarmor.mod.module.compound.ItemModuleCompound;
import de.ellpeck.rarmor.mod.module.ender.ItemModuleEnder;
import de.ellpeck.rarmor.mod.module.furnace.ItemModuleFurnace;
import de.ellpeck.rarmor.mod.module.generator.ItemModuleGenerator;
import de.ellpeck.rarmor.mod.module.jump.ActiveModuleJump;
import de.ellpeck.rarmor.mod.module.jump.ItemModuleJump;
import de.ellpeck.rarmor.mod.module.protection.ActiveModuleProtectionDiamond;
import de.ellpeck.rarmor.mod.module.protection.ActiveModuleProtectionGold;
import de.ellpeck.rarmor.mod.module.protection.ActiveModuleProtectionIron;
import de.ellpeck.rarmor.mod.module.protection.ItemModuleProtection;
import de.ellpeck.rarmor.mod.module.solar.ItemModuleSolar;
import de.ellpeck.rarmor.mod.module.speed.ActiveModuleSpeed;
import de.ellpeck.rarmor.mod.module.speed.ItemModuleSpeed;
import de.ellpeck.rarmor.mod.module.storage.ItemModuleStorage;
import de.ellpeck.rarmor.mod.proxy.ClientProxy;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ItemRegistry{

    public static Item itemRarmorHelmet;
    public static Item itemRarmorChest;
    public static Item itemRarmorPants;
    public static Item itemRarmorBoots;

    public static Item itemWireCutter;
    public static Item itemControlCircuit;
    public static Item itemWire;
    public static Item itemConnector;
    public static Item itemSolarCell;
    public static Item itemGenerator;
    public static Item itemBattery;

    public static Item itemModuleStorage;
    public static Item itemModuleEnder;
    public static Item itemModuleFurnace;
    public static Item itemModuleSolar;
    public static Item itemModuleGenerator;
    public static ItemModuleSpeed itemModuleSpeed;
    public static ItemModuleJump itemModuleJump;
    public static Item itemModuleMovement;

    public static Item itemModuleProtectionIron;
    public static Item itemModuleProtectionGold;
    public static Item itemModuleProtectionDiamond;

    public static void preInit(){
        itemRarmorHelmet = new ItemRarmor("itemRarmorHelmet", EntityEquipmentSlot.HEAD);
        itemRarmorChest = new ItemRarmorChest("itemRarmorChest");
        itemRarmorPants = new ItemRarmor("itemRarmorPants", EntityEquipmentSlot.LEGS);
        itemRarmorBoots = new ItemRarmor("itemRarmorBoots", EntityEquipmentSlot.FEET);

        itemWireCutter = new ItemWireCutter("itemWireCutter");
        itemControlCircuit = new ItemBase("itemControlCircuit");
        itemWire = new ItemBase("itemWire");
        itemConnector = new ItemBase("itemConnector");
        itemSolarCell = new ItemBase("itemSolarCell");
        itemGenerator = new ItemBase("itemGenerator");
        itemBattery = new ItemBattery("itemBattery");

        itemModuleStorage = new ItemModuleStorage("itemModuleStorage");
        itemModuleEnder = new ItemModuleEnder("itemModuleEnder");
        itemModuleFurnace = new ItemModuleFurnace("itemModuleFurnace");
        itemModuleSolar = new ItemModuleSolar("itemModuleSolar");
        itemModuleGenerator = new ItemModuleGenerator("itemModuleGenerator");
        itemModuleSpeed = new ItemModuleSpeed("itemModuleSpeed");
        itemModuleJump = new ItemModuleJump("itemModuleJump");
        itemModuleMovement = new ItemModuleCompound("itemModuleMovement", new String[]{ActiveModuleSpeed.IDENTIFIER, ActiveModuleJump.IDENTIFIER}, new ItemRarmorModule[]{itemModuleSpeed, itemModuleJump});

        itemModuleProtectionIron = new ItemModuleProtection("itemModuleProtectionIron", ActiveModuleProtectionIron.IDENTIFIER);
        itemModuleProtectionGold = new ItemModuleProtection("itemModuleProtectionGold", ActiveModuleProtectionGold.IDENTIFIER);
        itemModuleProtectionDiamond = new ItemModuleProtection("itemModuleProtectionDiamond", ActiveModuleProtectionDiamond.IDENTIFIER);
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient(){
        ClientProxy.addLocation(itemRarmorHelmet);
        ClientProxy.addLocation(itemRarmorChest, 0);
        ClientProxy.addLocation(itemRarmorChest, 1);
        ClientProxy.addLocation(itemRarmorPants);
        ClientProxy.addLocation(itemRarmorBoots);

        ClientProxy.addLocation(itemWireCutter);
        ClientProxy.addLocation(itemControlCircuit);
        ClientProxy.addLocation(itemWire);
        ClientProxy.addLocation(itemConnector);
        ClientProxy.addLocation(itemSolarCell);
        ClientProxy.addLocation(itemGenerator);
        ClientProxy.addLocation(itemBattery);

        ClientProxy.addLocation(itemModuleStorage);
        ClientProxy.addLocation(itemModuleEnder);
        ClientProxy.addLocation(itemModuleFurnace);
        ClientProxy.addLocation(itemModuleSolar);
        ClientProxy.addLocation(itemModuleGenerator);
        ClientProxy.addLocation(itemModuleSpeed);
        ClientProxy.addLocation(itemModuleJump);
        ClientProxy.addLocation(itemModuleMovement);

        ClientProxy.addLocation(itemModuleProtectionIron);
        ClientProxy.addLocation(itemModuleProtectionGold);
        ClientProxy.addLocation(itemModuleProtectionDiamond);
    }

}
