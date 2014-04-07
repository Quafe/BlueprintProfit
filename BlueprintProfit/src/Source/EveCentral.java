package Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Organizes prices from eve central
 */
public class EveCentral {
    
    /*************
    * ATTRIBUTES *
    *************/
    LinkedList<String> DownloadQueue;
    
    /***************
    * CONSTRUCTORS *
    ***************/
    public EveCentral(){
        DownloadQueue = new LinkedList();
        DownloadQueue.add("34");
        DownloadQueue.add("35");
        DownloadQueue.add("36");
    }
    
    /*******************
    * MEMBER FUNCTIONS *
    *******************/
    public void Download(){
        // abort if nothing in queue
        if(DownloadQueue.size() == 0)
            return;

        // construct download url string
        String URLName = "http://api.eve-central.com/api/marketstat?usesystem=30000142";
        while(!DownloadQueue.isEmpty()){
            URLName += "&typeid="+DownloadQueue.poll();
        }
        try{
            System.err.println(URLName);
            URL url = new URL(URLName);
            BufferedReader br = new BufferedReader( new InputStreamReader(url.openStream()));
            String xmlfile = "";
            String line;
            while ((line = br.readLine()) != null) {                
                xmlfile += line;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    public void addID(){
        // adds ID to download queue
    }
    public void Buy(int ID){
        // returns buy price for given id
    }
    public void Sell(int ID){
        // returns sell price for given id
    }
}
