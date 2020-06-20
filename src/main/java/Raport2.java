import java.util.Set;

public class Raport2 implements Raport {
	private String [][] raport;
	private String name = "Raport 2: ilość godzin dla poszczególnych projektów.";

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		String[][] raport = new String[projects.size() + 1][2];
		raport[0][0] = "Projekt";
		raport[0][1] = "Przepracowane godziny";
		
		int i = 0;
		for (Project project : projects) {
			raport[i + 1][0] = project.getName();
			int workedHours = 0;
			
			for (Task task : project.getTasks()) {
				workedHours += task.getDuration();
			}
			
			raport[i + 1][1] = String.valueOf(workedHours);
			i++;
		}
		
		// Docelowo posortować
		this.raport = raport;
		return raport;
	}
	
	public String[][] getRaport() {
		return raport;
	}

	public String getName() {
		return name;
	}

}
