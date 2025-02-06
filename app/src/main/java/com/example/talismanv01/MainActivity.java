package com.example.talismanv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnCreateLobby;
    private Button btnJoinLobby;
    private Button btnViewCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons mit Layout-Elementen verknüpfen
        btnCreateLobby = findViewById(R.id.btnCreateLobby);
        btnJoinLobby = findViewById(R.id.btnJoinLobby);
        btnViewCharacters = findViewById(R.id.btnViewCharacters);

        // Button: Lobby erstellen (Host)
        btnCreateLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LobbyHostActivity.class);
                startActivity(intent);
            }
        });

        // Button: Lobby beitreten (Client)
        btnJoinLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LobbyClientActivity.class);
                startActivity(intent);
            }
        });

        // Button: Charaktere anzeigen
        btnViewCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CharacterListActivity.class);
                startActivity(intent);
            }
        });
    }
}

