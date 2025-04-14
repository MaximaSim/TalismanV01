package com.example.talismanv01;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExpansionSelectionActivity extends AppCompatActivity {

    private CheckBox cbBase, cbCity, cbForest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expansion_selection);

        cbBase = findViewById(R.id.cbBase);
        cbCity = findViewById(R.id.cbCity);
        cbForest = findViewById(R.id.cbForest);

        Button confirmButton = findViewById(R.id.btnConfirm);
        confirmButton.setOnClickListener(v -> {
            ArrayList<String> selectedExpansions = new ArrayList<>();
            if (cbBase.isChecked()) selectedExpansions.add("Base");
            if (cbCity.isChecked()) selectedExpansions.add("City");
            if (cbForest.isChecked()) selectedExpansions.add("Forest");

            if(!selectedExpansions.isEmpty()) {
                Intent intent = new Intent(this, CharacterSelectionActivity.class);
                intent.putStringArrayListExtra("SELECTED_EXPANSIONS", selectedExpansions);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Wähle eine Erweiterung",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


