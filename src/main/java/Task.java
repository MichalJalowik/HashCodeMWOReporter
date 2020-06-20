
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Task {
	private LocalDate date;
	private String description;
	private double duration;
	
	public Set<Employee> employees = new HashSet<Employee>();
	public Set<Project> projects = new HashSet<Project>();
	
	public LocalDate getDate() {
		return date;
	}
	public String getDescription() {
		return description;
	}
	
	public double getDuration() {
		return duration;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public  void addEmployee(Employee employee) {
		employees.add(employee);
	}
	public  void addProject(Project project) {
		projects.add(project);
	}

}
