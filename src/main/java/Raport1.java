import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Raport1 implements Raport {

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		Set<Employee> employees = parseEmployees(projects);
		
		String[][] raport = new String[employees.size()][2];
		
		int i = 0;
		for (Employee employee : employees) {
			raport[i][0] = employee.getName();
			int workedHours = 0;
			
			for (Task task : employee.getTasks()) {
				workedHours += task.getDuration();
			}
			
			raport[i][1] = String.valueOf(workedHours);
			i++;
		}
				
		// Docelowo posortować
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
}
