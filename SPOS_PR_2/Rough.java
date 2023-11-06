import java.io.*;
import java.util.*;

public class Rough {
    public static void main(String[] args) throws IOException {
        BufferedReader imc = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedReader ltf = new BufferedReader(new FileReader("littbl.txt"));
        BufferedReader stf = new BufferedReader(new FileReader("symtbl.txt"));
        FileWriter output = new FileWriter("output.txt");

        HashMap<Integer, String> littab = new HashMap<>();
        HashMap<Integer, String> symtab = new HashMap<>();

        int sp = 1, lp = 1, offset;
        String s;

        while ((s = ltf.readLine()) != null) {
            String[] words = s.split("\t\t");
            littab.put(lp++, words[1]);
        }

        while ((s = stf.readLine()) != null) {
            String[] words = s.split("\t\t\t");
            symtab.put(sp++, words[1]);
        }

        while ((s = imc.readLine()) != null) {
            if (s.substring(1, 6).compareToIgnoreCase("IS,00") == 0) {
                output.write("+ 00 0 000\n");
            } else if (s.substring(1, 3).compareToIgnoreCase("IS") == 0) {
                output.write("+ " + s.substring(4, 6) + " ");
                if (s.charAt(9) == ')') {
                    output.write(s.charAt(8) + " ");
                    offset = 3;
                } else {
                    output.write("0 ");
                    offset = 0;
                }
                if (s.charAt(8 + offset) == 'S') {
                    output.write(symtab.get(Integer.parseInt(s.substring(10 + offset, s.length() - 1))) + "\n");
                } else {
                    output.write(littab.get(Integer.parseInt(s.substring(10 + offset, s.length() - 1))) + "\n");

                }
            } else if (s.substring(1, 6).compareToIgnoreCase("DL,01") == 0) {
                String s1 = s.substring(10, s.length() - 1), s2 = "";
                for (int i = 0; i < 3 - s1.length(); i++) {
                    s2 += "0";
                }
                s2 += s1;
                output.write("+ 00 0 " + s2 + "\n");
            } else {
                output.write("\n");
            }
        }

        output.close();
        imc.close();
        stf.close();
        ltf.close();
    }
}
