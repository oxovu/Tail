import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

class TailLauncher {

    @Option(name = "-o", metaVar = "oFile", usage = "Output file")
    private String outputFile = null;

    @Option(name = "-c", metaVar = "num", usage = "Char number")
    private Integer charNumber = null;

    @Option(name = "-n", metaVar = "num", usage = "String number")
    private Integer stringNumber = null;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private List<String> inputFiles = null;


    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Tail.jar -o oFile -c num -n num file0, file1, file2...");
            parser.printUsage(System.err);
            return;
        }

        if (charNumber != null && stringNumber != null) {
            System.err.println("-c and -n can't be used together");
            return;
        }

        if (charNumber == null && stringNumber == null) {
            charNumber = 0;
            stringNumber = 10;
        }

        Tail tail = new Tail(charNumber, stringNumber);

        try {
            StringBuilder outputText = new StringBuilder();
            if (inputFiles != null) {
                for (String inputFileName : inputFiles) {
                    outputText = outputText.append("File: ").append(inputFileName).append("\n").append(tail.fromFile(inputFileName)).append("\n\n");
                }
            } else {
                outputText = outputText.append(tail.getTail(System.in));
            }

            final OutputStream outputStream =
                    (outputFile == null) ? System.out : new FileOutputStream(outputFile);
            final OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(outputText.toString());
            writer.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
