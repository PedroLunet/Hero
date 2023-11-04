import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY() );
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());

    }
    @Override
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#8D0CCD"));
        graphics.putString(x, y, "M");
    }
}
