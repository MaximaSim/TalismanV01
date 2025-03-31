package com.example.talismanv01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterManager {
    // Initialize the static list directly or in a static block
    private static List<GameCharacter> characters = initCharacters();

    // Static method to initialize characters
    private static List<GameCharacter> initCharacters() {
        List<GameCharacter> chars = new ArrayList<>();
        chars.add(new GameCharacter("Krieger", 4, 2, 1, 5, 3, "Base"));
        chars.add(new GameCharacter("Zauberer", 2, 5, 2, 4, 5, "Base"));
        chars.add(new GameCharacter("Dieb", 3, 4, 3, 4, 4, "Base"));
        chars.add(new GameCharacter("Priester", 3, 3, 2, 5, 5, "Base"));
        chars.add(new GameCharacter("Elf", 2, 5, 1, 4, 4, "Base"));
        return chars;
    }

    public CharacterManager() {
        // Constructor now empty since initialization happens statically
    }

    public GameCharacter getRandomCharacter() {
        Random random = new Random();
        return characters.get(random.nextInt(characters.size()));
    }

    public static List<GameCharacter> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    public List<GameCharacter> getCharactersByExpansion(String expansion) {
        List<GameCharacter> filteredCharacters = new ArrayList<>();
        for (GameCharacter character : characters) {
            if (character.getExpansion().equals(expansion)) {
                filteredCharacters.add(character);
            }
        }
        return filteredCharacters;
    }
}