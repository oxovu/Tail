import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class TailLauncher {

    @Option(name = "-o", metaVar = "oFile", usage = "Output file")
    private String outputFile = null;

    @Option(name = "-c", metaVar = "num", usage = "Char number")
    private Integer charNumber = null;

    @Option(name = "-n", metaVar = "num", usage = "String number")
    private Integer stringNumber = null;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private List<String> inputFiles = new ArrayList<String>();


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

        if (stringNumber != null && stringNumber < 0) {
            throw new IllegalArgumentException("String number must be positive");
        }
        if (charNumber != null && charNumber < 0) {
            throw new IllegalArgumentException("Char number must be positive");
        }

        Tail tail = new Tail(charNumber, stringNumber);
        StringBuilder sb = new StringBuilder();



        if (inputFiles.isEmpty()) {
            if (charNumber != null) {
                sb.append(tail.getChars(null));
            } else sb.append(tail.getStrings(null));
        }

        for (String file : inputFiles) {
            if (inputFiles.size() == 1) {
                if (charNumber != null) {
                    sb.append(tail.getChars(file));
                } else sb.append(tail.getStrings(file));
            } else {
                if (charNumber != null) {
                    sb.append(file + "\r\n" + tail.getChars(file) + "\r\n");
                } else sb.append(file + "\r\n" + tail.getStrings(file) + "\r\n");
            }
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.append(sb.toString());
        } catch (Exception ex) {
            if (outputFile == null) {
                System.out.println(sb.toString());
            } else throw new IllegalArgumentException("ошибка записи файла");
        }
    }
}

