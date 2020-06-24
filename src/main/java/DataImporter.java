import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataImporter
{
    private ArrayList<String> paths = new ArrayList<String>();
    private HashSet<Project> projects = new HashSet<>();

    private String getEmployeeNameFromFileName(String fileName){
        String employeeName = fileName.toString().replace('_', ' ');
        int position = employeeName.indexOf(".");
        return employeeName.substring(0, position);
    }

    private HashSet<Project> getTasks(ArrayList<String> files)
    {
        for (String filePath : files)
        {
            Path path = Paths.get(filePath);
            String fileName = path.getFileName().toString();

            String employeeName = getEmployeeNameFromFileName(fileName);
            Workbook workbook = openWorkbook(filePath);

            if (workbook == null)
                continue;

            for (Sheet sheet : workbook)
            {
                String projectName = sheet.getSheetName();
                Project project = getProjectByName(projectName);
                Employee employee = getEmployee(project, employeeName);

                for (Row row : sheet)
                {
                    boolean isFirstRow = row.getRowNum() == 0;

                    if (!isFirstRow)
                    {
                        try
                        {
                            boolean haveDate = !cellIsNull(row, 0);
                            boolean haveDescription = !cellIsNull(row, 1);
                            boolean haveDuration = !cellIsNull(row, 2);
                            boolean recordIsNotEmpty = haveDate || haveDescription || haveDuration;

                            CheckImportValues checkImportValues = new CheckImportValues(filePath, projectName, row.getRowNum() + 1);

                            if (recordIsNotEmpty)
                            {
                                if (haveDate)
                                {
                                    checkImportValues.isCorrectDate(row, 0);
                                }

                                if (haveDuration)
                                {
                                    checkImportValues.isCorrectNumberValue(row.getCell(2));
                                }

                                if (haveDate && haveDescription && haveDuration)
                                {
                                    addNewTask(project, employee, row);
                                }
                                else
                                {
                                    checkImportValues.printErrorMessage(haveDate, haveDescription, haveDuration);
                                }
                            }

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return projects;
    }

    private void addNewTask(Project project, Employee employee, Row row)
    {
        Task task = new Task();
        task.setDate(row.getCell(0).getLocalDateTimeCellValue().toLocalDate());
        task.setDescription(row.getCell(1).getStringCellValue());
        task.setDuration(row.getCell(2).getNumericCellValue());
        employee.tasks.add(task);
        employee.projects.add(project);
        project.addTask(task);
    }

    private boolean cellIsNull(Row row, int cellNumber)
    {
        if (row.getCell(cellNumber) == null || row.getCell(cellNumber).toString().equals(""))
            return true;
        else
            return false;
    }

    private Employee getEmployee(Project project, String employeeName)
    {
        for (Employee employee : project.getEmployees())
        {
            if (employee.getName().equals(employeeName))
            {
                return employee;
            }
        }

        Employee employee = new Employee();
        employee.setName(employeeName);
        project.employees.add(employee);
        return employee;
    }

    private Project getProjectByName(String projectName)
    {
        for (Project project : projects)
        {
            if (project.getName().equals(projectName))
            {
                return project;
            }
        }

        Project project = new Project();
        project.setName(projectName);
        projects.add(project);
        return project;
    }

    private Workbook openWorkbook(String path)
    {
        try
        {
            return WorkbookFactory.create(new File(path));
        }
        catch (EncryptedDocumentException | IOException e)
        {
            e.printStackTrace();
            System.out.println("Błąd przy próbie otwarcia pliku " + path);
            return null;
        }
    }

    private void scanFolder(String path)
    {

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
        {
            try
            {
                BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

                if (attributes.isDirectory())
                {
                    scanFolder(file.getPath());
                }
                else
                {
                    this.paths.add(file.getPath());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Set<Project> importDataFromFiles(String path)
    {
        scanFolder(path);
        return getTasks(this.paths);
    }
}