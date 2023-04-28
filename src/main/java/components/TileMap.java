package components;

import core.converter.TileMapConverter;
import core.os.AssetPool;
import engine.GameObject;
import engine.Transform;
import org.joml.Vector2f;

import java.util.ArrayList;

public class TileMap {
    private ArrayList<GameObject> sprites = new ArrayList<>();
    private ArrayList<Integer> dataFromTileMapFile = new ArrayList<>();
    private Spritesheet spritesheet;

    private int zIndex;
    private int oneTileSize;
    private String path;

    public TileMap(String tileMapFilePath, String tileMapImagePath , int oneTileSizeReal, int oneTileSize, int numberOfSprites, int zIndex) {
        // Data From File Reader
        TileMapConverter tileMapConverter = new TileMapConverter(tileMapFilePath);
        tileMapConverter.convert();
        dataFromTileMapFile = tileMapConverter.getDataToTileMap();

        // AssetsPoolAdd
        AssetPool.addSpritesheet(tileMapImagePath,
                new Spritesheet(AssetPool.getTexture(tileMapImagePath),
                        oneTileSizeReal, oneTileSizeReal, numberOfSprites, 0));

        // Other data
        this.zIndex = zIndex;
        this.spritesheet = AssetPool.getSpritesheet(tileMapImagePath);
        this.oneTileSize = oneTileSize;
        this.path = tileMapImagePath;
    }

    public void generateTileMap() {
        for(int i = 0; i < dataFromTileMapFile.size(); i += 3) {
            GameObject obj = new GameObject("Sprite " + i, new Transform(new Vector2f(dataFromTileMapFile.get(i),dataFromTileMapFile.get(i+1)),
                    new Vector2f(oneTileSize, oneTileSize)), zIndex);
            obj.addComponent(new SpriteRenderer(spritesheet.getSprite(dataFromTileMapFile.get(i+2))));
            sprites.add(obj);
        }
    }

    public ArrayList<GameObject> getSprites() {
        return sprites;
    }
}
