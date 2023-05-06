package core.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private String data;
    private List<String> dataLineByLine;

    public FileReader(String path) {
        try {
            File myObj = new File(path);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FOUNDATION ENGINE::::FILE READING ERROR: FILE NOT FOUND: " + path);
            e.printStackTrace();
        }
    }

    public FileReader(String path, String mode) {
        if(mode.equals("LineByLine")) {
            try{
                dataLineByLine = Files.readAllLines(Paths.get(path));
            } catch (IOException e) {
                System.out.printf("FOUNDATION ENGINE::::FILE READING ERROR: FILE NOT FOUND: " + path);
                e.printStackTrace();
            }
        } else {
            System.out.printf("FOUNDATION ENGINE::::FILE READING ERROR: File reading mode is not exist");
        }
    }

    public String getData(){
        return data;
    }

    public List<String> getDataLineByLine(){
        return dataLineByLine;
    }
}
