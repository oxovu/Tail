import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

class TailLauncher {

    @Option(name = "-o", metaVar = "Encoding1", usage = "Input file encoding")
    private String inputEncoding;

    @Option(name = "-c", metaVar = "Char", usage = "Char number")
    private String outputChar;

    @Option(name = "-n", metaVar = "String", usage = "String number")
    private String outputString;

    @Argument(required = true, metaVar = "OutputName", usage = "Output file name")
    private String OutputFileName;


    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar tail.jar -o Encoding1 -c Char -n String OutputName");
            parser.printUsage(System.err);
            return;
        }

        Tail tail = new Tail(inputEncoding, outputChar, outputString, OutputFileName);
        try {
            String result = tail.getTail(); //method is in work
            System.out.println("");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
