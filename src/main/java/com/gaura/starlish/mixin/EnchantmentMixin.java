package com.gaura.starlish.mixin;

import com.gaura.starlish.Starlish;
import com.gaura.starlish.config.EnchantmentBeforeIcon;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void getStylishName(int level, CallbackInfoReturnable<Text> cir) {

        Enchantment enchantment = (Enchantment) (Object) this;

        MutableText enchantmentName = Text.translatable(enchantment.getTranslationKey());

        // region ENCHANTMENT NAME
        if (enchantment.isCursed()) {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.curse_enchantment));
        }
        else if (enchantment.isTreasure()) {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.treasure_enchantment));
        }
        else {

            enchantmentName.setStyle(Style.EMPTY.withColor(Starlish.CONFIG.normal_enchantment));
        }
        // endregion

        // region BEFORE
        if (Starlish.CONFIG.enable_before_icon) {

            MutableText before = Text.empty();

            for (EnchantmentBeforeIcon elem : Starlish.CONFIG.iconBeforeEnchantment) {

                if (enchantment == Registries.ENCHANTMENT.get(new Identifier(elem.enchantment))) {

                    before.append(Text.literal(elem.icon.getIcon()).setStyle(Style.EMPTY.withColor(elem.color)).append(ScreenTexts.SPACE));
                }
            }

            enchantmentName = before.append(enchantmentName);
        }
        // endregion

        // region AFTER
        if (Starlish.CONFIG.enable_after_icon) {

            MutableText after = Text.empty().append(ScreenTexts.SPACE);

            if (enchantment.isCursed() && Starlish.CONFIG.enable_curse_icon) {

                after.append(Text.literal(Starlish.CONFIG.curse_icon.getIcon()).setStyle(Style.EMPTY.withColor(Starlish.CONFIG.curse_icon_color)));
            }
            else if (!enchantment.isCursed()) {

                for (int i = 0; i < level; i++) {

                    after.append(Text.literal(Starlish.CONFIG.level_icon.getIcon()).setStyle(Style.EMPTY.withColor(getLevelColor(level, enchantment))));
                }
            }

            enchantmentName.append(after);
        }
        else if (level > 1 || enchantment.getMaxLevel() > 1) {

            String original = cir.getReturnValue().getString();

            String[] split = original.split(" ");

            MutableText romanLevel = Text.literal(split[split.length - 1]);

            MutableText after = Text.empty().append(ScreenTexts.SPACE);

            after.append(romanLevel).setStyle(Style.EMPTY.withColor(getLevelColor(level, enchantment)));

            enchantmentName.append(after);
        }
        // endregion

        cir.setReturnValue(enchantmentName);
    }

    private static int getLevelColor(int level, Enchantment enchantment) {

        if (level == enchantment.getMaxLevel() && Starlish.CONFIG.enable_level_max_color) {

            return Starlish.CONFIG.level_max_color;
        }
        else {

            switch (level) {

                case 1 -> {

                    return Starlish.CONFIG.level_1_color;
                }
                case 2 -> {

                    return Starlish.CONFIG.level_2_color;
                }
                case 3 -> {

                    return Starlish.CONFIG.level_3_color;
                }
                case 4 -> {

                    return Starlish.CONFIG.level_4_color;
                }
                case 5 -> {

                    return Starlish.CONFIG.level_5_color;
                }
                case 6 -> {

                    return Starlish.CONFIG.level_6_color;
                }
                case 7 -> {

                    return Starlish.CONFIG.level_7_color;
                }
                case 8 -> {

                    return Starlish.CONFIG.level_8_color;
                }
                case 9 -> {

                    return Starlish.CONFIG.level_9_color;
                }
                case 10 -> {

                    return Starlish.CONFIG.level_10_color;
                }
                default -> {

                    return 0xFFFFFF;
                }
            }
        }
    }
}
