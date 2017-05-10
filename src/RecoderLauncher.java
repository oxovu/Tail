import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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


    public static void main(String[] args) throws Exception {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) throws Exception {
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
            throw new IllegalArgumentException("-c and -n can't be used together");

        }
        if (charNumber == null && stringNumber == null) {
            charNumber = null;
            stringNumber = 10;
        }

        Tail tail = new Tail(charNumber, stringNumber);


        for (String file : inputFiles) {
            if (inputFiles.size() == 1) {
                if (charNumber != null) {
                    tail.getChars(file, outputFile);
                } else tail.getStrings(file, outputFile);
            } else {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
                    String s;
                    while ((s = br.readLine()) != null) {
                        sb.append(s);
                        sb.append("\r\n");
                    }
                    try (FileWriter writer = new FileWriter(outputFile)) {
                        writer.append(sb.toString() + "\r\n" + file);
                    }
                    if (charNumber != null) {
                        tail.getChars(file, outputFile);
                    } else tail.getStrings(file, outputFile);
                }
            }
        }
    }
}
