<<<<<<< HEAD

public class Raport {

    String raport[][] = { { "Pracownik", "Roboczogodziny" }, { "Jan Kowalski", "12" }, { "Piotr Nowak", "20" }, };
    String name = "Raport nr 1";

    public String[][] getRaport() {
        return raport;
    }

    public String getName() {
        return name;
    }

       

=======
import java.util.Set;

public interface Raport {
	
	public String[][] generateRaport(Set<Project> projects);
	public String[][] getRaport();
	public String getName();
>>>>>>> d6d3142ec72ea76210d744b304b089a42b9d6915
}
