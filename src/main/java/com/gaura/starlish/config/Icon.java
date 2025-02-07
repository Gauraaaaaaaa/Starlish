package com.gaura.starlish.config;

public enum Icon {

    SWORD("\uEFE0"),
    PICKAXE("\uEFE1"),
    AXE("\uEFE2"),
    SHOVEL("\uEFE3"),
    HOE("\uEFE4"),
    BOW("\uEFE5"),
    CROSSBOW("\uEFE6"),
    TRIDENT("\uEFE7"),
    FISHING_ROD("\uEFE8"),
    SHIELD("\uEFE9"),
    MACE("\uEFEA"),
    HELMET("\uEFEB"),
    CHESTPLATE("\uEFEC"),
    LEGGINGS("\uEFED"),
    BOOTS("\uEFEE"),
    ELYTRA("\uEFEF"),
    STAR("\uEFF0"),
    HEART("\uEFF1"),
    LIGHTNING("\uEFF2"),
    SKULL("\uEFF3");

    private final String icon;

    Icon(String icon) {

        this.icon = icon;
    }

    public String getIcon() {

        return icon;
    }

    @Override
    public String toString() {

        return icon;
    }
}
