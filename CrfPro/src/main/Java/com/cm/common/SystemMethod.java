package com.cm.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SystemMethod {
    public static List<String> executeNewFlow(List<String> commands) {
        List<String> rspList = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();
        try {
            Process proc = run.exec("/bin/bash", null, null);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            for (String line : commands) {
                out.println(line);
            }
            out.println("exit");// 这个命令必须执行，否则in流不结束。
            String rspLine = "";
            while ((rspLine = in.readLine()) != null) {
//                System.out.println(rspLine);
                String[] tot = rspLine.split("\t");
                if(tot.length==4){
//                    System.out.println(tot[0]+"\t"+tot[3]);
                    rspList.add(tot[0]+"\t"+tot[3]);
                }
                else{
//                    System.out.println(rspLine+"??????"+tot.length);
                }
            }
            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rspList;
    }

}
