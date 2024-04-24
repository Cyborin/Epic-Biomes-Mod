package net.cyborin.tutorialmod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.minecraft.client.MinecraftClient;

public class ClientLoop implements EndTick {

    @Override
    public void onEndTick(MinecraftClient client) {
        PlayerBiomeUpdate.getPlayerBiome();
    }

}
