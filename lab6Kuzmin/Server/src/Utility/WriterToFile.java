package Utility;

import Stuff.LabWork;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;

public class WriterToFile {
    static String filename ;

    public static void setFilename(String filename) {
        WriterToFile.filename = filename;
    }



    public static void writeLabToFile(HashSet<LabWork> labs) throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            File file = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gson.toJson(labs).getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}