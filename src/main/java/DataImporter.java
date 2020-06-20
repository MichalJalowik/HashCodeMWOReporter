
public class DataImporter {

	Set<Project> private getTasks(ArrayList<String> files) {
		//TO DO
	}
	
	ArrayList<String> private scanFolder(String path) {
		//TO DO
	}
	
	Set<Project> public importDataFromFiles(String path) {
		return getTasks(scanFolder(path));
	}
}
