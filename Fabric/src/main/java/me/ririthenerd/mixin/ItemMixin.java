package me.ririthenerd.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(at = @At("HEAD"), method = "finishUsing")
	private void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
		if(stack.getItem().equals(Items.GOLDEN_CARROT)){
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100));
		}
		if(stack.getItem().equals(Items.SPIDER_EYE)){
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100));
		}
		if(stack.getItem().equals(Items.PUFFERFISH)){
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 100));
		}
	}
}