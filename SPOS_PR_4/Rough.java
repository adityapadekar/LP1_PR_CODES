import java.io.*;
import java.util.*;

public class Rough {
    public static void main(String[] args) throws IOException {
        BufferedReader imc = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedReader mdtf = new BufferedReader(new FileReader("mdt.txt"));
        BufferedReader mnt = new BufferedReader(new FileReader("mnt.txt"));
        BufferedReader kpdtf = new BufferedReader(new FileReader("kpdt.txt"));
        FileWriter output = new FileWriter("output.txt");

        HashMap<Integer,String> apt = new HashMap<>();
        HashMap<String,Integer> aptInverse = new HashMap<>();
        HashMap<String,Integer> mdtpHash = new HashMap<>();
        HashMap<String,Integer> kpdtpHash = new HashMap<>();
        HashMap<String,Integer> kpHash = new HashMap<>();
        HashMap<String,Integer> macroNameHash = new HashMap<>();

        Vector<String> mdt = new Vector<>();
        Vector<String> kpdt = new Vector<>();

        String s, temp;
        int pp, kp, mdtp, kpdtp, pNo;

        while((s = mdtf.readLine()) != null){
            mdt.add(s);
        }

        while((s = kpdtf.readLine()) != null){
            kpdt.add(s);
        }

        while ((s = mnt.readLine()) != null){
            String[] words = s.split("\\s");
        }

    }
}
