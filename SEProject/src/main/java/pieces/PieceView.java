/**
 * This class is the visual representation of our board and pieces
 */

package pieces;

import board.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class PieceView {
    protected Circle viewRepresentation;
    private PieceModel pieceModel;
    private FieldView fieldView;
    private ChessboardView chessboardView;

    /**
     * Get the table view and put the pieces on it
     * @param pieceModel
     * @param chessboardView
     */
    PieceView(PieceModel pieceModel, ChessboardView chessboardView) {
        this.pieceModel = pieceModel;
        this.chessboardView = chessboardView;
        Circle viewRepresentation = new Circle();
        viewRepresentation.getStyleClass().add("piece");
        viewRepresentation.radiusProperty().bind(chessboardView.getPieceRadiusProperty());
        this.viewRepresentation = viewRepresentation;
    }

    public Circle getViewRepresentation() {
        return viewRepresentation;
    }

    /**
     * This describes a field in the board
     * @param fieldModel
     */
    void place(FieldModel fieldModel) {
        if (pieceModel.place(fieldModel)) {
            chessboardView.addPiece(this.viewRepresentation, fieldModel.getCol(), fieldModel.getRow());
            this.fieldView = chessboardView.getFieldView(fieldModel);
            this.fieldView.addEventHandlers(this.viewRepresentation);
            this.fieldView.setPieceView(this);
        }
    }

    /**
     * This is the part where we decide what happens to a tile, when the user interacts with it.
     * @param fieldModel
     */
    public void move(FieldModel fieldModel) {
        if (pieceModel.move(fieldModel)) {
            this.moveWithoutColorChange(fieldModel);
        /* without this color setting, mouse leaves the circle
        without entering it after the move, resulting in
        the circle getting darker and darker
        */
            this.makeBrighter();
        }
    }

    public void moveWithoutColorChange(FieldModel fieldModel) {
        fieldView.setPieceView(null);
        fieldView = chessboardView.getFieldView(fieldModel);
        fieldView.setPieceView(this);
        fieldView.addEventHandlers(this.viewRepresentation);
        chessboardView.movePiece(this, fieldModel.getCol(), fieldModel.getRow());
    }

    public void makeBrighter() {
        viewRepresentation.setFill(Color.valueOf(viewRepresentation.getFill().toString()).brighter());
    }

    public void makeDarker() {
        viewRepresentation.setFill(Color.valueOf(viewRepresentation.getFill().toString()).darker());
    }


}
