import FileManager.Catalog;
import FileManager.FileObjectFM;
import Form.MainForm;
import SecuritySystem.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class main {
    public static void main(String[] args) throws IOException {
        CsvParser csvData = new CsvParser().parse("main/resources/csv/matrix.csv");
        MainForm form = new MainForm(csvData.subjects, csvData.objects, csvData.grid, csvData.matrix);
    }
}
