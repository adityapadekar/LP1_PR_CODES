import java.io.*;

public class pass1 {

    public static void main(String args[]) throws IOException {

        BufferedReader br;

        String IS[] = { "STOP", "ADD", "SUB", "MUL", "MOVER", "MOVEM" };
        String REG[] = { "AREG", "BREG", "CREG", "DREG" };
        String AD[] = { "START", "END" };
        String DS[] = { "DC", "DS" };

        int address = 0;
        String[] symIdx = new String[20];
        String[] litIdx = new String[20];
        int[] symAdd = new int[20];
        int[] litAdd = new int[20];

        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        FileWriter imc = new FileWriter("IM.txt");
        FileWriter stf = new FileWriter("ST.txt");
        FileWriter ltf = new FileWriter("LT.txt");

        int symp = 0, litp = 0;
        String s;

        while ((s = input.readLine()) != null) {
            String[] words = s.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].matches("\\d*") && words[i].length() > 2) {
                    int adr = Integer.parseInt(words[i]);
                    imc.write("(C," + Integer.parseInt(words[i]) + ")\n");
                    address = adr;
                } else {
                    for (int j = 0; j < AD.length; j++) {
                        if (words[i].equals(AD[j])) {
                            imc.write("(AD," + (j + 1) + ")");
                        }
                    }

                    for (int j = 0; j < IS.length; j++) {
                        if (words[i].equals(IS[j])) {
                            imc.write("(IS," + (j + 1) + ")");
                        }
                    }
                    for (int j = 0; j < REG.length; j++) {
                        if (words[i].equals(REG[j])) {
                            imc.write("(" + (j + 1) + ")");
                        }
                    }
                    for (int j = 0; j < DS.length; j++) {
                        if (words[i].equals(DS[j])) {
                            imc.write("(DL," + (j + 1) + ")");
                        }
                    }
                    if (words[i].length() == 1 && (words[words.length - 1].equals(words[i]))) {
                        int flag = 0;
                        for (int j = 0; j < symp; j++) {
                            if (words[i].equals(symIdx[j])) {
                                imc.write("(S," + j + ")\n");
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 0) {
                            imc.write("(S," + symp + ")\n");
                            symIdx[symp++] = words[i];
                        }
                    }
                    if (words[i].length() == 2 && (words[words.length - 1].equals(words[i]))
                            && words[i].charAt(0) == '=') {
                        imc.write("(L," + litp + ")\n");
                        litIdx[litp++] = words[i];
                    }
                    if (words[i].matches("\\d*")) {
                        imc.write("(C," + Integer.parseInt(words[i]) + ")\n");
                    }
                    if (words[i].length() == 1 && i == 0) {
                        for (int j = 0; j < symp; j++) {
                            if (words[i].equals(symIdx[j])) {
                                symAdd[j] = address;
                                continue;
                            }
                        }
                    }

                }
            }
            address++;
        }
        address--;
        for (int i = 0; i < litp; i++) {
            litAdd[i] = address;
            address++;
        }
        for (int i = 0; i < symp; i++) {
            stf.write(i + "\t\t\t" + symAdd[i] + "\n");
        }
        for (int i = 0; i < litp; i++) {
            ltf.write(i + "\t\t" + litAdd[i] + "\n");
        }

        imc.close();
        stf.close();
        ltf.close();

    }
}
