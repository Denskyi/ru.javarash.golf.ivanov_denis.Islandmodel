package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.animal;

import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.IslandModelObject;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.island.Runner;

public class Animal extends IslandModelObject{
    String name;
    int maximumQuantity;
    String APPEARANCE;
    int movementSpeed;

    public Animal(int x, int y, String name, int movementSpeed, String APPEARANCE, int maximumQuantity) {
        super(x, y, name, movementSpeed, APPEARANCE, maximumQuantity);
        this.name = name;
        this.movementSpeed = movementSpeed;
        this.APPEARANCE = APPEARANCE;
        this.maximumQuantity = maximumQuantity;
    }

    public void reproduction() {
        for (int x = 0; x < Runner.WIDTH; x++) {
            for (int y = 0; y < Runner.HEIGHT; y++) {
                int n = 0;
                for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
                    if (Runner.objectMap.get(name).get(i).x == x && Runner.objectMap.get(name).get(i).y == y) {
                        n++;
                    }
                }
                if (n > 1 && n < maximumQuantity) {
                    Runner.objectMap.get(name).add(new IslandModelObject(((int) (Math.random() * Runner.WIDTH)),
                            ((int) (Math.random() * Runner.HEIGHT)), name, movementSpeed, APPEARANCE, maximumQuantity));
                    System.out.println("add new" + name);

                }
            }
        }

    }

    public void move() {//Пробегает по массиву животных этого типа и двигает каждое
        for (int i = 0; i < Runner.objectMap.get(name).size(); i++) {
            //Каждое животное копируется индивидуально, возможно пригодиться для введения постепенных изменений
            int height_Y = Runner.objectMap.get(name).get(i).y;
            int width_X = Runner.objectMap.get(name).get(i).x;
            int select_distance = ((int) (Math.random() * movementSpeed));
            switch (setDirection()) {
                case UP: //Идём вверх с проверкой выхода за границы
                    if (height_Y - select_distance < 0) {select_distance = select_distance - Runner.HEIGHT;}
                    Runner.objectMap.get(name).set(i, new Animal(width_X, height_Y - select_distance,name,movementSpeed,APPEARANCE,maximumQuantity));
                    break;
                case DOWN: //Идём вниз с проверкой выхода за границы
                    if (height_Y + select_distance > (Runner.HEIGHT - 1)) {select_distance = select_distance - Runner.HEIGHT;}
                    Runner.objectMap.get(name).set(i, new Animal(width_X, height_Y + select_distance,name,movementSpeed,APPEARANCE,maximumQuantity));
                    break;
                case RIGHT: //Идём вправо с проверкой выхода за границы
                    if (width_X + select_distance > Runner.WIDTH - 1) {select_distance = select_distance - Runner.WIDTH;}
                    Runner.objectMap.get(name).set(i, new Animal(width_X + select_distance, height_Y,name,movementSpeed,APPEARANCE,maximumQuantity));
                    break;
                case LEFT: //Идём влево с проверкой выхода за границы:
                    if (width_X - select_distance < 0) {select_distance = select_distance - Runner.WIDTH;}
                    Runner.objectMap.get(name).set(i, new Animal(width_X - select_distance, height_Y,name,movementSpeed,APPEARANCE,maximumQuantity));
                    break;
            }
        }
    }
    public Direction setDirection() {//Выбрать направление передвижения
        int random = ((int) (Math.random() * 4));
        switch (random) {
            case 0: return Direction.UP;
            case 1: return Direction.DOWN;
            case 2: return Direction.RIGHT;
            default: return Direction.LEFT;
        }
    }
}
