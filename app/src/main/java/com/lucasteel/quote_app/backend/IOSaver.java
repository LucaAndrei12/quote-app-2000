package com.lucasteel.quote_app.backend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class IOSaver {

    final static File FILE = new File("user-pref.txt");

    public void saveFavs(ArrayList favlist)
    {
        try {
            FileWriter fw = new FileWriter(FILE);
            fw.write(favlist.toString());
            fw.flush();
            fw.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFavs() throws IOException
    {
        FileReader fr = new FileReader(FILE);
        String unParsedList = "";
        int i;
        while((i=fr.read())!=-1)
            unParsedList = unParsedList + (char)i;
        fr.close();
        String readyToParse = unParsedList.substring(1, unParsedList.length()-1);
        String [] arrayToList = readyToParse.split(", ");
        ArrayList finalList = new ArrayList<String>();
        Collections.addAll(finalList, arrayToList);

        return finalList;
    }
}
