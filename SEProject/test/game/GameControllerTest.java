package game;

import org.junit.jupiter.api.Test;

class GameControllerTest {

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
