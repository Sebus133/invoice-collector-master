package pl.iterative.invoicecollector;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class GoogleApiTest {
    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1haqPGTtFXgDKEyTYC--IUYaTzkiW0wkwEnQh7cSRfas";

    @BeforeAll
    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }


    public List<List<Object>> getLinesFromCsv() {
        String filename = "C:\\Users\\filip\\Desktop\\nowy.csv";
        File file = new File(filename);

        List<List<Object>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }


    @Test
    public void whenWriteSheet_thenReadSheetOk() throws IOException {
        List<List<Object>> linesFromCsv = getLinesFromCsv();
        Invoice invoice = new Invoice();
        //ValueRange range = new ValueRange();
       // for(List<Object> line : linesFromCsv){
       //     range.setValues(linesFromCsv);
       // }

        ValueRange range = new ValueRange().setValues(Arrays.asList(
                Arrays.asList(invoice.acquisitionTimestamp),
                Arrays.asList(invoice.invoicingDate),
                Arrays.asList(invoice.currency),
                Arrays.asList(invoice.ksefReferenceNumber),
                Arrays.asList(invoice.net),
                Arrays.asList(invoice.vat)
        ));





    UpdateValuesResponse result = sheetsService.spreadsheets().values()
            .update(SPREADSHEET_ID,"A1",range)
            .setValueInputOption("RAW")
            .execute();
    }

}
