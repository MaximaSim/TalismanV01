package com.example.talismanv01;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CharacterDisplayActivity extends AppCompatActivity {

    private GameCharacter character;

    // Attribute Control Instanzen für jedes Attribut
    private AttributeControl strengthControl;
    private AttributeControl talentControl;
    private AttributeControl goldControl;
    private AttributeControl lifeControl;
    private AttributeControl fateControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);

        // Hole den Character von der vorherigen Activity
        character = (GameCharacter) getIntent().getSerializableExtra("CHARACTER");

        // Initialisiere TextView für den Charakternamen
        if (character != null) {
            // Wenn das Objekt nicht null ist, setze die Views
            TextView tvName = findViewById(R.id.tvCharacterName);
            tvName.setText(character.getName());

            // Initialisiere die Container für die AttributeControls
            ViewGroup strengthContainer = findViewById(R.id.strengthContainer);
            ViewGroup talentContainer = findViewById(R.id.talentContainer);
            ViewGroup goldContainer = findViewById(R.id.goldContainer);
            ViewGroup lifeContainer = findViewById(R.id.lifeContainer);
            ViewGroup fateContainer = findViewById(R.id.fateContainer);

            // Initialisiere AttributeControls für jedes Attribut
            strengthControl = new AttributeControl(this, strengthContainer, "Stärke", character.getStrength(), character.getStrength());
            talentControl = new AttributeControl(this, talentContainer, "Talent", character.getTalent(), character.getTalent());
            goldControl = new AttributeControl(this, goldContainer, "Gold", character.getGold(), 0);
            lifeControl = new AttributeControl(this, lifeContainer, "Leben", character.getLife(), 0);
            fateControl = new AttributeControl(this, fateContainer, "Schicksal", character.getFate(), 0);
        } else {
            // Fehlerbehandlung, falls das Objekt null ist
            Toast.makeText(this, "Kein Charakter ausgewählt", Toast.LENGTH_SHORT).show();
            finish();  // Activity schließen, falls kein Charakter übergeben wurde
        }
    }

    //TODO Bilder einfügen
}



