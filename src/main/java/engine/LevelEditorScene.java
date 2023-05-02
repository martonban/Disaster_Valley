package engine;

import components.Animation;
import components.SpriteRenderer;
import components.Spritesheet;
import components.TileMap;
import org.joml.Vector2f;
import core.os.AssetPool;

import java.util.ArrayList;

public class LevelEditorScene extends Scene{
    private GameObject obj1;
    private Spritesheet sprites;

    Animation animation1 = new Animation(0,3);
    Animation animation2 = new Animation(7);
    Animation animation3 = new Animation(16);

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        sprites = AssetPool.getSpritesheet("assets/Character/PlayerMovement.png");

        TileMap tileMap = new TileMap("assets/test/test.txt",
                "assets/Tilesets/ground tiles/new tiles/Grass hill tiles v.2.png",
                16, 64,77, -1);

        tileMap.generateTileMap();
        tileMapGameObjectsAttachToRenderer(tileMap.getSprites());

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100,100),
                new Vector2f(100, 100)) , 1);
        obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/Character/PlayerMovement.png",
                new Spritesheet(AssetPool.getTexture("assets/Character/PlayerMovement.png"),
                        16, 16, 16, 0));
    }

    @Override
    public void update(float dt) {
        // Camera Changing
        // camera.position.x -= dt * 50.0f;
        // camera.position.y -= dt * 30.0f;

        // FPS Counter
        System.out.println("FPS: " + (1.0 / dt));

        // Movement
        // obj1.transform.position.x += 10 * dt;

        // Animation
        animation1.play(dt, obj1, sprites);


        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
    }

    public void tileMapGameObjectsAttachToRenderer(ArrayList<GameObject> sprites) {
        for(int i = 0; i < sprites.size(); i++) {
           this.addGameObjectToScene(sprites.get(i));
        }
    }
}
