package com.example.pe_se150294;

import static com.example.pe_se150294.ClockProvider.PROVIDER_URI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView dataList;
    TextView txtColumnName;

    ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = findViewById(R.id.txtData);
        txtColumnName = findViewById(R.id.txtColumnName);
        Uri uri = Uri.parse(PROVIDER_URI);

        Cursor cursor = getContentResolver().query(uri,
                new String[]{"id","name", "price", }, null, null, null);
        Log.e("ee", cursor.getColumnName(2));
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            dataList.append(cursor.getString(0) +"        "+cursor.getString(1)+"        " + cursor.getString(2) +"\n");
            cursor.moveToNext();
        }


    }

    public void addProduct(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
    public void deleteProduct(View view) {
        dataList = findViewById(R.id.txtData);
        txtColumnName = findViewById(R.id.txtColumnName);

        if (!dataList.getText().toString().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Doesn't have any car to delete!", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateProduct(View view) {
        dataList = findViewById(R.id.txtData);
        txtColumnName = findViewById(R.id.txtColumnName);
        if (!dataList.getText().toString().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Doesn't have any car to update!", Toast.LENGTH_SHORT).show();
        }

    }
}