package com.lucasteel.quote_app.backend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class IOSaver {

    public void saveFavs(ArrayList favlist, File file) {
        System.out.println(favlist);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(makeARString(favlist));
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFavs(File file) throws IOException {
        if (file.exists()) {
        } else {
            file.createNewFile();
        }
        FileReader fr = new FileReader(file);
        String unParsedList = "";
        int i;
        while ((i = fr.read()) != -1)
            unParsedList = unParsedList + (char) i;
        fr.close();
        if (!unParsedList.equals("")) {
            System.out.println(unParsedList);
            String readyToParse = unParsedList.substring(1, unParsedList.length() - 1);
            String[] arrayToList = readyToParse.split("222");
            ArrayList finalList = new ArrayList<String>();
            Collections.addAll(finalList, arrayToList);

            return finalList;
        }
        return null;
    }

    public String makeARString(ArrayList<String> arraylist){
        String result = "[";
        for(String str: arraylist)
        {
            result += (str + "222");
        }
        result += "]";
        return result;
    }
}
