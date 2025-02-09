package com.example.talismanv01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LobbyActivity extends AppCompatActivity {

    // Views
    private TextView currentPlayerCharacterName;
    private TextView currentPlayerStrength;
    private TextView currentPlayerTalent;
    private TextView currentPlayerGold;
    private TextView currentPlayerHealth;
    private TextView currentPlayerFate;
    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.lobby_activity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    // Initialisiere die Views
        currentPlayerCharacterName = findViewById(R.id.currentPlayerCharacterName);
        currentPlayerStrength = findViewById(R.id.currentPlayerStrength);
        currentPlayerTalent = findViewById(R.id.currentPlayerTalent);
        currentPlayerGold = findViewById(R.id.currentPlayerGold);
        currentPlayerHealth = findViewById(R.id.currentPlayerHealth);
        currentPlayerFate = findViewById(R.id.currentPlayerFate);
        startGameButton = findViewById(R.id.startGameButton);

        setCurrentPlayerCharacterData();

        // Button Listener für den Spielstart
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

     //Methode zum Setzen der statischen Charakterdaten des aktuellen Spielers
    private void setCurrentPlayerCharacterData() {
        // Hier werden statische Werte gesetzt. Diese könnten später dynamisch kommen
        currentPlayerCharacterName.setText("Charakter: Krieger");
        currentPlayerStrength.setText("Stärke: 4");
        currentPlayerTalent.setText("Talent: 1");
        currentPlayerGold.setText("Gold: 10");
        currentPlayerHealth.setText("Leben: 20");
        currentPlayerFate.setText("Schicksal: 3");
    }

    // Methode, um das Spiel zu starten (dies könnte später durch Logik zum Starten des Spiels ersetzt werden)
    private void startGame() {
        // Hier könnte die Spiel-Start-Logik implementiert werden, z.B. Verbindungsaufbau oder Spiellogik
        Toast.makeText(LobbyActivity.this, "Spiel gestartet!", Toast.LENGTH_SHORT).show();

        // Beispiel: Nach dem Spielstart könnte die Aktivität zu einer anderen wechseln
        // Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
        // startActivity(intent);
    }
}

