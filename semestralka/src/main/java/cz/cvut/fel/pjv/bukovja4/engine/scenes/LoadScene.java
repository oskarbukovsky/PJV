package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import cz.cvut.fel.pjv.bukovja4.Main;

final class LoadScene {
    static void Load(String sceneName) throws Throwable {
        
         Main.class.getResourceAsStream("/" + sceneName);
    }
}
