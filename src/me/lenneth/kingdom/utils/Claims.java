package me.lenneth.kingdom.utils;

import me.lenneth.kingdom.Kingdom;

public class Claims {
    int kleinsteX;
    int grootsteX;
    int kleinsteZ;
    int grootsteZ;
    String name;

    @SuppressWarnings("unused")
    private Kingdom plugin;

    public Claims(int x1, int x2, int z1, int z2, String name) {
        this.name = name;
        if(x1 < x2) {
            kleinsteX = x1 -1;
            grootsteX = x2 + 1;
        }
        else {
            kleinsteX = x2 -1;
            grootsteX = x1 + 1;
        }
        if(z1 < z2) {
            kleinsteZ = z1 -1;
            grootsteZ = z2 + 1;
        }
        else {
            kleinsteZ = z2 - 1;
            grootsteZ = z1 + 1;
        }
    }

    public Claims(Kingdom plugin) {
        this.plugin = plugin;
    }

    public int getKleinsteX() {return kleinsteX;}
    public void setKleinsteX(int x) {this.kleinsteX = x;}

    public int getGrootsteX() {return grootsteX;}
    public void setGrootsteX(int x) {this.grootsteX = x;}

    public int getKleinsteZ() {return kleinsteZ;}
    public void setKleinsteZ(int z) {this.kleinsteZ = z;}

    public int getGrootsteZ() {return grootsteZ;}
    public void setGrootsteZ(int z) {this.grootsteZ = z;}

    public String getName() { return name;}

    public boolean between(int x, int z) {
        if((x > kleinsteX && x < grootsteX) && (z > kleinsteZ && z < grootsteZ)) {
            return true;
        }
        else {
            return false;
        }
    }

}
