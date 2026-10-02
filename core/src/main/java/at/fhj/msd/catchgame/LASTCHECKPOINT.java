package at.fhj.msd.catchgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.fhj.msd.catchgame.world.GameWorld;
import at.fhj.msd.catchgame.entities.FallingObject;
import at.fhj.msd.catchgame.entities.Projectile;

public class LASTCHECKPOINT extends ApplicationAdapter {

    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private GameWorld world;

    private Texture hero;
    private Texture human;
    private Texture corruptedA;
    private Texture corruptedB;
    private Texture corruptedC;
    private Texture bullet;
    private Texture background;
    private Texture logo;

    private Sound gunshotSound;
    private Sound impactSound;
    private Sound gameOverSound;

    private Music introMusic;
    private Music backgroundMusic;

    private boolean introFinished = false;
    private boolean gameOverSoundPlayed = false;

    private boolean logoFinished = false;
    private float logoTimer = 0;

    private String[] introTexts = {
            "SYSTEM BOOTING...",
            "ACCESSING SURVIVOR DATABASE...",
            "YEAR: 2084",
            "GLOBAL STATUS: CRITICAL",
            "HUMAN POPULATION: 3%",
            "CORRUPTION LEVEL: 97%",
            "",
            "Only one checkpoint remains..."
    };

    private int currentLine = 0;
    private float introTimer = 0;

    @Override
    public void create() {
        logo = new Texture(
                Gdx.files.internal("lastCheckpoint.png")
        );

        spriteBatch = new SpriteBatch();

        font = new BitmapFont();
        font.getData().setScale(1.5f);

        world = new GameWorld();

        hero = new Texture(Gdx.files.internal("Charakter/hero.png"));
        human = new Texture(Gdx.files.internal("Charakter/human.png"));

        corruptedA = new Texture(Gdx.files.internal("Charakter/corruptedA.png"));
        corruptedB = new Texture(Gdx.files.internal("Charakter/corruptedB.png"));
        corruptedC = new Texture(Gdx.files.internal("Charakter/corruptedC.png"));

        bullet = new Texture(Gdx.files.internal("Bullets/bullet.png"));
        background = new Texture(Gdx.files.internal("background/background.png"));

        gunshotSound = Gdx.audio.newSound(Gdx.files.internal("soundeffekt/gunshot.mp3"));
        impactSound = Gdx.audio.newSound(Gdx.files.internal("soundeffekt/gunshot-impact.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("soundeffekt/GameOver.mp3"));

        introMusic = Gdx.audio.newMusic(Gdx.files.internal("soundeffekt/IntroMusik.mp3"));
        introMusic.setLooping(true);
        introMusic.setVolume(0.35f);
        introMusic.play();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("soundeffekt/Backgroundmusik.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f);
    }

    @Override
    public void render() {

        if (!logoFinished) {
            renderLogo();
            return;
        }

        if (!introFinished) {
            renderIntro();
            return;
        }

        float speed = 350;
        float delta = Gdx.graphics.getDeltaTime();

        if (world.isGameOver() && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            world = new GameWorld();
            gameOverSoundPlayed = false;

            if (!backgroundMusic.isPlaying()) {
                backgroundMusic.play();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P) && !world.isGameOver()) {
            world.togglePause();
        }

        if (!world.isGameOver() && !world.isPaused()) {
            world.update(delta);

            if (world.consumeProjectileHit()) {
                impactSound.play(0.8f);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                world.getPlayer().move(-speed * delta, 0);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                world.getPlayer().move(speed * delta, 0);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                world.shoot();
                gunshotSound.play(0.8f);
            }

            if (world.getPlayer().getX() < 0) {
                world.getPlayer().setX(0);
            }

            if (world.getPlayer().getX() > 590) {
                world.getPlayer().setX(590);
            }
        }

        if (world.isGameOver()) {
            if (backgroundMusic.isPlaying()) {
                backgroundMusic.stop();
            }

            if (!gameOverSoundPlayed) {
                gameOverSound.play(1f);
                gameOverSoundPlayed = true;
            }
        }

        Gdx.gl.glClearColor(world.isGameOver() ? 0.5f : 0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0, 640, 480);

        spriteBatch.draw(
                hero,
                world.getPlayer().getX(),
                world.getPlayer().getY(),
                80,
                80
        );

        for (Projectile p : world.getProjectiles()) {
            spriteBatch.draw(
                    bullet,
                    p.getX(),
                    p.getY(),
                    12,
                    24
            );
        }

        for (FallingObject obj : world.getFallingObjects()) {
            Texture enemyTexture;

            if (obj.getType() == FallingObject.Type.HUMAN) {
                enemyTexture = human;
            } else {
                if (world.getLevel() == 1) {
                    enemyTexture = corruptedA;
                } else if (world.getLevel() == 2) {
                    enemyTexture = corruptedB;
                } else {
                    enemyTexture = corruptedC;
                }
            }

            float scale = 15 + ((480 - obj.getY()) / 480f) * 55;

            spriteBatch.draw(
                    enemyTexture,
                    obj.getX(),
                    obj.getY(),
                    scale,
                    scale
            );
        }

        if (world.isGameOver()) {
            font.draw(
                    spriteBatch,
                    "GAME OVER! Final Score: " + world.getScore(),
                    145,
                    250
            );

            font.draw(
                    spriteBatch,
                    "Press ENTER to Restart",
                    190,
                    215
            );

        } else if (world.isPaused()) {

            font.draw(spriteBatch, "PAUSE", 280, 240);

            font.draw(
                    spriteBatch,
                    "Score: " + world.getScore() + "   Level: " + world.getLevel(),
                    20,
                    460
            );

        } else {

            font.draw(
                    spriteBatch,
                    "Score: " + world.getScore() + "   Level: " + world.getLevel(),
                    20,
                    460
            );
        }

        spriteBatch.end();
    }

    private void renderLogo() {

        logoTimer += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(
                logo,
                120,
                180,
                400,
                150
        );

        spriteBatch.end();

        if (logoTimer >= 3f) {
            logoFinished = true;
        }
    }

    private void renderIntro() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            introFinished = true;

            introMusic.stop();
            backgroundMusic.play();

            return;
        }

        float delta = Gdx.graphics.getDeltaTime();
        introTimer += delta;

        if (introTimer > 0.6f) {
            currentLine++;
            introTimer = 0;

            if (currentLine > introTexts.length) {
                currentLine = introTexts.length;
            }
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        for (int i = 0; i < currentLine; i++) {
            font.draw(
                    spriteBatch,
                    introTexts[i],
                    50,
                    420 - (i * 35)
            );
        }

        if (currentLine >= introTexts.length) {
            font.draw(spriteBatch, "PRESS ENTER TO START", 170, 100);
        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();

        hero.dispose();
        human.dispose();

        corruptedA.dispose();
        corruptedB.dispose();
        corruptedC.dispose();

        bullet.dispose();
        background.dispose();
        logo.dispose();

        gunshotSound.dispose();
        impactSound.dispose();
        gameOverSound.dispose();

        introMusic.dispose();
        backgroundMusic.dispose();
    }
}
