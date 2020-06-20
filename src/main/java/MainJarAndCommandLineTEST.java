import org.apache.commons.cli.*;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MainJarAndCommandLineTEST {


    public static void main (String[] args) throws ParseException{

        Options options = new Options();
        options.addOption("runFunctionOne", false, "runs given functionOne");
        options.addOption("runFunctionTwo", false, "runs given functionTwo");

        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            if(line.hasOption("runFunctionOne")) {
                System.out.println("running......One");
            }
            if(line.hasOption("runFunctionTwo")) {
                System.out.println("running......Two");
            }
//            else {
//                System.out.println("no function to run");
//            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }



    }

}
