package com.example.korai.icstest;

/**
 * Created by korai on 2018. 01. 13..
 */

public class ParameterSet {
    double param;
    double valid;
    double a;
    double b;

    public ParameterSet(String param, String valid, String a, String b){
        this.param = Double.parseDouble(param);
        this.valid = Double.parseDouble(valid);
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }
}
