import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DataImporter {

    private ArrayList<String> paths = new ArrayList<String>();
    private HashSet<Project> projects = new HashSet<>();
    private HashSet<Employee> employees = new HashSet<>();

    private HashSet<Project> getTasks(ArrayList<String> files) {
        for (String filePath : files) {

            Path path = Paths.get(filePath);
            Path fileName = path.getFileName();
            String employeeName = fileName.toString().replace('_', ' ');
            int position = employeeName.indexOf(".");
            employeeName = employeeName.substring(0, position);
            Workbook workbook = openWorkbook(filePath);

            for (Sheet sheet : workbook) {

                String projectName = sheet.getSheetName();

                Project project = getProjectByName(sheet.getSheetName());

                if (project == null) {
                    project = new Project();
                    project.setName(projectName);
                    projects.add(project);
                }

                Employee employee = getEmployee(employeeName);

                if (employee == null) {
                    employee = new Employee();
                    employee.setName(employeeName);
                    project.employees.add(employee);
                    employees.add(employee);
                }

                for (Row row : sheet) {

                    boolean isFirstRow = row.getRowNum() == 0;

                    if (!isFirstRow) {

                        Task task = new Task();
                        try {

                            boolean haveDate = !cellIsNull(row, 0);
                            boolean haveDescription = !cellIsNull(row, 1);
                            boolean haveDuration = !cellIsNull(row, 2);

                            CheckImportValues checkImportValues = new CheckImportValues(filePath, projectName, row.getRowNum() + 1);

                            if(haveDate){
                                checkImportValues.isCorrectDate( row, 0);
                            }

                            if(haveDuration){
                                checkImportValues.isCorrectNumberValue(row.getCell(2).toString());
                            }

                            if (haveDate && haveDescription && haveDuration) {
                                task.setDate(row.getCell(0).getLocalDateTimeCellValue().toLocalDate());
                                task.setDescription(row.getCell(1).getStringCellValue());
                                task.setDuration(row.getCell(2).getNumericCellValue());
                                employee.tasks.add(task);
                                employee.projects.add(project);
                                project.addTask(task);
                            }
                            else{
                                checkImportValues.errorInfo(haveDate ,haveDescription, haveDuration);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return projects;
    }

    private boolean cellIsNull(Row row, int cellNumber) {
        if (row.getCell(cellNumber) == null)
            return true;
        else
            return false;
    }

    private Employee getEmployee(String employeeName) {

        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                return employee;
            }
        }
        return null;
    }

    private Project getProjectByName(String projectName) {

        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    private Workbook openWorkbook(String path) {
        try {
            return WorkbookFactory.create(new File(path));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void scanFolder(String path) {

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            try {
                BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                
                if (attributes.isDirectory()) {
                    scanFolder(file.getPath());
                } else {
                    this.paths.add(file.getPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Set<Project> importDataFromFiles(String path) {
        scanFolder(path);
        return getTasks(this.paths);
    }
}