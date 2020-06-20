import java.util.Arrays;
import java.util.Set;

public class Raport2 implements Raport {
	private String [][] raport;
	private String name = "Raport 2: iloœæ godzin dla poszczególnych projektów.";

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		int rows = projects.size();
		String[][] rawRaport = new String[rows][2];
		
		int i = 0;
		for (Project project : projects) {
			rawRaport[i][0] = project.getName();
			int workedHours = 0;
			
			for (Task task : project.getTasks()) {
				workedHours += task.getDuration();
			}
			
			rawRaport[i][1] = String.valueOf(workedHours);
			i++;
		}
		
		Arrays.sort(rawRaport, (a, b) -> Integer.compare(Integer.valueOf(b[1]), Integer.valueOf(a[1])));
		
		String[][] raport = new String[rows + 1][2];
		raport[0][0] = "Projekt";
		raport[0][1] = "Przepracowane godziny";
		
		for (int j = 1; i < rows + 1; j++) {
			raport[j][0] = rawRaport[j - 1][0];
			raport[j][1] = rawRaport[j - 1][1];
		}
		
		this.raport = raport;
		return raport;
	}
	
	@Override
	public String[][] getRaport() {
		return this.raport;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
