package game;

import board.FieldModel;
import pieces.HoundModel;
import pieces.FoxModel;

import java.util.Stack;

public class Game {
    private boolean foxsTurn = true; //FoxModel starts the game
    public boolean houndMoveUp;
    private Stack<Move> listOfMoves = new Stack<>();

    public void saveMove(Move move) {
        listOfMoves.push(move);
    }
    public Move popMove() {
        return listOfMoves.pop();
    }

    public void changeTurn() {
        foxsTurn = !foxsTurn;
    }

    public Class turnOf() {
        return foxsTurn ? FoxModel.class : HoundModel.class;
    }

    public boolean canUndo() {
        return !listOfMoves.empty();
    }

    public static class Move{
        public FieldModel from;
        public FieldModel to;

        public Move(FieldModel from, FieldModel to) {
            this.from = from;
            this.to = to;
        }
    }

}

