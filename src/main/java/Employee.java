
import java.util.HashSet;
import java.util.Set;

public class Employee {


		private String name;
		
		public Set<Task> tasks = new HashSet<Task>();
		public Set<Project> projects = new HashSet<Project>();
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public  void addTask(Task task) {
				tasks.add(task);
			}
			public  void addProject(Project project) {
				projects.add(project);
			}
		}


