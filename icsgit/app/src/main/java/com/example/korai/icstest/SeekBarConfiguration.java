package com.example.korai.icstest;

import android.widget.SeekBar;

/**
 * Created by korai on 17/11/25.
 */

public class SeekBarConfiguration {


    private static final int STEP = 1;
    private static final int MAX = 21;
    private static final int MIN = 8;


    private SeekBarConfiguration() {
    }

    public static TimeSeekBarChangeListener configureSeekbar(SeekBar seekBar) {
        seekBar.setMax((MAX - MIN) / STEP);
        TimeSeekBarChangeListener timeSeekBarChangeListener = new TimeSeekBarChangeListener(MIN);
        seekBar.setOnSeekBarChangeListener(timeSeekBarChangeListener);
        return timeSeekBarChangeListener;
    }
}
