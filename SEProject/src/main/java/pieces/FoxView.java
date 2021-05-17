package pieces;

import board.ChessboardView;

class FoxView extends PieceView {
    FoxView(PieceModel pieceModel, ChessboardView chessboardView) {
        super(pieceModel, chessboardView);
        this.viewRepresentation.setFill(ChessboardView.foxColor);
    }
}
