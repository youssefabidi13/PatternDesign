package com.youssefabidi.ecommerce.entity;

public enum SizeEnum {
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Double Extra Large");

    private final String description;

    private SizeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
