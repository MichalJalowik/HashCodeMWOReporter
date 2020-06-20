import org.apache.commons.cli.*;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.Set;

public class MainJarAndCommandLineTEST {


    public static void main (String[] args) throws ParseException{

        DataImporter importer = new DataImporter();

        Options options = new Options();
        options.addOption("path", true, "put path of location");
        options.addOption("report1", false, "put report needed");
        options.addOption("report2", false, "put printer type");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse( options, args );
            String path = line.getOptionValue("path");
            String report1 = line.getOptionValue("report");
            String report2 = line.getOptionValue("printer");

            if(line.hasOption("path")) {
                System.out.println("running......path" + " " + path);
                //Set<Project> set = importer.importDataFromFiles(path);
            }
            if(line.hasOption("report1")) {
                //System.out.println("running......report1" + " " + report1);

                Set<Project> set = importer.importDataFromFiles(path);
                Raport raport1 = new Raport1();
                raport1.generateRaport(set);
                DataRaportPrinter print1 = new ConsoleDataRaportPrinter();
                try {
                    print1.printRaport(raport1);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            if(line.hasOption("report2")) {
                //System.out.println("running......report2" + " " + report2);

                Set<Project> set = importer.importDataFromFiles(path);
                Raport2 raport2 = new Raport2();
                raport2.generateRaport(set);
                DataRaportPrinter print2 = new ConsoleDataRaportPrinter();
                try {
                    print2.printRaport(raport2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            else {
//                System.out.println("no function to run");
//            }
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }

    }

}