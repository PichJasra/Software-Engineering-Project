package pieces;

import board.ChessboardModel;

public class HoundModel extends PieceModel {

    public HoundModel(ChessboardModel chessboardModel, boolean playerControlsSheep) {
        super(chessboardModel);
        movingWay = playerControlsSheep ? new MovesUpward() : new MovesDownward();
    }
}
