import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Reporter {

	public static void main(String[] args) {

		DataImporter importer = new DataImporter();
		Raport raport = null;
		DataRaportPrinter printer = null;

		System.out.println("WITAJ W SYSTEMIE DO RAPORTOWANIA PRACY\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj ścieżkę do plików, dla których chcesz wygenerować raport:");
		String path = scan.nextLine();
		Set<Project> set = importer.importDataFromFiles(path);

		Menu menu1 = new Menu("WYBÓR RAPORTU",
				new String[] { "Raport godzin przepracowanych przez poszczególnych pracowników",
						"Raport godzin poświęconych na każdy projekt", "Raport czasowy pracownik/projekt" });

		Menu menu2 = new Menu("WYBÓR PREZENTACJI RAPORTU", new String[] { "Wyświetlenie na konsoli",
				"Eksport do pliku CSV", "Eksport do pliku XLSX", "Eksport do pliku PDF" });

		int selection1 = -1;
		int selection2 = -1;

		while (selection1 != 0) {
			selection1 = menu1.makeSelection();
			switch (selection1) {
			case 1:
				raport = new Raport1();
				break;
			case 2:
				raport = new Raport2();
				break;
			case 3:
				raport = new Raport3();
				break;
			}
			raport.generateRaport(set);

			if (selection1 != 0) {
				selection2 = -1;
				while (selection2 != 0) {
					selection2 = menu2.makeSelection();
					switch (selection2) {
					case 1:
						printer = new ConsoleDataRaportPrinter();
						break;
					case 2:
						printer = new CSVDataRaportPrinter();
						break;
					case 3:
						printer = new ExcelDataRaportPrinter();
						break;
					case 4:
						printer = new PDFDataRaportPrinter();
						break;
					}

					if (selection2 != 0) {
						try {
							printer.printRaport(raport);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
