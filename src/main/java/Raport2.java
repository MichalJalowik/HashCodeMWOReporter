import java.util.Set;

public class Raport2 implements Raport {

	@Override
	public String[][] generateRaport(Set<Project> projects) {
		
		String[][] raport = new String[projects.size()][2];
		
		int i = 0;
		for (Project project : projects) {
			raport[i][0] = project.getName();
			int workedHours = 0;
			
			for (Task task : project.getTasks()) {
				workedHours += task.getDuration();
			}
			
			raport[i][1] = String.valueOf(workedHours);
			i++;
		}
		
		// Docelowo posortować
		return raport;
	}

}
