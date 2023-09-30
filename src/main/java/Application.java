import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import com.googlecode.lanterna.input.KeyStroke;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        try {
            while(true) {
                game.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}