package com.example.mytravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button currencyButton;
    Button fuelButton;
    Button temperatureButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        currencyButton = findViewById(R.id.currencyButton);
        fuelButton = findViewById(R.id.fuelButton);
        temperatureButton = findViewById(R.id.Temperature);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currencyButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Currency.class));
        });

        fuelButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FuelEfficiency.class));
        });

        temperatureButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Temperature.class));
        });

    }
}