package me.ririthenerd.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.Map;

public class ItemUseEvent implements UseItemCallback {

    private static final int TIME_SECONDS = 10;

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity p, World w, Hand h) {
        if(!p.raycast(4.5, 10f, true).getType().equals(HitResult.Type.BLOCK)){
            applyEffect(StatusEffects.SLOW_FALLING, p, Items.PHANTOM_MEMBRANE);
            if(p.getMainHandStack().getItem().equals(Items.TURTLE_SCUTE)) {
                p.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, TIME_SECONDS * 20));
                p.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TIME_SECONDS * 20));
                subOne(p.getMainHandStack());
            }
            applyEffect(StatusEffects.REGENERATION, p, Items.GHAST_TEAR);
            applyEffect(StatusEffects.STRENGTH, p, Items.BLAZE_POWDER);
            applyEffect(StatusEffects.FIRE_RESISTANCE, p, Items.MAGMA_CREAM);
            applyEffect(StatusEffects.INSTANT_HEALTH, p, Items.GLISTERING_MELON_SLICE);
            applyEffect(StatusEffects.JUMP_BOOST, p, Items.RABBIT_FOOT);
            applyEffect(StatusEffects.SPEED, p, Items.SUGAR);
            applyEffect(StatusEffects.OOZING, p, Items.SLIME_BLOCK);
            applyEffect(StatusEffects.INFESTED, p, Items.STONE);
            applyEffect(StatusEffects.WIND_CHARGED, p, Items.BREEZE_ROD);
            applyEffect(StatusEffects.WEAVING, p, Items.COBWEB);

            //Corrupted Effects
            applyCorruptedEffect(p, StatusEffects.JUMP_BOOST, StatusEffects.SLOWNESS);
            applyCorruptedEffect(p, StatusEffects.SPEED, StatusEffects.SLOWNESS);
            applyCorruptedEffect(p, StatusEffects.INSTANT_HEALTH, StatusEffects.INSTANT_DAMAGE);
            applyCorruptedEffect(p, StatusEffects.POISON, StatusEffects.INSTANT_DAMAGE);
            applyCorruptedEffect(p, StatusEffects.NIGHT_VISION, StatusEffects.INVISIBILITY);

            //Effect Modifiers
            if(p.getMainHandStack().getItem().equals(Items.GLOWSTONE_DUST)) {
                Map<RegistryEntry<StatusEffect>, StatusEffectInstance> se = p.getActiveStatusEffects();
                for (RegistryEntry<StatusEffect> eff : se.keySet()) {
                    if (p.getMainHandStack().getItem().equals(Items.GLOWSTONE_DUST)) {
                        if (se.get(eff).getAmplifier() + 1 != 3) {
                            p.addStatusEffect(new StatusEffectInstance(eff, se.get(eff).getDuration(), se.get(eff).getAmplifier() + 1));
                            subOne(p.getMainHandStack());
                        }
                    }
                }
            }
            if(p.getMainHandStack().getItem().equals(Items.REDSTONE)) {
                Map<RegistryEntry<StatusEffect>, StatusEffectInstance> se = p.getActiveStatusEffects();
                for (RegistryEntry<StatusEffect> eff : se.keySet()) {
                    if (p.getMainHandStack().getItem().equals(Items.REDSTONE)) {
                        if (!(se.get(eff).getDuration() + 20 >= 9600)) {
                            p.addStatusEffect(new StatusEffectInstance(eff, se.get(eff).getDuration() + 20, se.get(eff).getAmplifier()));
                            subOne(p.getMainHandStack());
                        }
                    }
                }
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }

    private static void subOne(ItemStack mainHand){
        mainHand.setCount(mainHand.getCount() - 1);
    }

    private static void applyEffect(RegistryEntry<StatusEffect> e, PlayerEntity p, Item i){
        if(p.getMainHandStack().getItem().equals(i)) {
            p.addStatusEffect(new StatusEffectInstance(e, TIME_SECONDS * 20));
            subOne(p.getMainHandStack());
        }
    }

    private static void applyCorruptedEffect(PlayerEntity p, RegistryEntry<StatusEffect> e, RegistryEntry<StatusEffect> ne){
        int x = 0;
        Map<RegistryEntry<StatusEffect>, StatusEffectInstance> ae = p.getActiveStatusEffects();
        for (RegistryEntry<StatusEffect> ignored : ae.keySet()){
            x++;
        }
        if(x == 0 && p.getMainHandStack().getItem().equals(Items.FERMENTED_SPIDER_EYE)){
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, TIME_SECONDS * 20));
            subOne(p.getMainHandStack());
        }else{
            if(p.getMainHandStack().getItem().equals(Items.FERMENTED_SPIDER_EYE)){
                if(p.hasStatusEffect(e)){
                    if(p.hasStatusEffect(e)){
                        p.removeStatusEffect(e);
                        p.addStatusEffect(new StatusEffectInstance(ne, TIME_SECONDS * 20));
                        subOne(p.getMainHandStack());
                    }
                }
            }
        }
    }
}
