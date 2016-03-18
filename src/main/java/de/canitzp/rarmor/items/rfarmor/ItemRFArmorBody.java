package de.canitzp.rarmor.items.rfarmor;

import de.canitzp.rarmor.RarmorProperties;
import de.canitzp.rarmor.api.IRarmorModule;
import de.canitzp.rarmor.util.EnergyUtil;
import de.canitzp.rarmor.util.ItemStackUtil;
import de.canitzp.rarmor.util.NBTUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author canitzp
 */
public class ItemRFArmorBody extends ItemRFArmor {

    public static int rfPerTick = 20, slotAmount = 31;
    public static final int FURNACEINPUT = 27, FURNACEOUTPUT = 28, MODULESLOT = 29, GENERATORSLOT = 30;

    public ItemRFArmorBody() {
        super(ItemRFArmor.RFARMOR, ArmorType.BODY, 250000, 1500, "rfArmorBody");
        rfPerTick = RarmorProperties.getInteger("maxRarmorTransferPerTick");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
        this.setEnergy(stack, 0);
        NBTUtil.setBoolean(stack, "isFirstOpened", false);
        NBTUtil.setInteger(stack, "rfPerTick", rfPerTick);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        ItemStack stackFull = new ItemStack(this);
        this.setEnergy(stackFull, this.getMaxEnergyStored(stackFull));
        NBTUtil.setBoolean(stackFull, "isFirstOpened", false);
        NBTUtil.setInteger(stackFull, "rfPerTick", rfPerTick);
        list.add(stackFull);

        ItemStack stackEmpty = new ItemStack(this);
        this.setEnergy(stackEmpty, 0);
        NBTUtil.setBoolean(stackEmpty, "isFirstOpened", false);
        NBTUtil.setInteger(stackEmpty, "rfPerTick", rfPerTick);
        list.add(stackEmpty);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack){
        double energyDif = this.maxEnergy- NBTUtil.getInteger(stack, "Energy");
        double maxAmount = this.maxEnergy;
        return energyDif/maxAmount;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        this.handleModules(world, player, armor);
        if(NBTUtil.getBoolean(armor, "isFirstOpened")){
            if(NBTUtil.getInteger(armor, "rfPerTick") == 0) NBTUtil.setInteger(armor, "rfPerTick", rfPerTick);
            if(NBTUtil.getInteger(armor, "BurnTimeMultiplier") == 0) NBTUtil.setInteger(armor, "BurnTimeMultiplier", 1);
            ItemStack foot = player.getCurrentArmor(0);
            ItemStack leggins = player.getCurrentArmor(1);
            ItemStack head = player.getCurrentArmor(3);
            if (this.isArmor(foot, leggins, armor, head)) {
                if (isBurnable(armor)) {
                    burn(armor);
                } else NBTUtil.setInteger(armor, "BurnTime", 0);
                if(NBTUtil.getInteger(armor, "Energy") - NBTUtil.getInteger(foot, "Energy") >= 4 || NBTUtil.getInteger(armor, "Energy") - NBTUtil.getInteger(foot, "Energy") <= 4){
                    EnergyUtil.balanceEnergy(new ItemStack[]{foot, armor, leggins, head});
                }
            }
        }
    }

    private void handleModules(World world, EntityPlayer player, ItemStack armor){
        IInventory inventory = NBTUtil.readSlots(armor, slotAmount);
        ItemStack module = inventory.getStackInSlot(MODULESLOT);
        if(module != null && module.getItem() instanceof IRarmorModule){
            IRarmorModule mod = (IRarmorModule) module.getItem();
            mod.onModuleTickInArmor(world, player, armor, module, inventory);
        }
    }

    private boolean isArmor(ItemStack foot, ItemStack leggins, ItemStack armor, ItemStack head) {
        if(head != null && foot != null && leggins != null && armor != null) {
            if (head.getItem() instanceof ItemRFArmorGeneric && foot.getItem() instanceof ItemRFArmorGeneric && leggins.getItem() instanceof ItemRFArmorGeneric && armor.getItem() instanceof ItemRFArmorBody) {
                return true;
            }
        }
        return false;
    }

    private boolean isBurnable(ItemStack armor){
        IInventory inventory = NBTUtil.readSlots(armor, slotAmount);
        if(inventory != null){
            ItemStack input = inventory.getStackInSlot(FURNACEINPUT);
            if(input != null && FurnaceRecipes.instance().getSmeltingResult(input) != null){
                ItemStack result = FurnaceRecipes.instance().getSmeltingResult(input);
                if(this.extractEnergy(armor, NBTUtil.getInteger(armor, "rfPerTick") * 200, true) > 0){
                    ItemStack output = inventory.getStackInSlot(FURNACEOUTPUT);
                    if(output != null && result.isItemEqual(output)){
                        if(output.stackSize+result.stackSize<=inventory.getInventoryStackLimit()){
                            return true;
                        }
                    } else if(output == null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void burn(ItemStack body){
        int burnTime = NBTUtil.getInteger(body, "BurnTime");
        if(burnTime < 200){
            burnTime += NBTUtil.getInteger(body, "BurnTimeMultiplier");
            this.extractEnergy(body, NBTUtil.getInteger(body, "rfPerTick"), false);
        } else {
            smeltItem(body);
            burnTime = 0;
        }
        NBTUtil.setInteger(body, "BurnTime", burnTime);
    }

    public void smeltItem(ItemStack body){
        IInventory inventory = NBTUtil.readSlots(body, slotAmount);
        ItemStack input = inventory.getStackInSlot(FURNACEINPUT);
        ItemStack output = inventory.getStackInSlot(FURNACEOUTPUT);
        if(input != null) {
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(input);
            if (result != null) {
                if(output == null || output.isItemEqual(result.copy())){
                    inventory = ItemStackUtil.reduceStackSize(inventory, FURNACEINPUT);
                    inventory = ItemStackUtil.addStackToSlot(inventory, result.copy(), FURNACEOUTPUT);
                    NBTUtil.saveSlots(body, inventory);
                }
            }
        }
    }



}
