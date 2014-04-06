package Source;

/**
 * Datastructure for a single ExtraMaterial
 */
public class ExtraMaterial {
    
    /*************
    * ATTRIBUTES *
    *************/
    int MaterialID;         // typeID of material
    String MaterialName;    // typeName of material
    int Quantity;           // quantity needed for single run
    float DamagePerJob;     // damage of material per run in percentage
    
    /**************
    * CONSTRUCTOR *
    **************/
    public ExtraMaterial(){
        this.MaterialID = 0;
        this.MaterialName = "";
        this.Quantity = 0;
        this.DamagePerJob = 0.0f;
    }
    public ExtraMaterial(int MaterialID, String MaterialName, int Quantity, float DamagePerJob){
        this.MaterialID = MaterialID;
        this.MaterialName = MaterialName;
        this.Quantity = Quantity;
        this.DamagePerJob = DamagePerJob;
    }
    
    /*******************
    * MEMBER FUNCTIONS *
    *******************/
    void printExtraMaterials() {
        System.out.println("# MatID:" + MaterialID + "\t MatName:" + MaterialName);
        System.out.println("# Quantity:" + Quantity + "\t DmgPerJob:" + DamagePerJob);
    }
}
