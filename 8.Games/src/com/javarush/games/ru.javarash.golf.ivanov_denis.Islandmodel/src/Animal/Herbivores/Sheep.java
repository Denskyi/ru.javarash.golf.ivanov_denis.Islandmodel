package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Herbivores;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Animal;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Direction;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.Runner;

import java.util.ArrayList;
import java.util.List;

public class Sheep extends Animal {
//    public int x;//координаты по х
//    public int y;//координаты по у
    private List<IslandModelObject> islandModelObjects = new ArrayList<>();

    private Direction direction = Direction.LEFT;
    private static final String APPEARANCE = "\uD83D\uDC11";//Внещний вид
//    double weight = 70;//Вес объекта
//    int maximum_quantity_per_cell = 140;//Максимальное количество этого вида на одной клетке
//    int movement_speed = 3;//Скорость перемещения, не более чем, клеток за ход
//    double kilograms_food_needed_for_full_saturation = 15;//Сколько килограммов пищи нужно животному для полного насыщения

    //конвеер для сколько хочешь животных этого типа
    public void createNewObjects(int n, double weight, int maximumQuantityPerCell, int movementSpeed, double kilogramsFoodNeeded, boolean alive, boolean busy) {
        for (int i = 0; i < n; i++) {
            islandModelObjects.add(new IslandModelObject(
                    ((int) (Math.random() * Runner.WIDTH)),
                    ((int) (Math.random() * Runner.HEIGHT)),
                    weight,
                    maximumQuantityPerCell,
                    movementSpeed,
                    kilogramsFoodNeeded,
                    alive,
                    busy));
        }
    }
    public void draw(Game game) {//рисует животных этого типа на карте
        for (IslandModelObject islandModelObject : islandModelObjects) {
            game.setCellValueEx(islandModelObject.x, islandModelObject.y,Color.DARKSEAGREEN, APPEARANCE, Color.GREEN,75 );
        }
    }
    public int eat(int x, int y, int howMuchFood) {//покушать
        int n = 0;
        for (IslandModelObject islandModelObject : islandModelObjects) {//мы можем есть если есть еда и животные
            if (islandModelObject.x == x && islandModelObject.y == y && howMuchFood > 0) {
                n++;
                howMuchFood--;
            }
        }
        return n;
    }

    public void move() {//Пробегает по массиву животных этого типа и двигает каждое
        for (int i = 0; i < islandModelObjects.size(); i++) {
            //Каждое животное копируется индивидуально, возможно пригодиться для введения потепенных изменений
            int height_Y = islandModelObjects.get(i).y;
            int width_X = islandModelObjects.get(i).x;
            double weight = islandModelObjects.get(i).weight;
            int maximumQuantityPerCell = islandModelObjects.get(i).maximumQuantityPerCell;
            int movement_speed = islandModelObjects.get(i).movementSpeed;
            double kilograms_food_needed_for_full_saturation = islandModelObjects.get(i).kilogramsFoodNeeded;
            boolean alive = islandModelObjects.get(i).alive;
            boolean busy = islandModelObjects.get(i).busy;
            int select_distance = ((int) (Math.random() * movement_speed));
            setDirection();
            switch (direction) {
                case UP: //Идём вверх с проверкой выхода за границы
                    if (height_Y - select_distance < 0) {select_distance = select_distance - Runner.HEIGHT;}
                    islandModelObjects.set(i, new IslandModelObject(width_X, height_Y - select_distance, weight, maximumQuantityPerCell, movement_speed, kilograms_food_needed_for_full_saturation, alive, busy));
                    break;
                case DOWN: //Идём вниз с проверкой выхода за границы
                    if (height_Y + select_distance > (Runner.HEIGHT - 1)) {select_distance = select_distance - Runner.HEIGHT;}
                    islandModelObjects.set(i, new IslandModelObject(width_X, height_Y + select_distance, weight, maximumQuantityPerCell, movement_speed, kilograms_food_needed_for_full_saturation, alive, busy));
                    break;
                case RIGHT: //Идём вправо с проверкой выхода за границы
                    if (width_X + select_distance > Runner.WIDTH - 1) {select_distance = select_distance - Runner.WIDTH;}
                    islandModelObjects.set(i, new IslandModelObject(width_X + select_distance, height_Y, weight, maximumQuantityPerCell, movement_speed, kilograms_food_needed_for_full_saturation, alive, busy));
                    break;
                case LEFT: //Идём влево с проверкой выхода за границы:
                    if (width_X - select_distance < 0) {select_distance = select_distance - Runner.WIDTH;}
                    islandModelObjects.set(i, new IslandModelObject(width_X - select_distance, height_Y, weight, maximumQuantityPerCell, movement_speed, kilograms_food_needed_for_full_saturation, alive, busy));
                    break;
            }
        }
    }
    @Override
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
                    System.out.println("wolf eat sheep");
                }
            }
        }
    }

}
