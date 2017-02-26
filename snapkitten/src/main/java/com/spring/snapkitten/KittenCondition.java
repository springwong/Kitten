package com.spring.snapkitten;

import com.spring.snapkitten.enums.KittenCompareEnum;

/**
 * Created by spring on 26/2/2017.
 */

public class KittenCondition {
    Integer value;
    KittenCompareEnum condition;

    public KittenCondition(Integer value, KittenCompareEnum condition) {
        this.value = value;
        this.condition = condition;
    }
}
