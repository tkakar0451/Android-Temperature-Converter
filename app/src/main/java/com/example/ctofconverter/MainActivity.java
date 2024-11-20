package com.example.ctofconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctofconverter.Utils.Converters;
import com.example.ctofconverter.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String CONVERTED_VALUE_EXTRA_KEY = "MainActivity_Converted_value_double";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        double celsius = getIntent().getDoubleExtra(CONVERTED_VALUE_EXTRA_KEY, 0.0);
        binding.CelsiusValueEditTextNumberSigned.setText(String.format(Locale.ENGLISH, "%.2f", celsius));

        binding.CtoFTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hey... it worked!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.celsiusConvertButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = FtoCActivity.fToCIntentFactory(getApplicationContext(), convertValue());
                startActivity(intent);
                return false;
            }
        });
    }

    private double convertValue(){
        String enteredValue = binding.CelsiusValueEditTextNumberSigned.getText().toString();
        double valueToCovert = 0;

        if(!enteredValue.isEmpty()){
            valueToCovert = Double.parseDouble(enteredValue);
        }

        valueToCovert = Converters.celsiusToFahrenheit(valueToCovert);
        return valueToCovert;
    }

    public void displayConvertedValue(View v){
        binding.celsiusConvertedValueTextView.setText(getString(R.string.degrees_fahrenheit, convertValue()));
    }

    public static Intent MainActivityIntentFactory(Context context, double receivedValue){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, receivedValue);
        return intent;
    }
}