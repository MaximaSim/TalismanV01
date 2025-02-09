package com.example.talismanv01.models;

import java.io.Serializable;

public class Character implements Serializable {
    public String name;
    public int strength;
    public int talent;
    public int gold;
    public int life;
    public int fate;

    public Character(String name, int strength, int talent, int gold, int life, int fate) {
        this.name = name;
        this.strength = strength;
        this.talent = talent;
        this.gold = gold;
        this.life = life;
        this.fate = fate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTalent() {
        return talent;
    }

    public void setTalent(int talent) {
        this.talent = talent;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFate() {
        return fate;
    }

    public void setFate(int fate) {
        this.fate = fate;
    }
}
