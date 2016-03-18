package de.canitzp.rarmor.inventory.slots;

import de.canitzp.rarmor.api.SlotUpdate;
import de.canitzp.rarmor.items.rfarmor.ItemRFArmor;
import de.canitzp.rarmor.items.rfarmor.ItemRFArmorBody;
import de.canitzp.rarmor.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author canitzp
 */
public class SlotFurnaceOutput extends SlotUpdate {

    private final EntityPlayer player;

    public SlotFurnaceOutput(IInventory inventory, int id, int x, int y, EntityPlayer player, ItemStack saveStack) {
        super(inventory, id, x, y, saveStack);
        this.player = player;
    }

    @Override
    public void onSlotChanged(){
        ItemStack stack = this.player.getCurrentArmor(ItemRFArmor.ArmorType.BODY.getId() + 1);
        if(stack != null && stack.getItem() != null && stack.getItem() instanceof ItemRFArmorBody){
            NBTUtil.saveSlots(stack, inventory);
        }
        super.onSlotChanged();
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

}
