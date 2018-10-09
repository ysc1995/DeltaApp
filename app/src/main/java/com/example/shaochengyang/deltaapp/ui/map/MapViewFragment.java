package com.example.shaochengyang.deltaapp.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.facebook.places.Places;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapViewFragment extends Fragment implements
        ActivityCompat.OnRequestPermissionsResultCallback,
        IMapView {

    private static final String TAG = "MapViewFragment";

    MapView mMapView;
    private GoogleMap mMap;
    ImageView img_confirm;
    IMapViewPresenter iMapViewPresenter;

    String departLati = "41.385064";
    String departLong = "2.173403";
    String arrLati = "40.416775";
    String arrLong ="-3.70379";
    String departCity = "Barcelona";
    String arrCity = "Madrid";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        iMapViewPresenter = new MapViewPresenter(this);

        final NiceSpinner fromSpinner = (NiceSpinner) rootView.findViewById(R.id.from_spinner);
        final NiceSpinner toSpinner = (NiceSpinner) rootView.findViewById(R.id.to_spinner);
        Resources res = getResources();
        final String[] cities = res.getStringArray(R.array.city_names);
        List<String> dataset = new LinkedList<>(
                Arrays.asList(cities));
        fromSpinner.attachDataSource(dataset);
        toSpinner.attachDataSource(dataset);

        img_confirm = rootView.findViewById(R.id.img_mapclick);
        img_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos1 = fromSpinner.getSelectedIndex();
                int pos2 = toSpinner.getSelectedIndex();
                departCity = cities[pos1];
                Log.d(TAG, "onClick: " + departCity);
                arrCity = cities[pos2];
                iMapViewPresenter.getLocationFromDb(departCity, arrCity);
            }
        });


        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());

        } catch (Exception e) {
            e.printStackTrace();
        }



        return rootView;
    }


    public GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3).setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void setLocation(String fromCityLati, String fromCityLong,
                            String toCityLati, String toCityLong) {
        departLati = fromCityLati;
        departLong = fromCityLong;
        arrLati = toCityLati;
        arrLong = toCityLong;

        mMapView.getMapAsync(new OnMapReadyCallback() {
                                 @Override
                                 public void onMapReady(GoogleMap googleMap) {
                                     mMap = googleMap;

        /*LatLng barcelona = new LatLng(41.385064,2.173403);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Marker in Barcelona"));

        LatLng madrid = new LatLng(40.416775,-3.70379);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));

        LatLng zaragoza = new LatLng(41.648823,-0.889085);*/

                                     //Define list to get all latlng for the route
                                     List<LatLng> path = new ArrayList();

                                     LatLng fromCity = new LatLng(Double.parseDouble(departLati),
                                             Double.parseDouble(departLong));
                                     mMap.addMarker(new MarkerOptions().position(fromCity).title("Marker in " + departCity));

                                     LatLng toCity = new LatLng(Double.parseDouble(arrLati),
                                             Double.parseDouble(arrLong));
                                     mMap.addMarker(new MarkerOptions().position(toCity).title("Marker in " + arrCity));

                                     String origin = departLati+","+departLong;
                                     String destination = arrLati+","+arrLong;


                                     DirectionsApiRequest req = DirectionsApi.getDirections(getGeoContext(), origin, destination);
                                     try {
                                         DirectionsResult res = req.await();

                                         //Loop through legs and steps to get encoded polylines of each step
                                         if (res.routes != null && res.routes.length > 0) {
                                             //Log.d("MyTag", "hi");
                                             DirectionsRoute route = res.routes[0];

                                             if (route.legs != null) {
                                                 for (int i = 0; i < route.legs.length; i++) {
                                                     DirectionsLeg leg = route.legs[i];
                                                     if (leg.steps != null) {
                                                         for (int j = 0; j < leg.steps.length; j++) {
                                                             DirectionsStep step = leg.steps[j];
                                                             if (step.steps != null && step.steps.length > 0) {
                                                                 for (int k = 0; k < step.steps.length; k++) {
                                                                     DirectionsStep step1 = step.steps[k];
                                                                     EncodedPolyline points1 = step1.polyline;
                                                                     if (points1 != null) {
                                                                         //Decode polyline and add points to list of route coordinates
                                                                         List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                                         for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                             path.add(new LatLng(coord1.lat, coord1.lng));
                                                                         }
                                                                     }
                                                                 }
                                                             } else {
                                                                 EncodedPolyline points = step.polyline;
                                                                 if (points != null) {
                                                                     //Decode polyline and add points to list of route coordinates
                                                                     List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                                     for (com.google.maps.model.LatLng coord : coords) {
                                                                         path.add(new LatLng(coord.lat, coord.lng));
                                                                     }
                                                                 }
                                                             }
                                                         }
                                                     }
                                                 }
                                             }
                                         }
                                     } catch (Exception ex) {
                                         Log.d("MyTag", ex.toString());
                                     }

                                     //Draw the polyline
                                     if (path.size() > 0) {
                                         Log.d("MyTag", "onMapReady: ");
                                         PolylineOptions opts = new PolylineOptions()
                                                 .addAll(path)
                                                 .color(Color.BLUE)
                                                 .width(5);
                                         mMap.addPolyline(opts);
                                     } else {
                                         Log.d("MyTag", "error");
                                     }

                                     mMap.getUiSettings().setZoomControlsEnabled(true);

                                     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromCity, 6));
                                 }
                             }
        );


    }
}