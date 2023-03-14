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

public class UpdateActivity extends AppCompatActivity {

    EditText productId;
    EditText productName;
    EditText productPrice;
    ContentValues contentValues;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        productId = (EditText) findViewById(R.id.edit_id);
        productName = (EditText) findViewById(R.id.edit_name);
        productPrice = (EditText) findViewById(R.id.edit_price);
    }
    public void updateData(View view) {
        String id = productId.getText().toString();
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        if (!id.isEmpty()) {
            if (!name.isEmpty()) {
                if (!price.isEmpty() && Integer.parseInt(price) > 0) {
                    try {
                        uri = Uri.parse(PROVIDER_URI);

                        contentValues = new ContentValues();
                        contentValues.put("name", name);
                        contentValues.put("price", price);
                        int rowsDeleted = getContentResolver().update(uri, contentValues, "id = ?", new String[]{id} );
                        if (rowsDeleted > 0) {
                            productId.setText("");
                            productName.setText("");
                            productPrice.setText("");
                            Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Please fill correctly!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(this, "Clock not found", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Clock price is required and must be greater than 0", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Clock name is required", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Clock ID is required!", Toast.LENGTH_LONG).show();
        }
    }
    public void backToHome(View view) {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
    }
}