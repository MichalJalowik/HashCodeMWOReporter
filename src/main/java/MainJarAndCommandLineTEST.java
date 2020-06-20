import org.apache.commons.cli.*;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MainJarAndCommandLineTEST {


    public static void main (String[] args) throws ParseException{

        Options options = new Options();
        options.addOption("path", true, "put path of location");
        options.addOption("report", true, "put report needed");
        options.addOption("printer", true, "put printer type");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse( options, args );
            String path = line.getOptionValue("path");
            String report = line.getOptionValue("report");
            String printer = line.getOptionValue("printer");

            if(line.hasOption("path")) {
                System.out.println("running......path" + " " + path);
            }
            if(line.hasOption("report")) {
                System.out.println("running......report" + " " + report);
            }
            if(line.hasOption("printer")) {
                System.out.println("running......printer" + " " + printer);
            }
            else {
                System.out.println("no function to run");
            }
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }



    }

}
