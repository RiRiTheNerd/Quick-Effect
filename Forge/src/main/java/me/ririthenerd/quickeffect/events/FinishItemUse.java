package me.ririthenerd.quickeffect.events;

import me.ririthenerd.quickeffect.QuickEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QuickEffect.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FinishItemUse {
    @SubscribeEvent
    public static void finishItemUse(LivingEntityUseItemEvent.Finish e){
        Player p = (Player) e.getEntity();

        if(e.getItem().getItem().equals(Items.GOLDEN_CARROT)){
            p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100));
        }
        if(e.getItem().getItem().equals(Items.PUFFERFISH)){
            p.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100));
        }
    }
}
