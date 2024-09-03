package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(PotionUtil.class)
public class PotionUtilMixin {

    @Inject(method = "buildTooltip(Ljava/util/List;Ljava/util/List;F)V", at = @At("HEAD"), cancellable = true)
    private static void stylishPotion(List<StatusEffectInstance> statusEffects, List<Text> list, float durationMultiplier, CallbackInfo ci) {

        ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> list2 = Lists.newArrayList();
        if (statusEffects.isEmpty()) {
            list.add(Text.translatable("effect.none").formatted(Formatting.GRAY));
        } else {
            for (StatusEffectInstance statusEffectInstance : statusEffects) {
                StatusEffect statusEffect = statusEffectInstance.getEffectType();
                MutableText mutableText = Text.translatable(statusEffectInstance.getTranslationKey()).setStyle(Style.EMPTY.withColor(statusEffect.getCategory() == StatusEffectCategory.BENEFICIAL ? Starlish.CONFIG.beneficial_potion_color : statusEffect.getCategory() == StatusEffectCategory.NEUTRAL ? Starlish.CONFIG.neutral_potion_color : Starlish.CONFIG.harmful_potion_color));
                Map<EntityAttribute, EntityAttributeModifier> map = statusEffect.getAttributeModifiers();
                if (!map.isEmpty()) {
                    for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : map.entrySet()) {
                        EntityAttributeModifier entityAttributeModifier = entry.getValue();
                        EntityAttributeModifier entityAttributeModifier2 = new EntityAttributeModifier(entityAttributeModifier.getName(), statusEffect.adjustModifierAmount(statusEffectInstance.getAmplifier(), entityAttributeModifier), entityAttributeModifier.getOperation());
                        list2.add(new Pair<>(entry.getKey(), entityAttributeModifier2));
                    }
                }
                if (statusEffectInstance.getAmplifier() >= 0) {
                    if (Starlish.CONFIG.enable_level_icon) {
                        int level = statusEffectInstance.getAmplifier() + 1;
                        mutableText.append(ScreenTexts.SPACE).append(Text.literal(Starlish.CONFIG.level_icon.getIcon().repeat(level)).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(level))));
                    }
                    else if (statusEffectInstance.getAmplifier() > 0) {
                        mutableText = Text.translatable("potion.withAmplifier", mutableText, Text.translatable("potion.potency." + statusEffectInstance.getAmplifier())).setStyle(Style.EMPTY.withColor(Starlish.getLevelColor(statusEffectInstance.getAmplifier())));
                    }
                }
                if (!statusEffectInstance.isDurationBelow(20)) {
                    if (Starlish.CONFIG.hms_potion_time) {
                        mutableText.append(ScreenTexts.SPACE).append(Text.literal(getPotionTime(statusEffectInstance.getDuration())).setStyle(Style.EMPTY.withColor(Starlish.CONFIG.potion_time_color)));
                    }
                    else {
                        mutableText = Text.translatable("potion.withDuration", mutableText, StatusEffectUtil.getDurationText(statusEffectInstance, durationMultiplier)).setStyle(Style.EMPTY.withColor(Starlish.CONFIG.potion_time_color));
                    }
                }
                list.add(mutableText);
            }
        }
        if (!list2.isEmpty()) {
            list.add(ScreenTexts.EMPTY);
            list.add(Text.translatable("potion.whenDrank").formatted(Formatting.DARK_PURPLE));
            for (Pair pair : list2) {
                EntityAttributeModifier entityAttributeModifier3 = (EntityAttributeModifier)pair.getSecond();
                double d = entityAttributeModifier3.getValue();
                double e = entityAttributeModifier3.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_BASE || entityAttributeModifier3.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_TOTAL ? entityAttributeModifier3.getValue() * 100.0 : entityAttributeModifier3.getValue();
                if (d > 0.0) {
                    list.add(Text.translatable("attribute.modifier.plus." + entityAttributeModifier3.getOperation().getId(), ItemStack.MODIFIER_FORMAT.format(e), Text.translatable(((EntityAttribute)pair.getFirst()).getTranslationKey())).formatted(Formatting.BLUE));
                    continue;
                }
                if (!(d < 0.0)) continue;
                list.add(Text.translatable("attribute.modifier.take." + entityAttributeModifier3.getOperation().getId(), ItemStack.MODIFIER_FORMAT.format(e *= -1.0), Text.translatable(((EntityAttribute)pair.getFirst()).getTranslationKey())).formatted(Formatting.RED));
            }
        }

        ci.cancel();
    }

    @Unique
    private static String getPotionTime(int ticks) {

        int totalSeconds = ticks / 20;
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        if (hours > 0) {

            if (minutes > 0 || seconds > 0) {

                return String.format("(%dh%dm%ds)", hours, minutes, seconds);
            }
            else {

                return String.format("(%dh)", hours);
            }
        }
        else if (minutes > 0) {

            if (seconds > 0) {

                return String.format("(%dm%ds)", minutes, seconds);
            }
            else {

                return String.format("(%dm)", minutes);
            }
        }
        else {

            return String.format("(%ds)", seconds);
        }
    }
}
