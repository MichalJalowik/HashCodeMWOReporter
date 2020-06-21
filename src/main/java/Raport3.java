import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Raport3 implements Raport {
	private String [][] raport;
	private String name = "Raport 3: ilość godzin dla poszczególnych pracowników w poszczególnych projektach.";
	private LocalDate minDate;
	private LocalDate maxDate;

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		// TODO Auto-generated method stub
		return null;
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
