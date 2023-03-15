package com.scaler.tictactoe;

import com.scaler.tictactoe.controllers.GameController;
import com.scaler.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Package by layers
// Package by features
// https://medium.com/sahibinden-technology/package-by-layer-vs-package-by-feature-7e89cde2ae3a



public class TicTacToeGame {

    public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);
       GameController gameController = new GameController();

       System.out.println("Enter the board size : ");
       int boardSize = scanner.nextInt();

       List<Player> players = new ArrayList<>();

       System.out.println("Will there be any bot players ? (y/n)");
       String botPlayer = scanner.next();


       int numOfPlayers = boardSize - 1;
       if(botPlayer.equals("y")) {
           System.out.println("Enter the name of bot " + " : ");
           String name = scanner.next();

           System.out.println("Enter the character/symbol of bot " + " : ");
           String character = scanner.next();
           players.add(new Bot(name,  character.charAt(0), BotDifficultyLevel.EASY));

           numOfPlayers--;
       }

       for(int i = 0; i < numOfPlayers; i++) {
           System.out.println("Enter the name of player " + (i+1) + " : ");
           String name = scanner.next();

           System.out.println("Enter the character/symbol of player " + (i+1) + " : ");
           String character = scanner.next();
           players.add(new Player(name,  character.charAt(0), PlayerType.HUMAN));
       }

       Game game = gameController.createGame(boardSize, players);

       while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
           System.out.println("Current board : ");
           gameController.printBoard(game);

           System.out.println("Do you want to undo? y/n");
           String undo = scanner.next();
           if(undo.equals("y")) {
               gameController.undo(game);
           } else {
               gameController.executeNextMove(game);
           }
       }

       System.out.println("Game has ended. Result: ");
       if(!gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
           System.out.println("Winner is " + gameController.getWinner(game).getName());
       } else {
           System.out.println("Game has drawn");
       }
    }
}