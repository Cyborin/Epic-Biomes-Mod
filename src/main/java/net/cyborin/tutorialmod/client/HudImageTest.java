package net.cyborin.tutorialmod.client;

import net.minecraft.client.render.BufferBuilder;
import org.joml.Matrix4f;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import net.cyborin.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;

public class HudImageTest {
    static float totalTickDelta = 0f;
    static float lerpedAlpha = 0f;
    static boolean fullAlpha = false;
    static boolean areWeDrawing = false;
    static String currentImage;

    public static void drawImage() {

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            int w = 0;
            int h = 0;

            if (client != null) {
                w = client.getWindow().getScaledWidth();
                h = client.getWindow().getScaledHeight();
            }

            totalTickDelta += tickDelta;

            if (areWeDrawing == false && lerpedAlpha == 0f) {
                fullAlpha = false;
                totalTickDelta = 0f;
                
            }

            if (areWeDrawing == true) {
                
                

                Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();

                buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
                buffer.vertex(positionMatrix, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
                buffer.vertex(positionMatrix, 0, h, 0).color(1f, 1f, 1f, 1f).texture(0f, 1f).next();
                buffer.vertex(positionMatrix, w, h, 0).color(1f, 1f, 1f, 1f).texture(1f, 1f).next();
                buffer.vertex(positionMatrix, w, 0, 0).color(1f, 1f, 1f, 1f).texture(1f, 0f).next();
            
                RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
                RenderSystem.setShaderTexture(0, new Identifier("tutorialmod", currentImage));

                if (lerpedAlpha != 1f && fullAlpha == false) {
                    lerpedAlpha = Math.min(1f, (float) totalTickDelta / 30);
                    RenderSystem.setShaderColor(1f, 1f, 1f, lerpedAlpha);
                    tessellator.draw();
                    TutorialMod.LOGGER.info(Float.toString(tickDelta));
                    if (lerpedAlpha == 1f) {
                        fullAlpha = true;
                        totalTickDelta = 0f;
                    }
                }
                else if (lerpedAlpha != 0f && fullAlpha == true) {
                    lerpedAlpha = Math.max(0f, 1 - (float) totalTickDelta / 100);
                    RenderSystem.setShaderColor(1f, 1f, 1f, lerpedAlpha);
                    tessellator.draw();
                    
                    //TutorialMod.LOGGER.info(Float.toString(lerpedAlpha));
                    if (lerpedAlpha == 0f) {
                        areWeDrawing = false;
                        buffer.clear();
                    }
                }
            }
        });
    }
    public static void toggleDraw(String filePath) {
        if (areWeDrawing == false) {
            currentImage = filePath;
            TutorialMod.LOGGER.info("new image queued");
            areWeDrawing = true;
            

        }
    }
}
