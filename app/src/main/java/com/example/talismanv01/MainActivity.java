package com.example.talismanv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnCreateLobby;
    private Button btnJoinLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateLobby = findViewById(R.id.btnCreateLobby);
        btnJoinLobby = findViewById(R.id.btnJoinLobby);

        // Lobby erstellen
        btnCreateLobby.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExpansionSelectionActivity.class);
            //intent.putExtra("isHost", true);
            startActivity(intent);
        });


        // Lobby beitreten
        btnJoinLobby.setOnClickListener(v -> {
            Intent intent = new Intent(this, LobbyClientActivity.class);
            startActivity(intent);  // Wechsel zur LobbyActivity
        });
    }

    // Hilfsmethode zur Generierung einer eindeutigen Spieler-ID
    private String generateRandomPlayerId() {
        // Hier könnte man z.B. eine zufällige Zahl oder UUID generieren
        return "Spieler-" + (int) (Math.random() * 1000); // Beispiel: "Spieler-123"
    }

    private void filterCharactersByExpansions(ArrayList<String> expansions) {
        List<GameCharacter> allCharacters = CharacterManager.getAllCharacters();
        List<GameCharacter> availableCharacters = new ArrayList<>();

        for (GameCharacter character : allCharacters) {
            // Basis-Spiel oder gewählte Erweiterungen erlauben
            if (character.getExpansion().equals("Base") || expansions.contains(character.getExpansion())) {
                availableCharacters.add(character);
            }
        }
    }

}



