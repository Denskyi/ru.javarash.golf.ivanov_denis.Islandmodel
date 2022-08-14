package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.herbivores.Sheep;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal.predators.Wolf;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.plants.Clover;

import java.util.ArrayList;
import java.util.HashMap;

public class Runner extends Game {
    public static int WIDTH = 20;//ширина
    public static int HEIGHT = 20;//Высота
    ArrayList<String> predatorFood = new ArrayList<>();
    private int turnDelay = 300;//скорость воспроизведения
    public static HashMap<String, ArrayList<IslandModelObject>> objectMap = new HashMap<>();
    private Sheep sheep = new Sheep(0, 0, "Sheep", 3, "\uD83D\uDC11",140);
    private Clover plant = new Clover(0, 0, "Plant", 0, "\uD83C\uDF40", 200);
    private Wolf wolf = new Wolf(0, 0, "Wolf", 3, "\uD83D\uDC3A", 30);

    @Override
    public void initialize() {
        super.initialize();
        setScreenSize(WIDTH, HEIGHT);//задаём размер поля
        createIslandModel();//вызов отрисовки поля
    }

    private void drawScene() {//отрисовка поля
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "", Color.NONE, 75);//замена цвета для каждой ячейки
            }
        }

        plant.draw(this);
        sheep.draw(this);
        wolf.draw(this);
    }

    public void onTurn(int Step) {//исполняем и смотрим мультики
        sheep.move();
        wolf.move();
        sheep.reproduction();
        wolf.reproduction();
        plant.reproduction();
        //plant.reproduce();
        eater();
        drawScene();
    }

    private void createIslandModel() {//создаём новый мир животных
        predatorFood.add("Sheep");
        sheep.createNewObjects(20);
        wolf.createNewObjects(5);
        plant.createNewObjects(50);
        drawScene();
        setTurnTimer(turnDelay);
    }

    public void eater() {//заставляет кушать
        wolf.eat(predatorFood);
        sheep.eat();
    }


}
