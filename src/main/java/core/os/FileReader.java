package core.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String data;

    public FileReader(String path) {
        try {
            File myObj = new File(path);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("BAG ENGINE::::FILE READING ERROR: FILE NOT FOUND: " + path);
            e.printStackTrace();
        }
    }

    public String getData(){
        return data;
    }
}
