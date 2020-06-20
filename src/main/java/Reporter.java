import java.util.Scanner;

public class Reporter {

	public static void main(String[] args) {

		System.out.println("WITAJ W SYSTEMIE DO RAPORTOWANIA PRACY\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Proszę podaj ścieżkę do plików, dla których chcesz wygenerować raport:");
		String path = scan.nextLine();
		// DataImporter importer = new DataImporter();
		// Set <Project> set = importer.importDataFromFiles(path);

		System.out.println("Wybierz rodzaj raportu, który chcesz wygenerwać:\n "
				+ "1. Raport godzin przepracowanych przez poszczególnych pracowników\n "
				+ "2. Raport godzin poświęconych na każdy projekt\n " + "3. Wyjście z pogramu");
		int i = scan.nextInt();
		switch (i) {
		case 1:
			// Raport1 raport1 = new Raport1(set);
			// DataRaportPrint print1 = new DataRaportPrinter();
			// print1.printaRaport(raport1);
			break;
		case 2:
			// Raport2 raport2 = new Raport2(set);
			// DataRaportPrint print2 = new DataRaportPrinter();
			// print2.printaRaport(raport2);
			break;
		case 3:
			break;
		default:
			System.out.println("Wybrano niepoprawny numer raportu!");
		}
	}

}
