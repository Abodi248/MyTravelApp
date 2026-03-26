package com.example.mytravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Temperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature);

        Spinner spinner = findViewById(R.id.fromTemp);
        Spinner spinner1 = findViewById(R.id.toTemp);
        TextView textView = findViewById(R.id.TempResult);
        EditText editText = findViewById(R.id.TempInput);
        Button button = findViewById(R.id.TempCalculate);
        TextView backT = findViewById(R.id.backT);

        backT.setOnClickListener(v -> {
            startActivity(new Intent(Temperature.this, MainActivity.class));
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.temp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.temp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);

        button.setOnClickListener(v -> {
            String from = spinner.getSelectedItem().toString();
            String to = spinner1.getSelectedItem().toString();
            String inputStr = editText.getText().toString();

            if (inputStr.isEmpty()) {
                textView.setText("Please enter a value");
                return;
            }

            double amount = Double.parseDouble(inputStr);
            double celsius;


            switch (from) {
                case "Fahrenheit": celsius = (amount - 32) * 1.8; break;
                case "Kelvin":     celsius = amount - 273.15;        break;
                default:           celsius = amount;                 break;
            }


            double result;
            switch (to) {
                case "Fahrenheit": result = (celsius * 1.8) + 32;  break;
                case "Kelvin":     result = celsius + 273.15;         break;
                default:           result = celsius;                  break;
            }

            textView.setText(String.format("%.2f %s = %.2f %s", amount, from, result, to));
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}