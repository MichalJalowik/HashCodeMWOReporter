# HashCodeMWOReporter
Easy soft for managing programming team performance 

opis wymagań: https://docs.google.com/document/d/1MElr1D3eZve1uQ6V7IYn2igUwUQ20usLmQ3nQJM6iyo/edit

diagram klas UML: https://github.com/MichalJalowik/HashCodeMWOReporter/blob/master/UML_Reporter.jpg

#### INSTRUKCJA OBSLUGI Z WIERSZA POLECEN: ####
1. Aby uruchomić program należy z katalogu w którym znajduje się plik HashCodeMWOReporter.jar wpisać komende w wierszu poleceń:
$ java -jar HashCodeMWOReporter.jar

2. Pojawi się opis funkcjonalności:
usage: MainJarAndCommandLineTEST [-chart] [-chartPDF] [-console] [-CSV]
       [-filterDATE <arg>] [-filterEMP <arg>] [-filterTASK <arg>] [-help] [-path
       <arg>] [-PDF] [-report1] [-report2] [-report3] [-report4] [-XLS]

Nalezy zwrocic uwage ze w niektorych funkcjach program oczekuję podania argumentow. 

3. Aby uruchomic funkcje programu musimy za kazdym razem podać nazwe wykonywanego pliku JAR tzn:
$ java -jar HashCodeMWOReporter.jar -help

pojawi się okno pomocy z opisem funkcjonalności:

usage: MainJarAndCommandLineTEST [-chart] [-chartPDF] [-console] [-CSV]
       [-filterDATE <arg>] [-filterEMP <arg>] [-filterTASK <arg>] [-help] [-path
       <arg>] [-PDF] [-report1] [-report2] [-report3] [-report4] [-XLS]
Program options:
-path          put path of location
-report1       Raport godzin przepracowanych przez poszczególnych pracowników
-report2       Raport godzin poświęconych na każdy projekt
-report3       Raport czasowy pracownik/projekt
-report4       Raport TOP10 najbardziej czasochłonnych zadań
-console       printing result on console
-CSV           printing result as CSV into project directory
-XLS           printing result as XLS into project directory
-PDF           printing result as PDF into project directory
-chart         printing result as chart into project directory
-chartPDF      printing result as chart in PDF file into project directory
-help          operation manual
-filterEMP     filter data by employee name
-filterDATE    filter data by date format DD.MM.YYYY_DD.MM.YYYY
-filterTASK    filter data by task

4. dla przykladu jesli chcemy wyswietlic raport 1 na konsoli z bazy danych w folderze "C:\HashCodeMWOReporter\src\main\resources\valid\2018" wpisujemy:
$ java -jar HashCodeMWOReporter.jar -path "C:\Users\micha\Desktop\Java programming\HashCodeMWOReporter\src\main\resources\valid\2018" -report1 -console

5. dla przykladu jesli chcemy z powyzszej bazy danych wyswietlic raport 1, 2, 3, 4 na konsoli jako plik CSV, tabeli w PDF, wykres w PDF dodatkowo przefiltrowany wg dat wpisujemy:
$ java -jar HashCodeMWOReporter.jar -path "C:\Users\micha\Desktop\Java programming\HashCodeMWOReporter\src\main\resources\valid\2018" -report1 -report2 -console -CSV -chartPDF -PDF filterDATE 01.02.2018_01.04.2018 -report3 -report4

kolejnosc wpisywania funkcji nie jest wazna. W razie pomocy zaesze moana uzyc funkcji -help gdzie sa podane wszystkie informacje i funkcjonalnosci

6. Generowanie pliku JAR.
w wierszu polecen z poziomu gluwnego katalogu programu wpisujemy:
$ mvn clean install

W folderze \target pojawi sie plik o nazwie wygenerowanej z pliku pom.xml projektu. Od tej pory mozemy zmieniac jego nazwe recznie i korzystac w funkcjonalnej wersji JAR.  

