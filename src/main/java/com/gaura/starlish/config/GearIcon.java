package com.gaura.starlish.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class GearIcon {

    public String enchantment;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Icon icon = Icon.SWORD;
    @ConfigEntry.ColorPicker
    public int color = 0xAAAAAA;

    public GearIcon() {}

    public GearIcon(String enchantment, Icon icon, int color) {

        this.enchantment = enchantment;
        this.icon = icon;
        this.color = color;
    }
}
