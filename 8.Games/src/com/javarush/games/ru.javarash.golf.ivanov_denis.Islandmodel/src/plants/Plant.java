package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.plants;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.Runner;

public class Plant extends IslandModelObject {
    String name;
    int maximumQuantity;
    int movementSpeed;
    String APPEARANCE;

    public Plant(int x, int y, String name, int movementSpeed, String APPEARANCE, int maximumQuantity) {
        super(x, y, name, movementSpeed, APPEARANCE, maximumQuantity);
        this.name = name;
        this.APPEARANCE = APPEARANCE;
        this.maximumQuantity = maximumQuantity;
    }

    public void draw(Game game) {//рисует животных этого типа на карте
        for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
            game.setCellValueEx(Runner.objectMap.get(name).get(i).x, Runner.objectMap.get(name).get(i).y, Color.DARKSEAGREEN, APPEARANCE, Color.GREEN,45 );
        }
    }
    public void reproduction() {
        Runner.objectMap.get(name).add(new IslandModelObject(((int) (Math.random() * Runner.WIDTH)),
                ((int) (Math.random() * Runner.HEIGHT)), name, movementSpeed, APPEARANCE,maximumQuantity));
    }
}
