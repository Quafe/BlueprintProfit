package Source;

import java.util.ArrayList;

/**
 * Datastructure for a single Blueprint
 */
public class Blueprint {
    
    /*************
    * ATTRIBUTES *
    *************/
    int BlueprintID;            // typeID of the blueprint
    String BlueprintName;       // blueprint name
    int ProductID;              // typeID of produced item
    String ProductName;         // name of produced item
    int ProductionTime;         // base production time
    int PortionSize;            // units produced per single run
    int TechLevel;              // blueprint tech level
    int ResearchTimePE;         // base time of productivity research
    int ResearchTimeME;         // seconds for +1 ME
    int ResearchTimeCopy;       // base copy time, equal to half maxProductionLimit runs?
    int ProductivityModifier;   // used for manufacturing time (time+(1-PE)*modifier)
    int MaterialModifier;       // used at all? base mineral modifier?
    float WasteFactor;          // wastage factor for materials
    int ProductionLimit;        // maximum number of runs per blueprintcopy
    ArrayList<Material> Materials;  // vector of blueprint materials
    ArrayList<ExtraMaterial> ExtraMaterials;   // vector of blueprint extra materials
    
    /***************
    * CONSTRUCTORS *
    ***************/
    public Blueprint() {
        this.BlueprintID = 0;
        this.BlueprintName = "";
        this.ProductID = 0;
        this.ProductName = "";
        this.ProductionTime = 0;
        this.PortionSize = 0;
        this.ResearchTimePE = 0;
        this.ResearchTimeME = 0;
        this.ResearchTimeCopy = 0;
        this.ProductivityModifier = 0;
        this.MaterialModifier = 0;
        this.WasteFactor = 0.0f;
        this.ProductionLimit = 0;
        Materials = new ArrayList<>();
        ExtraMaterials = new ArrayList<>();
    }
    public Blueprint(int BlueprintID, String BlueprintName, int ProductID, String ProductName, int ProductionTime, int PortionSize, int ResearchTimePE, int ResearchTimeME, int ResearchTimeCopy, int ProductivityModifier, int MaterialModifier, float WasteFactor, int ProductionLimit, ArrayList<Material> Materials, ArrayList<ExtraMaterial> ExtraMaterials) {
        this.BlueprintID = BlueprintID;
        this.BlueprintName = BlueprintName;
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductionTime = ProductionTime;
        this.PortionSize = PortionSize;
        this.ResearchTimePE = ResearchTimePE;
        this.ResearchTimeME = ResearchTimeME;
        this.ResearchTimeCopy = ResearchTimeCopy;
        this.ProductivityModifier = ProductivityModifier;
        this.MaterialModifier = MaterialModifier;
        this.WasteFactor = WasteFactor;
        this.ProductionLimit = ProductionLimit;
        this.Materials = Materials;
        this.ExtraMaterials = ExtraMaterials;
    }
    
    /*******************
    * MEMBER FUNCTIONS *
    *******************/
    public void printAttributes() {
        System.out.println("###[BLUEPRINT]###");
        System.out.println("# BID:" + BlueprintID + "\t BName:" + BlueprintName);
        System.out.println("# PID:" + ProductID + "\t PName:" + ProductName);
        System.out.println("# PTime:" + ProductionTime + "\t PSize:" + PortionSize);
        System.out.println("# RPE:" + ResearchTimePE + "\t RME:" + ResearchTimeME + "\t RCP:" + ResearchTimeCopy);
        System.out.println("# PMod:" + ProductivityModifier + "\t MMod:" + MaterialModifier);
        System.out.println("# Waste:" + WasteFactor + "\t PLimit:" + ProductionLimit);
        // TODO add print materials
        // TODO add print extra materials
    }
    public boolean hasMaterials(){
        return Materials.size() == 0 ? false : true;
    }
    public boolean hasExtraMaterials(){
        return ExtraMaterials.size() == 0 ? false : true;
    }
}
