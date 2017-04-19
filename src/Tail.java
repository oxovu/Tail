import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tail {

    private final Integer charNumber;
    private final Integer stringNumber;

    public Tail(Integer charNumber, Integer stringNumber) {
        this.charNumber = charNumber;
        this.stringNumber = stringNumber;
    }

    private List<String> getChars(InputStream in) throws IOException{
        List<Character> reader = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){
            int c;
            int count = 0;
            while((c = br.read())!= -1){
                reader.add((char) c);
                count++;
            }
        }
        List<String> result = new ArrayList<>();
        char[] readerC = new char[reader.size()];
        for(int j = 0; j < readerC.length; j++) {
            readerC[j] = reader.get(j);
        }
        String readerS = new String(readerC);
        result.add(charNumber, readerS);
        return result;
    }

    private List<String> getStrings(InputStream in) throws IOException{
        List<String> reader = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){
            String s;
            int count = 0;
            while((s = br.readLine())!= null){
                reader.add((String) s);
                count++;
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < reader.size() - 1; i ++) {
            result.add(stringNumber, reader.get(i));
        }
        return result;
    }

    public List<String> getTail(InputStream in) throws IOException {
        List<String> result = new ArrayList<>();
        if (charNumber > 0) {
            result.addAll(getChars(in));
            return result;
        }
        if (stringNumber > 0) {
            result.addAll(getStrings(in));
            return result;
        }
        return result;
    }

    public String fromFile(String inputFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            return getTail(inputStream).toString();
        }
    }
}
