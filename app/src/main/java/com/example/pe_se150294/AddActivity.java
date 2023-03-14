package com.example.pe_se150294;

import static com.example.pe_se150294.ClockProvider.PROVIDER_URI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText productName;
    EditText productPrice;

    ContentValues contentValues;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        productName = (EditText) findViewById(R.id.edit_name);
        productPrice = (EditText) findViewById(R.id.edit_price);
    }

    public void addData(View view) {
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        if (!name.isEmpty()) {
            if (!price.isEmpty() && Integer.parseInt(price) > 0) {
                uri = Uri.parse(PROVIDER_URI);
                contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("price", price);
                getContentResolver().insert(uri, contentValues);
                contentValues.clear();

                productName.setText("");
                productPrice.setText("");
                Toast.makeText(this, "Add successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Price of product is required and greater than 0!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Name of product is required!", Toast.LENGTH_LONG).show();
        }
    }
    public void backToHome(View view) {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}