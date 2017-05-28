import java.io.*;
import java.util.Scanner;

public class Tail {

    private final Integer charNumber;
    private final Integer stringNumber;

    public Tail(Integer charNumber, Integer stringNumber) {
        this.charNumber = charNumber;
        this.stringNumber = stringNumber;
    }

    public String getChars(String inputFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
        } catch (Exception ex) {
            if (inputFile == null) {
                sb = noInputFile();
            } else throw new IllegalArgumentException("ошибка чтения файла");
        }
        int num = this.charNumber;
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb.length() - 1; num > 0 && i >= 0; i--) { // если файл меньше заданного числа символов, выводится весь файл
            sb2.append(sb.charAt(i));
            if (sb.charAt(i) != '\n' && sb.charAt(i) != '\r') num--;
        }
        sb2.reverse();
        return sb2.toString();
    }

    private StringBuilder noInputFile() {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            sb = sb.append(in.nextLine()).append("\n");
        }
        return sb;
    }

    public String getStrings(String inputFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\r\n");
            }
        } catch (Exception ex) {
            if (inputFile == null) {
                sb = noInputFile();
            } else throw new IllegalArgumentException("ошибка чтения файла");
        }
        int num = this.stringNumber + 1;
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb.length() - 1; num > 0 && i >= 0; i--) {   // если файл меньше заданного числа строк, выводится весь файл
            sb2.append(sb.charAt(i));
            if (sb.charAt(i) == '\n' && sb.charAt(i - 1) == '\r') num--;
        }
        sb2.reverse();
        return sb2.toString();
    }
}