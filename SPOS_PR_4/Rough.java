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
        int pp, kp, kpdtp, mdtp, paramNo;

        while ((s = mdtf.readLine()) != null) {
            mdt.addElement(s);
            System.out.println(1);
        }
        while ((s = kpdtf.readLine()) != null) {
            kpdt.addElement(s);
        }
        while ((s = mntf.readLine()) != null) {
            String[] words = s.split("\\s");
            String temp = words[0] + words[1];
            macroNameHash.put(words[0], 1);
            mdtpHash.put(temp, Integer.parseInt(words[2]));
            kpHash.put(temp, Integer.parseInt(words[3]));
            kpdtpHash.put(temp, Integer.parseInt(words[4]));
        }

        while ((s = imc.readLine()) != null) {
            String[] words = s.split("\\s");
            if (macroNameHash.containsKey(words[0])) {
                pp = words[1].split(",").length - words[1].split("=").length + 1;
                mdtp = mdtpHash.get(words[0] + Integer.toString(pp));
                kp = kpHash.get(words[0] + Integer.toString(pp));
                kpdtp = kpdtpHash.get(words[0] + Integer.toString(pp));
                paramNo = 1;
                String[] actualParams = words[1].split(",");
                for (int i = 0; i < pp; i++) {
                    apt.put(paramNo, actualParams[paramNo - 1]);
                    aptInverse.put(actualParams[paramNo - 1], paramNo);
                    paramNo++;
                }
                int i = kpdtp - 1;
                for (int j = 0; j < kp; j++) {
                    String[] params = kpdt.get(i).split("\t");
                    apt.put(paramNo, params[1]);
                    aptInverse.put(params[0], paramNo);
                    i++;
                    paramNo++;
                }
                
                i = pp + 1;
                while (i <= actualParams.length) {
                    String[] initParam = actualParams[i - 1].split("=");
                    apt.put(aptInverse.get(initParam[0].substring(1)), initParam[1]);
                    i++;
                }
                
                i = mdtp - 1;
                System.out.println(mdtp);
                while (mdt.get(i).compareToIgnoreCase("MEND") != 0) {
                    output.write("+ ");
                    for (int j = 0; j < mdt.get(i).length(); j++) {
                        if (mdt.get(i).charAt(j) == '#') {
                            output.write(apt.get(Integer.parseInt("" + mdt.get(i).charAt(++j))));
                        } else {
                            output.write(mdt.get(i).charAt(j));
                        }
                    }
                    output.write("\n");
                    i++;
                }

                apt.clear();
                aptInverse.clear();
            } else {
                output.write(s + "\n");
            }
        }

        output.close();
        imc.close();
        mdtf.close();
        mntf.close();
        kpdtf.close();

    }
}
