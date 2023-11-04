import com.googlecode.lanterna.TerminalSize;
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
    private Arena arena;

    public Game(int  width, int height) {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            arena = new Arena(width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() throws IOException {
        while (arena.isHeroAlive()) {
            draw();
            KeyStroke key = screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    break;
                }
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
                processKey(key);
            }
        }

        screen.close();

        if (!arena.isHeroAlive()) {
            System.out.println("Game Over! Your Hero died!");
        }
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        screen.refresh();
    }


    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
}


