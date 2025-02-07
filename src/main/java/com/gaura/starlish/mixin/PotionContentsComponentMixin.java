package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(PotionContentsComponent.class)
public class PotionContentsComponentMixin {

    @Unique
    private static RegistryEntry<StatusEffect> capturedRegistryEntry;

    @Inject(
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/entry/RegistryEntry;value()Ljava/lang/Object;",
                    ordinal = 0
            )
    )
    private static void captureRegistryEntry(Iterable<StatusEffectInstance> effects, Consumer<Text> textConsumer, float durationMultiplier, float tickRate, CallbackInfo ci, @Local RegistryEntry<StatusEffect> registryEntry) {

        capturedRegistryEntry = registryEntry;
    }

    @Redirect(
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;",
                    ordinal = 0
            )
    )
    private static MutableText stylishPotionName(MutableText mutableText, Formatting formatting) {

        if (capturedRegistryEntry != null) {

            StatusEffectCategory category = capturedRegistryEntry.value().getCategory();

            if (category == StatusEffectCategory.BENEFICIAL) {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.beneficial_potion_color));
            }
            else if (category == StatusEffectCategory.HARMFUL) {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.harmful_potion_color));
            }
            else {

                return mutableText.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.neutral_potion_color));
            }
        }

        return mutableText.formatted(formatting);
    }

    @Redirect(
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectInstance;getAmplifier()I",
                    ordinal = 1
            )
    )
    private static int enablePotionLevelOne(StatusEffectInstance instance) {

        return Starlish.CONFIG.enable_level_icon ? instance.getAmplifier() + 1 : instance.getAmplifier();
    }

    @Redirect(
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
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
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
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
            method = "buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectUtil;getDurationText(Lnet/minecraft/entity/effect/StatusEffectInstance;FF)Lnet/minecraft/text/Text;"
            )
    )
    private static Text stylishPotionTime(StatusEffectInstance statusEffectInstance, float durationMultiplier, float tickRate) {

        return StatusEffectUtil.getDurationText(statusEffectInstance, durationMultiplier, tickRate).copy().setStyle(Style.EMPTY.withColor(Starlish.CONFIG.potion_time_color));
    }
}
