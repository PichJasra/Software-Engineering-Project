package pieces;

import board.ChessboardModel;

public class FoxModel extends PieceModel {
    public FoxModel(ChessboardModel chessboardModel) {
        super(chessboardModel);
        movingWay = new MovesBothWays();
    }
}
