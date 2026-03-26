package com.example.mytravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class FuelEfficiency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fuel_efficiency);

        Spinner spinner = findViewById(R.id.fromFuel);
        Spinner spinner1 = findViewById(R.id.toFuel);
        Spinner spinner2 = findViewById(R.id.choose);
        TextView textView = findViewById(R.id.FuelResult);
        EditText editText = findViewById(R.id.FuelInput);
        Button button = findViewById(R.id.FuelCalculate);
        TextView back = findViewById(R.id.backF);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.fuelE, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Vol, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Dist, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.choice, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter3);

        back.setOnClickListener(v -> {
            startActivity(new Intent(FuelEfficiency.this, MainActivity.class));
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spinner.setAdapter(adapter);
                        spinner1.setAdapter(adapter);
                        break;
                    case 1:
                        spinner.setAdapter(adapter1);
                        spinner1.setAdapter(adapter1);
                        break;
                    case 2:
                        spinner.setAdapter(adapter2);
                        spinner1.setAdapter(adapter2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        button.setOnClickListener(v -> {
            String choice = spinner2.getSelectedItem().toString();
            String from = spinner.getSelectedItem().toString();
            String to = spinner1.getSelectedItem().toString();
            String inputStr = editText.getText().toString();

            if (inputStr.isEmpty()) {
                textView.setText("Please enter a value");
                return;
            }

            double amount = Double.parseDouble(inputStr);
            double result;

            if (choice.equals("Fuel Efficiency")) {
                result = from.equals("Miles per gallon(mpg)") ? amount * 0.425 : amount / 0.425;
            } else if (choice.equals("Volume")) {
                result = from.equals("Gallon") ? amount * 3.785 : amount / 3.785;
            } else {
                result = from.equals("Kilometers") ? amount / 1.852 : amount * 1.852;
            }

            textView.setText(String.format("%.3f %s = %.3f %s", amount, from, result, to));
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}