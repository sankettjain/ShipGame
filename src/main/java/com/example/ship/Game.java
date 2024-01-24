package com.example.ship;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private int[][] battlefield;
    private int size;
    private Ship playerA;
    private Ship playerB;
    //Default FireStrategy
    private FireStrategy fireStrategy = new RandomFireStrategy();
    private Set<String> usedCoordinates = new HashSet<>();
    private Player playerActive = Player.PLAYER_A;

    public void initGame(Integer size) {
        this.size = size;
        battlefield = new int[size][size];
    }

    public void addShip(String id, Integer size, Integer xPositionA, Integer yPositionA, Integer xPositionB, Integer yPositionB) throws Exception {

        boolean isValid = isPositionValid(size, xPositionA, yPositionA, xPositionB, yPositionB);
        if (!isValid) {
            throw new Exception("Coordinates invalid");
        }
        playerA = new Ship("A-" + id, size, xPositionA, yPositionA);
        playerA.area();
        playerB = new Ship("B-" + id, size, xPositionB, yPositionB);
        playerB.area();
    }

    private boolean isPositionValid(Integer size, Integer xPositionA, Integer yPositionA, Integer xPositionB, Integer yPositionB) {
        //todo
        return true;
    }

    public Boolean startGame() throws Exception {
        Boolean isGameOver = false;
        if (Player.PLAYER_A.equals(playerActive)) {

            String randomCoordinates = isRandomCoordinatesUsed(size, Player.PLAYER_B);

            if (playerB.getArea().contains(randomCoordinates)) {
                System.out.println("PlayerA’s turn: Missile fired at " + randomCoordinates + "'Hit' " + playerB.getId() + " Destroyed :" +
                        " Ships Remaining - PlayerA:1, PlayerB:0");
                isGameOver = true;
            } else {
                System.out.println("PlayerA’s turn: Missile fired at " + randomCoordinates + "'Miss' : Ships " +
                        "Remaining - PlayerA:1, PlayerB:1");
            }

            playerActive = Player.PLAYER_B;
        } else if (Player.PLAYER_B.equals(playerActive)) {

            String randomCoordinates = isRandomCoordinatesUsed(size, Player.PLAYER_A);

            if (playerA.getArea().contains(randomCoordinates)) {
                System.out.println("PlayerB’s turn: Missile fired at " + randomCoordinates + "'Hit' " + playerB.getId() + " Destroyed :" +
                        " Ships Remaining - PlayerA:0, PlayerB:1");
                isGameOver = true;
            } else {
                System.out.println("PlayerB’s turn: Missile fired at " + randomCoordinates + "'Miss' : Ships\n" +
                        "Remaining - PlayerA:1, PlayerB:1");
            }

            playerActive = Player.PLAYER_A;
        }
        return isGameOver;
    }

    private String isRandomCoordinatesUsed(int size, Player player) throws Exception {
        int counter = 0;
        String randomCoordinates = fireStrategy.getCoordinates(size, player);
        while (usedCoordinates.contains(randomCoordinates)) {
            if (counter >= size) {
                throw new Exception();
            }
            randomCoordinates = fireStrategy.getCoordinates(size, player);
        }
        return randomCoordinates;
    }

    public static void main(String[] args) throws Exception {

        Game game = new Game();
        game.initGame(6);

        game.addShip("SH1", 2, 1, 5, 4, 4);

        Boolean isGame;
        do {
            isGame = game.startGame();
        } while (!isGame);

    }
}
