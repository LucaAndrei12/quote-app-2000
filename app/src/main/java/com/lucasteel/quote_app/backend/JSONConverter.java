package com.lucasteel.quote_app.backend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONConverter {
    ArrayList<String> result = new ArrayList<String>();

    public ArrayList<String> formatJSON(String json, String formatType)
    {

        switch (formatType)
        {
            //Gets the raw JSON quote info and returns only the author of the quote and the quote itself
            case "quote":
                HashMap<String, Object> mapQuote = new Gson().fromJson(json, HashMap.class);
                String content = mapQuote.get("content").toString();
                String author = mapQuote.get("author").toString();
                result.add(content);
                result.add(author);
                break;

            //Gets the raw response from the API in JSON format and returns only the random color hex value
            case "color":
                JsonElement rawInfoBeingParsed = JsonParser.parseString(json);
                JsonObject fetchedJson = rawInfoBeingParsed.getAsJsonObject();
                JsonArray jarray = fetchedJson.getAsJsonArray("colors");
                String subarray = jarray.toString().substring(1,jarray.toString().length() - 1);
                //  System.out.println(subarray);
                HashMap<String, Object> mapColor = new Gson().fromJson(subarray, HashMap.class);
                String hexValue = mapColor.get("hex").toString();
                //  System.out.println(hexValue);
                result.add("0x" + hexValue);
                break;
        }
        return result;
    }
}
