package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island;

import com.javarush.engine.cell.*;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Herbivores.Sheep;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Predators.Wolf;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Plants.Plant;

public class Runner extends Game{
    public static int WIDTH = 20;//ширина
    public static int HEIGHT = 20;//Высота
    private int turnDelay;//скорость воспроизведения
    private Sheep sheep = new Sheep();
    private Plant plant = new Plant();
    private Wolf wolf = new Wolf();

    @Override
    public void initialize() {
        super.initialize();
        setScreenSize(WIDTH, HEIGHT);//задаём размер поля
        createIslandModel();//вызов отрисовки поля
    }

    private void drawScene() {//отрисовка поля
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y,Color.DARKSEAGREEN, "", Color.NONE,75 );//замена цвета для каждой ячейки
            }
        }
        sheep.draw(this);
        plant.draw(this);
        wolf.draw(this);
    }
    public void onTurn(int Step) {//исполняем и смотрим мультики
        sheep.move();
        wolf.move();
        plant.reproduce(30);
        eater();
        drawScene();
    }

    private void createIslandModel() {//создаём новый мир животных
        sheep.createNewObjects(50, 70, 140, 3, 15, true, false);
        wolf.createNewObjects(0, 50, 30, 3, 3, true, false);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    public void eater() {//переберает ечейки и заставляет кушать
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                //поидее Метод который может убить объект, опрашивает сколько есть убийц и сколько есть еды на координате
                plant.eating(x, y, sheep.eat(x, y, plant.howMuchFood(x, y)));
                sheep.eating(x, y, wolf.eat(x, y, sheep.howMuchFood(x, y)));
            }
        }
    }


}
