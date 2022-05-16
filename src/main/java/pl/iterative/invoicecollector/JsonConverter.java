package pl.iterative.invoicecollector;

import org.apache.commons.io.FileUtils;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonConverter {
    @SuppressWarnings("deprecation")
    public static void main(String args[]) {
        String jsonString;
        JSONObject jsonObject;
        try {
            jsonString = new String(
                    Files.readAllBytes(Paths.get("C:\\Users\\filip\\Desktop\\iterative-master\\src\\main\\resources\\Image Pasted at 2022-5-12 10-03.json")));
            jsonObject = new JSONObject(jsonString);
            JSONArray docs
                    = jsonObject.getJSONArray("test");
            File file = new File("C:\\Users\\filip\\Desktop\\crack\\Test.csv");
            String csvString = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csvString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }
}