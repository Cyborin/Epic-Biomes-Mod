package net.cyborin.tutorialmod.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.util.math.BlockPos;

import net.cyborin.tutorialmod.TutorialMod;

public class PlayerBiomeUpdate extends MinecraftClient {
    public PlayerBiomeUpdate(RunArgs args) {
        super(args);
    }
    static String currentBiome;

    @SuppressWarnings("resource")
    public static void getPlayerBiome() {
        if (MinecraftClient.getInstance().player != null) {
            BlockPos playerPosition = MinecraftClient.getInstance().player.getBlockPos();
            String newBiome = MinecraftClient.getInstance().player.getWorld().getBiome(playerPosition).getKey().get().getValue().getPath();
            if (currentBiome != newBiome) {
                currentBiome = newBiome;
                TutorialMod.LOGGER.info("pics/" + currentBiome + ".png");
                //HudImageTest.drawImage("tutorialmod","pics/" + currentBiome + ".png");
                //MinecraftClient.getInstance().player.sendMessage(Text.literal("The " + currentBiome));
                HudImageTest.toggleDraw("pics/" + currentBiome + ".png");
            }
            
            
            
        }

        
    }
    







}
