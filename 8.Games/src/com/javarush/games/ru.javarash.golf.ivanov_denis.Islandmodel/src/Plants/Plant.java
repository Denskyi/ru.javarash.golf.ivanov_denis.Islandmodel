package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Plants;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.Runner;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    double weight = 1;//Вес одного растения, кг
    private int maximum_quantity_per_cell = 200;//Максимальное количество растений на одной клетке
    private static final String APPEARANCE = "\uD83C\uDF40";//значёк клевера
    private List<IslandModelObject> islandModelObjects = new ArrayList<>();

    public void draw(Game game) {
        for (IslandModelObject islandModelObject : islandModelObjects) {
            game.setCellValueEx(islandModelObject.x, islandModelObject.y,Color.DARKSEAGREEN, APPEARANCE, Color.GREEN,35 );
        }
    }
    public void reproduce(int maximum_quantity_per_cell) {
        int new_x = ((int) (Math.random() * Runner.WIDTH));
        int new_y = ((int) (Math.random() * Runner.HEIGHT));
        int total = 0;
        for (IslandModelObject islandModelObject : islandModelObjects) {
            if (islandModelObject.x == new_x && islandModelObject.y == new_y) {
                total++;
            }
        }
        if (total < maximum_quantity_per_cell) {
            islandModelObjects.add(new IslandModelObject(new_x, new_y, 1, maximum_quantity_per_cell, 0, 0, true, false));
        }
    }
    public int howMuchFood(int x, int y) {
        int n = 0;
        for (int i = 0; i < islandModelObjects.size(); i++) {
            if (islandModelObjects.get(i).x == x && islandModelObjects.get(i).y == y) {
                n++;
            }
        }
        return n;
    }
    public void eating(int x,int y, int howMuchEat) {
        for (int i = 0; i < howMuchEat; i++) {
            for (int j = 0; j < islandModelObjects.size(); j++) {
                if (islandModelObjects.get(i).x == x && islandModelObjects.get(i).y == y) {
                    islandModelObjects.remove(i);
                    System.out.println("sheep eat plant");
                }
            }
        }
    }

}
