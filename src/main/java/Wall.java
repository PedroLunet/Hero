import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{


    public Wall(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#494847"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(x,y), "#");
    }
}
