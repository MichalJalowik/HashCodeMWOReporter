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

}
