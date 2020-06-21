import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private String[] menuItems;
    private String title;
    private Scanner scanner = new Scanner(System.in);

    /**
     * konstruktor - ustawia tytuł/nagłówek menu
     * @param title nagłówek/tutuł wyświetlany na górze menu - String
     */
    Menu(String title){
        this.setTitle(title);
    }

    /**
     * konstruktor - tworzy menu z nagłówkiem i podanymi pozycjami menu
     * @param title nagłówek/tutuł wyświetlany na górze menu - String
     * @param menuItems lista pozycji menu - String[]
     */
    Menu(String title, String[] menuItems){
        this(title);
        for (String menuItem: menuItems){
            this.addMenuItem(menuItem);
        }
    }

    /**
     * Zwraca tytuł/nagłówek menu
     * @return tytuł/nagłówek menu - String
     */
    String getTitle() {
        return title;
    }

    /**
     * ustawia tytuł/nagłówek menu
     * @param title nagłówek/tutuł wyświetlany na górze menu - String
     */
    void setTitle(String title) {
        this.title = title;
    }

    /**
     * metoda pomocnicza dołączająca do listy String nowy element
     * @param array lista String do której należy dodać nowy element - String[]
     * @param element element do dodania - String
     * @return lista String powiększona o nowy element - String[]
     */
    private String[] appendTo(String[] array, String element) {
        String[] result;
        if (array == null){
            result = new String[1];
            result[0]= element;
        } else {
            result = Arrays.copyOf(array, array.length + 1);
            result[result.length - 1] = element;
        }
        return result;
    }

    /**
     * dodaje do menu nową pozycję
     * @param menuItem pozycja menu do dodania - String
     */
    void addMenuItem(String menuItem){
        this.menuItems = this.appendTo(menuItems, menuItem);
    }

    /**
     * wyświetla menu na ekranie
     */
    private void printMenu(){
        System.out.println("\n--- " + this.getTitle() + " ---");
        for (int i=0; i<this.menuItems.length; i++){
            System.out.println((i+1) + " - " + this.menuItems[i]);
        }
        System.out.println("0 - Wyjście");
    }

    /**
     * prosi o dokonanie wyboru pozycji z menu i zwraca liczbę odpowiadającą wyborowi
     * @return numer wybranej pozycji menu, 0 jeśli wybrano wyjście - int
     */
    int makeSelection(){
        int selection = -1;
        this.printMenu();
        while (selection == -1){
            System.out.print("Twój wybór? ");
            try{
                selection = scanner.nextInt();
            }
            catch (Exception e){
                scanner.next();
            }
            if (!((selection >= 0) && (selection <= this.menuItems.length))) {
                System.out.println("Nieprawidłowy wybór!");
                selection = -1;
            }
        }
        return selection;
    }
}