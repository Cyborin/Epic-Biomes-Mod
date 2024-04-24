package net.cyborin.tutorialmod;

import net.cyborin.tutorialmod.client.ClientLoop;
import net.cyborin.tutorialmod.client.HudImageTest;
import net.cyborin.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		HudImageTest.drawImage();
		ClientTickEvents.END_CLIENT_TICK.register(new ClientLoop());
	}
}