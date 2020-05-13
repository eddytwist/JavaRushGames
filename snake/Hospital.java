package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Hospital extends GameObject{
    private static final String HOSP_SIGN = "\uD83C\uDFE5";
    public boolean isAlive = true;

    public Hospital (int x, int y) {
        super(x, y);
    }
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, HOSP_SIGN, Color.GRAY, 75);
    }
}

