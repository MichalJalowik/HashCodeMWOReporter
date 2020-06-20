import java.util.Set;

public interface Raport {
	
	public String[][] generateRaport(Set<Project> projects);
	public String[][] getRaport();
	public String getName();
}
