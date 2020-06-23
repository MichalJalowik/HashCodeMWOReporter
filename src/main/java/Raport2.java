import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class Raport2 implements Raport {
	private String [][] raport;
	private String name = "Raport 2: ilosc godzin dla poszczegolnych projektow.";
	private LocalDate minDate;
	private LocalDate maxDate;

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		if (projects == null || projects.isEmpty()) {
			String[][] raport = new String[1][2];
			raport[0][0] = "Projekt";
			raport[0][1] = "Przepracowane godziny";

			this.raport = raport;
			return raport;
		}
		
		this.minDate = LocalDate.MAX;
		this.maxDate = LocalDate.MIN;
		
		int rows = projects.size();
		String[][] rawRaport = new String[rows][2];
		
		int i = 0;
		for (Project project : projects) {
			rawRaport[i][0] = project.getName();
			double workedHours = 0;
	
			for (Task task : project.getTasks()) {
				workedHours += task.getDuration();
				
				if (task.getDate().isBefore(this.minDate)) {
					this.minDate = task.getDate();
				}
				
				if (task.getDate().isAfter(this.maxDate)) {
					this.maxDate = task.getDate();
				}		
			}
			
			rawRaport[i][1] = String.valueOf(workedHours);
			i++;
		}
		
		Arrays.sort(rawRaport, (a, b) -> Double.compare(Double.valueOf(b[1]), Double.valueOf(a[1])));
		
		String[][] raport = new String[rows + 1][2];
		raport[0][0] = "Projekt";
		raport[0][1] = "Przepracowane godziny";
		
		for (int j = 1; j < rows + 1; j++) {
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
	
	@Override
	public LocalDate getMinDate() {
		return this.minDate;
	}

	@Override
	public LocalDate getMaxDate() {
		return this.maxDate;
	}	
}
