package com.example.delifood;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView recVItem;
    private RecItemItemAdapter recItemItemAdapter;
    private List<RecItemItemAct> itemActList;

    ImageView backImgV;

    private List<RecItemItemAct> generateItemList() {
        List<RecItemItemAct> itemList = new ArrayList<>();
        itemList.add(new RecItemItemAct(R.drawable.burgercircle64px, "Double Mc Spicy","$7.50","800.00 Cal."));
        itemList.add(new RecItemItemAct(R.drawable.burgercircle64px, "Double CheeseBurger","$7.00","443.00 Cal."));
        itemList.add(new RecItemItemAct(R.drawable.burgercircle64px, "Buttermilk Crispy Chicken","$7.30","894.00 Cal."));
        itemList.add(new RecItemItemAct(R.drawable.burgercircle64px, "CheeseBurger Happy Meal","$5.50","380.00 Cal."));
        itemList.add(new RecItemItemAct(R.drawable.burgercircle64px, "Big Mac","$6.20","522.00 Cal."));

        return itemList;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recVItem = findViewById(R.id.recVItems);
        recVItem.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data for RecyclerView
        itemActList = generateItemList();

        recItemItemAdapter = new RecItemItemAdapter(this, itemActList);

        // Set adapter to RecyclerView
        recVItem.setAdapter(recItemItemAdapter);

        backImgV=findViewById(R.id.backIImgv);

        backImgV.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class) );
        });

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        recVItem.setOnClickListener(v -> {
            startActivity(new Intent(this, IndvidualItemActivity.class) );
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location=new LatLng(12.9482,77.5972);
        googleMap.addMarker(new MarkerOptions().position(location).title("Wilson Garden"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));
    }
}