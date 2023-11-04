import java.io.*;
import java.util.*;

public class Rough {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        FileWriter imc = new FileWriter("intermediate.txt");
        FileWriter mntf = new FileWriter("mnt.txt");
        FileWriter mdtf = new FileWriter("mdt.txt");
        FileWriter kpdtf = new FileWriter("kpdt.txt");
        HashMap<String,Integer> pnt = new HashMap<String,Integer>();
        String s;
        int pNo = 1, mdtp = 1, flag = 0, pp = 0, kp = 0, kpdtp = 0;
        while((s = input.readLine()) != null){
            String[] words = s.split("\\s");
            if(words[0].compareToIgnoreCase("MACRO") == 0){
                flag = 1;
                if(words.length <= 2){
                    mntf.write(words[1] + "\t" + pp + "\t" + kp + "\t" + mdtp + "\t" + (kp == 0 ? kpdtp : kpdtp + 1) + "\n");
                    continue;
                }
                String[] params = words[2].split(",");
                for(int i = 0; i < params.length; i++){
                    if(params[i].contains("=")){
                        kp++;
                        String keywordParam[] = params[i].split("=");
                        pnt.put(keywordParam[0].substring(1),pNo++);
                        if(keywordParam.length==2){
                            kpdtf.write(keywordParam[0].substring(1) + "\t" + keywordParam[1] + "\n");
                        }else{
                            kpdtf.write(keywordParam[0].substring(1) + "\t-\n");
                        }
                    }else{
                        pnt.put(params[i].substring(1),pNo++);
                        pp++;
                    }
                }
                mntf.write(words[1] + "\t" + pp + "\t" + kp + "\t" + mdtp + "\t" + (kp == 0 ? kpdtp : kpdtp + 1) + "\n");
                kpdtp += kp;
            }else if(words[0].compareToIgnoreCase("MEND")==0){
                mdtf.write(s+"\n");
                flag = pp = kp = 0;
                mdtp++;
                pNo = 1;
                pnt.clear();
            }else if(flag == 1){
                for(int i = 0; i < s.length(); i++){
                    if(s.charAt(i) == '&'){
                        i++;
                        String temp = "";
                        while(!(s.charAt(i) == ' ' || s.charAt(i) == ',')){
                            temp += s.charAt(i);
                            i++;
                            if(i == s.length()){
                                break;
                            }
                        }
                        i--;
                        mdtf.write("#"+pnt.get(temp));
                    }else{
                        mdtf.write(s.charAt(i));
                    }
                }
                mdtf.write("\n");
                mdtp++;
            }else{
                imc.write(s + "\n");
            }
        }
        input.close();
        imc.close();
        mdtf.close();
        mntf.close();
        kpdtf.close();
    }
}
