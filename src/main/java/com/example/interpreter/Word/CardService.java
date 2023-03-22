package com.example.interpreter.Word;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;
    //    Раньше было:
    //    private SortedMap<String, Definition> dictionary;

    public CardService() {

    }

    public List<Card> getCards() {
        return repository.findAll();
    }

    public List<Card> getCardsToLearn() {
        return repository.getCardsToLearn();
    }


    public Card addCard(Card c) {
        return this.repository.save(c);
    }

    public void removeCard(Card c) {
        this.repository.delete(c);
    }

    public Card markViewed(Card c) {
        c.timesViewed++;
        return this.repository.save(c);
    }

    public ArrayList<String> translate(String s, boolean frRtoE) {
        ArrayList<String> translations = new ArrayList<>();
        try {

            URL url = new URL("https://api.reverso.net/translate/v1/translation");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Firefox 31");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\n" +
                    "  format: 'text',\n" +
                    "  from:  '" + (frRtoE ? "rus" : "eng") + "',\n" +
                    "  input: '" + s + "',\n" +
                    "  options: {\n" +
                    "    contextResults: true,\n" +
                    "    languageDetection: true,\n" +
                    "    origin: 'reversomobile',\n" +
                    "    sentenceSplitter: false,\n" +
                    "  },\n" +
                    "  to: '" + (frRtoE ? "eng" : "rus") + "',\n" +
                    "}");
            osw.flush();
            osw.close();
            os.close();

            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONObject response = new JSONObject(inline);

                System.out.println(inline);
                JSONArray translations2 = response.getJSONObject("contextResults").getJSONArray("results");

                for (int i = 0; i < translations2.length(); i++) {
                    translations.add(translations2.getJSONObject(i).getString("translation"));
                }
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return translations;

    }


}
