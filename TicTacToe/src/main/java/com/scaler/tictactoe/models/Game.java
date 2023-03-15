package com.scaler.tictactoe.models;

import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;

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
        Player toMovePlayer = players.get(nextPlayerIndex);

        System.out.println("It is " + players.get(nextPlayerIndex).getName() + "'s turn.");

        Move move = toMovePlayer.decideMove(this.board);

        // validate move

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Move happened at: " +
                row + ", " + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove = new Move(
                players.get(nextPlayerIndex),
                board.getBoard().get(row).get(col)
        );

        this.moves.add(finalMove);

        if (gameWinningStrategy.checkWinner(
                board, players.get(nextPlayerIndex), finalMove.getCell()
        )) {
            gameStatus = GameStatus.ENDED;
            winner = players.get(nextPlayerIndex);
        }

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
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

    }
}
