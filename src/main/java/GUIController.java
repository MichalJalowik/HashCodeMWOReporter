import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {

    DataImporter importer = new DataImporter();
    Raport raport;
    DataRaportPrinter printer;
    Set<Project> allProjects;
    String message = "";

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
        message += "Zaimportowano dane: " + pathToData + "\n";
        textMessageLabel.setText(message);
        allProjects = importer.importDataFromFiles(pathToData);
    }

    @FXML
    public void generateRaports() {
        Set<Raport> raports = new HashSet<>();
        if (!raport1checkbox.isSelected() && !raport2checkbox.isSelected() && !raport3checkbox.isSelected() && !raport4checkbox.isSelected()) {
            message += "Nie wybrano zadnego raportu\n";
            textMessageLabel.setText(message);
        }
        if (!csvFileCheckbox.isSelected() && !xlsxFileCheckbox.isSelected() && !jpgFileCheckbox.isSelected() && !pdfFileCheckbox.isSelected()) {
            message += "Nie wybrano zadnego rozszerzenia pliku dla raportu\n";
            textMessageLabel.setText(message);
        }
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
                message += "Wygenerowano plik csv dla " + raport.getName() + "\n";
                textMessageLabel.setText(message);
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
                message += "Wygenerowano plik xlsx dla " + raport.getName() + "\n";
                textMessageLabel.setText(message);
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
                message += "Wygenerowano plik jpg dla " + raport.getName() + "\n";
                textMessageLabel.setText(message);
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
                message += "Wygenerowano plik pdf dla " + raport.getName() + "\n";
                textMessageLabel.setText(message);
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
