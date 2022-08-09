package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Predators;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Animal;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal.Direction;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.Runner;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends Animal {
    private List<IslandModelObject> islandModelObjects = new ArrayList<>();

    private Direction direction = Direction.LEFT;
    private static final String APPEARANCE = "\uD83D\uDC3A";//Внещний вид
    @Override
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
    public void draw(Game game) {//рисует животных этого типа на карте
        for (IslandModelObject islandModelObject : islandModelObjects) {
            game.setCellValueEx(islandModelObject.x, islandModelObject.y,Color.DARKSEAGREEN, APPEARANCE, Color.RED,75 );
        }
    }

    @Override
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

    @Override
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
}
