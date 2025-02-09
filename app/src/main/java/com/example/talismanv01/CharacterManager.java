package com.example.talismanv01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CharacterManager {
        private List<Character> allCharacters;
        private Map<String, Character> assignedCharacters;

//        public CharacterManager() {
//            allCharacters = new ArrayList<>();
//            assignedCharacters = new HashMap<>();
//
//            // Beispiel-Charaktere
//            allCharacters.add(new Character("Krieger", 4, 1, 2, 5, 3));
//            allCharacters.add(new Character("Hexe", 2, 4, 3, 4, 5));
//            allCharacters.add(new Character("Dieb", 3, 3, 4, 3, 2));
//        }

        public Character assignCharacter(String playerId) {
            if (allCharacters.isEmpty()) return null;
            Random random = new Random();
            Character character = allCharacters.remove(random.nextInt(allCharacters.size()));
            assignedCharacters.put(playerId, character);
            return character;
        }

        public Character getCharacter(String playerId) {
            return assignedCharacters.get(playerId);
        }

        public Map<String, Character> getAllAssignedCharacters() {
            return assignedCharacters;
        }
    }


