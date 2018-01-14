package com.example.korai.icstest;

/**
 * Created by korai on 17/11/25.
 */

public enum Store {
    Corvin("TP-Elastic Bp Corvin"),
    Arena("TP-Novala Bp Aréna Plaza"),
    Europeum("TP-Novala Bp Europeum"),
    Westend_Niagara("TP-Novala Bp Westend Niagara"),
    Alle("TÜ-Allee");

    private final String name;

    Store(String name){
        this.name = name;
    }

    String getName(){return name;}
}
