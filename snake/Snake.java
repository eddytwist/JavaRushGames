package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC51";
    private static final String BODY_SIGN = "\uD83D\uDE37";

    public boolean isAlive = true;

    public boolean checkCollision(GameObject object) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (object.x == snakeParts.get(i).x && object.y == snakeParts.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public void move(Apple apple){
    GameObject head = createNewHead();
            if (head.x < 0 || head.x >= 15 || head.y < 0 || head.y >= 15) {
                isAlive = false;
            }
            else if (checkCollision(head)) {
            isAlive = false;
            return;
            }
            else if (head.x == apple.x && head.y == apple.y) {
                apple.isAlive = false;
                snakeParts.add(0, head);
            }
            else {
                snakeParts.add(0,head);
                removeTail();
            }
    }
    public void move(Hospital hospital){
        GameObject head = createNewHead();
        if(head.x == hospital.x && head.y == hospital.y) {
            hospital.isAlive = false;
            removeTail();
        }
    }
    public void move(Tractor tractor){
        GameObject head = createNewHead();
        if(head.x == tractor.x && head.y == tractor.y) {
            tractor.isAlive = false;
            removeAllTail();
        }
    }
    public void move(Policeman policeman){
        GameObject head = createNewHead();
        if(head.x == policeman.x && head.y == policeman.y) {
            policeman.isAlive = false;
            snakeParts.add(0, head);
        }
    }

     //*/

    private Direction direction = Direction.LEFT;

    public void setDirection(Direction direcnew){
        if ( ((direction==Direction.RIGHT) || (direction==Direction.LEFT)) && (snakeParts.get(0).y==snakeParts.get(1).y) && ((direcnew==Direction.DOWN) || (direcnew==Direction.UP)) ) {
            this.direction = direcnew;
        }

        if ( ((direction==Direction.DOWN) || (direction==Direction.UP)) && (snakeParts.get(0).x==snakeParts.get(1).x) && ((direcnew==Direction.RIGHT) || (direcnew==Direction.LEFT)) ) {
            this.direction = direcnew;
        }
    }

    private List<GameObject> snakeParts = new ArrayList<>();
    public Snake(int x, int y) {
        GameObject snake0 = new GameObject(x, y);
        GameObject snake1 = new GameObject(x + 1, y);
        GameObject snake2 = new GameObject(x + 2, y);
        snakeParts.add(snake0);
        snakeParts.add(snake1);
        snakeParts.add(snake2);
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (isAlive) {
                if (i == 0) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.LIMEGREEN, 75);
                }
                else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                }
            }
            else {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
            }
        }
    }

    public GameObject createNewHead(){
        GameObject objectnew = null; // = new GameObject(0, 0);
        if (direction == Direction.UP) {
            objectnew = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
        } else if (direction == Direction.DOWN) {
            objectnew = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
        } else if (direction == Direction.RIGHT) {
            objectnew = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
        } else if (direction == Direction.LEFT) {
            objectnew = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
        }
        return objectnew;
    }
    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);
    }
    public void removeAllTail(){
        snakeParts.removeAll(snakeParts.subList(2,snakeParts.size()));
        }
    public int getLength (){
        return snakeParts.size();
    }
}
