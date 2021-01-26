package com.radello.glasses_store.domain;

public enum Model {

    PRELUGIUM (30),
    M0TT0(39),
    ASTRAL(25),
    ELYSION(66),
    MOKKA(49);

    public final int PRICE;

    Model(int PRICE) {
        this.PRICE = PRICE;
    }
}
