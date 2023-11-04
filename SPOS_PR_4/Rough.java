import java.io.*;
import java.util.*;

public class Rough {
    public static void main(String[] args) throws IOException {
        BufferedReader imc = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedReader mdtf = new BufferedReader(new FileReader("mdt.txt"));
        BufferedReader mntf = new BufferedReader(new FileReader("mnt.txt"));
        BufferedReader kpdtf = new BufferedReader(new FileReader("kpdt.txt"));
        FileWriter output = new FileWriter("output.txt");

        HashMap<Integer, String> apt = new HashMap<Integer, String>();
        HashMap<String, Integer> aptInverse = new HashMap<String, Integer>();
        HashMap<String, Integer> mdtpHash = new HashMap<String, Integer>();
        HashMap<String, Integer> kpdtpHash = new HashMap<String, Integer>();
        HashMap<String, Integer> kpHash = new HashMap<String, Integer>();
        HashMap<String, Integer> macroNameHash = new HashMap<String, Integer>();

        Vector<String> mdt = new Vector<String>();
        Vector<String> kpdt = new Vector<String>();

        String s;
        int pp, kp, mdtp, kpdtp, pNo;

        while ((s = mdtf.readLine()) != null) {
            mdt.add(s);
        }

        while ((s = kpdtf.readLine()) != null) {
            kpdt.add(s);
        }

        while ((s = mntf.readLine()) != null) {
            String[] words = s.split("\\s");

            String temp = words[0] + words[1];
            macroNameHash.put(words[0], 1);
            kpHash.put(temp, Integer.parseInt(words[2]));
            mdtpHash.put(temp, Integer.parseInt(words[3]));
            kpdtpHash.put(temp, Integer.parseInt(words[4]));
        }

        while ((s = imc.readLine()) != null) {
            String[] words = s.split("\\s");
            if (macroNameHash.containsKey(words[0])) {
                pp = words[1].split(",").length - words[1].split("=").length + 1;
                kp = kpHash.get(words[0] + Integer.toString(pp));
                mdtp = mdtpHash.get(words[0] + Integer.toString(pp));
                kpdtp = kpdtpHash.get(words[0] + Integer.toString(pp));
                String[] actualParams = words[1].split(",");
                pNo = 1;
                for (int i = 0; i < pp; i++) {
                    apt.put(pNo, actualParams[pNo - 1]);
                    aptInverse.put(actualParams[pNo - 1], pNo);
                    pNo++;
                }

                int j = kpdtp - 1;
                for (int i = 0; i < kp; i++) {
                    String[] temp = kpdt.get(j).split("\\s");
                    apt.put(pNo, temp[1]);
                    aptInverse.put(temp[0], pNo);
                    j++;
                    pNo++;
                }

                j = pp + 1;
                while (j <= actualParams.length) {
                    String[] initializedParams = actualParams[j - 1].split("=");
                    apt.put(aptInverse.get(initializedParams[0].substring(1)), initializedParams[1]);
                    j++;
                }

                j = mdtp - 1;
                while (mdt.get(j).compareToIgnoreCase("MEND") != 0) {
                    output.write("+ ");
                    for (int i = 0; i < mdt.get(j).length(); i++) {
                        if (mdt.get(j).charAt(i) == '#') {
                            output.write(apt.get(Integer.parseInt("" + mdt.get(j).charAt(++i))));
                        } else {
                            output.write(mdt.get(j).charAt(i));
                        }
                    }
                    output.write("\n");
                    j++;
                }

                apt.clear();
                aptInverse.clear();
            } else {
                output.write("+ " + s + "\n");
            }
        }
        output.close();
        imc.close();
        mdtf.close();
        mntf.close();
        kpdtf.close();

    }
}
