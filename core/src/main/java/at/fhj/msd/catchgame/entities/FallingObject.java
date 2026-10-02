package at.fhj.msd.catchgame.entities;

public class FallingObject {
    public enum Type { MONSTER, HUMAN }
    private float x, y, speed;
    private Type type;
    private float drift;

    public FallingObject(float x, float y, Type type, float speed) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.speed = speed;

        if (x < 320) {
            drift = -15f;
        } else {
            drift = 15f;
        }
    }

    public void update(float delta) { y -= speed * delta; x += drift * delta; }
    public float getX() { return x; }
    public float getY() { return y; }
    public Type getType() { return type; }
}
