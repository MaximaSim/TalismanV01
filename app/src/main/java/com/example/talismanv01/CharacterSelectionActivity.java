package com.example.talismanv01;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class CharacterSelectionActivity extends BaseActivity {

    private Button btnCharacter1, btnCharacter2, btnCharacter3, btnRandomCharacter;
    private ArrayList<GameCharacter> allCharacters;
    private ArrayList<String> selectedExpansions;
    private ArrayList<GameCharacter> validCharacters;
    private ArrayList<GameCharacter> displayedCharacters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        // Erhalte die ausgewählten Erweiterungen aus dem Intent
        selectedExpansions = getIntent().getStringArrayListExtra("SELECTED_EXPANSIONS");

        // Erhalte die Liste aller Charaktere
        allCharacters = (ArrayList<GameCharacter>) CharacterManager.getAllCharacters();

        // Initialisiere die gültigen Charaktere einmal
        validCharacters = getValidCharacters();

        // Initialisiere die Buttons
        btnCharacter1 = findViewById(R.id.btnCharacter1);
        btnCharacter2 = findViewById(R.id.btnCharacter2);
        btnCharacter3 = findViewById(R.id.btnCharacter3);
        btnRandomCharacter = findViewById(R.id.btnRandomCharacter);

        // Wähle 3 zufällige Charaktere aus
        updateCharacterButtons();

        // Setze OnClickListener für den Random-Button
        btnRandomCharacter.setOnClickListener(v -> {
            // Wähle einen einzelnen zufälligen Charakter aus, der nicht bereits angezeigt wird
            GameCharacter randomCharacter = getRandomSingleCharacter();
            if (randomCharacter != null) {
                openCharacterDisplay(randomCharacter);
            } else {
                // Falls kein weiterer Charakter verfügbar ist
                Toast.makeText(this, "Keine weiteren Charaktere verfügbar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Methode zum Aktualisieren der 3 Hauptcharakterbuttons
    private void updateCharacterButtons() {
        ArrayList<GameCharacter> randomCharacters = getRandomCharacters(3);

        // Speichere die aktuell angezeigten Charaktere
        displayedCharacters.clear();
        displayedCharacters.addAll(randomCharacters);

        // Falls keine oder zu wenige Charaktere gefunden wurden
        if (randomCharacters.size() < 3) {
            // Fehlerbehandlung - z.B. Buttons deaktivieren oder Meldung anzeigen
            if (randomCharacters.size() >= 1) {
                btnCharacter1.setText(randomCharacters.get(0).getName());
                btnCharacter1.setOnClickListener(v -> openCharacterDisplay(randomCharacters.get(0)));
            } else {
                btnCharacter1.setText("Kein Charakter");
                btnCharacter1.setEnabled(false);
            }

            if (randomCharacters.size() >= 2) {
                btnCharacter2.setText(randomCharacters.get(1).getName());
                btnCharacter2.setOnClickListener(v -> openCharacterDisplay(randomCharacters.get(1)));
            } else {
                btnCharacter2.setText("Kein Charakter");
                btnCharacter2.setEnabled(false);
            }

            btnCharacter3.setText("Kein Charakter");
            btnCharacter3.setEnabled(false);

            return;
        }

        // Setze den Text der Buttons auf die Namen der zufälligen Charaktere
        btnCharacter1.setText(randomCharacters.get(0).getName());
        btnCharacter2.setText(randomCharacters.get(1).getName());
        btnCharacter3.setText(randomCharacters.get(2).getName());

        // Setze OnClickListener für die Buttons
        btnCharacter1.setOnClickListener(v -> openCharacterDisplay(randomCharacters.get(0)));
        btnCharacter2.setOnClickListener(v -> openCharacterDisplay(randomCharacters.get(1)));
        btnCharacter3.setOnClickListener(v -> openCharacterDisplay(randomCharacters.get(2)));
    }

    // Methode für einen einzelnen Zufallscharakter, der nicht bereits angezeigt wird
    private GameCharacter getRandomSingleCharacter() {
        ArrayList<GameCharacter> remainingCharacters = new ArrayList<>();

        for (GameCharacter character : validCharacters) {
            if (!displayedCharacters.contains(character)) {
                remainingCharacters.add(character);
            }
        }

        if (remainingCharacters.isEmpty()) {
            return null;
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(remainingCharacters.size());
        return remainingCharacters.get(randomIndex);
    }

    private ArrayList<GameCharacter> getRandomCharacters(int count) {
        ArrayList<GameCharacter> randomCharacters = new ArrayList<>();

        // Sicherstellen, dass wir genügend gültige Charaktere haben
        if (validCharacters.size() < count) {
            return new ArrayList<>(validCharacters);
        }

        ArrayList<GameCharacter> availableCharacters = new ArrayList<>(validCharacters);

        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            if (availableCharacters.isEmpty()) break;
            int randomIndex = rand.nextInt(availableCharacters.size());
            GameCharacter randomCharacter = availableCharacters.get(randomIndex);
            randomCharacters.add(randomCharacter);
            availableCharacters.remove(randomIndex);
        }
        return randomCharacters;
    }

    // Filtert die Charaktere, die zu den ausgewählten Erweiterungen passen
    private ArrayList<GameCharacter> getValidCharacters() {
        ArrayList<GameCharacter> validChars = new ArrayList<>();

        for (GameCharacter character : allCharacters) {
            String characterExpansion = character.getExpansion();
            if (selectedExpansions.contains(characterExpansion)) {
                validChars.add(character);
            }
        }
        return validChars;
    }

    // Methode, um zur CharacterDisplayActivity zu wechseln
    private void openCharacterDisplay(GameCharacter character) {
        Intent intent = new Intent(CharacterSelectionActivity.this, CharacterDisplayActivity.class);
        intent.putExtra("CHARACTER", character);
        startActivity(intent);
    }
}

