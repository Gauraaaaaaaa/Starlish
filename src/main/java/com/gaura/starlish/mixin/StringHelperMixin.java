package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StringHelper.class)
public class StringHelperMixin {

    @Inject(method = "formatTicks", at = @At("RETURN"), cancellable = true)
    private static void formatTicksHMS(int ticks, CallbackInfoReturnable<String> cir) {

        if (Starlish.CONFIG.hms_potion_time) {

            int totalSeconds = MathHelper.floor((float) ticks / 20);
            int seconds = totalSeconds % 60;
            int totalMinutes = totalSeconds / 60;
            int minutes = totalMinutes % 60;
            int hours = totalMinutes / 60;

            StringBuilder sb = new StringBuilder();
            if (hours > 0) sb.append(hours).append("h");
            if (minutes > 0) sb.append(minutes).append("m");
            sb.append(seconds).append("s");

            cir.setReturnValue(sb.toString());
        }

        cir.setReturnValue("(" + cir.getReturnValue() + ")");
    }
}
