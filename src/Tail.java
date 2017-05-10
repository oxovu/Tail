import java.io.*;
import java.util.Scanner;

public class Tail {

    private final Integer charNumber;
    private final Integer stringNumber;

    public Tail(Integer charNumber, Integer stringNumber) {
        this.charNumber = charNumber;
        this.stringNumber = stringNumber;
    }

    public void getChars(String inputFile, String outputFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
        } catch (Exception ex) {
            if (inputFile == null) {
                sb = noInputFile();
            } else throw new IllegalArgumentException("имя входного файла указано неверно");
        }
        int num = this.charNumber;
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb.length() - 1; num > 0 && i >= 0; i--) { // если файл меньше заданного числа символов, выводится весь файл
            sb2.append(sb.charAt(i));
            if (sb.charAt(i) != '\n' && sb.charAt(i) != '\r') num--;
        }
        sb2.reverse();
        StringBuilder sb3 = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb3.append(s);
                sb3.append("\r\n");
            }
        } catch (Exception ex) {
            if (outputFile == null) {
                System.out.println("отсутсвует выходной файл");
            } else throw new IllegalArgumentException("имя выходного файла указано неверно");
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.append(sb3.toString() + sb2.toString());
            writer.flush();
        } catch (Exception e) {
            System.out.println(sb2);
        }

    }

    private StringBuilder noInputFile() {
        StringBuilder sb = new StringBuilder();
        System.out.println("введите текст");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            sb = sb.append(in.nextLine() + "\r\n");
        }
        return sb;
    }

    public void getStrings(String inputFile, String outputFile) throws Exception {
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
            } else throw new IllegalArgumentException("имя входного файла указано неверно");
        }
        int num = this.stringNumber + 1;
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb.length() - 1; num > 0 && i >= 0; i--) {   // если файл меньше заданного числа строк, выводится весь файл
            sb2.append(sb.charAt(i));
            if (sb.charAt(i) == '\n' && sb.charAt(i - 1) == '\r') num--;
        }
        sb2.reverse();
        StringBuilder sb3 = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb3.append(s);
                sb3.append("\r\n");
            }
        } catch (Exception ex) {
            if (outputFile == null) {
                System.out.println("отсутсвует выходной файл");
            } else throw new IllegalArgumentException("имя выходного файла указано неверно");
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.append(sb3.toString() + sb2.toString());
            writer.flush();
        } catch (Exception e) {
            System.out.println(sb2);
        }


    }
}