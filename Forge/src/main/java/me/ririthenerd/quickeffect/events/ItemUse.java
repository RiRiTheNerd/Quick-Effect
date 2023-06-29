package me.ririthenerd.quickeffect.events;

import me.ririthenerd.quickeffect.QuickEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = QuickEffect.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ItemUse {
    @SubscribeEvent
    public static void useItem(PlayerInteractEvent.RightClickItem e) {
        Player p = e.getEntity();
        ItemStack hand = e.getItemStack();

        if(e.getItemStack().getItem().equals(Items.PHANTOM_MEMBRANE)){
            p.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.SCUTE)){
            p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100));
            p.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.GHAST_TEAR)){
            p.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.BLAZE_POWDER)){
            p.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.MAGMA_CREAM)){
            p.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.GLISTERING_MELON_SLICE)){
            p.addEffect(new MobEffectInstance(MobEffects.HEAL, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.RABBIT_FOOT)){
            p.addEffect(new MobEffectInstance(MobEffects.JUMP, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.SUGAR)){
            p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100));
            subOne(hand);
        }
        if(e.getItemStack().getItem().equals(Items.FERMENTED_SPIDER_EYE)){
            int no = 3;
            if(p.hasEffect(MobEffects.MOVEMENT_SPEED) || p.hasEffect(MobEffects.JUMP)){
                p.removeEffect(MobEffects.MOVEMENT_SPEED);
                p.removeEffect(MobEffects.JUMP);
                p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100));
                subOne(hand);
                no--;
            }
            if(p.hasEffect(MobEffects.HEAL) || p.hasEffect(MobEffects.POISON)){
                p.removeEffect(MobEffects.HEAL);
                p.removeEffect(MobEffects.POISON);
                p.addEffect(new MobEffectInstance(MobEffects.HARM, 100));
                subOne(hand);
                no--;
            }
            if(p.hasEffect(MobEffects.NIGHT_VISION)){
                p.removeEffect(MobEffects.NIGHT_VISION);
                p.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100));
                subOne(hand);
                no--;
            }
            if(no == 3){
                p.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
            }
        }
        if(e.getItemStack().getItem().equals(Items.REDSTONE)){
            Map<MobEffect, MobEffectInstance> se = p.getActiveEffectsMap();
            for (MobEffect eff : se.keySet()){
                if(!(se.get(eff).getDuration() + 20 >= 9600)){
                    p.addEffect(new MobEffectInstance(eff, se.get(eff).getDuration() + 20, se.get(eff).getAmplifier()));
                    subOne(hand);
                }
            }
        }
        if(e.getItemStack().getItem().equals(Items.GLOWSTONE_DUST)){
            Map<MobEffect, MobEffectInstance> se = p.getActiveEffectsMap();
            for (MobEffect eff : se.keySet()){
                if(!(se.get(eff).getDuration() + 20 >= 9600)){
                    p.addEffect(new MobEffectInstance(eff, se.get(eff).getDuration(), se.get(eff).getAmplifier() + 1));
                    subOne(hand);
                }
            }
        }
    }

    private static void subOne(ItemStack mainHand){
        mainHand.setCount(mainHand.getCount() - 1);
    }

}