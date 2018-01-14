package com.example.korai.icstest;

/**
 * Created by korai on 2018. 01. 13..
 */

public enum PolinomEgyutthatokColumn implements TableColumn{
    //ID("id"),
    STORE("üzlet"),
    DAY_OF_THE_WEEK("hét_napja"),
    A0("a0"),
    A1("a1"),
    A2("a2"),
    A3("a3"),
    A4("a4"),
    A5("a5"),
    A6("a6"),
    A7("a7"),
    A8("a8"),
    A9("a9"),
    A10("a10"),
    A11("a11"),
    A12("a12"),
    A13("a13"),
    A14("a14");

    private final String columnName;

    PolinomEgyutthatokColumn(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
