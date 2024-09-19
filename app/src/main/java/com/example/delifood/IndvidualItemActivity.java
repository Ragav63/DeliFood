package com.example.delifood;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.List;

public class IndvidualItemActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView recVItem;
    private RecItemIndividualItemAdapter recItemIndividualItemAdapter;
    private List<RecItemIndividualItemAct> itemActList;
    ImageView backImgV;
    LinearLayout addtoCartlayout;

    private List<RecItemIndividualItemAct> generateItemList() {
        List<RecItemIndividualItemAct> itemList = new ArrayList<>();
        itemList.add(new RecItemIndividualItemAct( "Extra Beef Patty","$1.50"));
        itemList.add(new RecItemIndividualItemAct( "Extra Cheese Slice","$0.50"));
        itemList.add(new RecItemIndividualItemAct( "Extra Pickle","$0.10"));
        itemList.add(new RecItemIndividualItemAct( "Extra Lettuce","$0.30"));
        itemList.add(new RecItemIndividualItemAct( "Extra Onion","$1.50"));
        itemList.add(new RecItemIndividualItemAct( "No cheese slice",""));

        return itemList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_indvidual_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addtoCartlayout=findViewById(R.id.addtocartll);

        // Initialize RecyclerView
        recVItem = findViewById(R.id.recVIndividualItems);
        recVItem.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data for RecyclerView
        itemActList = generateItemList();

        recItemIndividualItemAdapter = new RecItemIndividualItemAdapter(this, itemActList);

        // Set adapter to RecyclerView
        recVItem.setAdapter(recItemIndividualItemAdapter);

        backImgV=findViewById(R.id.backIImgv);

        backImgV.setOnClickListener(v -> {
            startActivity(new Intent(this, ItemActivity.class) );
        });

        recVItem.setOnClickListener(v -> {
            startActivity(new Intent(this, IndvidualItemActivity.class) );
        });

        addtoCartlayout.setOnClickListener(v -> {
            openCheckoutDialog();
        });
    }

    private void openCheckoutDialog() {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_checkout);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) AppCompatButton orderBtn = dialog.findViewById(R.id.orderBtn);

        orderBtn.setOnClickListener(v -> {
            dialog.dismiss();
            openOrderSucessDialog();
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location=new LatLng(12.9482,77.5972);
        googleMap.addMarker(new MarkerOptions().position(location).title("Wilson Garden"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));
    }

    private void openOrderSucessDialog(){

        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ordersucess);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) AppCompatButton backtoHome = dialog.findViewById(R.id.backtoHomeBtn);

        backtoHome.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, HomeActivity.class));
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}