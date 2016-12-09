/*
 * This file ("BasicInventory.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.inventory.gui;

import de.canitzp.rarmor.api.internal.IRarmorData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class BasicInventory implements IInventory{

    private final IRarmorData data;
    private final String name;
    private final ItemStack[] slots;

    public BasicInventory(String name, int slotAmount, IRarmorData data){
        this.name = name;
        this.slots = new ItemStack[slotAmount];
        this.data = data;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public void markDirty(){
        this.data.setDirty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player){

    }

    @Override
    public void closeInventory(EntityPlayer player){

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return true;
    }

    @Override
    public int getField(int id){
        return 0;
    }

    @Override
    public void setField(int id, int value){

    }

    @Override
    public int getFieldCount(){
        return 0;
    }

    @Override
    public void clear(){
        for(int i = 0; i < this.slots.length; i++){
            this.slots[i] = null;
        }
        this.markDirty();
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack){
        this.slots[i] = stack;
        this.markDirty();
    }

    @Override
    public int getSizeInventory(){
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i){
        if(i < this.getSizeInventory()){
            return this.slots[i];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j){
        if(this.slots[i] != null){
            ItemStack stackAt;
            if(this.slots[i].stackSize <= j){
                stackAt = this.slots[i];
                this.slots[i] = null;
                this.markDirty();
                return stackAt;
            }
            else{
                stackAt = this.slots[i].splitStack(j);
                if(this.slots[i].stackSize <= 0){
                    this.slots[i] = null;
                }
                this.markDirty();
                return stackAt;
            }
        }
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index){
        ItemStack stack = this.slots[index];
        this.slots[index] = null;
        return stack;
    }

    @Override
    public boolean hasCustomName(){
        return false;
    }

    @Override
    public ITextComponent getDisplayName(){
        return new TextComponentTranslation(this.getName());
    }

    public void saveSlots(NBTTagCompound compound){
        if(this.slots != null && this.slots.length > 0){
            NBTTagList tagList = new NBTTagList();
            for(ItemStack slot : this.slots){
                NBTTagCompound tagCompound = new NBTTagCompound();
                if(slot != null){
                    slot.writeToNBT(tagCompound);
                }
                tagList.appendTag(tagCompound);
            }
            compound.setTag("Items", tagList);
        }
    }

    public void loadSlots(NBTTagCompound compound){
        if(this.slots != null && this.slots.length > 0){
            NBTTagList tagList = compound.getTagList("Items", 10);
            for(int i = 0; i < this.slots.length; i++){
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                this.slots[i] = tagCompound != null && tagCompound.hasKey("id") ? ItemStack.loadItemStackFromNBT(tagCompound) : null;
            }
        }
    }

    public void drop(Entity entity){
        if(!entity.getEntityWorld().isRemote){
            for(int i = 0; i < this.slots.length; i++){
                this.dropSingle(entity, i);
            }
        }
    }

    public void dropSingle(Entity entity, int i){
        if(this.slots[i] != null){
            entity.entityDropItem(this.slots[i].copy(), 0);
            this.slots[i] = null;

            this.markDirty();
        }
    }
}
