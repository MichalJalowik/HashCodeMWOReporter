import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class MainJarAndCommandLineTEST {
    public static void main(String[] args) throws ParseException {
        DataImporter importer = new DataImporter();
        Raport raport = null;
        Filter filter = new Filter();
        HelpFormatter formatter = new HelpFormatter();

        Options options = new Options();
        options.addOption("path", true, "put path of location");
        options.addOption("report1", false, "Raport godzin przepracowanych przez poszczególnych pracowników");
        options.addOption("report2", false, "Raport godzin poświęconych na każdy projekt");
        options.addOption("report3", false, "Raport czasowy pracownik/projekt");
        options.addOption("report4", false, "Raport TOP10 najbardziej czasochłonnych zadań");
        options.addOption("console", false, "printing result on console");
        options.addOption("CSV", false, "printing result as CSV into project directory");
        options.addOption("XLS", false, "printing result as XLS into project directory");
        options.addOption("PDF", false, "printing result as PDF into project directory");
        options.addOption("chart", false, "printing result as chart into project directory");
        options.addOption("chartPDF", false, "printing result as chart in PDF file into project directory");
        options.addOption("help", false, "operations manual");
        options.addOption("filterEMP", true, "filter data by employee name");
        options.addOption("filterDATE", true, "filter data by date format DD.MM.YYYY_DD.MM.YYYY");
        options.addOption("filterTASK", true, "filter data by task");

        final PrintWriter writer = new PrintWriter(System.out);
        formatter.printUsage(writer, 80, "MainJarAndCommandLineTEST", options);
        writer.flush();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            String path = line.getOptionValue("path");
            String filterEMP = line.getOptionValue("filterEMP");
            String filterDATE = line.getOptionValue("filterDATE");
            String filterTASK = line.getOptionValue("filterTASK");

            if (line.hasOption("path")) {
                System.out.println("running......path" + " " + path);
            }
            if (line.hasOption("report1")) {
                Set<Project> set = importer.importDataFromFiles(path);
                raport = new Raport1();

                printFilteredReport(raport, filter, line, filterEMP, filterDATE, filterTASK, set);
            }
            if (line.hasOption("report2")) {
                Set<Project> set = importer.importDataFromFiles(path);
                raport = new Raport2();
                printFilteredReport(raport, filter, line, filterEMP, filterDATE, filterTASK, set);
            }
            if (line.hasOption("report3")) {
                Set<Project> set = importer.importDataFromFiles(path);
                raport = new Raport3();
                printFilteredReport(raport, filter, line, filterEMP, filterDATE, filterTASK, set);
            }
            if (line.hasOption("report4")) {
                Set<Project> set = importer.importDataFromFiles(path);
                raport = new Raport4();
                printFilteredReport(raport, filter, line, filterEMP, filterDATE, filterTASK, set);
            }
            if (line.hasOption("help")) {
                System.out.println("Program options:\n" +
                        "-path          put path of location\n" +
                        "-report1       Raport godzin przepracowanych przez poszczególnych pracowników\n" +
                        "-report2       Raport godzin poświęconych na każdy projekt\n" +
                        "-report3       Raport czasowy pracownik/projekt\n" +
                        "-report4       Raport TOP10 najbardziej czasochłonnych zadań\n" +
                        "-console       printing result on console\n" +
                        "-CSV           printing result as CSV into project directory\n" +
                        "-XLS           printing result as XLS into project directory\n" +
                        "-PDF           printing result as PDF into project directory\n" +
                        "-chart         printing result as chart into project directory\n" +
                        "-chartPDF      printing result as chart in PDF file into project directory\n" +
                        "-help          operation manual\n" +
                        "-filterEMP     filter data by employee name\n" +
                        "-filterDATE    filter data by date format DD.MM.YYYY_DD.MM.YYYY\n" +
                        "-filterTASK    filter data by task");
            }
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }

    private static void printFilteredReport(Raport raport, Filter filter, CommandLine line, String filterEMP, String filterDATE, String filterTASK, Set<Project> set) {
        if (line.hasOption("filterEMP")) {
            set = filter.filterByEmployeeName(set, filterEMP);
        }
        if (line.hasOption("filterDATE")) {
            String[] parts = filterDATE.split("_");
            String dateFrom = parts[0];
            String dateTo = parts[1];
            set = filter.filterByDate(set, dateFrom, dateTo);
        }
        if (line.hasOption("filterTASK")) {
            set = filter.filterByTaskName(set, filterTASK);
        }
        raport.generateRaport(set);
        System.out.println("");
        printReport(raport, line);
    }

    static void printReport(Raport raport, CommandLine line) {
        DataRaportPrinter printer;
        if (line.hasOption("console")) {
            printer = new ConsoleDataRaportPrinter(raport);
            try {
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (line.hasOption("CSV")) {
            printer = new CSVDataRaportPrinter(raport);
            try {
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (line.hasOption("XLS")) {
            printer = new ExcelDataRaportPrinter(raport);
            try {
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (line.hasOption("PDF")) {
            printer = new PDFDataRaportPrinter(raport);
            try {
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (line.hasOption("chart")) {
            printer = new ChartDataRaportPrinter(raport);
            try {
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (line.hasOption("chartPDF")) {
            DataRaportPrinter printer1 = new ChartDataRaportPrinter(raport);
            printer = new PDFDataRaportPrinter(raport);

            try {
                printer1.printRaport();
                printer.printRaport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
