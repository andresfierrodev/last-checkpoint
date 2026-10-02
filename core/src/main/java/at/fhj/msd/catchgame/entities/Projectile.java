package at.fhj.msd.catchgame.entities;

public class Projectile {
    private float x, y;
    private float speed = 500f;

    public Projectile(float x, float y) { this.x = x; this.y = y; }
    public void update(float delta) { y += speed * delta; }
    public float getX() { return x; }
    public float getY() { return y; }
}