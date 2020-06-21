import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Raport1 implements Raport {
	private String [][] raport;
	private String name = "Raport 1: ilość godzin dla poszczególnych pracowników.";
	private LocalDate minDate;
	private LocalDate maxDate;
	
	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		this.minDate = LocalDate.MAX;
		this.maxDate = LocalDate.MIN;
		
		Set<Employee> employees = parseEmployees(projects);
		int rows = employees.size();
		
		String[][] rawRaport = new String[rows][2];
				
		int i = 0;
		for (Employee employee : employees) {
			rawRaport[i][0] = employee.getName();
			int workedHours = 0;
			
			for (Task task : employee.getTasks()) {
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
				
		Arrays.sort(rawRaport, (a, b) -> Integer.compare(Integer.valueOf(b[1]), Integer.valueOf(a[1])));
		
		String[][] raport = new String[rows + 1][2];
		raport[0][0] = "Pracownik";
		raport[0][1] = "Przepracowane godziny";
		
		for (int j = 1; j < rows + 1; j++) {
			raport[j][0] = rawRaport[j - 1][0];
			raport[j][1] = rawRaport[j - 1][1];
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
