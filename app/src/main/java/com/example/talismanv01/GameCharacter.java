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

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setTalent(int talent) {
        this.talent = talent;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setFate(int fate) {
        this.fate = fate;
    }
}
