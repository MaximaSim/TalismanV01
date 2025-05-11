package com.example.talismanv01;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {

    // Statische Variable für alle Charaktere
    private static List<GameCharacter> allCharacters = new ArrayList<>();

    // Diese Methode fügt die Charaktere zur Liste hinzu
    static {
        allCharacters.add(new GameCharacter("Krieger", 4, 2, 1, 5, 3, "Base"));
        allCharacters.add(new GameCharacter("Zauberer", 2, 5, 2, 4, 5, "Base"));
        allCharacters.add(new GameCharacter("Wächter", 3, 3, 2, 6, 4, "City"));
        allCharacters.add(new GameCharacter("Schurke", 4, 4, 1, 3, 3, "City"));
        allCharacters.add(new GameCharacter("Druide", 3, 4, 3, 5, 2, "Forest"));
    }

    // Methode, um alle Charaktere zurückzugeben
    public static List<GameCharacter> getAllCharacters() {
        return allCharacters;
    }

    //Erweiterungsname: High=Hochland, KK=Katakomben, City=Stadt, Forest=Waldland, Drag=Die Drachen,
    //KL=Kataklysmus, Sn=Schnitter, Quell=Die heilige Quelle, Frost=Frostmark, Blood=Blutmond, Fire=Feuerlande
}

