import java.util.HashSet;
import java.util.Set;

public class Raport1 implements Raport {

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		String[][] raport = new String[][];
		
		// Jak starczy czasu, to zrobić posortowaną tablicę

		
	}
	
	private Set<Employee> parseEmployees(Set<Project> projects) {
		Set<Employee> employees = new HashSet<>();
		
		for (Project project : projects) {
			for (Employee employee : project.getEmployees()) {
				employees.add(employee);
			}
		}
	}
}
