package com.example.korai.icstest;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by korai on 17/11/25.
 */

public class MainActivity extends AppCompatActivity {

    private MapFragmentClass MF = new MapFragmentClass();
    public static TimeSeekBarChangeListener timeSeekBarChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        Toolbar tb = findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);

// Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.maplayout, MF);
        FT.commit();
        getFragmentManager().executePendingTransactions();

        LinearLayout mainLayout = findViewById(R.id.csuszkasresz);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mainLayout.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.BELOW, R.id.my_toolbar);
        mainLayout.setLayoutParams(params); //causes layout update

        // requestPermissions();


    }

    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1
                );
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1
                );
            }
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
        timeSeekBarChangeListener = SeekBarConfiguration.configureSeekbar(this.findViewById(R.id.timeseekbar));
        SpinnerConfiguration.configureSpinner(this,this.findViewById(R.id.day_spinner));
       // MF.dataBaseManager.test(this);
    }

}
