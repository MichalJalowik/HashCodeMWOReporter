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
				+ "2. Raport godzin poświęconych na każdy projekt\n " + "3. Wyjście z pogramu");
		int i = scan.nextInt();
		switch (i) {
		case 1:
			Raport raport1 = new Raport1();
			raport1.generateRaport(set);
			DataRaportPrinter print1 = new ConsoleDataRaportPrinter();
			try {
				print1.printRaport(raport1);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			break;
		case 2:
			Raport2 raport2 = new Raport2();
			raport2.generateRaport(set);
			DataRaportPrinter print2 = new ConsoleDataRaportPrinter();
			try {
				print2.printRaport(raport2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			break;
		default:
			System.out.println("Wybrano niepoprawny numer raportu!");
		}
	}
}
