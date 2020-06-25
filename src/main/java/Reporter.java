import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reporter {

    public static void main(String[] args) {

        DataImporter importer = new DataImporter();
        Raport raport = null;
        DataRaportPrinter printer = null;
        DataRaportPrinter printer1 = null;
        Filter filter = new Filter();

        System.out.println("WITAJ W SYSTEMIE DO RAPORTOWANIA PRACY\n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj ścieżkę do plików, dla których chcesz wygenerować raport:");
        String path = scan.nextLine();
        Set<Project> allProjects = importer.importDataFromFiles(path);
        Set<Project> projects = Set.copyOf(allProjects);
        Menu menu1 = new Menu("FILTROWANIE DANYCH", new String[] { "Użyj wszystkich zaimportowanych danych / resetuj filtry",
                "Filtrowanie po nazwisku", "Filtrowanie po datach", "Filtrowanie po zadaniu" });

        Menu menu2 = new Menu("WYBÓR RAPORTU",
                new String[] { "Raport godzin przepracowanych przez poszczególnych pracowników",
                        "Raport godzin poświęconych na każdy projekt", "Raport czasowy pracownik/projekt",
                        "Raport TOP10 najbardziej czasochłonnych zadań" });

        Menu menu3 = new Menu("WYBÓR PREZENTACJI RAPORTU", new String[] { "Wyświetlenie na konsoli",
                "Eksport do pliku CSV", "Eksport do pliku XLSX", "Eksport do pliku PDF", "Stworz wykres", "Stworz PDF z wykresem" });

        int selection1 = -1;
        int selection2 = -1;
        int selection3 = -1;

        while (selection1 != 0) {

            selection1 = menu1.makeSelection();
            switch (selection1) {
            case 1:
                projects = Set.copyOf(allProjects);
                break;
            case 2:
                System.out.println("Podaj fragment, który ma się zawierać w nazwiskach");
                String employeeName = scan.nextLine();
                projects = filter.filterByEmployeeName(projects, employeeName);
                break;
            case 3:
                System.out.println("Podaj datę początkową (d.MM.yyyy):");
                String userDateFrom = scan.nextLine();
                System.out.println("Podaj datę końcową (d.MM.yyyy):");
                String userDateTo = scan.nextLine();
                projects = filter.filterByDate(projects, userDateFrom, userDateTo);
                break;
            case 4:
            	 System.out.println("Podaj fragment, który ma się zawierać w nazwie zadania");
                 String taskName = scan.nextLine();
                 projects = filter.filterByTaskName(projects, taskName);
                 break;
            }

            if (selection1 != 0) {
                selection2 = -1;
                while (selection2 != 0) {
                    selection2 = menu2.makeSelection();
                    switch (selection2) {
                    case 1:
                        raport = new Raport1();
                        break;
                    case 2:
                        raport = new Raport2();
                        break;
                    case 3:
                        raport = new Raport3();
                        break;
                    case 4:
                        raport = new Raport4();
                        break;
                    }

                    if (selection2 != 0) {
                        raport.generateRaport(projects);
                        selection3 = -1;
                        while (selection3 != 0) {
                            selection3 = menu3.makeSelection();
                            switch (selection3) {
                            case 1:
                                printer = new ConsoleDataRaportPrinter(raport);
                                break;
                            case 2:
                                printer = new CSVDataRaportPrinter(raport);
                                break;
                            case 3:
                                printer = new ExcelDataRaportPrinter(raport);
                                break;
                            case 4:
                                printer = new PDFDataRaportPrinter(raport);
                                break;
                            case 5:
                                printer = new ChartDataRaportPrinter(raport);
                                break;
                            case 6:
								printer1 = new ChartDataRaportPrinter(raport);
								printer = new PDFDataRaportPrinter(raport);
								break;
                            }
                            
                            if (selection3 != 0 && selection3 != 6) {
								try {
									printer.printRaport();
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else if (selection3 != 0 && selection3 == 6) {
								try {
									printer1.printRaport();
									printer.printRaport();
								} catch (IOException e) {
									e.printStackTrace();
								}
                            }
                        }
                    }
                }
            }
        }
    }
}
