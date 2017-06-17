package com.yacineboulyali.bricole;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private Marker marker;
    private GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Marker currLocationMarker;
    LatLng latLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        if (googleServicesAvailable()) {
            Toast.makeText(this, "Perfect!", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_map);
            initMap();

        } else {
            initMap();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    public void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initMap() {

        MapFragment mapFragmaent = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragmaent);
        mapFragmaent.getMapAsync(this);


    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    /*
    public void geoLocate(View view) throws IOException {

        String location = "this var will be get list of all ouvrier ==> location ";

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String locality = address.getLocality();

        Toast.makeText(this, locality, Toast.LENGTH_SHORT).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        goToLocationZoom(lat, lat, 12);

        setMarker(locality, lat, lng);
    }
*/
    private void setMarker(String locality, double lat, double lng) {
        MarkerOptions markerWorker = new MarkerOptions()
                .title(locality)
                .snippet("worker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .position(new LatLng(lat, lng));
        marker = mMap.addMarker(markerWorker);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);


        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);



        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();

            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerUser = new MarkerOptions();
            markerUser.position(latLng)
                    .title("Current Position")
                    .snippet("user")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.point_location_icon));
            currLocationMarker = mMap.addMarker(markerUser);



            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(5000); //5 seconds
            mLocationRequest.setFastestInterval(3000); //3 seconds
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }



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
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {   }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {  }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                boolean status = false;

                if(marker.getSnippet().equals("user")){
                    status= true;
                }
                else if(marker.getSnippet().equals("worker")){
                    status= false;
                }
                return status;
            }
        });
        setMarker("Yacine boulyalii",33.922082, -6.907914);
        setMarker("Yacine boulyalii",33.919877, -6.931680);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.list_user_view, null);

                    ImageView user_image_profile = (ImageView)view.findViewById(R.id.user_image_profile);
                    TextView user_name = (TextView)view.findViewById(R.id.user_name);
                    TextView user_job = (TextView) view.findViewById(R.id.user_job);
                    ImageView user_number = (ImageView) view.findViewById(R.id.user_number);
                    Log.e("INFO","Test");
                    user_name.setText(marker.getTitle());

                    user_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"tel icon clicked",Toast.LENGTH_SHORT);
                        }
                    });
                    return view;

                }
            });
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "c'ant get current Location", Toast.LENGTH_SHORT).show();
        } else {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());

           // double lat = location.getLatitude();
            //double ln = location.getLongitude();
            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll,13));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(ll).zoom(13).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 13.5f), 3000, null);

            // goToLocationZoom(lat, ln,12);
            //place marker at current position
            //mMap.clear();
            if (currLocationMarker != null) {
                currLocationMarker.remove();
            }
            latLng = new LatLng(location.getLatitude(), location.getLongitude());

            MarkerOptions markerPosition = new MarkerOptions();

            markerPosition.position(latLng)
                    .title("Current Position")
                    .snippet("user")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.point_location_icon));

            currLocationMarker = mMap.addMarker(markerPosition);

            //Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();


            //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        }
    }




}
