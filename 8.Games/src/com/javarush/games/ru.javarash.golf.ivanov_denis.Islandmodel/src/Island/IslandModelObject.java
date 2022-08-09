package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island;

public class IslandModelObject {

    public int x;
    public int y;
    public double weight;
    public int maximumQuantityPerCell;
    public int movementSpeed;
    public double kilogramsFoodNeeded;
    public boolean alive;
    public boolean busy;

    public IslandModelObject(int x, int y,double weight,int maximumQuantityPerCell,int movementSpeed,double kilogramsFoodNeeded,boolean alive,boolean busy) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.maximumQuantityPerCell = maximumQuantityPerCell;
        this.movementSpeed = movementSpeed;
        this.kilogramsFoodNeeded = kilogramsFoodNeeded;
        this.alive = alive;
        this.busy = busy;
    }
}
