package pieces;

import board.ChessboardModel;
import board.ChessboardView;
import board.FieldModel;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    private ChessboardModel chessboardModel;
    private ChessboardView chessboardView;

    public PieceFactory(ChessboardModel chessboardModel, ChessboardView chessboardView) {
        this.chessboardModel = chessboardModel;
        this.chessboardView = chessboardView;
    }
    public List<PieceModel> placePieces(boolean playerControlsHound) {
        List<PieceModel> result = new ArrayList<>();
        PieceModel foxModel = new FoxModel(chessboardModel);
        result.add(foxModel);
        PieceView fox = new FoxView(foxModel, chessboardView);
        FieldModel foxField = playerControlsHound ?
                chessboardModel.getField(0,ChessboardModel.size /2 - 1) :
                chessboardModel.getField(ChessboardModel.size - 1,ChessboardModel.size / 2);
        fox.place(foxField);
        for (int i = 0; i < ChessboardModel.size / 2; i++) {
            PieceModel houndModel = new HoundModel(chessboardModel, playerControlsHound);
            result.add(houndModel);
            PieceView houndView = new HoundView(houndModel, chessboardView);
            FieldModel houndField = playerControlsHound ?
                    chessboardModel.getField(ChessboardModel.size - 1, i * 2) :
                    chessboardModel.getField(0, i * 2 + 1);
            houndView.place(houndField);
        }
        return result;
    }

}
