import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {

    private Terminal terminal;
    private Screen screen;
    private Hero hero;

    public Game() {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don’t need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            hero = new Hero(10, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        draw();
        KeyStroke key = screen.readInput();
        if (key != null) {
            processKey(key);
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp) {
            hero.moveUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            hero.moveDown();
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            hero.moveLeft();
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            hero.moveRight();
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            try {
                screen.close(); // Close the screen
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.getKeyType() == KeyType.EOF) {
            try {
                screen.close(); // Close the screen
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

