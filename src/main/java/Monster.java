import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    public void move() {
        // Define how the monster moves; for example, randomly in this case
        Random random = new Random();
        int dx = random.nextInt(3) - 1; // Random change in x-coordinate (-1, 0, 1)
        int dy = random.nextInt(3) - 1; // Random change in y-coordinate (-1, 0, 1)

        // Update the position of the monster
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        position = new Position(newX, newY);
    }

    public static List<Monster> createMonsters(int numMonsters, int width, int height) {
        List<Monster> monsters = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numMonsters; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            monsters.add(new Monster(x, y));
        }

        return monsters;
    }

    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#6C05AB"));
        graphics.putString(x, y, "M");
    }
}

