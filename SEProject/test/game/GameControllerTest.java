package game;

import board.ChessboardModel;
import board.ChessboardView;
import board.FieldModel;
import org.junit.jupiter.api.Test;
import pieces.PieceFactory;
import pieces.PieceModel;
import pieces.PieceView;

import java.util.ArrayList;

class GameControllerTest {

    @Test
    void undoLastMove() {
        ChessboardModel chessboardModel = new ChessboardModel();
        ChessboardView chessboardView = new ChessboardView(chessboardModel);
        PieceFactory pieceFactory = new PieceFactory(chessboardModel, chessboardView);
        GameController gameController = new GameController(chessboardModel, chessboardView,
                pieceFactory.placePieces(true), true);
        assert !chessboardModel.canUndo();
        FieldModel foxField = chessboardModel.getField(0, 5);
        FieldModel toMove = chessboardModel.getField(1, 4);
        PieceModel fox = foxField.getPieceModel();
        PieceView foxView = chessboardView.getFieldView(foxField).getPieceView();
        foxView.move(toMove);
        assert !fox.hasTurnNow();
        assert fox == chessboardModel.getField(1, 4).getPieceModel();
        assert null == chessboardModel.getField(0, 5).getPieceModel();
        gameController.undoLastMove();
        assert fox == chessboardModel.getField(0, 5).getPieceModel();
        assert fox.hasTurnNow();
    }

    @Test
    void getHound() {
        GameController gameController = GameController.initGame(true);
        gameController.getHound().stream().
                map(a -> a.getFieldModel().getRow()).sorted().forEach(a -> {
            assert a == 7;
        } );
    }

    @Test
    void getFox() {
        GameController gameController = GameController.initGame(true);
        assert gameController.getFox().getFieldModel().getRow() == 0;
    }

    @Test
    void somebodyWon() {
        GameController gameController = GameController.initGame(true);
        assert !gameController.foxWon();
        assert !gameController.houndWon();
    }
}
