package Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Organizes prices from eve central
 */
public class EveCentral {

    // struct for single price stats
    public class Stats {

        // max price is 9.223.372.036.854,00
        long volume;
        long avg;
        long max;
        long min;
        int stddev;
        long median;
        int percentile;

        // parse data line from xml
        private void parse(String s) {
            volume = Long.parseLong(substr(s, "<volume>", "</volume>"));
            avg = Long.parseLong(substr(s, "<avg>", "</avg>").replace(".", ""));
            max = Long.parseLong(substr(s, "<max>", "</max>").replace(".", ""));
            min = Long.parseLong(substr(s, "<min>", "</min>").replace(".", ""));
            stddev = Integer.parseInt(substr(s, "<stddev>", "</stddev>").replace(".", ""));
            median = Long.parseLong(substr(s, "<median>", "</median>").replace(".", ""));
            percentile = Integer.parseInt(substr(s, "<percentile>", "</percentile>").replace(".", ""));
        }
    }

    // struct for XML data
    public class Price {

        Stats buy;
        Stats sell;
        Stats all;

        public Price() {
            buy = new Stats();
            sell = new Stats();
            all = new Stats();
        }
    }
    /**
     * ATTRIBUTES
     */
    public static LinkedList<String> DownloadQueue;
    public static Map<Integer, Price> Data;

    /**
     * CONSTRUCTORS
     */
    public EveCentral() {
        DownloadQueue = new LinkedList();
        Data = new HashMap<>();
    }

    /**
     * MEMBER FUNCTIONS
     */
    public void Download() {
        // abort if nothing in queue
        if (DownloadQueue.size() == 0) {
            return;
        }

        // construct download url string
        String URLName = "http://api.eve-central.com/api/marketstat?usesystem=30000142";
        while (!DownloadQueue.isEmpty()) {
            URLName += "&typeid=" + DownloadQueue.poll();
        }
        try {
            URL url = new URL(URLName);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String xmlfile = "";
            String line;
            while ((line = br.readLine()) != null) {
                xmlfile += line + "\n";
            }

            // STRIP EVE CENTRAL HEADER
            String ohneheader;
            ohneheader = substr(xmlfile, "<marketstat>", "</marketstat>");

            // BREAK DOWN INTO SINGLE ITEMS
            String[] items;
            String splitat = "</type>";
            items = ohneheader.split(splitat);

            // for each item in query
            for (String item : items) {
                // temporary data struct
                Price tmp = new Price();
                // item ID
                int itemID = Integer.parseInt(substr(item, "<type id=\"", "\">"));
                // buy values
                String buy = substr(item, "<buy>", "</buy>");
                tmp.buy.parse(buy);
                // sell values
                String sell = substr(item, "<sell>", "</sell>");
                tmp.buy.parse(sell);
                // all values
                String all = substr(item, "<all>", "</all>");
                tmp.buy.parse(all);
                // add to HashMap
                Data.put(itemID, tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addID(int id) {
        if (!Data.containsKey(id)) {
            DownloadQueue.add(String.valueOf(id));
        }
    }

    private String substr(String s, String b, String e) {
        return s.substring(s.indexOf(b) + b.length(), s.indexOf(e));
    }
}
