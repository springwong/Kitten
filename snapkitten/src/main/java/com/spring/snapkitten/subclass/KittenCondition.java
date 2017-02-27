package com.spring.snapkitten.subclass;

import com.spring.snapkitten.enums.KittenCompare;

/**
 * Created by spring on 26/2/2017.
 */

public class KittenCondition {
    public Integer value;
    public KittenCompare condition;

    public KittenCondition(Integer value, KittenCompare condition) {
        this.value = value;
        this.condition = condition;
    }
}
