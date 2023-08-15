package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriteUtil {

    public static void writeTxtFile(List object) {
        try {
            FileWriter fileWriter = new FileWriter("src/test/resources/ProductDetails/productInfo.txt"); // Replace with your file path
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Object line : object) {
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
