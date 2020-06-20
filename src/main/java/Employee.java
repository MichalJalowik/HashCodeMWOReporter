
import java.util.HashSet;
import java.util.Set;

public class Employee {


		private String name;
		
		public Set<Task> tasks = new HashSet<Task>();
		public Set<Task> getTasks() {
			return tasks;
		}
		public void setTasks(Set<Task> tasks) {
			this.tasks = tasks;
		}
		public Set<Project> getProjects() {
			return projects;
		}
		public void setProjects(Set<Project> projects) {
			this.projects = projects;
		}
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


