package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractInventoryScreen.class)
public class AbstractInventoryScreenMixin {

    @Redirect(
            method = "drawStatusEffects(Lnet/minecraft/client/gui/DrawContext;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectUtil;getDurationText(Lnet/minecraft/entity/effect/StatusEffectInstance;F)Lnet/minecraft/text/Text;"
            )
    )
    private Text stylishPotionTimeDisplay(StatusEffectInstance statusEffectInstance, float durationMultiplier) {

        Text originalText = StatusEffectUtil.getDurationText(statusEffectInstance, durationMultiplier);

        return originalText.copy().setStyle(Style.EMPTY.withColor(Starlish.CONFIG.potion_time_color));
    }

    @Inject(method = "getStatusEffectDescription", at = @At("RETURN"), cancellable = true)
    private void stylishPotionEffectDisplay(StatusEffectInstance statusEffectInstance, CallbackInfoReturnable<Text> cir) {

        MutableText mutableText = statusEffectInstance.getEffectType().getName().copy();

        StatusEffectCategory category = statusEffectInstance.getEffectType().getCategory();

        if (category == StatusEffectCategory.BENEFICIAL) {

            mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.beneficial_potion_color));
        }
        else if (category == StatusEffectCategory.HARMFUL) {

            mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.harmful_potion_color));
        }
        else {

            mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.neutral_potion_color));
        }

        mutableText.append(ScreenTexts.SPACE);

        int potionLevel = statusEffectInstance.getAmplifier() + 1;

        if (Starlish.CONFIG.enable_level_icon) {

            mutableText.append(Text.literal(Starlish.CONFIG.level_icon.getIcon().repeat(potionLevel)).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(potionLevel))));
        }
        else {

            if (statusEffectInstance.getAmplifier() >= 1 && statusEffectInstance.getAmplifier() <= 9) {

                mutableText.append(Text.translatable("enchantment.level." + potionLevel).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(potionLevel))));
            }
        }

        cir.setReturnValue(mutableText);
    }
}
