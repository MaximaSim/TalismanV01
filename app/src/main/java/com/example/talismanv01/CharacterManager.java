package com.example.talismanv01;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {

    // Statische Variable für alle Charaktere
    private static List<GameCharacter> allCharacters = new ArrayList<>();

    // Diese Methode fügt die Charaktere zur Liste hinzu
    static {
        allCharacters.add(new GameCharacter("Krieger", 4, 2, 1, 5, 3, "Base"));
        allCharacters.add(new GameCharacter("Magier", 2, 5, 1, 4, 3, "Base"));
        allCharacters.add(new GameCharacter("Barde", 2, 4, 1, 4, 5, "Base"));
        allCharacters.add(new GameCharacter("Assassine", 3, 3, 1, 4, 3, "Base"));
        allCharacters.add(new GameCharacter("Zwerg", 3, 3, 1, 5, 5, "Base"));
        allCharacters.add(new GameCharacter("Zauberin", 2, 4, 1, 4, 3, "Base"));
        allCharacters.add(new GameCharacter("Dieb", 3, 3, 1, 4, 2, "Base"));
        allCharacters.add(new GameCharacter("Wahrsagerin", 2, 4, 1, 4, 2, "Base"));
        allCharacters.add(new GameCharacter("Troll", 6, 1, 1, 6, 1, "Base"));
        allCharacters.add(new GameCharacter("Priester", 2, 4, 1, 4, 5, "Base"));
        allCharacters.add(new GameCharacter("Mönch", 2, 3, 1, 4, 5, "Base"));
        allCharacters.add(new GameCharacter("Elf", 3, 4, 1, 4, 3, "Base"));
        allCharacters.add(new GameCharacter("Ghul", 2, 4, 1, 4, 4, "Base"));
        allCharacters.add(new GameCharacter("Druide", 2, 4, 1, 4, 4, "Base"));

        allCharacters.add(new GameCharacter("Kleine Fee", 1, 5, 1, 4, 1, "High"));
        allCharacters.add(new GameCharacter("Vampirgräfin", 3, 3, 1, 5, 3, "High"));
        allCharacters.add(new GameCharacter("Hochländer", 4, 2, 1, 4, 2, "High"));
        allCharacters.add(new GameCharacter("Gaunerin", 3, 3, 1, 4, 4, "High"));
        allCharacters.add(new GameCharacter("Walküre", 3, 4, 1, 4, 3, "High"));
        allCharacters.add(new GameCharacter("Alchimist", 2, 4, 1, 4, 3, "High"));
    }

    // Methode, um alle Charaktere zurückzugeben
    public static List<GameCharacter> getAllCharacters() {
        return allCharacters;
    }

    //Erweiterungsname: High=Hochland, KK=Katakomben, City=Stadt, Forest=Waldland, Drag=Die Drachen,
    //KL=Kataklysmus, Sn=Schnitter, Quell=Die heilige Quelle, Frost=Frostmark, Blood=Blutmond, Fire=Feuerlande
}

