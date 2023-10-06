import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Wall {

    private Position position;

    public Wall(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(Screen screen) {
        int x = position.getX();
        int y = position.getY();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('#')[0]);
    }
}
