package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Tractor extends GameObject {
    private static final String TRACTOR_SIGN = "\uD83D\uDE9C️";
    public boolean isAlive = true;

    public Tractor(int x, int y) {
        super(x, y);
    }
    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, TRACTOR_SIGN, Color.BROWN, 75);
    }
}


