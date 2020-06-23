import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Raport3 implements Raport {
	private String [][] raport;
	private String name = "Raport 3: ilosc godzin dla poszczegolnych pracownikow w poszczegolnych projektach.";
	private LocalDate minDate;
	private LocalDate maxDate;

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		if (projects.isEmpty() || projects == null) {
			String[][] raport = new String[1][2];
			raport[0][0] = "Pracownik";
			raport[0][1] = "Przepracowane godziny";

			this.raport = raport;
			return raport;
		}
		
		this.minDate = LocalDate.MAX;
		this.maxDate = LocalDate.MIN;
		
		Set<Employee> employees = parseEmployees(projects);
		int rows = employees.size();
		int columns = projects.size() + 2;
		
		String[][] rawRaport = new String[rows][columns];
		String[][] raport = new String[rows + 1][columns];
		raport[0][0] = "Pracownik";
		raport[0][columns - 1] = "Przepracowane godziny";
		
		int i = 0;
		for (Employee employee : employees) {
			
			rawRaport[i][0] = employee.getName();
			double workedHours = 0;
			
			int j = 1;
			for (Project project : projects) {
				if (i == 0) {
					raport[i][j] = "Godziny dla " + project.getName();
				}
				
				double projectWorkedHours = 0;
				
				if (employee.getProjects().contains(project)) {
					for (Task task : project.getTasks()) {
						if (employee.getTasks().contains(task)) {
							workedHours += task.getDuration();
							projectWorkedHours += task.getDuration();
							
							if (task.getDate().isBefore(this.minDate)) {
								this.minDate = task.getDate();
							}
							
							if (task.getDate().isAfter(this.maxDate)) {
								this.maxDate = task.getDate();
							}
						}
					}
				}
				
				rawRaport[i][j] = String.valueOf(projectWorkedHours);
				j++;
			}
			
			rawRaport[i][columns - 1] = String.valueOf(workedHours);
			i++;
		}
			
		Arrays.sort(rawRaport, (a, b) -> Double.compare(Double.valueOf(b[columns - 1]), Double.valueOf(a[columns - 1])));
			
		for (int k = 1; k < rows + 1; k++) {
			for (int l = 0; l < columns; l++) {
				raport[k][l] = rawRaport[k - 1][l];
			}
		}
				
		this.raport = raport;
		return raport;	
	}

	private Set<Employee> parseEmployees(Set<Project> projects) {
		Set<Employee> employees = new HashSet<>();
		
		for (Project project : projects) {
			for (Employee employee : project.getEmployees()) {
				employees.add(employee);
			}
		}
		
		return employees;
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
