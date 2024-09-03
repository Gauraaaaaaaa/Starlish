package com.gaura.starlish.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "starlish")
@Config.Gui.Background("minecraft:textures/block/yellow_wool.png")
public class StarlishConfig implements ConfigData {

    // ENCHANTMENT

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.ColorPicker
    public int normal_enchantment = 0xAAAAAA;

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.ColorPicker
    public int treasure_enchantment = 0xAAAAAA;

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.ColorPicker
    public int curse_enchantment = 0xFF5555;

    @ConfigEntry.Category("enchantment")
    @Comment("If enchantments at max level will have a different color.")
    public boolean enable_level_max_color = true;

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.ColorPicker
    public int level_max_color = 0xFFAA00;

    @ConfigEntry.Category("enchantment")
    public boolean enable_curse_icon = true;

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Icon curse_icon = Icon.SKULL;

    @ConfigEntry.Category("enchantment")
    @ConfigEntry.ColorPicker
    public int curse_icon_color = 0xFF5555;

    @ConfigEntry.Category("enchantment")
    public boolean enable_gear_icon = true;

    @ConfigEntry.Category("enchantment")
    @Comment("Icon before the enchantment name (possibles entries: SWORD, PICKAXE, AXE, SHOVEL, HOE, BOW, CROSSBOW, TRIDENT, FISHING_ROD, SHIELD, SHEARS, HELMET, CHESTPLATE, LEGGINGS, BOOTS, ELYTRA, STAR, HEART, LIGHTNING, SKULL)")
    @ConfigEntry.Gui.Tooltip(count = 0)
    public GearIcon[] gear_icon_list = {

            new GearIcon("minecraft:sharpness", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:smite", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:bane_of_arthropods", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:fire_aspect", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:sweeping", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:knockback", Icon.SWORD, 0xAAAAAA),
            new GearIcon("minecraft:looting", Icon.SWORD, 0xAAAAAA),

            new GearIcon("minecraft:efficiency", Icon.PICKAXE, 0xAAAAAA),
            new GearIcon("minecraft:fortune", Icon.PICKAXE, 0xAAAAAA),
            new GearIcon("minecraft:silk_touch", Icon.PICKAXE, 0xAAAAAA),
            new GearIcon("minecraft:mending", Icon.PICKAXE, 0xAAAAAA),
            new GearIcon("minecraft:unbreaking", Icon.PICKAXE, 0xAAAAAA),
            new GearIcon("minecraft:vanishing_curse", Icon.PICKAXE, 0xAAAAAA),

            new GearIcon("minecraft:lure", Icon.FISHING_ROD, 0xAAAAAA),
            new GearIcon("minecraft:luck_of_the_sea", Icon.FISHING_ROD, 0xAAAAAA),

            new GearIcon("minecraft:protection", Icon.CHESTPLATE, 0xAAAAAA),
            new GearIcon("minecraft:fire_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new GearIcon("minecraft:blast_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new GearIcon("minecraft:projectile_protection", Icon.CHESTPLATE, 0xAAAAAA),
            new GearIcon("minecraft:thorns", Icon.CHESTPLATE, 0xAAAAAA),
            new GearIcon("minecraft:binding_curse", Icon.CHESTPLATE, 0xAAAAAA),

            new GearIcon("minecraft:feather_falling", Icon.BOOTS, 0xAAAAAA),
            new GearIcon("minecraft:frost_walker", Icon.BOOTS, 0xAAAAAA),
            new GearIcon("minecraft:depth_strider", Icon.BOOTS, 0xAAAAAA),
            new GearIcon("minecraft:soul_speed", Icon.BOOTS, 0xAAAAAA),

            new GearIcon("minecraft:aqua_affinity", Icon.HELMET, 0xAAAAAA),
            new GearIcon("minecraft:respiration", Icon.HELMET, 0xAAAAAA),

            new GearIcon("minecraft:swift_sneak", Icon.LEGGINGS, 0xAAAAAA),

            new GearIcon("minecraft:power", Icon.BOW, 0xAAAAAA),
            new GearIcon("minecraft:flame", Icon.BOW, 0xAAAAAA),
            new GearIcon("minecraft:punch", Icon.BOW, 0xAAAAAA),
            new GearIcon("minecraft:infinity", Icon.BOW, 0xAAAAAA),

            new GearIcon("minecraft:multishot", Icon.CROSSBOW, 0xAAAAAA),
            new GearIcon("minecraft:piercing", Icon.CROSSBOW, 0xAAAAAA),
            new GearIcon("minecraft:quick_charge", Icon.CROSSBOW, 0xAAAAAA),

            new GearIcon("minecraft:loyalty", Icon.TRIDENT, 0xAAAAAA),
            new GearIcon("minecraft:channeling", Icon.TRIDENT, 0xAAAAAA),
            new GearIcon("minecraft:impaling", Icon.TRIDENT, 0xAAAAAA),
            new GearIcon("minecraft:riptide", Icon.TRIDENT, 0xAAAAAA)
    };

    // POTION

    @ConfigEntry.Category("potion")
    @Comment("If the potion time should be (h/m/s) (for example: (1m30s)).")
    public boolean hms_potion_time = true;

    @ConfigEntry.Category("potion")
    @ConfigEntry.ColorPicker
    public int potion_time_color = 0xAAAAAA;

    @ConfigEntry.Category("potion")
    @ConfigEntry.ColorPicker
    public int beneficial_potion_color = 0x5555FF;

    @ConfigEntry.Category("potion")
    @ConfigEntry.ColorPicker
    public int neutral_potion_color = 0xAAAAAA;

    @ConfigEntry.Category("potion")
    @ConfigEntry.ColorPicker
    public int harmful_potion_color = 0xAA0000;

    // LEVEL ICON

    @ConfigEntry.Category("level_icon")
    public boolean enable_level_icon = true;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Icon level_icon = Icon.STAR;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_1_color = 0x55FF55;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_2_color = 0x55FFFF;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_3_color = 0xFF55FF;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_4_color = 0xFFFF55;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_5_color = 0xFFAA00;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_6_color = 0xFF5555;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_7_color = 0x000000;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_8_color = 0x000000;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_9_color = 0x000000;

    @ConfigEntry.Category("level_icon")
    @ConfigEntry.ColorPicker
    public int level_10_color = 0x000000;
}
