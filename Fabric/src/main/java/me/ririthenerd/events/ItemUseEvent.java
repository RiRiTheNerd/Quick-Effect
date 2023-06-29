package me.ririthenerd.events;

import jdk.jshell.Snippet;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Map;

public class ItemUseEvent implements UseItemCallback {
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity playerEntity, World world, Hand hand) {
        if(playerEntity.getMainHandStack().getItem().equals(Items.PHANTOM_MEMBRANE)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.SCUTE)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.GHAST_TEAR)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.BLAZE_POWDER)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.MAGMA_CREAM)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.GLISTERING_MELON_SLICE)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.RABBIT_FOOT)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.SUGAR)){
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100));
            subOne(playerEntity.getMainHandStack());
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.FERMENTED_SPIDER_EYE)){
            if(playerEntity.hasStatusEffect(StatusEffects.JUMP_BOOST) || playerEntity.hasStatusEffect(StatusEffects.SPEED)){
                playerEntity.removeStatusEffect(StatusEffects.SPEED);
                playerEntity.removeStatusEffect(StatusEffects.JUMP_BOOST);
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100));
                subOne(playerEntity.getMainHandStack());
            }
            if(playerEntity.hasStatusEffect(StatusEffects.INSTANT_HEALTH) || playerEntity.hasStatusEffect(StatusEffects.POISON)){
                playerEntity.removeStatusEffect(StatusEffects.INSTANT_HEALTH);
                playerEntity.removeStatusEffect(StatusEffects.POISON);
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 100));
                subOne(playerEntity.getMainHandStack());
            }
            if(playerEntity.hasStatusEffect(StatusEffects.NIGHT_VISION)){
                playerEntity.removeStatusEffect(StatusEffects.NIGHT_VISION);
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100));
                subOne(playerEntity.getMainHandStack());
            }
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.GLOWSTONE_DUST)) {
            Map<StatusEffect, StatusEffectInstance> se = playerEntity.getActiveStatusEffects();
            for (StatusEffect eff : se.keySet()){
                if(se.get(eff).getAmplifier() + 1 != 3){
                    playerEntity.addStatusEffect(new StatusEffectInstance(eff, se.get(eff).getDuration(), se.get(eff).getAmplifier() + 1));
                    subOne(playerEntity.getMainHandStack());
                }
            }
        }
        if(playerEntity.getMainHandStack().getItem().equals(Items.REDSTONE)) {
            Map<StatusEffect, StatusEffectInstance> se = playerEntity.getActiveStatusEffects();
            for (StatusEffect eff : se.keySet()){
                if(!(se.get(eff).getDuration() + 20 >= 9600)){
                    playerEntity.addStatusEffect(new StatusEffectInstance(eff, se.get(eff).getDuration() + 20, se.get(eff).getAmplifier()));
                    subOne(playerEntity.getMainHandStack());
                }
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }

    private static void subOne(ItemStack mainHand){
        mainHand.setCount(mainHand.getCount() - 1);
    }
}
