import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaportsTest {
	private Set<Project> emptyProjects;
	private Set<Project> nullProjects;
	private Set<Project> projects;
	private Raport raport1;
	private Raport raport2;
	private Raport raport3;
	private Raport raport4;
	
	@Before
	public void createEmptyProjectsAndRaportsForTheTest() {
		emptyProjects = new HashSet<>();
		nullProjects = null;
		projects = generateProjects();	
		raport1 = new Raport1();
		raport2 = new Raport2();
		raport3 = new Raport3();
		raport4 = new Raport4();	
	}
	
	private Set<Project> generateProjects() {
		Set<Project> projects = new HashSet<>();
		
		Employee employee1 = new Employee();
		employee1.setName("Jan Kowalski");
		
		Employee employee2 = new Employee();
		employee2.setName("Anna Nowak");
		
		Task task1 = new Task();
		task1.setDescription("Spotkanie z klientem");
		task1.setDuration(1.5);
		task1.setDate(LocalDate.of(2015, 5, 5));
		
		Task task2 = new Task();
		task2.setDescription("Spotkanie z klientem");
		task2.setDuration(2.5);
		task2.setDate(LocalDate.of(2015, 5, 6));
		
		Task task3 = new Task();
		task3.setDescription("Spotkanie z managerem");
		task3.setDuration(1);
		task3.setDate(LocalDate.of(2015, 5, 7));
		
		Task task4 = new Task();
		task4.setDescription("Poprawki");
		task4.setDuration(3.5);
		task4.setDate(LocalDate.of(2015, 5, 8));
		
		Project project1 = new Project();
		project1.setName("Projekt 1");
		
		Project project2 = new Project();
		project2.setName("Projekt 2");
		
		employee1.addTask(task1);
		employee2.addTask(task2);
		employee2.addTask(task3);
		employee2.addTask(task4);
		task1.addEmployee(employee1);
		task2.addEmployee(employee2);
		task3.addEmployee(employee2);
		task4.addEmployee(employee2);
		project1.addTask(task1);
		project2.addTask(task2);
		project1.addTask(task3);
		project2.addTask(task4);
		task1.addProject(project1);
		task2.addProject(project2);
		task3.addProject(project1);
		task4.addProject(project2);
		employee1.addProject(project1);
		employee2.addProject(project1);
		employee2.addProject(project2);
		project1.addEmployee(employee1);
		project1.addEmployee(employee2);
		project2.addEmployee(employee2);
		projects.add(project1);
		projects.add(project2);
		
		return projects;
	}
	
	@Test
	public void testRaport1WithEmptyProjects() {
		raport1.generateRaport(emptyProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Pracownik";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport1.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport2WithEmptyProjects() {
		raport2.generateRaport(emptyProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Projekt";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport2.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport3WithEmptyProjects() {
		raport3.generateRaport(emptyProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Pracownik";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport3.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport4WithEmptyProjects() {
		raport4.generateRaport(emptyProjects);
		String[][] expectedEmptyRaport = new String[1][3];
		expectedEmptyRaport[0][0] = "Zadanie";
		expectedEmptyRaport[0][1] = "Projekt";
		expectedEmptyRaport[0][2] = "Przepracowane godziny";
		Assert.assertEquals(raport4.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport1WithNullProjects() {
		raport1.generateRaport(nullProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Pracownik";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport1.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport2WithNullProjects() {
		raport2.generateRaport(nullProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Projekt";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport2.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport3WithNullProjects() {
		raport3.generateRaport(nullProjects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Pracownik";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport3.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testRaport4WithNullProjects() {
		raport4.generateRaport(nullProjects);
		String[][] expectedEmptyRaport = new String[1][3];
		expectedEmptyRaport[0][0] = "Zadanie";
		expectedEmptyRaport[0][1] = "Projekt";
		expectedEmptyRaport[0][2] = "Przepracowane godziny";
		Assert.assertEquals(raport4.getRaport(), expectedEmptyRaport);
	}
	
	@Test
	public void testDatesInRaport1WithEmptyProjects() {
		raport1.generateRaport(emptyProjects);
		Assert.assertNull(raport1.getMinDate());
		Assert.assertNull(raport1.getMaxDate());
	}

	@Test
	public void testDatesInRaport2WithEmptyProjects() {
		raport2.generateRaport(emptyProjects);
		Assert.assertNull(raport2.getMinDate());
		Assert.assertNull(raport2.getMaxDate());
	}

	@Test
	public void testDatesInRaport3WithEmptyProjects() {
		raport3.generateRaport(emptyProjects);
		Assert.assertNull(raport3.getMinDate());
		Assert.assertNull(raport3.getMaxDate());
	}

	@Test
	public void testDatesInRaport4WithEmptyProjects() {
		raport4.generateRaport(emptyProjects);
		Assert.assertNull(raport4.getMinDate());
		Assert.assertNull(raport4.getMaxDate());
	}
	
	@Test
	public void testDatesInRaport1() {
		raport1.generateRaport(projects);
		Assert.assertEquals(raport1.getMinDate(), LocalDate.of(2015, 5, 5));
		Assert.assertEquals(raport1.getMaxDate(), LocalDate.of(2015, 5, 8));
	}
	
	@Test
	public void testDatesInRaport2() {
		raport2.generateRaport(projects);
		Assert.assertEquals(raport2.getMinDate(), LocalDate.of(2015, 5, 5));
		Assert.assertEquals(raport2.getMaxDate(), LocalDate.of(2015, 5, 8));
	}
	
	@Test
	public void testDatesInRaport3() {
		raport3.generateRaport(projects);
		Assert.assertEquals(raport3.getMinDate(), LocalDate.of(2015, 5, 5));
		Assert.assertEquals(raport3.getMaxDate(), LocalDate.of(2015, 5, 8));
	}
	
	@Test
	public void testDatesInRaport4() {
		raport4.generateRaport(projects);
		Assert.assertEquals(raport4.getMinDate(), LocalDate.of(2015, 5, 5));
		Assert.assertEquals(raport4.getMaxDate(), LocalDate.of(2015, 5, 8));
	}
	
	@Test
	public void testRaport1LengthForNotEmptyProjects() {
		raport1.generateRaport(projects);
		Assert.assertTrue(raport1.getRaport().length == 3);
	}
	
	@Test
	public void testRaport1WorkedHoursValue() {
		String[][] raport = raport1.generateRaport(projects);
		
		int index = 0;
		for (int i = 0; i < raport.length; i++) {
			if (raport[i][0].equals("Anna Nowak")) {
				index = i;
				break;
			}
		}
		
		Assert.assertEquals(raport[index][1], "7.0");
	}
	
	@Test
	public void testRaport2LengthForNotEmptyProjects() {
		raport2.generateRaport(projects);
		Assert.assertTrue(raport2.getRaport().length == 3);
	}
	
	@Test
	public void testRaport2WorkedHoursValue() {
		String[][] raport = raport2.generateRaport(projects);
		
		int index = 0;
		for (int i = 0; i < raport.length; i++) {
			if (raport[i][0].equals("Projekt 2")) {
				index = i;
				break;
			}
		}
		
		Assert.assertEquals(raport[index][1], "6.0");
	}
	
	@Test
	public void testRaport3LengthForNotEmptyProjects() {
		raport3.generateRaport(projects);
		Assert.assertTrue(raport3.getRaport().length == 3);
	}
	
	@Test
	public void testRaport3ProjectWorkedHoursValue() {
		String[][] raport = raport3.generateRaport(projects);
		
		int rowIndex = 0;
		int columnIndex = 0;
		boolean isFound = false;
		for (int i = 0; i < raport.length; i++) {
			for (int j = 0; j < raport[0].length; j++) {
				if (raport[i][0].equals("Anna Nowak") && raport[0][j].equals("Godziny dla Projekt 2")) {
					rowIndex = i;
					columnIndex = j;
					isFound = true;
					break;
				}
			}
			if (isFound) {
				break;
			}
		}
		
		Assert.assertEquals(raport[rowIndex][columnIndex], "6.0");
	}
	
	@Test
	public void testRaport4LengthForNotEmptyProjects() {
		raport4.generateRaport(projects);
		Assert.assertTrue(raport4.getRaport().length == 5);
	}
	

	
	
	
	
	// zrobić testy dat dla projects
	
	// zrobić testy dla raportu 5
	
	// zrobić testy dla projercts
	

}
