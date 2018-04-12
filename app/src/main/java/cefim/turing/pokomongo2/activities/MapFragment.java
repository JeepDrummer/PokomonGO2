package cefim.turing.pokomon.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cefim.turing.pokomon.R;
import cefim.turing.pokomon.interfaces.APICallback;
import cefim.turing.pokomon.models.Pokomon;
import cefim.turing.pokomon.utils.UtilsAPI;
import cefim.turing.pokomon.utils.UtilsPreferences;
import okhttp3.Response;

public class MapFragment extends Fragment implements OnMapReadyCallback, APICallback {

    private static final int INTERVAL_REQUEST = 30000;

    private Date mLastUpdatedDate;
    private List<Pokomon> mPokomonList;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();

    private Location mCurrentLocation;

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private Handler mHandler;
    private Context mContext;

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("lol","onCreate Frag");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("lol","onCreateView Frag");
        Log.d("lol", "MapFragment : onCreateView");

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mHandler = new Handler();
        mPokomonList = new ArrayList<>();

        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        Log.d("lol","onMapReady Frag");

		// TODO : ...
    }

    public void onLocationChanged(Location location) {
        Log.d("lol","onLocationChanged Frag");

        Log.d("lol", "---> onLocationChange : " + location.toString());

        if (isTimeToUpdate()) {

            Log.d("lol", "---> onLocationChange : On y va");

            Object[] paramsValues = {mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()};
            String params = String.format(UtilsAPI.URL_MAP_PARAMS, paramsValues);

            try {
                UtilsAPI.getInstance().post(this, UtilsAPI.URL_MAP, params, UtilsPreferences.getPreferences(getContext()).getString("token"));
            } catch (IOException e) {
                //showProgress(false);
                //Toast.makeText(this, "Erreur...", Toast.LENGTH_SHORT).show();
            }
            //Object[] paramsValues = {String.valueOf(mCurrentLocation.getLatitude()), String.valueOf(mCurrentLocation.getLongitude())};
            // Object[] paramsValues = {String.valueOf(47.397026), String.valueOf(0.689860)};

        } else {
            Log.d("lol", "---> onLocationChange : On attend encore un peu");
        }
    }


    /**
     * @return true s'il y a plus de INTERVAL_REQUEST (30s) depuis la dernière mise à jour
     */
    public boolean isTimeToUpdate() {

        Log.d("lol","isTimeToUpdate Frag");
        Date currentDate = new Date(System.currentTimeMillis());

        return false;
    }

    private void updateMapMarkers() {
        Log.d("lol","updateMapMarkers Frag");
    	// TODO : ...
    	// Indice
		// Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(pokomon.mLat, pokomon.mLng)).title(pokomon.mName).icon(BitmapDescriptorFactory.fromResource(Utils.TAB_IMAGE_POKOMON_S[pokomon.mPicture])));
   }


    @Override
    public void successCallback(final Response response) {

        Log.d("lol","successCallback Frag");

        final String stringJson = response.body().string();

        Log.d("lol", "---------> Success - " + stringJson);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                /*try {
                    
                    // TODO

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    @Override
    public void failCallback(final Response response) {

        Log.d("lol","failCallback Frag");

        if (response != null) {
            final int g = response.code();

            Log.d("lol", "---------> Fail - " + g + " - " + response.message());

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                
					//TODO : ?               
                }
            });
        }
    }
}
