package com.example.korai.icstest;

/**
 * Created by korai on 2018. 01. 13..
 */

public enum ParamSetColumn implements TableColumn{
    //ID("id"),
    STORE("üzlet"),
    SERVICE("szolgáltatás"),
    DAY_OF_THE_WEEK("hét_napja"),
    PARAM("param"),
    VALID("valid"),
    A("a"),
    B("b"),
    QUEUE("sor");


    private final String columnName;

    ParamSetColumn(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
