package pieces;

import board.ChessboardView;

class HoundView extends PieceView {
    HoundView(PieceModel pieceModel, ChessboardView chessboardView) {
        super(pieceModel, chessboardView);
        this.viewRepresentation.setFill(ChessboardView.houndColor);
    }
}
