package Source;

/**
 * Datastructure for a single Material
 */
public class Material {

    /**
     * ATTRIBUTES
     */
    int MaterialID;         // typeID of material
    String MaterialName;    // typeName of material
    int Quantity;           // quantity needed for single run

    /**
     * CONSTRUCTOR
     */
    public Material() {
        this.MaterialID = 0;
        this.MaterialName = "";
        this.Quantity = 0;
    }

    public Material(int MaterialID, String MaterialName, int Quantity) {
        this.MaterialID = MaterialID;
        this.MaterialName = MaterialName;
        this.Quantity = Quantity;
    }

    /**
     * MEMBER FUNCTIONS
     */
    void printMaterials() {
        System.out.println("# Material: [" + MaterialID + "] " + MaterialName);
        System.out.println("# Quantity:" + Quantity);
    }
}
