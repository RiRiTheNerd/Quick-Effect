package me.ririthenerd;

import me.ririthenerd.events.ItemUseEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class QuickEffect implements ModInitializer {
	@Override
	public void onInitialize() {
		UseItemCallback.EVENT.register(new ItemUseEvent());
	}
}