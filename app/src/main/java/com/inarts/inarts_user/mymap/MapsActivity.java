package com.inarts.inarts_user.mymap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ArrayList<LatLng> points;
    private Button btn;
    private Button test;
    private MarkerOptions markerOptions;
    private Marker mMarker;
    private Polygon mPolygon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        points = new ArrayList<LatLng>();
        btn = (Button)findViewById(R.id.btn);
//        test = (Button)findViewById(R.id.test_btn);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popup = new PopupMenu(MapsActivity.this, test);
//
//                //Inflating the Popup using xml file
//                popup.getMenuInflater()
//                        .inflate(R.menu.menu_test, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(
//                                MapsActivity.this,
//                                "You Clicked : " + item.getTitle(),
//                                Toast.LENGTH_SHORT
//                        ).show();
//                        return true;
//                    }
//                });
//
//                popup.show(); //showing popup menu
//            }
//        });
//checkConnection();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * -6.280853, 106.826299
     * -6.280352, 106.826444
     * -6.280299, 106.825841
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        markerOptions = new MarkerOptions();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                markerOptions.position(latLng);
                markerOptions.snippet("Latitude :" + latLng.latitude + ", Longtitude :" + latLng.longitude);


                points.add(latLng);
                mMarker = mMap.addMarker(markerOptions);

//                polygonOptions.strokeColor(Color.RED);
//                polygonOptions.add(latLng);
//                mMap.addPolygon(polygonOptions);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, String.valueOf(marker.getPosition().latitude)+","+String.valueOf(marker.getPosition().longitude), Toast.LENGTH_LONG).show();
                LatLng mLatLng = marker.getPosition();
                Log.e("MARKER: ", mLatLng.toString());
                for (int i = 0; i<points.size();i++){
                    if (mLatLng.equals(points.get(i))){
                        points.remove(i);
                        Log.e("IF: ", points.toString());
                        mMap.clear();
//                        mPolygon.remove();

//                        polygonOptions.addAll(points);
//                        mMap.addPolygon(polygonOptions);
                    }
                }

                for (int i = 0; i<points.size();i++){
                    markerOptions.position(points.get(i));
                    mMap.addMarker(markerOptions);

                }
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.strokeColor(Color.RED);
                polygonOptions.addAll(points);
                mMap.addPolygon(polygonOptions);
                Log.e("POLIGON: ", polygonOptions.getPoints().toString());
                Log.e("POINT: ", points.toString());
                return true;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       mMap.clear();

                                       for (int i = 0; i<points.size();i++){
                                           markerOptions.position(points.get(i));
                                           mMap.addMarker(markerOptions);

                                       }

                                       PolygonOptions polygonOptions = new PolygonOptions();
                                       polygonOptions.strokeColor(Color.RED);
                                       polygonOptions.addAll(points);
                                       mMap.addPolygon(polygonOptions);
                                       SphericalUtil.computeLength(points);
                                       Log.e("Point", String.valueOf(SphericalUtil.computeArea(points)));
                                   }
                               }
        );

    }

}
