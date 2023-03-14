package com.example.pe_se150294;

import static com.example.pe_se150294.ClockProvider.PROVIDER_URI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    EditText id;
    ContentValues contentValues;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id = (EditText) findViewById(R.id.edit_id);
    }

    public void deleteData(View view) {
        String productId = id.getText().toString();
        if (!productId.isEmpty()) {
            try {
                uri = Uri.parse(PROVIDER_URI);
                int rowsDeleted = getContentResolver().delete(uri, "id = ?", new String[]{productId});
                if (rowsDeleted > 0) {
                    id.setText("");
                    Toast.makeText(this,"Delete successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Please fill right id!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this,"Id must be a number!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Please fill id!", Toast.LENGTH_SHORT).show();
        }
    }
    public void backToHome(View view) {
        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
        startActivity(intent);
    }
}