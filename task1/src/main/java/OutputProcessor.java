import com.google.gson.Gson;
import lombok.Data;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
@Data
public class OutputProcessor  {
    private ArrayList<PurchasedItems> purchasedItemsList = new ArrayList<>();
    private ArrayList<String> outPut= new ArrayList<>();
    public void creatOutput(HashMap<String, Integer> products,HashMap<String, Object> mappings){
        for (String key: mappings.keySet()){
            if(products.containsKey(key)){
                toProductClass(mappings.get(key),products.get(key));
            }
        }

      Collections.sort(purchasedItemsList,new ItemsComparator());

      for (PurchasedItems purchasedItems : purchasedItemsList){
          Gson gson = new Gson();
          outPut.add(gson.toJson(purchasedItems));
      }
    }
    private void toProductClass(Object obj,Integer quantity){
          Gson gson = new Gson();
          PurchasedItems purchasedItems = gson.fromJson(String.valueOf(obj), PurchasedItems.class);
          purchasedItems.setQuantity(quantity);
          purchasedItemsList.add(purchasedItems);
    }

}
