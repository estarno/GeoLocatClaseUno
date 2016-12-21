package com.adorno.geolocatclaseuno;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Localianzdo pessoas
        MyLocationListener locationListener = new MyLocationListener();
        //El servicio de localizacion
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //parametros de toma de medidas
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,10,
                (android.location.LocationListener) locationListener);
        List<String> providers=locationManager.getAllProviders();
        //Los ContentsProviders son elementos que me permiten obtener informacion entre aplciaciones
        //mediante estos providers puede tener, por ejemplo, contactos., citas del calendario
        LocationProvider locationProvider=locationManager.getProvider(providers.get(2));


        //Chequear los permisos
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED&&
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            return;
        }
        location= locationManager.getLastKnownLocation(providers.get(0));

    }

    class  MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
    }
}
