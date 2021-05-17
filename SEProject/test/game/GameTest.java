package game;

import board.ChessboardModel;
import org.junit.jupiter.api.Test;
import pieces.PieceModel;
import pieces.HoundModel;
import pieces.FoxModel;

class GameTest {
    @Test
    void turnTest() {
        ChessboardModel chessboardModel = new ChessboardModel();
        assert chessboardModel.turnOf() == FoxModel.class;
        chessboardModel.changeTurn();
        assert chessboardModel.turnOf() == HoundModel.class;
        PieceModel sheep = new HoundModel(chessboardModel, false);
        assert chessboardModel.turnOf().isInstance(sheep);
        PieceModel wolf = new FoxModel(chessboardModel);
        assert !chessboardModel.turnOf().isInstance(wolf);
    }
}