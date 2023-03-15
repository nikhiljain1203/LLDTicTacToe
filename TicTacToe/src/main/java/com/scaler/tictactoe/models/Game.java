package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exceptions.InvalidGameConstructorExcpetion;
import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {}

    public void makeNextMove() {

    }

    public void displayBoard() {
        this.board.display();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static class Builder {
        private int boardSize;
        private List<Player> players;

        public Builder setBoard(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public boolean valid() throws Exception {
            if(this.boardSize < 3) {
                throw new InvalidGameConstructorExcpetion("Dimensions of board should be greater than 3");
            } else if(this.players.size() < 2) {
                throw new InvalidGameConstructorExcpetion("Players should be more than 2");
            }
            return true;
        }

        public Game build() throws Exception {

            try{
                valid();
            } catch (Exception e) {
                throw new InvalidGameConstructorExcpetion(e.getMessage());
            }

            Game game = new Game();
            game.setBoard(new Board(this.boardSize));
            game.setPlayers(this.players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            //game.gameWinningStrategy();
            return game;
        }
    }
}
