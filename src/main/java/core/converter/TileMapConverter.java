package core.converter;

import core.os.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TileMapConverter {
    private String dataFromFileReader;
    private ArrayList <String> dataFromFileReaderLineByLine = new ArrayList<>();

    // Return value
    private ArrayList <Integer> dataToTileMap = new ArrayList<>();
    String mode = "default";

    public TileMapConverter(String path) {
        FileReader fileReader = new FileReader(path);
        this.dataFromFileReader = fileReader.getData();
    }

    public TileMapConverter(String path, String mode) {
        this.mode = mode;
        FileReader fileReader = new FileReader(path, mode);
        this.dataFromFileReaderLineByLine = (ArrayList<String>)fileReader.getDataLineByLine();
    }

    public void convert(){
        if(mode.equals("default")){
            String[] dataBuffer = dataFromFileReader.split(";");
            dataToTileMap = convertStringArrayToIntegerArray(dataBuffer);
        }else {
            String dataBuffer[] = new String[dataFromFileReaderLineByLine.size() * 3];
            for(int i = 0, j = 0; i < dataFromFileReaderLineByLine.size(); i++, j += 3) {
                String[] buffer = dataFromFileReaderLineByLine.get(i).split(";");
                dataBuffer[j] = buffer[0];
                dataBuffer[j + 1] = buffer[1];
                dataBuffer[j + 2] = buffer[2];
            }
            dataToTileMap = convertStringArrayToIntegerArray(dataBuffer);
        }

    }

    public ArrayList<Integer> convertStringArrayToIntegerArray(String[] data) {
        ArrayList<Integer> bufferData = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            try{
                bufferData.add(Integer.parseInt(data[i]));
            } catch (NumberFormatException ex) {
                System.out.println("FOUNDATION ENGINE::::TILEMAP CONVERT ERROR: The tilemap file is not correct!");
            }
        }
        return bufferData;
    }

    public ArrayList<Integer> getDataToTileMap() {
        return dataToTileMap;
    }
}
