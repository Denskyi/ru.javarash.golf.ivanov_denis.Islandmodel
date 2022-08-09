package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Herbivores;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Direction;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.Runner;

import java.util.ArrayList;
import java.util.List;

public class Sheep {
    public int x;//координаты по х
    public int y;//координаты по у
    private List<IslandModelObject> islandModelObjects = new ArrayList<>();

    private Direction direction = Direction.LEFT;
    private static final String APPEARANCE = "\uD83D\uDC11";//Внещний вид
    double weight = 70;//Вес объекта
    int maximum_quantity_per_cell = 140;//Максимальное количество этого вида на одной клетке
    int movement_speed = 3;//Скорость перемещения, не более чем, клеток за ход
    double kilograms_food_needed_for_full_saturation = 15;//Сколько килограммов пищи нужно животному для полного насыщения
    public Sheep(int x, int y) {//инициализация одной овечки
        this.x = x;
        this.y = y;
        islandModelObjects.add(new IslandModelObject(x, y));
    }
    public void createNewObjects(int n, int WIDTH, int HEIGHT) {//конвеер для сколько хочешь животных этого типа
        for (int i = 0; i < n; i++) {
            islandModelObjects.add(new IslandModelObject(((int) (Math.random() * Runner.WIDTH)), ((int) (Math.random() * Runner.HEIGHT))));
        }
    }
    public void draw(Game game) {//рисует животных этого типа на карте
        for (IslandModelObject islandModelObject : islandModelObjects) {
            game.setCellValueEx(islandModelObject.x, islandModelObject.y, Color.DARKSEAGREEN, APPEARANCE);
        }
    }
    public int eat(int x, int y, int howMuchPlant) {//покушать
        int n = 0;
        for (IslandModelObject islandModelObject : islandModelObjects) {//мы можем есть если есть еда и животные
            if (islandModelObject.x == x && islandModelObject.y == y && howMuchPlant > 0) {
                n++;
                howMuchPlant--;
            }
        }
        return n;
    }

    public void move() {
        for (int i = 0; i < islandModelObjects.size(); i++) {
            int select_distance = ((int) (Math.random() * this.movement_speed));
            int height_Y = islandModelObjects.get(i).y;
            int width_X = islandModelObjects.get(i).x;
            setDirection();
            switch (direction) {
                case UP: //Идём вверх с проверкой выхода за границы
                    if (height_Y - select_distance < 0) {select_distance = select_distance - Runner.HEIGHT;}
                    islandModelObjects.set(i, new IslandModelObject(width_X, height_Y - select_distance));
                    break;
                case DOWN: //Идём вниз с проверкой выхода за границы
                    if (height_Y + select_distance > (Runner.HEIGHT - 1)) {select_distance = select_distance - Runner.HEIGHT;}
                    islandModelObjects.set(i, new IslandModelObject(width_X, height_Y + select_distance));
                    break;
                case RIGHT: //Идём вправо с проверкой выхода за границы
                    if (width_X + select_distance > Runner.WIDTH - 1) {select_distance = select_distance - Runner.WIDTH;}
                    islandModelObjects.set(i, new IslandModelObject(width_X + select_distance, height_Y));
                    break;
                case LEFT: //Идём влево с проверкой выхода за границы:
                    if (width_X - select_distance < 0) {select_distance = select_distance - Runner.WIDTH;}
                    islandModelObjects.set(i, new IslandModelObject(width_X - select_distance, height_Y));
                    break;
            }

        }


    }
    public void setDirection() {//Выбрать направление передвижения
        int random = ((int) (Math.random() * 4));
        switch (random) {
            case 0:
                direction = Direction.UP;
                break;
            case 1:
                direction = Direction.DOWN;
                break;
            case 2:
                direction = Direction.RIGHT;
                break;
            default:
                direction = Direction.LEFT;
                break;
        }
    }

}
