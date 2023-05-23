package core.converter;

import core.os.FileReader;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetectionConverter {

    private List<String> dataFromFileReader;
    private ArrayList<Integer> dataForInitCollisionBoxes;

    public CollisionDetectionConverter(String path) {
        FileReader fileReader = new FileReader(path, "LineByLine");
        dataFromFileReader = fileReader.getDataLineByLine();
        dataForInitCollisionBoxes = convert(dataFromFileReader);
    }

    public ArrayList<Integer> convert(List<String> data) {
        ArrayList<Integer> buffer = new ArrayList<>();
        return buffer;
    }

    public ArrayList<Integer> getDataForInitCollisionBoxes() {
        return dataForInitCollisionBoxes;
    }
}
