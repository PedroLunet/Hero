import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {

    private Terminal terminal;
    private Screen screen;
    private Hero hero;
    private Arena arena;

    public Game(int  width, int height) {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don’t need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            arena = new Arena(10, 10);
            TextGraphics graphics = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position position) {
        arena.moveHero(position);
    }

    public void run() throws IOException {
        while(true){
        draw();
        KeyStroke key = screen.readInput();
        if (key != null) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF) {
                break;
            }

            processKey(key);
        }
        }
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        // Set the background color using a hexadecimal color code
        graphics.setBackgroundColor(TextColor.Factory.fromString("#BA7262"));

        // Clear the screen with the background color
        graphics.fill(' ');
        arena.draw(screen);
        screen.refresh();
    }


    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
    }


