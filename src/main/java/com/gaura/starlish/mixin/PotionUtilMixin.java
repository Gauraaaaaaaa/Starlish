package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionUtil.class)
public class PotionUtilMixin {

    @Unique
    private static StatusEffectCategory CapturedStatusEffectCategory;

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffect;getCategory()Lnet/minecraft/entity/effect/StatusEffectCategory;"
            )
    )
    private static StatusEffectCategory captureStatusEffectCategory(StatusEffect statusEffect) {

        CapturedStatusEffectCategory = statusEffect.getCategory();

        return statusEffect.getCategory();
    }

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;",
                    ordinal = 0
            )
    )
    private static MutableText stylishPotionName(MutableText mutableText, Formatting formatting) {

        if (CapturedStatusEffectCategory != null) {

            if (CapturedStatusEffectCategory == StatusEffectCategory.BENEFICIAL) {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.beneficial_potion_color));
            }
            else if (CapturedStatusEffectCategory == StatusEffectCategory.HARMFUL) {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.harmful_potion_color));
            }
            else {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.neutral_potion_color));
            }
        }

        return mutableText.formatted(formatting);
    }

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectInstance;getAmplifier()I",
                    ordinal = 1
            )
    )
    private static int enablePotionLevelOne(StatusEffectInstance statusEffectInstance) {

        return Starlish.CONFIG.enable_level_icon ? statusEffectInstance.getAmplifier() + 1 : statusEffectInstance.getAmplifier();
    }

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;",
                    ordinal = 1
            )
    )
    private static MutableText stylishPotionLevel(String key) {

        String[] keyParts = key.split("\\.");
        int potionLevel = Integer.parseInt(keyParts[keyParts.length - 1]) + 1;

        if (Starlish.CONFIG.enable_level_icon) {

            return Text.literal(Starlish.CONFIG.level_icon.getIcon().repeat(potionLevel)).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(potionLevel)));
        }

        return Text.translatable(key).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(potionLevel)));
    }

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;",
                    ordinal = 1
            )
    )
    private static MutableText removeParentheses(String key, Object[] args) {

        return Text.translatable("%s %s", args);
    }

    @Redirect(
            method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectUtil;getDurationText(Lnet/minecraft/entity/effect/StatusEffectInstance;F)Lnet/minecraft/text/Text;"
            )
    )
    private static Text stylishPotionTime(StatusEffectInstance statusEffectInstance, float durationMultiplier) {

        return StatusEffectUtil.getDurationText(statusEffectInstance, durationMultiplier).copy().setStyle(Style.EMPTY.withColor(Starlish.CONFIG.potion_time_color));
    }
}
