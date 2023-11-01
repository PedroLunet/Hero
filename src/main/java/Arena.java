import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        walls = createWalls();
        coins = createCoins();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFEFDA"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');


        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        hero.draw(graphics);
    }
    public void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(hero.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        int newX = position.getX();
        int newY = position.getY();

        if (newX < 1 || newX >= width - 1 || newY < 1 || newY >= height - 1) {
            return false;
        }

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }

        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            if (coin.getPosition().equals(hero.getPosition())) {
                coinIterator.remove();
            }
        }

        return true;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        int wallLength = 5;


        int wallDistanceX = 8;
        int wallDistanceY = 7;

        for (int r = wallDistanceY; r < wallDistanceY + wallLength; r++) {
            walls.add(new Wall(wallDistanceX, r));
        }
        for (int c = wallDistanceX; c < wallDistanceX + wallLength; c++) {
            walls.add(new Wall(c, wallDistanceY));
        }

        int wallDistanceX2 = width - 20;
        int wallDistanceY2 = height - 12;

        for (int r = wallDistanceY2; r < wallDistanceY2 + wallLength; r++) {
            walls.add(new Wall(wallDistanceX2 + wallLength - 1, r));
        }
        for (int c = wallDistanceX2; c < wallDistanceX2 + wallLength; c++) {
            walls.add(new Wall(c, wallDistanceY2 + wallLength - 1));
        }


        int uWallDistanceX1 = 30;
        int uWallDistanceY1 = 15;

        for (int r = uWallDistanceY1; r < uWallDistanceY1 + wallLength; r++) {
            walls.add(new Wall(uWallDistanceX1, r));
            walls.add(new Wall(uWallDistanceX1 + wallLength - 1, r));
        }
        for (int c = uWallDistanceX1 + 1; c < uWallDistanceX1 + wallLength - 1; c++) {
            walls.add(new Wall(c, uWallDistanceY1));
        }

        int vLineDistanceX = 20;
        int vLineStartY = 5;
        int vLineEndY = vLineStartY + 4;

        for (int r = vLineStartY; r <= vLineEndY; r++) {
            walls.add(new Wall(vLineDistanceX, r));
        }


        int hWallStartX = 10;
        int hWallEndX = hWallStartX + 6;
        int hWallDistanceY = 20;

        for (int c = hWallStartX; c <= hWallEndX; c++) {
            walls.add(new Wall(c, hWallDistanceY));
        }

        int hWallStartX2 = 40;
        int hWallEndX2 = hWallStartX2 + 7;
        int hWallDistanceY2 = 5;

        for (int c = hWallStartX2; c <= hWallEndX2; c++) {
            walls.add(new Wall(c, hWallDistanceY2));
        }


        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        int minDistanceBetweenCoins = 4;

        while (coins.size() < 20) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;

            boolean isValidPosition = true;

            for (Coin existingCoin : coins) {
                int coinX = existingCoin.getPosition().getX();
                int coinY = existingCoin.getPosition().getY();

                if (Math.abs(x - coinX) < minDistanceBetweenCoins
                        && Math.abs(y - coinY) < minDistanceBetweenCoins) {
                    isValidPosition = false;
                    break;
                }
            }

            if (isValidPosition) {
                coins.add(new Coin(x, y));
            }
        }

        return coins;
    }
}
