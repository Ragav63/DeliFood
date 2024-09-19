package com.example.delifood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {
    TextView locationTv;
    private final int FINE_PERMISSION_CODE =1;
    private GoogleMap myMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    CardView itemCv1, itemCv2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        locationTv=v.findViewById(R.id.clocation);

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Fetch current location and update locationTv
        fetchCurrentLocation();

        itemCv1=v.findViewById(R.id.featureCview);
        itemCv2=v.findViewById(R.id.featureCview1);

        itemCv1.setOnClickListener(v1 -> {
            startActivity(new Intent(getActivity(), ItemActivity.class));
        });

        itemCv2.setOnClickListener(v1 -> {
            startActivity(new Intent(getActivity(), ItemActivity.class));
        });

        return v;
    }

    private void fetchCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }

        if (locationTv.isClickable()) { // Check if locationTv is clicked
            // Open Google Maps activity for location selection
            // You need to implement this part according to how you want to open Google Maps
            // For example:
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=")); // Opens Google Maps without any specific location
            startActivity(intent);
        } else {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                        double latitude=currentLocation.getLatitude();
                        double longitude=currentLocation.getLongitude();
                        String locationText = "Latitude: " + currentLocation.getLatitude() + ", Longitude: " + currentLocation.getLongitude();
                        locationTv.setText(locationText);
                        Toast.makeText(getContext(), locationText, Toast.LENGTH_LONG).show();
                        Log.d("catFrag", locationText);
                        fetchAddress(latitude,longitude);
                    } else {
                        Toast.makeText(getContext(), "Failed to get location", Toast.LENGTH_SHORT).show();
                        Log.d("catFrag", "failed to get location");
                    }
                }
            });
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==FINE_PERMISSION_CODE){
            if (grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                fetchCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Location permission denied, Please allow permission", Toast.LENGTH_SHORT).show();
                Log.d("catFrag","permission denied");
            }
        }
    }

    private void fetchAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String locationName = address.getAddressLine(0);
                locationTv.setText(locationName);
            } else {
                locationTv.setText("Location not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            locationTv.setText("Error fetching location");
        }
    }
}