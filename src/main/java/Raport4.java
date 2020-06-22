import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Raport4 implements Raport {
	private String [][] raport;
	int printedValues = 10;
	private String name = "Raport 4: ilosc godzin dla " + printedValues + " najbardziej czasochlonnych zadan.";
	private LocalDate minDate;
	private LocalDate maxDate;

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		this.minDate = LocalDate.MAX;
		this.maxDate = LocalDate.MIN;
		
		int rows = 0;
		
		for (Project project : projects) {
			rows += project.getTasks().size();
		}
		
		String[][] rawRaport = new String[rows][3];
		
		int i = 0;
		for (Project project : projects) {
			for (Task task : project.getTasks()) {
				rawRaport[i][0] = task.getDescription();
				rawRaport[i][1] = project.getName();
				rawRaport[i][2] = String.valueOf(task.getDuration());
				
				if (task.getDate().isBefore(this.minDate)) {
					this.minDate = task.getDate();
				}
				
				if (task.getDate().isAfter(this.maxDate)) {
					this.maxDate = task.getDate();
				}
				
				i++;
			}
		}
		
		Arrays.sort(rawRaport, (a, b) -> Integer.compare(Integer.valueOf(b[2]), Integer.valueOf(a[2])));
		
		String[][] raport = new String[printedValues + 1][3];
		raport[0][0] = "Zadanie";
		raport[0][1] = "Projekt";
		raport[0][2] = "Przepracowane godziny";
		
		for (int j = 1; j < printedValues + 1; j++) {
			raport[j][0] = rawRaport[j - 1][0];
			raport[j][1] = rawRaport[j - 1][1];
			raport[j][2] = rawRaport[j - 1][2];
		}
		
		this.raport = raport;
		return raport;
	}
	
//	private Map<Map<String, String>, Integer> generateTaskMap(Set<Project> projects) {
//		Map<Map<String, String>, Integer> taskMap = new HashMap<>();
//		
//		for (Project project : projects) {
//			
//		}
//		
//		return taskMap;
//	}

	@Override
	public String[][] getRaport() {
		return this.raport;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public LocalDate getMinDate() {
		return this.minDate;
	}

	@Override
	public LocalDate getMaxDate() {
		return this.maxDate;
	}

}
