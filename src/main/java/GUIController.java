import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {

    DataImporter importer = new DataImporter();
    Raport raport;
    DataRaportPrinter printer;
    Set<Project> allProjects;

    @FXML
    TextField importPathField;
    @FXML
    TextArea textMessageLabel;
    @FXML
    CheckBox raport1checkbox;
    @FXML
    CheckBox raport2checkbox;
    @FXML
    CheckBox raport3checkbox;
    @FXML
    CheckBox raport4checkbox;
    @FXML
    CheckBox csvFileCheckbox;
    @FXML
    CheckBox xlsxFileCheckbox;
    @FXML
    CheckBox jpgFileCheckbox;
    @FXML
    CheckBox pdfFileCheckbox;

    @FXML
    public void importData() {
        String pathToData = importPathField.getText();
        textMessageLabel.setText("Data imported from: " + pathToData);
        allProjects = importer.importDataFromFiles(pathToData);
    }

    @FXML
    public void generateRaports() {
        Set<Raport> raports = new HashSet<>();
        if (raport1checkbox.isSelected()) {
            Raport raport1 = new Raport1();
            raports.add(raport1);
        }
        if (raport2checkbox.isSelected()) {
            Raport raport2 = new Raport2();
            raports.add(raport2);
        }
        if (raport3checkbox.isSelected()) {
            Raport raport3 = new Raport3();
            raports.add(raport3);
        }
        if (raport4checkbox.isSelected()) {
            Raport raport4 = new Raport4();
            raports.add(raport4);
        }
        if (csvFileCheckbox.isSelected()) {
            for (Raport raport : raports) {
                raport.generateRaport(allProjects);
                printer = new CSVDataRaportPrinter(raport);
                try {
                    printer.printRaport();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (xlsxFileCheckbox.isSelected()) {
            for (Raport raport : raports) {
                raport.generateRaport(allProjects);
                printer = new ExcelDataRaportPrinter(raport);
                try {
                    printer.printRaport();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (jpgFileCheckbox.isSelected()) {
            for (Raport raport : raports) {
                raport.generateRaport(allProjects);
                printer = new ChartDataRaportPrinter(raport);
                try {
                    printer.printRaport();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (pdfFileCheckbox.isSelected()) {
            for (Raport raport : raports) {
                raport.generateRaport(allProjects);
                printer = new PDFDataRaportPrinter(raport);
                try {
                    printer.printRaport();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
