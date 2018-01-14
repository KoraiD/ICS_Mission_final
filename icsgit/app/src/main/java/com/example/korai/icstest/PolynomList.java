package com.example.korai.icstest;

/**
 * Created by korai on 2018. 01. 13..
 */

public class PolynomList {

    private double polynoms[];

    public PolynomList(     String A0,
                            String A1,
                            String A2,
                            String A3,
                            String A4,
                            String A5,
                            String A6,
                            String A7,
                            String A8,
                            String A9,
                            String A10,
                            String A11,
                            String A12,
                            String A13,
                            String A14){
        polynoms = new double[15];
        polynoms[0] = Double.parseDouble(A0);
        polynoms[1] = Double.parseDouble(A1);
        polynoms[2] = Double.parseDouble(A2);
        polynoms[3] = Double.parseDouble(A3);
        polynoms[4] = Double.parseDouble(A4);
        polynoms[5] = Double.parseDouble(A5);
        polynoms[6] = Double.parseDouble(A6);
        polynoms[7] = Double.parseDouble(A7);
        polynoms[8] = Double.parseDouble(A8);
        polynoms[9] = Double.parseDouble(A9);
        polynoms[10] = Double.parseDouble(A10);
        polynoms[11] = Double.parseDouble(A11);
        polynoms[12] = Double.parseDouble(A12);
        polynoms[13] = Double.parseDouble(A13);
        polynoms[14] = Double.parseDouble(A14);

    }

    public double[] getPolynoms(){
        return polynoms;
    }

}
