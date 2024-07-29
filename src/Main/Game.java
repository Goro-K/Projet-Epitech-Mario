package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import Character.Entity;
import Character.Enemy.Goomba;
import Character.Enemy.Koopa;
import Character.Enemy.HammerMan;
import Character.Enemy.Fire;
import Character.Mario.Mario;
import Map.Camera;
import Map.GameMap;
import Map.Tiles;
import Map.Obstacle.Obstacle;
import Music.mainMusic;
import Utils.CollisionManager;

public class Game implements Runnable {

    private Windows window; // AJout de la scene window
    private Scene scene; // Ajout de la scene gamePanel
    private Thread gameThread; // Ajout du thread
    private final int FPS = 120; // Ajout des fps
    private Mario mario; // Ajout du player
    private mainMusic music; // Ajout de la musique

    private Goomba goomba; // ajout d'un goomba
    private Koopa koopa; // ajout d'un koopa
    private HammerMan hammerMan;
    private Fire fire;
    private List<Koopa> arrayKoopas = new ArrayList<Koopa>();
    private List<Goomba> arrayGoombas = new ArrayList<Goomba>();
    private List<HammerMan> arrayHammerMans = new ArrayList<HammerMan>();
    private List<Fire> arrayFires = new ArrayList<Fire>();


    private long lastGenerationKoopa = System.currentTimeMillis(); //pour générer aléatoirement dans le temps
    private long lastGenerationGoomba = System.currentTimeMillis();
    private long lastGenerationHammerMan = System.currentTimeMillis();
    private long lastGenerationFire = System.currentTimeMillis();
    private Obstacle obstacle;
    private GameMap gameMap;

    private Random random = new Random();
    private CollisionManager collisionManager;

    private Camera camera;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;
    public static final int MAP_WIDTH = 9000; 
    public static final int MAP_HEIGHT = 1000;
    public Game() {
        music = new mainMusic();
        music.musicTheme();
        initClasses(); // Initialisation des classes
        collisionManager = new CollisionManager(gameMap);

        scene = new Scene(this); // Scene initialisé avant demerrage de la fenetre
        window = new Windows(scene);
        scene.setFocusable(true);
        scene.requestFocus(); // Permet de focus la fenetre pour que le keyListener fonctionne
        startGameLoop(); // Demarrage du gameLoop
    }

    private void initClasses() {
        // arrayGoombas.add(goomba);
        // arrayKoopas.add(koopa);
        mario = new Mario(this, 50, 422, 26, 30, 0.20f);
        goomba = new Goomba(600, 422, 26, 28, 0.5f);
        koopa = new Koopa(200, 405, 28, 30, 0.5f);
        hammerMan = new HammerMan(500, 405, 28, 30, 0.5f);
        fire = new Fire(496, 405, 28, 30, 0.5f);


        int mapWidth = 1000;
        int mapHeight = 10;

        int[][] map = generateRandomMap(mapWidth, mapHeight);
        gameMap = new GameMap(map, 50);
        camera = new Camera(0);
    }

    private int[][] generateRandomMap(int width, int height) {
        int[][] map = new int[height][width];

        for (int j = 0; j < width; j++) {
            if (Math.random() < 0.2) {
                map[height - 1][j] = Tiles.GROUND;
                map[height - 2][j] = Tiles.PIPE;
            } else {
                if (j > 0 && map[height - 1][j - 1] == Tiles.SKY) { // On vérifie que qu'il n'y est pas plusieurs fois
                                                                     // à la suite des trous
                    map[height - 1][j] = Tiles.GROUND;
                } else {
                    map[height - 1][j] = Math.random() < 0.15 ? Tiles.SKY : Tiles.GROUND;
                }
                map[height - 2][j] = Tiles.SKY;
            }
        }

        for (int i = 0; i < height - 2; i++) {
            for (int j = 0; j < width; j++) {
                if (Math.random() < 0.06) {
                    map[i][j] = Tiles.CLOUD;
                } else {
                    map[i][j] = Tiles.SKY;
                }
            }
        }
   
    for (int j = 0; j < width; j++) {
        if (Math.random() < 0.06) {
              map[height - 4][j] = Tiles.MYSTERYBLOCK;
        } 
        else {
            if (j > 0 && map[height - 2][j] == Tiles.PIPE){
                map[height - 4][j] = Tiles.SKY;
            }
        }
    }
        
        map[height - 1][2] = Tiles.GROUND;
        return map;
    }

    private void startGameLoop() {
        gameThread = new Thread(this); // Création du thread
        gameThread.start(); // Demarrage du thread
    }

    public void update() {
        mario.update();
        mario.setOnGround(false);
        mario.setBelowObstacle(false);
    
        for (Entity enemy : arrayGoombas) {
            enemy.update();
        }
        for (Entity enemy : arrayKoopas) {
            enemy.update();
        }
        for (Entity enemy : arrayHammerMans) {
            enemy.update();
        }
        for (Entity enemy : arrayFires) {
            enemy.update();
        }
        randomCreate();

        collisionManager.checkCollisionWithObstacles(mario);
        collisionManager.checkCollisionWithObstacles(goomba);
        collisionManager.checkCollisionWithObstacles(koopa);
        collisionManager.checkCollisionWithObstacles(hammerMan);
        collisionManager.checkCollisionWithObstacles(fire);

        camera.update(mario);
    }

    public void randomCreate() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastGenerationKoopa > 5000) { // apparait tjr au même moment ???
            createEnemy(2);//id 2 = koopa
            lastGenerationKoopa = currentTime;
        }
        if (currentTime - lastGenerationGoomba > 3000) { // apparait tjr au même moment ???
            createEnemy(1);//id 1 = goomba
            lastGenerationGoomba = currentTime;
        }
        if (currentTime - lastGenerationHammerMan > 8000) { 
            createEnemy(3);//id 3 = hammerMan
            lastGenerationHammerMan = currentTime;
        }

        if (currentTime - lastGenerationFire > 8100) { 
            createEnemy(4);//id 4 = hammerMan
            lastGenerationFire = currentTime;
        }
    }

    public void createEnemy(int id) {
        
        if (arrayGoombas.size() <= 0 && id == 1) {
            Goomba g = new Goomba(920, 422, 28, 30, 0.5f);
            arrayGoombas.add(g);
        }
        if (arrayKoopas.size() <= 0 && id == 2) {
            Koopa k = new Koopa(1000, 405, 28, 30, 0.5f);
            k.setAppeared();
            arrayKoopas.add(k);
        }
        if (arrayHammerMans.size() <= 0 && id == 3) {
            HammerMan k = new HammerMan(1500, 405, 28, 30, 0.5f);
            k.setAppeared();
            arrayHammerMans.add(k);
        }
        if (arrayFires.size() <= 0 && id == 4) {
            Fire k = new Fire(1496, 405, 28, 30, 0.5f);
            k.setAppeared();
            arrayFires.add(k);
        }
    }
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(-camera.getX(), 0);
        gameMap.render(g);
        mario.render(g);
        for (Koopa enemy : arrayKoopas) {
            enemy.render(g);
        }
        for (Goomba enemy : arrayGoombas) {
            enemy.render(g);
        }
        for (HammerMan enemy : arrayHammerMans) {
            enemy.render(g);
        }
        for (Fire enemy : arrayFires) {
            enemy.render(g);
        }

        g2.translate(camera.getX(), 0);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS; // 1 seconde en nano sceonde parce que une frame est représenté en
                                                  // milliseconde
        long lastFrame = System.nanoTime(); // Temps de la dernière frame
        long now = System.nanoTime(); // Temps actuel
        int frames = 0; // gestion des fps
        long lastCheck = System.currentTimeMillis(); // gestion des fps
        int updates = 0;

        while (true) {
            now = System.nanoTime(); // Temps actuel
            if (now - lastFrame >= timePerFrame) { // Si le temps entre la dernière frame et la frame
                                                   // actuelle est supérieur au temps par frame
                update(); // update = update le gameLoop du jeu
                updates++;
                scene.repaint(); // repaint = rafraichir la fenetre (agit sur le gameLoop du jeu)
                lastFrame = System.nanoTime(); // reset le timer
                frames++; // ajoute 1 au compteur de fps

            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis(); // reset le timer
                // System.out.println("FPS: " + frames);
                frames = 0; // reset le compteur de fps
                updates = 0;
            }
        }
    }

    public void pause() {
        mario.resetDirBooleans();
    }

    public Mario getPlayer() {
        return mario;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public Koopa getKoopa() {
        return koopa;
    }

    public HammerMan getHammerMan() {
        return hammerMan;
    }

    public Fire getFire() {
        return fire;
    }
}
