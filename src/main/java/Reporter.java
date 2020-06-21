import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Reporter {

	public static void main(String[] args) {

		System.out.println("WITAJ W SYSTEMIE DO RAPORTOWANIA PRACY\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Proszę podaj ścieżkę do plików, dla których chcesz wygenerować raport:");
		String path = scan.nextLine();
		DataImporter importer = new DataImporter();
		Set <Project> set = importer.importDataFromFiles(path);

		System.out.println("Wybierz rodzaj raportu, który chcesz wygenerwać:\n "
				+ "1. Raport godzin przepracowanych przez poszczególnych pracowników\n "
				+ "2. Raport godzin poświęconych na każdy projekt\n " 
				+ "3. Raport czasowy pracownik/projekt\n "
				+ "4. Wyjście z pogramu");
		int i = scan.nextInt();
		Raport raport = null;
		switch (i) {
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
			break;
		default:
			System.out.println("Wybrano niepoprawny numer raportu!");
		}
		raport.generateRaport(set);
		
		System.out.println("Wybierz sposób prezentacji raportu:\n "
				+ "1. Wyświetlenie na konsoli\n "
				+ "2. Eksport do pliku csv\n "
				+ "3. Eksport do pliku xls\n "
				+ "4. Eksport do pliku pdf\n " 
				+ "5. Wyjście z pogramu");
		i = scan.nextInt();
		DataRaportPrinter printer = null;
		switch (i) {
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
		case 5:
			break;
		default:
			System.out.println("Wybrano niepoprawny numer!");
		}
		
		try {
			printer.printRaport(raport);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
