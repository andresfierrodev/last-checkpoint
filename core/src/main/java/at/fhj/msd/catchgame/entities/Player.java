package at.fhj.msd.catchgame.entities;

public class Player {

    private float x;
    private float y;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }
}
