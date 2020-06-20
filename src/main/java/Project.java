import java.util.HashSet;
import java.util.Set;

public class Project {
	
	private String name;
	public Set<Employee> employees = new HashSet<Employee>();
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	public Set<Task> tasks = new HashSet<Task>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public  void addTask(Task task) {
		tasks.add(task);
	}
	public  void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
}
