package Source;

/**
 * Class for (all) calculations profit, pe, me, time, ...
 */
public class Calculator {

    // perfect ME, formula used descripted below (wasteFactor 10% -> 0.1)
    // perfect ME = roundUp( 2 * highestQuantity * (wasteFactor / (WasteFactor+1)) - 1 )
    public int perfectME(Blueprint bp) {
        // highest quantity used for single run (with ME 0) from all materials
        int Q = bp.highestMaterialQuantity();
        // blueprint waste factor
        double W = bp.WasteFactor / 100.0d;
        // perfect ME double value
        double pME = 2.0d * Q * (W / W + 1.0d) - 1.0d;

        return (int) Math.ceil(pME);
    }
    // perfect PE with perfect = less than X
    // time wasted per run with PE X
    // ME needed to waste less than X isk
    // ME needed to waste less than X of Material X
    // time needed for ME X
    // time needed for PE X
    // ME max in X time
    // PE max in X time

    // cost ExtraMaterials
    public long PriceExtraMaterials(Blueprint bp) {
        long cost = 0;
        for (ExtraMaterial em : bp.ExtraMaterials) {
            cost += (em.Quantity * em.DamagePerJob * EveCentral.Data.get(em.MaterialID).sell.min) / 200;
            cost += (em.Quantity * em.DamagePerJob * EveCentral.Data.get(em.MaterialID).sell.median) / 200;
        }
        return cost;
    }

    // cost materials with perfect ME (without wastage)
    public long PriceMaterials(Blueprint bp) {
        long cost = 0;
        for (Material m : bp.Materials) {
            cost += m.Quantity * (EveCentral.Data.get(m.MaterialID).sell.min / 2);
            cost += m.Quantity * (EveCentral.Data.get(m.MaterialID).sell.median / 2);
        }
        return cost;
    }

    // number of units wasted
    public int AmountWasted(Material m, int wasteFactor) {
        return (int) Math.round(m.Quantity * wasteFactor / 100.0d);
    }

    // cost of wasted materials
    public long PriceWastage(Blueprint bp) {
        long cost = 0;
        for (Material m : bp.Materials) {
            cost += AmountWasted(m, bp.WasteFactor) * (EveCentral.Data.get(m.MaterialID).sell.min / 2);
            cost += AmountWasted(m, bp.WasteFactor) * (EveCentral.Data.get(m.MaterialID).sell.median / 2);
        }
        return cost;
    }
    // profit per unit with ME X
    // profit with ME X and PE X per timespan X
    // time for production run with PE X
}

/*
 std::vector<Material> Blueprint::Wastage(unsigned int ME)
 {
 std::vector<Material> wastage;
 for (int i = 0; i < Materials.size(); ++i)
 {
 Material m;
 m.ProductID = Materials[i].ProductID;
 m.ProductName = Materials[i].ProductName;
 m.MaterialID = Materials[i].MaterialID;
 m.MaterialName = Materials[i].MaterialName;
 std::cout<<wasteFactor<<std::endl;
 m.PerfectAmount = round(Materials[i].PerfectAmount * (wasteFactor/100.0f) * (1.0f/(1.0f+ME)));
 wastage.push_back(m);
 }
 return wastage;
 }*/
