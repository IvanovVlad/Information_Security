import Form.MainForm;
import SecuritySystem.Checker;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        CsvParser csvData = new CsvParser().parse("main/resources/csv/matrix.csv");
        new MainForm(csvData.subjects, csvData.objects, csvData.grid, csvData.matrix);
    }
}
