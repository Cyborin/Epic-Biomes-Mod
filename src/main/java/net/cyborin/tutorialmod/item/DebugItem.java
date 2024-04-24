package net.cyborin.tutorialmod.item;

//import net.cyborin.tutorialmod.client.HudImageTest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //HudImageTest.toggleDraw();

        if (!context.getWorld().isClient()) {
            context.getWorld().playSound(null, context.getBlockPos(), SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        }

        return ActionResult.SUCCESS;
    }
}
