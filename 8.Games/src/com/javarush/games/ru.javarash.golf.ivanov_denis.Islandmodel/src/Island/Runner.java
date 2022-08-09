package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island;

import com.javarush.engine.cell.*;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Herbivores.Sheep;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Plants.Plant;

public class Runner extends Game{
    public static int WIDTH = 20;//ширина
    public static int HEIGHT = 20;//Высота
    private int turnDelay;//скорость воспроизведения
    private Sheep sheep = new Sheep(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
    private Plant plant = new Plant();

    @Override
    public void initialize() {
        super.initialize();
        setScreenSize(WIDTH, HEIGHT);//задаём размер поля
        createIslandModel();//вызов отрисовки поля
    }

    private void drawScene() {//отрисовка поля
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");//замена цвета для каждой ячейки
            }
        }
        sheep.draw(this);
        plant.draw(this);
    }
    public void onTurn(int Step) {//исполняем и смотрим мультики
        sheep.move();
        plant.reproduce();
        eater();
        drawScene();
    }

    private void createIslandModel() {//создаём новый мир животных
        sheep.createNewObjects(50, WIDTH, HEIGHT);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    public void eater() {//переберает ечейки и заставляет кушать
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                plant.eating(x, y, sheep.eat(x, y, plant.howMuchFood(x, y)));
            }
        }
    }


}
