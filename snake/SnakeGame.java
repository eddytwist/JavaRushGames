package com.javarush.games.snake;

import com.javarush.engine.cell.*;
public class SnakeGame extends Game {
        public static final int WIDTH = 15;
        public static final int HEIGHT = 15;
        private Snake snake;
        private Apple apple;
        private Hospital hospital;
        private Tractor tractor;
        private Policeman policeman;
        private int turnDelay;
        private boolean isGameStopped;
        private static final int GOAL = 17;
        private int score;

    @Override
    public  void initialize () {
        setScreenSize(WIDTH, HEIGHT);
        createGame();


    }


    private void createGame() {
        showGrid(false);
        snake = new Snake(WIDTH / 2,HEIGHT / 2 );
        createNewApple();
        createNewHospital();
        createNewPoliceman();
        createNewTractor();

        isGameStopped = false;
        score = 0;
        setScore(score);
        //apple = new Apple(WIDTH / 5, HEIGHT / 5);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        //Apple apple = new Apple(7,7);
            }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.MEDIUMAQUAMARINE,"");
                setCellValueEx(0, 0, Color.ROSYBROWN, String.valueOf(score));
            }
        }
        snake.draw(this);
        apple.draw(this);
        hospital.draw(this);
        tractor.draw(this);
        policeman.draw(this);
    }
    private void win() {
        stopTurnTimer();
        showMessageDialog(Color.NONE,"CARANTIULATIONS!", Color.YELLOW, 50);
        isGameStopped = true;
    }
    private void gameOver() {
        stopTurnTimer();
        showMessageDialog(Color.NONE,"IDEM NA PARAD", Color.RED, 50);
        isGameStopped = true;
    }
    @Override
    public void onTurn(int step){
        snake.move(apple);
        snake.move(hospital);
        snake.move(policeman);
        snake.move(tractor);
        if (!apple.isAlive) {
            score = score + 500;
            setScore(score);
            turnDelay = turnDelay - 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if (!policeman.isAlive) {
            score = score + 1000;
            setScore(score);
            turnDelay = turnDelay - 25;
            setTurnTimer(turnDelay);
            createNewPoliceman();
        }
        if (!hospital.isAlive) {
            score = score - 50;
            setScore(score);
            turnDelay = turnDelay + 20;
            setTurnTimer(turnDelay);
            createNewHospital();
        }
        if (!tractor.isAlive) {
            score = score - 50;
            setScore(score);
            turnDelay = turnDelay + 50;
            setTurnTimer(turnDelay);
            createNewTractor();
        }
        if (!snake.isAlive) gameOver();
        if (snake.getLength() > GOAL) win();
        drawScene();
    }
    @Override
    public void onKeyPress(Key key) {
        

        if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        }
        if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        }
        if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        }
        if (key == Key.SPACE) {
            if (isGameStopped) createGame();
        }
    }

    private void createNewApple() {
        do{
            int xN = getRandomNumber(WIDTH);
            int yN = getRandomNumber(HEIGHT);
            apple=new Apple(xN, yN);
        }while(snake.checkCollision(apple));
    }
    private void createNewPoliceman() {
        do{
            int xN = getRandomNumber(WIDTH);
            int yN = getRandomNumber(HEIGHT);
            policeman=new Policeman(xN, yN);
        }while(snake.checkCollision(policeman));
    }
    private void createNewTractor() {
        do{
            int xN = getRandomNumber(WIDTH);
            int yN = getRandomNumber(HEIGHT);
            tractor=new Tractor(xN, yN);
        }while(snake.checkCollision(tractor));
    }
    private void createNewHospital() {
        do{
            int xN = getRandomNumber(WIDTH);
            int yN = getRandomNumber(HEIGHT);
            hospital=new Hospital(xN, yN);
        }while(snake.checkCollision(hospital));
    }

}
