package com.creditsapp.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 30.10.14
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
public class MyLocationListener implements LocationListener{
    public static Location imHere; // здесь будет всегда доступна самая последняя информация о местоположении пользователя.
    public static String countryNow;
    public static void SetUpLocationListener(Context context) // это нужно запустить в самом начале работы программы
    {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
            countryNow = context.getResources().getConfiguration().locale.getDisplayCountry();
        LocationListener locationListener = new MyLocationListener();

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                10,
                locationListener); // здесь можно указать другие более подходящие вам параметры

        imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        System.out.println("location my " + imHere);
    }

    @Override
    public void onLocationChanged(Location loc) {
        imHere = loc;
    }
    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}