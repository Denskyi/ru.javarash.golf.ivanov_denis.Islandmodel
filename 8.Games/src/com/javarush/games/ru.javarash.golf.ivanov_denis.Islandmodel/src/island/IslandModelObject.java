package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island;

import java.util.ArrayList;

public class IslandModelObject {
    public int x;
    public int y;
    int maximumQuantity;
    String name;
    int movementSpeed;
    String APPEARANCE;

    public IslandModelObject(int x, int y,String name,int movementSpeed,String APPEARANCE,int maximumQuantity) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.movementSpeed = movementSpeed;
        this.APPEARANCE = APPEARANCE;
        this.maximumQuantity = maximumQuantity;
    }
    public void createNewObjects(int n) {
        ArrayList<IslandModelObject> islandModelObjects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            islandModelObjects.add(new IslandModelObject(
                    ((int) (Math.random() * Runner.WIDTH)),
                    ((int) (Math.random() * Runner.HEIGHT)),name,movementSpeed,APPEARANCE,maximumQuantity));
        }
        Runner.objectMap.put(name, islandModelObjects);
    }
}
