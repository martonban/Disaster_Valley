package engine;

import components.SpriteRenderer;
import components.Spritesheet;
import components.TileMap;
import org.joml.Vector2f;
import core.os.AssetPool;

import java.util.ArrayList;

public class LevelEditorScene extends Scene{
    private GameObject obj1;
    private Spritesheet sprites;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        sprites = AssetPool.getSpritesheet("assets/textures/spritesheet.png");

        TileMap tileMap = new TileMap("assets/test/test.txt",
                "assets/Tilesets/ground tiles/new tiles/Grass hill tiles v.2.png",
                16, 64,77, -1);

        tileMap.generateTileMap();
        tileMapGameObjectsAttachToRenderer(tileMap.getSprites());

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100,100),
                new Vector2f(100, 100)) , 1);
        obj1.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);

        /*
        GameObject obj3 = new GameObject("Object 1", new Transform(new Vector2f(400,100),
                new Vector2f(256, 256)) , 1);
        obj3.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(obj3);
        */

    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/textures/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/textures/spritesheet.png"),
                        16, 16, 26, 0));
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
    @Override
    public void update(float dt) {
        // Camera Changing
        // camera.position.x -= dt * 50.0f;
        // camera.position.y -= dt * 30.0f;

        // FPS Counter
        //System.out.println("FPS: " + (1.0 / dt));

        // Movement
        // obj1.transform.position.x += 10 * dt;

        // Animation
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if(spriteIndex > 4) {
                spriteIndex = 0;
            }
            obj1.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }

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
