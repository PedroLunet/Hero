import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x, y);
    }
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#CD7B0C"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(x,y), "o");
    }
}
