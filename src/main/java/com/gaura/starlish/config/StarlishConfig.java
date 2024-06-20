package com.gaura.starlish.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "starlish")
@Config.Gui.Background("minecraft:textures/block/yellow_wool.png")
public class StarlishConfig implements ConfigData {

    @ConfigEntry.Category("before_icon")
    public boolean enable_before_icon = true;

    @ConfigEntry.Category("before_icon")
    @Comment("Icon before the enchantment name (possibles entries: SWORD, PICKAXE, AXE, SHOVEL, HOE, BOW, CROSSBOW, TRIDENT, FISHING_ROD, SHIELD, SHEARS, HELMET, CHESTPLATE, LEGGINGS, BOOTS, ELYTRA, STAR, HEART, LIGHTNING, SKULL)")
    @ConfigEntry.Gui.Tooltip(count = 0)
    public EnchantmentBeforeIcon[] iconBeforeEnchantment = {

            new EnchantmentBeforeIcon("minecraft:sharpness", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:smite", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:bane_of_arthropods", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:fire_aspect", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:sweeping", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:knockback", Icon.SWORD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:looting", Icon.SWORD, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:efficiency", Icon.PICKAXE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:fortune", Icon.PICKAXE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:silk_touch", Icon.PICKAXE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:mending", Icon.PICKAXE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:unbreaking", Icon.PICKAXE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:vanishing_curse", Icon.PICKAXE, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:lure", Icon.FISHING_ROD, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:luck_of_the_sea", Icon.FISHING_ROD, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:protection", Icon.CHESTPLATE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:fire_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:blast_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:projectile_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:thorns", Icon.CHESTPLATE, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:binding_curse", Icon.CHESTPLATE, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:feather_falling", Icon.BOOTS, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:frost_walker", Icon.BOOTS, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:depth_strider", Icon.BOOTS, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:soul_speed", Icon.BOOTS, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:aqua_affinity", Icon.HELMET, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:respiration", Icon.HELMET, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:swift_sneak", Icon.LEGGINGS, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:power", Icon.BOW, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:flame", Icon.BOW, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:punch", Icon.BOW, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:infinity", Icon.BOW, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:multishot", Icon.CROSSBOW, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:piercing", Icon.CROSSBOW, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:quick_charge", Icon.CROSSBOW, 0xAAAAAA),

            new EnchantmentBeforeIcon("minecraft:loyalty", Icon.TRIDENT, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:channeling", Icon.TRIDENT, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:impaling", Icon.TRIDENT, 0xAAAAAA),
            new EnchantmentBeforeIcon("minecraft:riptide", Icon.TRIDENT, 0xAAAAAA)
    };



    @ConfigEntry.Category("enchantment_color")
    @ConfigEntry.ColorPicker
    public int normal_enchantment = 0xAAAAAA;

    @ConfigEntry.Category("enchantment_color")
    @ConfigEntry.ColorPicker
    public int treasure_enchantment = 0xAAAAAA;

    @ConfigEntry.Category("enchantment_color")
    @ConfigEntry.ColorPicker
    public int curse_enchantment = 0xFF5555;



    @ConfigEntry.Category("after_icon")
    public boolean enable_after_icon = true;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Icon level_icon = Icon.STAR;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_1_color = 0x55FF55;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_2_color = 0x55FFFF;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_3_color = 0xFF55FF;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_4_color = 0xFFFF55;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_5_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_6_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_7_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_8_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_9_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_10_color = 0x000000;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int level_max_color = 0xFFAA00;

    @ConfigEntry.Category("after_icon")
    public boolean enable_curse_icon = true;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Icon curse_icon = Icon.SKULL;

    @ConfigEntry.Category("after_icon")
    @ConfigEntry.ColorPicker
    public int curse_icon_color = 0xFF5555;
}
