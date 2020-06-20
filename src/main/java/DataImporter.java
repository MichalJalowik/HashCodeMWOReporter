import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Set;
import java.nio.file.Files;

public class DataImporter {

	private ArrayList<String> paths = new ArrayList<String>();

	private Set<Project> getTasks(ArrayList<String> files) {
		// TO DO
	}

	private void scanFolder(String path) {

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			try {
				BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				System.out.println(file.getPath());
				if (attributes.isDirectory()) {
					scanFolder(file.getPath());
				} else {
					this.paths.add(file.getPath());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public Set<Project> importDataFromFiles(String path) {
		scanFolder(path);
		return getTasks(this.paths);
	}
}
