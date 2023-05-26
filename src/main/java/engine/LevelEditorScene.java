package engine;

import components.Animation;
import components.SpriteRenderer;
import components.Spritesheet;
import components.TileMap;
import core.converter.CollisionDetectionConverter;
import core.physics.CollisionBox;
import org.joml.Vector2f;
import core.os.AssetPool;
import renderer.LineRenderBatch;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class LevelEditorScene extends Scene{
    private GameObject obj1;
    private Spritesheet sprites;

    Animation animationUp = new Animation(4, 7);
    Animation animationDown = new Animation(0, 3);
    Animation animationRight = new Animation(8, 11);
    Animation animationLeft = new Animation(12, 15);
    Animation animationStop = new Animation(0, 0);

    CollisionBox collisionBox1;
    CollisionBox collisionBox2;

    private CollisionDetectionConverter collisionDetectionConverter;

    float[] points  = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                        100.0f, 100.0f, 1.0f, 0.0f, 0.0f, 1.0f};

    private LineRenderBatch lineRenderBatch;


    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        sprites = AssetPool.getSpritesheet("assets/Character/PlayerMovement.png");

        lineRenderBatch = new LineRenderBatch(points);

        TileMap tileMap = new TileMap("assets/test/test.txt", "LineByLine",
                "assets/Tilesets/ground tiles/new tiles/Grass hill tiles v.2.png",
                16, 64,77, -1);

        tileMap.generateTileMap();
        tileMapGameObjectsAttachToRenderer(tileMap.getSprites());



        obj1 = new GameObject("Object 1", new Transform(new Vector2f(0 ,0),
                new Vector2f(100, 100)) , 1);
        obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);

        collisionBox1 = new CollisionBox(new Transform(new Vector2f(0,0),
                new Vector2f(100, 100)), new Vector2f(100, 100));
        collisionBox2 = new CollisionBox(new Transform(new Vector2f(128,0),
                new Vector2f(100, 100)), new Vector2f(64, 64));



        collisionDetectionConverter = new CollisionDetectionConverter("assets/test/coll.txt");
        ArrayList<Integer> test =  collisionDetectionConverter.getDataForInitCollisionBoxes();
        for(int i : test){
            System.out.println(i);
        }



    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/Character/PlayerMovement.png",
                new Spritesheet(AssetPool.getTexture("assets/Character/PlayerMovement.png"),
                        16, 16, 16, 0));
    }

    @Override
    public void update(float dt) {
        Transform previousPosition = obj1.transform.copy();


        // FPS Counter
        //System.out.println("FPS: " + (1.0 / dt));

        // Movement
        if(KeyListener.isKeyPressed(KeyEvent.VK_D)) {
            obj1.transform.position.x += 100 * dt;
            collisionBox1.changePos(new Vector2f(collisionBox1.transform.position.x + 100 * dt, collisionBox1.transform.position.y));
        }
        if(KeyListener.isKeyPressed(KeyEvent.VK_A)) {
            obj1.transform.position.x -= 100 * dt;
            collisionBox1.changePos(new Vector2f(collisionBox1.transform.position.x - 100 * dt, collisionBox1.transform.position.y));
        }
        if(KeyListener.isKeyPressed(KeyEvent.VK_W)) {
            obj1.transform.position.y += 100 * dt;
            collisionBox1.changePos(new Vector2f(collisionBox1.transform.position.x, collisionBox1.transform.position.y + 100 * dt));
        }
        if(KeyListener.isKeyPressed(KeyEvent.VK_S)) {
            obj1.transform.position.y -= 100 * dt;
            collisionBox1.changePos(new Vector2f(collisionBox1.transform.position.x, collisionBox1.transform.position.y - 100 * dt));
        }

        if(obj1.transform.position.x < previousPosition.position.x) {
            animationLeft.play(dt, obj1, sprites);
        } else if (obj1.transform.position.y < previousPosition.position.y) {
            animationDown.play(dt, obj1, sprites);
        } else if (obj1.transform.position.y > previousPosition.position.y) {
            animationUp.play(dt, obj1, sprites);
        } else if (obj1.transform.position.x > previousPosition.position.x) {
            animationRight.play(dt, obj1, sprites);
        } else if (obj1.transform.position.x == previousPosition.position.x) {
            animationStop.play(dt, obj1, sprites);
        }

        if (collisionBox1.isThatCollited(collisionBox2)){
            System.out.println("COLLITED");
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
        lineRenderBatch.render();
    }


    public void tileMapGameObjectsAttachToRenderer(ArrayList<GameObject> sprites) {
        for(int i = 0; i < sprites.size(); i++) {
           this.addGameObjectToScene(sprites.get(i));
        }
    }
}
