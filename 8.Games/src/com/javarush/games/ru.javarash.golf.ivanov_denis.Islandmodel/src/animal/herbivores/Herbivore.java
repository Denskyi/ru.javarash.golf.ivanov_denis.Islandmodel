package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.herbivores;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.Animal;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.Runner;

public class Herbivore extends Animal {
    String name;
    String APPEARANCE;

    public Herbivore(int x, int y, String name, int movementSpeed, String APPEARANCE, int maximumQuantity) {
        super(x, y, name, movementSpeed, APPEARANCE, maximumQuantity);
        this.name = name;
        this.APPEARANCE = APPEARANCE;
    }

    public void draw(Game game) {//рисует животных этого типа на карте
        for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
            game.setCellValueEx(Runner.objectMap.get(name).get(i).x, Runner.objectMap.get(name).get(i).y, Color.DARKSEAGREEN, APPEARANCE, Color.LIGHTGRAY,75 );
        }
    }
    public void eat() {
        for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
            for (int j = 0; j < Runner.objectMap.get("Plant").size(); j++) {
                if (Runner.objectMap.get(name).get(i).x == Runner.objectMap.get("Plant").get(j).x &&
                        Runner.objectMap.get(name).get(i).y == Runner.objectMap.get("Plant").get(j).y) {
                    Runner.objectMap.get("Plant").remove(j);
                    System.out.println( name + " eat Plant");
                }
            }
        }
    }
}
