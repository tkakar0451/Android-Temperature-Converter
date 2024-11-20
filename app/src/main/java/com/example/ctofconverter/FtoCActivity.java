package com.example.ctofconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctofconverter.Utils.Converters;
import com.example.ctofconverter.databinding.ActivityFtoCactivityBinding;

import java.util.Locale;

public class FtoCActivity extends AppCompatActivity {

    private static final String CONVERTED_VALUE_EXTRA_KEY = "FtoCActivity_Received_value";
    ActivityFtoCactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFtoCactivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        double fahrenheit = getIntent().getDoubleExtra(CONVERTED_VALUE_EXTRA_KEY, 0.0);
        binding.FtoCEditText.setText(String.format(Locale.ENGLISH, "%.2f", fahrenheit));


        binding.fahrenheitConvertButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = MainActivity.MainActivityIntentFactory(getApplicationContext(), convertValue());
                startActivity(intent);
                return false;
            }
        });

    }

    private double convertValue(){
        String enteredValue = binding.FtoCEditText.getText().toString();
        double valueToCovert = 0;

        if(!enteredValue.isEmpty()){
            valueToCovert = Double.parseDouble(enteredValue);
        }

        valueToCovert = Converters.fahrenheitToCelsius(valueToCovert);
        return valueToCovert;
    }

    public void displayConvertedFValue(View v){
        binding.fahrenheitConvertedValueTextView.setText(getString(R.string.degrees_celsius, convertValue()));
    }

    public static Intent fToCIntentFactory(Context context, double fahrenheitValue){
        Intent intent = new Intent(context, FtoCActivity.class);
        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, fahrenheitValue);
        return intent;
    }
}