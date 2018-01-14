package com.example.korai.icstest;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by korai on 17/11/25.
 */

public class StoreMarker {

    String name;
    Marker marker;

    public StoreMarker(String name, Marker marker){
        this.marker = marker;
        this.name = name;
    }
}
