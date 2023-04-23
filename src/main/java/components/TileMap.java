package components;

import core.converter.TileMapConverter;
import engine.GameObject;
import org.joml.Vector2f;

import java.util.ArrayList;

public class TileMap {

    private ArrayList<GameObject> sprites = new ArrayList<>();
    private ArrayList<Integer> dataFromTileMapFile = new ArrayList<>();
    private Spritesheet spritesheet;

    private int zIndex;
    private Vector2f oneTileSize;

    private String path;


    public TileMap(String tileMapFilePath, Vector2f oneTileSize, int zIndex) {
        TileMapConverter tileMapConverter = new TileMapConverter(tileMapFilePath);
        tileMapConverter.convert();
        dataFromTileMapFile = tileMapConverter.getDataToTileMap();
    }
}
