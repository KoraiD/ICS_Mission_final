package com.example.korai.icstest;

import android.widget.SeekBar;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.Calendar;

/**
 * Created by korai on 17/11/25.
 */

public class TimeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private double value;
    private int MIN;
    private int day;

    public void setDay(int day){
        this.day = day;
        render(value,day);
    }

    public TimeSeekBarChangeListener(int min) {
        MIN = min;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        value = MIN + progress;
        render(value, day);
    }

    private static void render(double value, int day){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, (int)value);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);


        for(Store store : Store.values()) {
            TimeBoundary timeBoundary = MapFragmentClass.dataBaseManager.getLoad(store, c);
            //set the picture according to the store's name and the timeBoundary
            for(StoreMarker storeMarker : MapFragmentClass.storeMarkers){
                if(storeMarker.name.equals(store.getName())){
                    if(timeBoundary == null){
                        storeMarker.marker.setIcon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture()));
                    }else{
                        storeMarker.marker.setIcon(BitmapDescriptorFactory.fromResource(timeBoundary.getPicture()));
                    }
                }
            }
        }
    }

}
