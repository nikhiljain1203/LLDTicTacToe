package com.scaler.tictactoe.strategies.botplayingstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.CellState;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

public class FindFirstPlayingStrategy implements BotPlayingStrategy {


    @Override
    public Move decideMove(Player player, Board board) {
        for (int i = 0; i < board.getBoard().size(); ++i) {
            for (int j = 0; j < board.getBoard().size(); ++j) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                        player,
                        new Cell(i, j)
                    );
                }
            }
        }
        return null;
    }
}
