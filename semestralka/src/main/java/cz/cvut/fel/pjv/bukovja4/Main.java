package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;
import cz.cvut.fel.pjv.bukovja4.utils.Utils;

import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("MainThread");

        LOG.info("Starting game...");

        GameLoop game = new GameLoop("GameLoop111");
        game.start();

        Clock clock = new Clock(Const.DESIRED_TPS);
        clock.start();

        Utils.CloseGame();
    }
}