package Source;

import java.util.ArrayList;

/**
 * Datastructure for a single Blueprint
 */
public class Blueprint {

    /**
     * ATTRIBUTES
     */
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
    int WasteFactor;            // wastage factor for materials
    int ProductionLimit;        // maximum number of runs per blueprintcopy
    ArrayList<Material> Materials;  // vector of blueprint materials
    ArrayList<ExtraMaterial> ExtraMaterials;   // vector of blueprint extra materials

    /**
     * CONSTRUCTORS
     */
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
        this.WasteFactor = 0;
        this.ProductionLimit = 0;
        Materials = new ArrayList<>();
        ExtraMaterials = new ArrayList<>();
    }

    public Blueprint(int BlueprintID, String BlueprintName, int ProductID, String ProductName, int ProductionTime, int PortionSize, int ResearchTimePE, int ResearchTimeME, int ResearchTimeCopy, int ProductivityModifier, int MaterialModifier, int WasteFactor, int ProductionLimit, ArrayList<Material> Materials, ArrayList<ExtraMaterial> ExtraMaterials) {
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

    /**
     * MEMBER FUNCTIONS
     */
    public void printBlueprint() {
        printAttributes();
        printMaterials();
        printExtraMaterials();
        System.out.println();
    }

    public void printAttributes() {
        System.out.println("##### [" + BlueprintID + "] " + BlueprintName + " #####");
        System.out.println("# Product: [" + ProductID + "] " + ProductName);
        System.out.println("# ProductionTime:" + ProductionTime + "\t PortionSize:" + PortionSize);
        System.out.println("# Research: PE[" + ResearchTimePE + "] ME[" + ResearchTimeME + "] Copy[" + ResearchTimeCopy + "]");
        System.out.println("# ProdMod:" + ProductivityModifier + "\t MatMod:" + MaterialModifier);
        System.out.println("# Wasteage:" + WasteFactor + "\t ProdLimit:" + ProductionLimit);
    }

    public void printMaterials() {
        System.out.println("##### MATERIALS #####");
        if (Materials.isEmpty()) {
            System.out.println("# none");
            return;
        }
        for (int i = 0; i < Materials.size(); i++) {
            Materials.get(i).printMaterials();
        }
    }

    public void printExtraMaterials() {
        System.out.println("##### EXTRAMATERIALS #####");
        if (ExtraMaterials.isEmpty()) {
            System.out.println("# none");
            return;
        }
        for (int i = 0; i < ExtraMaterials.size(); i++) {
            ExtraMaterials.get(i).printExtraMaterials();
        }
    }

    // returns highes amount of needed materials (used for perfect ME calculation)
    public int highestMaterialQuantity() {
        int highestAmount = 0;
        for (Material m : Materials) {
            if (highestAmount < m.Quantity) {
                highestAmount = m.Quantity;
            }
        }
        return highestAmount;
    }

    public boolean hasMaterials() {
        return !Materials.isEmpty();
    }

    public boolean hasExtraMaterials() {
        return !ExtraMaterials.isEmpty();
    }
}
