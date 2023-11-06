package com.example.exercicioaula08pdm202302;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class GPSActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private TextView latitude, longitude, provedor;
    private String urlBase = "https://maps.googleapis.com/maps/api/staticmap?size=400x400&sensor=true&markers=color:red|%s,%s";
    private WebView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        provedor = (TextView) findViewById(R.id.provedor);
        map = (WebView) findViewById(R.id.map);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Listener listener = new Listener();

        long tempoAtualizacao = 0;
        float distancia = 0;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempoAtualizacao, distancia, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tempoAtualizacao, distancia, listener);
    }

    private class Listener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            String latitudeStr = String.valueOf(location.getLatitude());
            String longitudeStr = String.valueOf(location.getLongitude());

            provedor.setText(location.getProvider());
            latitude.setText(latitudeStr);
            longitude.setText(longitudeStr);

            String url = String.format(urlBase, latitudeStr, longitudeStr);
            map.loadUrl(url);
        }
        @Override
        public void onStatusChanged (String provider, int status, Bundle extras) {}
        @Override
        public void onProviderEnabled (String provider) {}
        @Override
        public void onProviderDisabled (String provider) {}
    }
}