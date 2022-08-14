package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.predators;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.Animal;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.Runner;

import java.util.ArrayList;

public class Predator extends Animal {
    String name;
    String APPEARANCE;

    public Predator(int x, int y, String name, int movementSpeed, String APPEARANCE, int maximumQuantity) {
        super(x, y, name, movementSpeed, APPEARANCE, maximumQuantity);
        this.name = name;
        this.APPEARANCE = APPEARANCE;
    }

    public void draw(Game game) {//рисует животных этого типа на карте
        for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
            game.setCellValueEx(Runner.objectMap.get(name).get(i).x, Runner.objectMap.get(name).get(i).y, Color.DARKSEAGREEN, APPEARANCE, Color.RED,75 );
        }
    }
    public void eat(ArrayList<String> food) {//покушать
        for (int f = 0; f < food.size(); f++) {
            for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
                for (int j = 0; j < Runner.objectMap.get(food.get(f)).size(); j++) {
                    if (Runner.objectMap.get(name).get(i).x == Runner.objectMap.get(food.get(f)).get(j).x &&
                            Runner.objectMap.get(name).get(i).y == Runner.objectMap.get(food.get(f)).get(j).y) {
                        Runner.objectMap.get(food.get(f)).remove(j);
                        System.out.println(name + " eat " + food.get(f));
                    }
                }
            }
        }
    }
}
