package core.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private File file;
    private String path;
    private String data;


    public FileReader(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("BAG ENGINE::::FILE READING ERROR: FILE NOT FOUND: " + path);
            e.printStackTrace();
        }
    }

    public String getData(){
        
    }




}
