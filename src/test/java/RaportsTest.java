import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaportsTest {
	private Set<Project> projects;
	private Raport raport1;
	private Raport raport2;
	private Raport raport3;
	private Raport raport4;
	
	@Before
	public void createEmptyProjectsAndRaportsForTheTest() {
		projects = new HashSet<>();
		raport1 = new Raport1();
		raport2 = new Raport2();
		raport3 = new Raport3();
		raport4 = new Raport4();	
	}
	
	@Test
	public void testRaport1WithEmptyProjects() {
		raport1.generateRaport(projects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Pracownik";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport1.getRaport(),expectedEmptyRaport);
	}
	
	@Test
	public void testRaport2WithEmptyProjects() {
		raport2.generateRaport(projects);
		String[][] expectedEmptyRaport = new String[1][2];
		expectedEmptyRaport[0][0] = "Projekt";
		expectedEmptyRaport[0][1] = "Przepracowane godziny";
		Assert.assertEquals(raport2.getRaport(),expectedEmptyRaport);
	}
	
	@Test
	public void testDatesInRaport1WithEmptyProjects() {
		raport1.generateRaport(projects);
		Assert.assertNull(raport1.getMinDate());
		Assert.assertNull(raport1.getMaxDate());
	}

	@Test
	public void testDatesInRaport2WithEmptyProjects() {
		raport2.generateRaport(projects);
		Assert.assertNull(raport2.getMinDate());
		Assert.assertNull(raport2.getMaxDate());
	}

	@Test
	public void testDatesInRaport3WithEmptyProjects() {
		raport3.generateRaport(projects);
		Assert.assertNull(raport3.getMinDate());
		Assert.assertNull(raport3.getMaxDate());
	}

//	@Test
//	public void testDatesInRaport4WithEmptyProjects() {
//		raport4.generateRaport(projects);
//		Assert.assertNull(raport4.getMinDate());
//		Assert.assertNull(raport4.getMaxDate());
//	}
	
	// zrobić testy dla pustych raportów 2-5
	
	// zrobić testy dat dla pustych 1-5

}
