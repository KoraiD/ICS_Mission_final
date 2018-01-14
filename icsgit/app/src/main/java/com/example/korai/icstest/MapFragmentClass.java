// MapsActivity.java
package com.example.korai.icstest;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public final class MapFragmentClass extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MapView mapView;

    public static DataBaseManager dataBaseManager;
    public static List<StoreMarker> storeMarkers = new ArrayList<>();

    private final LatLng ALLEE = new LatLng(47.4743725D, 19.0485831D);
    private final LatLng ARENA = new LatLng(47.498224D, 19.0899307D);
    private final LatLng WESTEND2 = new LatLng(47.512823D, 19.058547D); //Jokai
    private final LatLng CORVIN = new LatLng(47.4860433D, 19.0745666D);
    private final LatLng EURO = new LatLng(47.4960124D, 19.06997D);


    public void processMap(Context context, View view, Bundle savedInstanceState) {
        if (mMap == null) {
            initDataBase(context);
            mapView = view.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        processMap(this.getContext(), view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    public void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    public void initDataBase(Context context) {
        try {
            dataBaseManager = new DataBaseManager(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Intrinsics.checkParameterIsNotNull(googleMap, "googleMap");

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.4877409D, 19.0591587D), 13.0F));

        storeMarkers.add(new StoreMarker(Store.Alle.getName(), googleMap.addMarker((new MarkerOptions()).position(this.ALLEE).title("Allee").snippet("Útvonalhoz kattints az alsó ikonra").icon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture())).anchor(0.5F, 1.0F))));
        storeMarkers.add(new StoreMarker(Store.Westend_Niagara.getName(), googleMap.addMarker((new MarkerOptions()).position(this.WESTEND2).title("Westend").snippet("Útvonalhoz kattints az alsó ikonra").icon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture())).anchor(0.5F, 1.0F))));
        storeMarkers.add(new StoreMarker(Store.Corvin.getName(), googleMap.addMarker((new MarkerOptions()).position(this.CORVIN).title("Corvin").snippet("Útvonalhoz kattints az alsó ikonra").icon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture())).anchor(0.5F, 1.0F))));
        storeMarkers.add(new StoreMarker(Store.Europeum.getName(), googleMap.addMarker((new MarkerOptions()).position(this.EURO).title("Europeum").snippet("Útvonalhoz kattints az alsó ikonra").icon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture())).anchor(0.5F, 1.0F))));
        storeMarkers.add(new StoreMarker(Store.Arena.getName(), googleMap.addMarker((new MarkerOptions()).position(this.ARENA).title("Aréna").snippet("Útvonalhoz kattints az alsó ikonra").icon(BitmapDescriptorFactory.fromResource(TimeBoundary.CLOSED.getPicture())).anchor(0.5F, 1.0F))));

        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) mMap.setMyLocationEnabled(true);
        else {
// Show rationale and request permission.
        }
    }

    public void closeDatabase() {
        dataBaseManager.close();
    }

}
