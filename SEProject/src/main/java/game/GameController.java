package game;

import board.ChessboardModel;
import board.ChessboardView;
import board.FieldController;
import board.FieldView;
import javafx.scene.layout.GridPane;
import org.tinylog.Logger;
import pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GameController implements PieceMovedObserver{
    private ChessboardModel chessboardModel;
    private ChessboardView chessboardView;
    private boolean houndMoveUp;
    private List<PieceModel> pieces;
    private List<GameOverObserver> observers;
    
    public static GameController initGame(boolean playerControlsHound) {
        ChessboardModel cm = new ChessboardModel();
        cm.setHoundMoveUp(playerControlsHound);
        ChessboardView cv = new ChessboardView(cm);
        PieceFactory pieceFactory = new PieceFactory(cm, cv);
        List<PieceModel> pieces = pieceFactory.placePieces(playerControlsHound);
        return new GameController(cm, cv, pieces, playerControlsHound);
    }

    GameController(ChessboardModel chessboardModel, ChessboardView chessboardView,
                   List<PieceModel> pieces, boolean houndMoveUp) {
        this.chessboardModel = chessboardModel;
        this.chessboardView = chessboardView;
        chessboardView.addObserver(this);
        this.pieces = pieces;
        this.houndMoveUp = houndMoveUp;
        this.observers = new ArrayList<>();
    }

    public void addObserver(GameOverObserver observer) {
        observers.add(observer);
    }

    public void undoLastMove() {
        if (chessboardModel.canUndo()) {
            FieldController fieldController = new FieldController(chessboardModel, chessboardView);
            fieldController.unselectField();

            Game.Move lastMove = chessboardModel.popMove();
            PieceModel pieceMoved = lastMove.to.getPieceModel();
            FieldView movedTo = chessboardView.getFieldView(lastMove.to);
            PieceView pieceViewMoved = movedTo.getPieceView();
            movedTo.colorField(FieldView.ColorTo.PLAYABLE);
            //without the above, field remains darker after undo
            //get's darker after move because mouse leaves it after it's fill is set back to playable
            pieceMoved.moveWithoutCheck(lastMove.from);
            pieceViewMoved.moveWithoutColorChange(lastMove.from);
            Logger.debug("Undo last move");
        }
    }

    boolean houndWon() {
        return getFox().getPossibleMoves().size() == 0 && getFox().hasTurnNow();
    }

    boolean foxWon() {
        List<Integer> houndRows = getHound().stream().
                map(a -> a.getFieldModel().getRow()).sorted().collect(Collectors.toList());
        int lastHoundRowNumber = houndMoveUp ? houndRows.get(houndRows.size() - 1) : houndRows.get(0);
        return getFox().getFieldModel().getRow() == lastHoundRowNumber;
    }

    public GridPane getChessboardGrid() {
        return chessboardView.getChessboardGrid();
    }

    List<PieceModel> getHound() {
        return pieces.stream().filter(a -> a instanceof HoundModel).collect(Collectors.toList());
    }

    PieceModel getFox() {
        return pieces.stream().filter(a -> a instanceof FoxModel).collect(Collectors.toList()).get(0);
    }


    @Override
    public void pieceMoved() {
        if (houndWon()) {
            observers.forEach(GameOverObserver::houndHaveWon);
        }
        if (foxWon()) {
            observers.forEach(GameOverObserver::foxHasWon);
        }
    }
}
