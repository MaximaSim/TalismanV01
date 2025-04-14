package com.example.talismanv01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameButton = findViewById(R.id.btnStartGame);
        startGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExpansionSelectionActivity.class);
            startActivity(intent);
        });
    }
}




