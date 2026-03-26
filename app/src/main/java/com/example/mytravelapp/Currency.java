package com.example.mytravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;

public class Currency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currency);

        Spinner spinner = findViewById(R.id.fromCurrency);
        Spinner spinner1 = findViewById(R.id.toCurrency);
        TextView textView = findViewById(R.id.currencyResult);
        EditText editText = findViewById(R.id.currencyInput);
        Button button = findViewById(R.id.currencyCalculate);
        TextView back = findViewById(R.id.backC);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.currency, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.currency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);

        back.setOnClickListener(v -> {
            startActivity(new Intent(Currency.this, MainActivity.class));
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, Double> ratesInUSD = new HashMap<>();
                ratesInUSD.put("USD", 1.0);
                ratesInUSD.put("EUR", 0.92);
                ratesInUSD.put("GBP", 0.78);
                ratesInUSD.put("JPY", 148.50);
                ratesInUSD.put("AUD", 1.55);


                String fromCurrency = spinner.getSelectedItem().toString();
                String toCurrency = spinner1.getSelectedItem().toString();
                String inputStr = editText.getText().toString();


                if (inputStr.isEmpty()) {
                    Toast.makeText(Currency.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(inputStr);


                double inUSD = amount / ratesInUSD.get(fromCurrency);
                double result = inUSD * ratesInUSD.get(toCurrency);


                String formatted = String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency);
                textView.setText(formatted);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}