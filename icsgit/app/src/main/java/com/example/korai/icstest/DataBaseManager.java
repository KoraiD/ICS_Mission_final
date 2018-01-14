package com.example.korai.icstest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseManager {

    private final DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public DataBaseManager(Context context) throws IOException {
        dataBaseHelper = new DataBaseHelper(context);
        dataBaseHelper.createDataBase();
        dataBaseHelper.openDataBase();
        database = dataBaseHelper.getReadableDatabase();
    }

    public void close(){database.close();}

    public BigGiantTestResult getLoadinformation(Store store, Calendar time, String service){
        //Create a list of the paramSet table's column names
        List<TableColumn> paramSetColumns = new ArrayList<>();
        paramSetColumns.add(ParamSetColumn.STORE);
        paramSetColumns.add(ParamSetColumn.SERVICE);
        paramSetColumns.add(ParamSetColumn.DAY_OF_THE_WEEK);
        paramSetColumns.add(ParamSetColumn.PARAM);
        paramSetColumns.add(ParamSetColumn.VALID);
        paramSetColumns.add(ParamSetColumn.A);
        paramSetColumns.add(ParamSetColumn.B);
        paramSetColumns.add(ParamSetColumn.QUEUE);
        //create a list of the polinomEgyutthatok table's column names
        List<TableColumn> polinomEgyutthatokColumns = new ArrayList<>();
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.STORE);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.DAY_OF_THE_WEEK);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A0);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A1);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A2);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A3);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A4);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A5);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A6);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A7);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A8);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A9);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A10);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A11);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A12);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A13);
        polinomEgyutthatokColumns.add(PolinomEgyutthatokColumn.A14);

        //get the polynoms from the table according to the store and the day of the week
        Cursor egyutthatoResult = database.rawQuery(
                selectColumnsFromTable(polinomEgyutthatokColumns,
                        Table.POLINOMEGYUTTHATOKTABLE) + whereClauseBuilderPolinom(store, time),
                new String[0]);
        Map<String, List<String>> egyutthatok = getDataFromCursor(egyutthatoResult);
        //collect the polynoms in the first row of the filtered table
        PolynomList myPolynoms = new PolynomList(
                egyutthatok.get(PolinomEgyutthatokColumn.A0.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A1.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A2.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A3.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A4.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A5.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A6.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A7.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A8.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A9.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A10.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A11.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A12.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A13.getColumnName()).get(0),
                egyutthatok.get(PolinomEgyutthatokColumn.A14.getColumnName()).get(0));

        //get the time in days (with the fractional part)
        double timeInDaysWithFractionalPart = getDayOfWeekWithTimeFractional(time);

        //get the queue size according to the time and the corresponding polynoms
        int queueSize = new QueueSize(timeInDaysWithFractionalPart, myPolynoms.getPolynoms()).rowSize;


        //get the parameter set from the table according to the store, the day of the week, service and queue size
        Cursor paramSets = database.rawQuery(
                selectColumnsFromTable(paramSetColumns,
                        Table.PARAMSETTABLE) + whereClauseBuilderParamset(store, service, time),
                new String[0]);
        Map<String, List<String>> paramSetsResult = getDataFromCursor(paramSets);

        ParameterSet myParamSet = null;
        Integer defaultRow = null;
        for(int i = 0; i < paramSetsResult.get(ParamSetColumn.QUEUE.getColumnName()).size(); i ++) {
            String queueVal = paramSetsResult.get(ParamSetColumn.QUEUE.getColumnName()).get(i);
            if (queueVal.contains("default")) {
                defaultRow = i;
            } else if (Integer.parseInt(queueVal) == queueSize) {
                //collect the parameter set in the corresponding row of the filtered table
                myParamSet = new ParameterSet(
                        paramSetsResult.get(ParamSetColumn.PARAM.getColumnName()).get(i),
                        paramSetsResult.get(ParamSetColumn.VALID.getColumnName()).get(i),
                        paramSetsResult.get(ParamSetColumn.A.getColumnName()).get(i),
                        paramSetsResult.get(ParamSetColumn.B.getColumnName()).get(i));
                break;
            }
        }
        if(myParamSet == null){
            //collect the parameter set in the default row of the filtered table
            myParamSet = new ParameterSet(
                    paramSetsResult.get(ParamSetColumn.PARAM.getColumnName()).get(defaultRow),
                    paramSetsResult.get(ParamSetColumn.VALID.getColumnName()).get(defaultRow),
                    paramSetsResult.get(ParamSetColumn.A.getColumnName()).get(defaultRow),
                    paramSetsResult.get(ParamSetColumn.B.getColumnName()).get(defaultRow));
        }

        return new BigGiantTestResult(myParamSet.param, myParamSet.a, myParamSet.b);
    }

    private double getDayOfWeekWithTimeFractional(Calendar c){
        double dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        double hour = c.get(Calendar.HOUR_OF_DAY);
        double minute = c.get(Calendar.MINUTE);
        double second = c.get(Calendar.SECOND);
        double milli = c.get(Calendar.MILLISECOND);
        return hour/24 + minute/60/24 + second/60/60/24 + milli/1000/60/60/24;
    }

    private Map<String, List<String>> getDataFromCursor(Cursor cursor){
        Map<String, List<String>> data = new HashMap<>();
        cursor.moveToFirst();
        for (String columnName : cursor.getColumnNames()) {
            data.put(columnName, new ArrayList<>());
        }
        while(!cursor.isAfterLast()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                data.get(cursor.getColumnName(i)).add(cursor.getString(i));
            }
            cursor.moveToNext();
        }
        return data;
    }

    private String whereClauseBuilderPolinom(Store store, Calendar time){
        return " WHERE " +
                PolinomEgyutthatokColumn.STORE.getColumnName() + " = \"" + store.getName() + "\""
                + " AND " + PolinomEgyutthatokColumn.DAY_OF_THE_WEEK.getColumnName() + " = \"" + Integer.toString((time.get(Calendar.DAY_OF_WEEK) + 6) % 7 +1) + "\"";
    }

    private String whereClauseBuilderParamset(Store store, String service, Calendar time){
        return " WHERE " +
                ParamSetColumn.STORE.getColumnName() + " = \"" + store.getName() + "\""
                + " AND " + ParamSetColumn.DAY_OF_THE_WEEK.getColumnName() + " = \"" + Integer.toString((time.get(Calendar.DAY_OF_WEEK) + 6) % 7 +1) + "\""
                + " AND " + ParamSetColumn.SERVICE.getColumnName() + " = \"" + service + "\"";
    }


    private String selectColumnsFromTable(List<TableColumn> dataBaseColumns, Table table){
        String sql = "SELECT ";
        for(TableColumn dataBaseColumn : dataBaseColumns){
            sql += "\"" + dataBaseColumn.getColumnName() + "\", ";
        }
        return sql.substring(0, sql.length()-2) + " FROM " + table.getName();
    }


    public TimeBoundary getLoad(Store store, Calendar calendar){
        BigGiantTestResult loadInformantion = getLoadinformation(store, calendar, "default");
        return loadInformantion != null ? getRelatedTimeBoundary(loadInformantion) : null;
    }

    private TimeBoundary getRelatedTimeBoundary(BigGiantTestResult loadInformantion) {
        for(TimeBoundary timeBoundary : TimeBoundary.values()){
            if(timeBoundary.getFrom() <= loadInformantion.atlag && timeBoundary.getTo() > loadInformantion.atlag){
                return timeBoundary;
            }
        }
        return null;
    }

/*
    public void test(Context context){
        try {
            String filePath =  Environment.getExternalStorageDirectory().getPath().toString() + "/output123.txt";
            File f = new File(filePath);
            PrintWriter out = new PrintWriter(new FileWriter(filePath));
            out.write("store,day,peopleinqueue,timebound,max,min,szoras,varh,perc,exists\n");

            for(Store store : Store.values()) {
                for (int i = 1; i < 15; i++) {
                    for (int y = 0; y < 20; y++) {
                        for (int day = 1; day < 8; day++) {
                            Calendar c = Calendar.getInstance();
                            c.set(2017,11,26 - 7 + day);
                            BigGiantTestResult r = MapFragmentClass.dataBaseManager.getLoadinformation(store, c, y, i);
                            if(r != null) {
                                out.write(store.getName() + "," + day + "," + y + "," + i + "," + r.max + "," + r.min + "," + r.szoras + "," + r.varh + "," + r.perc + ",true\n");
                            }else{out.write(store.getName() + "," + day + "," + y + "," + i + ",null,null,null,null,null" + ",false\n");}
                        }
                        out.flush();
                    }
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
