package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import com.gaura.starlish.config.GearIcon;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private static void getStylishName(RegistryEntry<Enchantment> enchantment, int level, CallbackInfoReturnable<Text> cir) {

        MutableText enchantmentName = enchantment.value().description().copy();

        // region ENCHANTMENT
        if (enchantment.isIn(EnchantmentTags.CURSE)) {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.curse_enchantment));
        }
        else if (enchantment.isIn(EnchantmentTags.TREASURE)) {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.treasure_enchantment));
        }
        else {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.normal_enchantment));
        }
        // endregion

        // region GEAR ICON
        if (Starlish.CONFIG.enable_gear_icon) {

            MutableText before = Text.empty();

            for (GearIcon elem : Starlish.CONFIG.gear_icon_list) {

                if (enchantment.matchesId(Identifier.of(elem.enchantment))) {

                    before.append(Text.literal(elem.icon.getIcon()).setStyle(Style.EMPTY.withColor(elem.color)).append(ScreenTexts.SPACE));
                }
            }

            enchantmentName = before.append(enchantmentName);
        }
        // endregion

        // region LEVEL ICON
        if (Starlish.CONFIG.enable_level_icon) {

            MutableText after = Text.empty().append(ScreenTexts.SPACE);

            if (enchantment.isIn(EnchantmentTags.CURSE) && Starlish.CONFIG.enable_curse_icon) {

                after.append(Text.literal(Starlish.CONFIG.curse_icon.getIcon()).setStyle(Style.EMPTY.withColor(Starlish.CONFIG.curse_icon_color)));
            }
            else {

                after.append(Text.literal(Starlish.CONFIG.level_icon.getIcon().repeat(level)).setStyle(Style.EMPTY.withColor(getEnchantmentLevelColor(level, enchantment.value()))));
            }

            enchantmentName.append(after);
        }
        else if (level > 1 || enchantment.value().getMaxLevel() > 1) {

            String original = cir.getReturnValue().getString();

            String[] split = original.split(" ");

            MutableText romanLevel = Text.literal(split[split.length - 1]);

            MutableText after = Text.empty().append(ScreenTexts.SPACE);

            after.append(romanLevel).setStyle(Style.EMPTY.withColor(getEnchantmentLevelColor(level, enchantment.value())));

            enchantmentName.append(after);
        }
        // endregion

        cir.setReturnValue(enchantmentName);
    }

    @Unique
    private static int getEnchantmentLevelColor(int level, Enchantment enchantment) {

        return (level == enchantment.getMaxLevel() && Starlish.CONFIG.enable_level_max_color) ? Starlish.CONFIG.level_max_color : Starlish.getLevelColor(level);
    }
}
