package com.example.korai.icstest;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by korai on 17/11/26.
 */

public class SpinnerConfiguration {

    private SpinnerConfiguration() {
    }

    public static void configureSpinner(Context context, Spinner spinner) {
        Calendar c = Calendar.getInstance();
        final List<String> list = new ArrayList<String>();
        list.add("MA");
        list.add("HOLNAP");
        c.add(Calendar.DATE, 1);
        for(int i = 2; i < 7; i++) {
            c.add(Calendar.DATE, 1);
            list.add("" + c.get(Calendar.YEAR) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.DAY_OF_MONTH));
        }
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.timeSeekBarChangeListener.setDay(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
