package com.example.talismanv01;

import java.io.Serializable;

public class GameCharacter implements Serializable {
    public String name;
    public int strength;
    public int talent;
    public int gold;
    public int life;
    public int fate;
    public String expansion;

    public GameCharacter(String name, int strength, int talent, int gold, int life, int fate, String expansion) {
        this.name = name;
        this.strength = strength;
        this.talent = talent;
        this.gold = gold;
        this.life = life;
        this.fate = fate;
        this.expansion = expansion;
    }
    public String getName() { return name; }
    public int getStrength() { return strength; }
    public int getTalent() { return talent; }
    public int getGold() { return gold; }
    public int getLife() { return life; }
    public int getFate() { return fate; }

    public String getExpansion() {
        return expansion;
    }

    // Methoden zum Erhöhen/Verringern der Werte
    public void increaseStrength() { strength++; }
    public void decreaseStrength() { if (strength > 0) strength--; }

    public void increaseTalent() { talent++; }
    public void decreaseTalent() { if (talent > 0) talent--; }

    public void increaseGold() { gold++; }
    public void decreaseGold() { if (gold > 0) gold--; }

    public void increaseLife() { life++; }
    public void decreaseLife() { if (life > 0) life--; }

    public void increaseFate() { fate++; }
    public void decreaseFate() { if (fate > 0) fate--; }



}
