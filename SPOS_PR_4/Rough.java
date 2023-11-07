import java.io.*;
import java.util.*;

public class Rough {
    public static void main(String[] args) throws IOException {
        BufferedReader imc = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedReader mdtf = new BufferedReader(new FileReader("mdt.txt"));
        BufferedReader mntf = new BufferedReader(new FileReader("mnt.txt"));
        BufferedReader kpdtf = new BufferedReader(new FileReader("kpdt.txt"));
        FileWriter output = new FileWriter("output.txt");

        HashMap<Integer, String> apt = new HashMap<>();
        HashMap<String, Integer> reverseApt = new HashMap<>();
        HashMap<String, Integer> mdtpHash = new HashMap<>();
        HashMap<String, Integer> kpHash = new HashMap<>();
        HashMap<String, Integer> kpdtpHash = new HashMap<>();
        HashMap<String, Integer> macroNameHash = new HashMap<>();

        Vector<String> mdt = new Vector<>();
        Vector<String> kpdt = new Vector<>();

        String s;
        int kp, pNo, pp, mdtp, kpdtp;

        while ((s = mdtf.readLine()) != null) {
            mdt.add(s);
        }

        while ((s = kpdtf.readLine()) != null) {
            kpdt.add(s);
        }

        while ((s = mntf.readLine()) != null) {
            String[] words = s.split("\\s");
            String temp = words[0] + words[1];
            macroNameHash.put(words[0], Integer.parseInt(words[1]));
            kpHash.put(temp, Integer.parseInt(words[2]));
            mdtpHash.put(temp, Integer.parseInt(words[3]));
            kpdtpHash.put(temp, Integer.parseInt(words[4]));
        }

        while((s = imc.readLine()) != null){
            String[] words = s.split("\\s")
            if(macroNameHash.containsKey(words[0])){
                pp = words[1].split(",").length - words[1].split("=").length + 1;
                mdtp = mdtpHash.get(words[0] + Integer.toString(pp));
                kp = kpHash.get(words[0] + Integer.toString(pp));
                kpdtp = kpdtpHash.get(words[0] + Integer.toString(pp));
                pNo = 1;

                String[] actucalParams = words[1].split(",");

                for(int i = 0; i < pp; i++){
                    apt.put(pNo,actucalParams[pNo - 1]);
                    reverseApt.put(actucalParams[pNo - 1],pNo);
                    pNo++;
                }

                int i = kpdtp - 1;

                for(int j = 0; j < kp; j++){
                    String[] temp = kpdt.get(i).split("\t");
                    apt.put(pNo,temp[1]);
                    reverseApt.put(temp[0],pNo);
                    pNo++;
                    i++;
                }

                i = pp + 1;

                while(i <= actucalParams.length){
                    
                }





            }
        }

        output.close();
        imc.close();
        mdtf.close();
        mntf.close();
        kpdtf.close();

    }
}
