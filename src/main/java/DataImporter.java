import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
​
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
​
public class DataImporter {
​
    private ArrayList<String> paths = new ArrayList<String>();
    HashSet<Project> projects = new HashSet<>();
​
    private HashSet<Project> getTasks(ArrayList<String> files) {
​
        for (String filePath : files) {
​
            Path path = Paths.get(filePath);
            Path fileName = path.getFileName();
            String employeeName = fileName.toString().replace('_', ' ');
​
            Workbook workbook = openWorkbook(filePath);
​
            for (Sheet sheet : workbook) {
​
                String projectName = sheet.getSheetName();
​
                Project project = getProjectByName(sheet.getSheetName());
​
                if (project == null) {
                    project = new Project();
                    project.setName(projectName);
                    projects.add(project);
                }
​
                Employee employee = getEmployee(project, employeeName);
​
                if (employee == null) {
                    employee = new Employee();
                    employee.setName(employeeName);
                    project.employees.add(employee);
                }
​
                for (Row row : sheet) {
​
                    boolean isFirstRow = row.getRowNum() == 0;
​
                    if (!isFirstRow) {
​
                        Task task = new Task();
                        task.setDescription(row.getCell(1).getStringCellValue());
                        task.setDuration(row.getCell(2).getNumericCellValue());
                        task.setDate(row.getCell(3).getLocalDateTimeCellValue().toLocalDate());
                        employee.tasks.add(task);
                        project.addTask(task);
                    }
                }
            }
        }
​
        return projects;
    }
​
    private Employee getEmployee(Project project, String employeeName) {
​
        for (Employee employee : project.employees) {
            if (employee.getName() == employeeName) {
                return employee;
            }
        }
        return null;
    }
​
    private Project getProjectByName(String projectName) {
​
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }
​
    private Workbook openWorkbook(String path) {
        try {
            return WorkbookFactory.create(new File(path));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
​
    private void scanFolder(String path) {
​
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            try {
                BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                System.out.println(file.getPath());
                if (attributes.isDirectory()) {
                    scanFolder(file.getPath());
                } else {
                    this.paths.add(file.getPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
​
        }
    }
​
    public Set<Project> importDataFromFiles(String path) {
        scanFolder(path);
        return getTasks(this.paths);
    }
}