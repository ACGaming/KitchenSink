package com.theundertaker11.kitchensink.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class IndestructibleEntityItem extends EntityItem {
/*
 * THIS WHOLE CLASS IS FROM TINKERS CONSTRUCT
 * I TAKE NO CREDIT FOR THIS
 * https://github.com/SlimeKnights/TinkersConstruct/blob/master/src/main/java/slimeknights/tconstruct/library/tinkering/IndestructibleEntityItem.java
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
  public IndestructibleEntityItem(World worldIn, double x, double y, double z) {
    super(worldIn, x, y, z);
    isImmuneToFire = true;
  }

  public IndestructibleEntityItem(World worldIn, double x, double y, double z, ItemStack stack) {
    super(worldIn, x, y, z, stack);
    isImmuneToFire = true;
  }

  public IndestructibleEntityItem(World worldIn) {
    super(worldIn);
    isImmuneToFire = true;
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if(source.getDamageType().equals(DamageSource.outOfWorld.damageType)) {
      return true;
    }
    // prevent any damage besides out of world
    return false;
  }

  public static class EventHandler {
    public static final EventHandler instance = new EventHandler();
    private EventHandler() {}

    @SubscribeEvent
    public void onExpire(ItemExpireEvent event) {
      if(event.getEntityItem() instanceof IndestructibleEntityItem) {
        event.setCanceled(true);
      }
    }
  }
}