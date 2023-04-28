package core.converter;

import core.os.FileReader;
import java.util.ArrayList;

public class TileMapConverter {
    private String dataFromFileReader;
    private ArrayList <Integer> dataToTileMap = new ArrayList<>();

    public TileMapConverter(String path) {
        FileReader fileReader = new FileReader(path);
        this.dataFromFileReader = fileReader.getData();
    }

    public void convert(){
        String[] dataBuffer = dataFromFileReader.split(";");
        dataToTileMap = convertStringArrayToIntegerArray(dataBuffer);
    }

    public ArrayList<Integer> convertStringArrayToIntegerArray(String[] data) {
        ArrayList<Integer> bufferData = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            try{
                bufferData.add(Integer.parseInt(data[i]));
            } catch (NumberFormatException ex) {
                System.out.println("BAG ENGINE::::TILEMAP CONVERT ERROR: The tilemap file is not correct!");
            }
        }
        return bufferData;
    }

    public ArrayList<Integer> getDataToTileMap() {
        return dataToTileMap;
    }
}
