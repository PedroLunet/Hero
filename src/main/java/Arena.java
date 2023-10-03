import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    //private Screen screen;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
    }

    public void draw(Screen screen) {
        hero.draw(screen);
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp) {
            hero.setPosition(hero.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            hero.setPosition(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            hero.setPosition(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            hero.setPosition(hero.moveRight());
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        return (position.getX() >= 0 && position.getX() < width) && ((position.getY() >= 0 && position.getY() < height));

    }
}
