package Utility;

import java.io.*;
import java.util.Scanner;

public class ReaderFromFile {
    public String readFromFile(String filename) throws FileNotFoundException {
        try {
            String data = "";
            File file = new File(filename);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line= bufferedReader.readLine())!=null){
                data+=line+"\n";
            }
            WriterToFile.setFilename(filename);
            return data;
        } catch (FileNotFoundException | NullPointerException e) {
            while (filename == (null)) {
                System.out.println("Вы забыли указать имя файла. Укажите имя файла сейчас:");
                String newFilename = Console.read();
                if (!(newFilename.equals(""))) {
                    WriterToFile.setFilename(filename);
                    break;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
