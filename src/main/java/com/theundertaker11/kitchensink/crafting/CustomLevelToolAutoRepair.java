package com.theundertaker11.kitchensink.crafting;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomLevelToolAutoRepair implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int pick = 0;
		int compcount = 0;
		int noitem = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			
			ItemStack item = inv.getStackInSlot(i);
			
			if (item != null && item.getItem() == Itemsss.LevelPick && item.getTagCompound() != null) 
			{
				if(!item.getTagCompound().getBoolean("autorepair"))
				{
					++pick;
				}
			}
			if(item != null && item.getItem()==Itemsss.diamondPlate)
			{
				++compcount;
			}
			if(item==null)
			{
				++noitem;
			}
		}
		if(inv.getSizeInventory()==9&&pick == 1 && compcount==1&&noitem==7)
		{
			return true;
		}
		else if(inv.getSizeInventory()==4&&pick == 1 && compcount==1&&noitem==2)
		{
			return true;
		}
		else return false;
	}

	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack item = CraftingManager.getPick(inv);
		if(item==null) return null;
		
		ItemStack result = new ItemStack(Itemsss.LevelPick);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setBoolean("autorepair", true);
		if(item.getItemDamage()==0)
		{
			result.setItemDamage(3);
		}
		else if(item.getItemDamage()==1)
		{
			result.setItemDamage(5);
		}
		else if(item.getItemDamage()==2)
		{
			result.setItemDamage(6);
		}
		else if(item.getItemDamage()==4)
		{
			result.setItemDamage(7);
		}
		return result;
	}
	@Override
	public int getRecipeSize()
	{
		return 2;
	}
	@Override
	public ItemStack getRecipeOutput()
	{
		ItemStack stack = new ItemStack(Itemsss.LevelPick);
		return stack;	
	}
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[inv.getSizeInventory()];
	}

}
