import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static final File file = new File("C:/Users/shuny/OneDrive/Документы/1.txt");
    static final String regEx = "\\d?";
    static final String enter = "^\\s*$";
    static Pattern p = Pattern.compile(regEx);
    static Pattern p2 = Pattern.compile(enter);
    static Matcher matcher;
    static String s;

    public static void main(String[] args) {

        List<String> firstBlock = new ArrayList<>();
        List<String> secondBlock = new ArrayList<>();

        FileWriter fileWriter;
        try {

            int n = findFirstCount();
            int m = findSecondCount(n);

            Scanner scanner = new Scanner(file);

            int a = 0;
            int c = 0;

            while (scanner.hasNextLine()){
                s = scanner.nextLine();
                matcher = p2.matcher(s);
                if (matcher.matches()) continue;
                matcher = p.matcher(s);
                if (matcher.matches()) {
                    int b = Integer.parseInt(s);
                    if (b == m) break;
                    else continue;
                }
                firstBlock.add(a,s);
                a++;
            }

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                matcher = p2.matcher(s);
                if (matcher.matches()) continue;
                secondBlock.add(c, s);
                c++;
            }

            scanner.close();

            String output = wordsComparison(firstBlock,secondBlock);

            fileWriter = new FileWriter("C:/Users/shuny/OneDrive/Документы/output.txt");
            fileWriter.write(output);
            fileWriter.close();

            System.out.println(output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Integer findFirstCount() throws FileNotFoundException {
        int n = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            matcher = p2.matcher(s);
            if (matcher.matches()) continue;
            matcher = p.matcher(s);
            if(matcher.matches()) {
                n = Integer.parseInt(s);
                break;
            }
        }
        return n;
    }

    public static Integer findSecondCount(int n) throws FileNotFoundException {
        int m = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            matcher = p2.matcher(s);
            if (matcher.matches()) continue;
            matcher = p.matcher(s);
            if(matcher.matches()) {
                m = Integer.parseInt(s);
                if (m == n) continue;
                break;
            }
        }
        return m;
    }

    public static String wordsComparison(List<String> list1,List<String> list2) {
        StringBuilder res = new StringBuilder();
        char[] s1;
        char[] s2;
        int number = 1;
        for (String value : list1) {
            s1 = value.toCharArray();
            number = 1;
            for (String item : list2) {
                s2 = item.toCharArray();
                for (int k = 0; k < (s1.length + s2.length) / 2; k++) {
                    try {
                        if (s1[k] == s2[k]) {
                            number += 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                if (s1.length / number < 2) {
                    res.append(value).append(" : ").append(item).append("\n");
                    break;
                }
                number = 1;
            }
            if (s1.length / number > 2) {
                res.append(value).append(" : ?\n");
            }


        }
        return res.toString();
    }
}
