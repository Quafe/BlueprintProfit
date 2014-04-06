package Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * holds a list of all blueprints
 */
public class Blueprints {
    
    /*************
    * ATTRIBUTES *
    *************/
    public ArrayList<Blueprint> BPL;
    
    /**************
    * CONSTRUCTOR *
    **************/
    public Blueprints() {
        BPL = new ArrayList<>();
    }
    
    /*******************
    * MEMBER FUNCTIONS *
    *******************/
    public void Load(){
        LoadBlueprints();
        LoadMaterials();
        LoadExtraMaterials();
    }
    private void LoadBlueprints(){
        String Path = "Data/";
        String Filename = "Blueprints.csv";
        try{
            // iostream
            BufferedReader br;
            // csv lies in data folder, charset is utf-8 without bom
            br = Files.newBufferedReader(Paths.get(Path + Filename), Charset.forName("UTF-8"));
            // string which holds a single line
            String line;
            // iterate over all lines of the file
            while ((line = br.readLine()) != null) {
                // split line by semicolons
                String[] split = line.split(";");
                // fill Material with parsed data
                Blueprint b;
                b = new Blueprint();
                b.BlueprintID = Integer.parseInt(split[0]);
                b.BlueprintName = split[1];
                b.ProductID = Integer.parseInt(split[2]);
                b.ProductName = split[3];
                b.ProductionTime = Integer.parseInt(split[4]);
                b.PortionSize = Integer.parseInt(split[5]);
                b.TechLevel = Integer.parseInt(split[6]);
                b.ResearchTimePE = Integer.parseInt(split[7]);
                b.ResearchTimeME = Integer.parseInt(split[8]);
                b.ResearchTimeCopy = Integer.parseInt(split[9]);
                b.ProductivityModifier = Integer.parseInt(split[10]);
                b.MaterialModifier = Integer.parseInt(split[11]);
                b.WasteFactor = Float.parseFloat(split[12]);
                b.ProductionLimit = Integer.parseInt(split[13]);

                // push into main data structure list from class
                BPL.add(b);
            }
            // close properly
            br.close();
        } catch (IOException e) {
            // basic "file not found" message
            System.err.format("IOException: %s%n", e);
        }
    }
    private void LoadMaterials(){
        String Path = "Data/";
        String Filename = "Materials.csv";
        try{
            // iostream
            BufferedReader br;
            // csv lies in data folder, charset is utf-8 without bom
            br = Files.newBufferedReader(Paths.get(Path + Filename), Charset.forName("UTF-8"));
            // string which holds a single line
            String line;
            // iterate over all lines of the file
            while ((line = br.readLine()) != null) {
                // split line by semicolons
                String[] split = line.split(";");
                // fill Material with parsed data
                Material m;
                m = new Material();
                int ProductID = Integer.parseInt(split[0]);
                m.MaterialID = Integer.parseInt(split[1]);
                m.MaterialName = split[2];
                m.Quantity = Integer.parseInt(split[3]);

                int index = SearchProductID(ProductID);
                
                // this should NEVER happen
                if(index == -1)
                    System.err.println("CSV DATA ERROR! PRODUCTID("+ProductID+") NOT FOUND! MATERIAL WITHOUT BLUEPRINT!");
                else{
                    BPL.get(index).Materials.add(m);
                }
            }
            // close file properly
            br.close();
        } catch (IOException e) {
            // basic "file not found" message
            System.err.format("IOException: %s%n", e);
        }
    }
    private void LoadExtraMaterials(){
        String Path = "Data/";
        String Filename = "ExtraMaterials.csv";
        try{
            // iostream
            BufferedReader br;
            // csv lies in data folder, charset is utf-8 without bom
            br = Files.newBufferedReader(Paths.get(Path + Filename), Charset.forName("UTF-8"));
            // string which holds a single line
            String line;
            // iterate over all lines of the file
            while ((line = br.readLine()) != null) {
                // split line by semicolons
                String[] split = line.split(";");
                // fill ExtraMaterial with parsed data
                ExtraMaterial m;
                m = new ExtraMaterial();
                int ProductID = Integer.parseInt(split[0]);
                m.MaterialID = Integer.parseInt(split[1]);
                m.MaterialName = split[2];
                m.Quantity = Integer.parseInt(split[3]);
                split[4] = split[4].replace(",", ".");
                m.DamagePerJob = Float.parseFloat(split[4]);
                        
                int index = SearchProductID(ProductID);
                
                // this should NEVER happen
                if(index == -1)
                    System.err.println("CSV DATA ERROR! PRODUCTID("+ProductID+") NOT FOUND! EXTRAMATERIAL WITHOUT BLUEPRINT!");
                else{
                    BPL.get(index).ExtraMaterials.add(m);
                }
            }
            // close file properly
            br.close();
        } catch (IOException e) {
            // basic "file not found" message
            System.err.format("IOException: %s%n", e);
        }
    }
    // returns index of Blueprint in BPL with searched ProductID
    // if ProductID wasnt found return value is -1
    public int SearchProductID(int ProductID){
        for (int i = 0; i < BPL.size(); i++) {
            if (ProductID == BPL.get(i).ProductID) {
                return i;
            }
        }
        return -1;
    }
    // returns true if all Blueprints have Materials or ExtraMaterials
    public boolean AllBlueprintsHaveMaterials(){
        for (int i = 0; i < BPL.size(); i++) {
            if(!BPL.get(i).hasMaterials() & !BPL.get(i).hasExtraMaterials()){
                return false;
            }
        }
        return true;
    }
}
