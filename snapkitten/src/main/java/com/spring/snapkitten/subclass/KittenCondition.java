package com.spring.snapkitten.subclass;

import com.spring.snapkitten.enums.KittenSign;

/**
 * Created by spring on 26/2/2017.
 */

public class KittenCondition {
    public Integer value;
    public KittenSign condition;

    public KittenCondition(Integer value, KittenSign condition) {
        this.value = value;
        this.condition = condition;
    }
}
