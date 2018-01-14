package com.example.korai.icstest;

/**
 * Created by korai on 17/11/25.
 */

public enum  Table {
    PARAMSETTABLE("paramSet"),
    POLINOMEGYUTTHATOKTABLE("polinomegyutthatok");

    final String name;

    Table(String name){this.name = name;}

    public String getName() {
        return name;
    }
}
