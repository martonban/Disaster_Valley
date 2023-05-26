package core.converter;

import core.os.FileReader;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetectionConverter {

    private List<String> dataFromFileReader;

    // OUTPUT
    private ArrayList<Integer> dataForInitCollisionBoxes;

    public CollisionDetectionConverter(String path) {
        FileReader fileReader = new FileReader(path, "LineByLine");
        dataFromFileReader = fileReader.getDataLineByLine();
        dataForInitCollisionBoxes = convert(dataFromFileReader);
    }

    public ArrayList<Integer> convert(List<String> dataFromFileReader) {
        // Result
        ArrayList<Integer> dataBufferToFunctionResult = new ArrayList<>();
        String[] dataBufferForSplit = new String[dataFromFileReader.size() * 4];
        for(int i = 0; i < dataFromFileReader.size(); i++){
            String[] buffer = dataFromFileReader.get(i).split(";");
            dataBufferToFunctionResult.add(stringToIntegerConverter(buffer[0]));
            dataBufferToFunctionResult.add(stringToIntegerConverter(buffer[1]));
            dataBufferToFunctionResult.add(stringToIntegerConverter(buffer[2]));
            dataBufferToFunctionResult.add(stringToIntegerConverter(buffer[3]));
        }

        return dataBufferToFunctionResult;
    }

    public int stringToIntegerConverter(String data) {
        try {
            Integer.parseInt(data);
        }catch (NumberFormatException ex) {
            System.out.println("FOUNDATION ENGINE::::COLLISION DATA IS NOT VALID!");
            ex.printStackTrace();
        }
        return Integer.parseInt(data);
    }

    public ArrayList<Integer> getDataForInitCollisionBoxes() {
        return dataForInitCollisionBoxes;
    }
}
