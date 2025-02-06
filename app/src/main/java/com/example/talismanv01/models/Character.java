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
}
