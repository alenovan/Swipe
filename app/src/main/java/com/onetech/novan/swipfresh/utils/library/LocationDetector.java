package com.onetech.novan.swipfresh.utils.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.okedev.layan.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class LocationDetector extends Service {

    final private String TAG = "LocationDetector";

    private Context mContext;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 60 * 1000; // 60 seconds
    private long FASTEST_INTERVAL = 10 * 1000; // 10 seconds
    private long SMALLEST_DISPLACEMENT = 10; // 10 meters

    private double latitude = 0;
    private double longitude = 0;

    public LocationDetector(Context context){
        this.mContext = context;
        this.mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.mLocationRequest = new LocationRequest();
    }

    private void setLatitude(double l) {
        this.latitude = l;
    }

    private void setLongitude(double l) {
        this.longitude = l;
    }


    // Trigger new location updates at interval
    public void getLocation() {
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setSmallestDisplacement(SMALLEST_DISPLACEMENT);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(mContext);
        settingsClient.checkLocationSettings(locationSettingsRequest);


        LocationServices.getFusedLocationProviderClient(mContext).requestLocationUpdates(
            mLocationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    onLocationChanged(locationResult.getLastLocation());
                }
            },
            Looper.myLooper());

    }

    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(mContext);
        locationClient.getLastLocation()
            .addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // GPS location can be null if GPS is switched off
                    if (location != null) {
                        onLocationChanged(location);
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
    }

    public void onLocationChanged(Location location) {
        Log.d(TAG, "Latitude " + location.getLatitude());
        Log.d(TAG, "Longitude " + location.getLongitude());

        setLatitude(location.getLatitude());
        setLongitude(location.getLongitude());
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public double getLatitude() {
        return convertNumber(latitude);
    }

    public double getLongitude() {
        return convertNumber(longitude);
    }

    public boolean isGPSEnabled() {
        boolean response = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return response;
    }

    public boolean isNetworkEnabled() {
        boolean response = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return response;
    }

    public void setGpsEnabled() {
        if ( !isGPSEnabled() || !isNetworkEnabled() ) {
            showSettingsAlert();
        }
    }

    public void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public boolean hasPermission() {
        if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle(R.string.alert_gps_not_active_title);
        alertDialog.setMessage(R.string.alert_gps_not_active);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private double convertNumber(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(decimalFormat.format(d));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
