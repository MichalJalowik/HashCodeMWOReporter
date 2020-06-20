import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Raport1 implements Raport {
	private String [][] raport;
	private String name = "Raport 1: ilość godzin dla poszczególnych pracowników.";

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		Set<Employee> employees = parseEmployees(projects);
		
		String[][] raport = new String[employees.size() + 1][2];
		raport[0][0] = "Pracownik";
		raport[0][1] = "Przepracowane godziny";
		
		
		int i = 0;
		for (Employee employee : employees) {
			raport[i + 1][0] = employee.getName();
			int workedHours = 0;
			
			for (Task task : employee.getTasks()) {
				workedHours += task.getDuration();
			}
			
			raport[i + 1][1] = String.valueOf(workedHours);
			i++;
		}
				
		// Docelowo posortować
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

	public String[][] getRaport() {
		return raport;
	}

	public String getName() {
		return name;
	}
}
