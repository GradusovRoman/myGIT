package gb.xokyopo.servlets.service.represantations;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderRep implements Serializable {
    private int id;
    private Map<ProductRep, Integer> productIntegerMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<ProductRep, Integer> getProductRepIntegerMap() {
        return productIntegerMap;
    }

    public void setProductRepIntegerMap(Map<ProductRep, Integer> productIntegerMap) {
        this.productIntegerMap = productIntegerMap;
    }

    public Set<Map.Entry<ProductRep, Integer>> getProductRepIntegerSet() {
        return this.productIntegerMap.entrySet();
    }

    public void setProductRepIntegerSet(Set<Map.Entry<ProductRep, Integer>> productRepIntegerSet) {
        this.productIntegerMap = productRepIntegerSet.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int rowCount() {
        return this.productIntegerMap.size();
    }

    public int getFullPrice() {
        int[] count = {0};
        this.productIntegerMap.forEach((productRep, integer) -> {
            count[0] += productRep.getPrice() * integer;
        });
        return count[0];
    }
}
