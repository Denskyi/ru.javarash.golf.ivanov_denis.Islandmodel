package com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Animal;

import com.javarush.engine.cell.Game;
import com.javarush.games.ru.javarash.golf.ivanov_denis.Islandmodel.src.Island.IslandModelObject;

import java.util.List;

public abstract class Animal {
    private Direction direction;
    private List<IslandModelObject> islandModelObjects;
    private static final String APPEARANCE = "";
    public void move() {//Пробегает по массиву животных этого типа и двигает каждое

    }

    public void draw(Game game) {//рисует животных этого типа на карте

    }

    public abstract int eat(int x, int y, int howMuchFood); //покушать



    public void setDirection() {//Выбрать направление передвижения

    }

    public void createNewObjects(int n, double weight, int maximumQuantityPerCell, int movementSpeed, double kilogramsFoodNeeded, boolean alive, boolean busy) {

    }

}
