import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.Data;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
@Data
public class OutputProcessor  {
    private ArrayList<PurchasedItems> purchasedItemsList = new ArrayList<>();
    private ArrayList<String> outPutList= new ArrayList<>();
    private JsonArray jsArray = new JsonArray();

    public void creatOutput(HashMap<String, Integer> products,HashMap<String, Object> mappings){
        for (String key: mappings.keySet()){
            if(products.containsKey(key)){
                toProductClass(mappings.get(key),products.get(key));
            }
        }

      Collections.sort(purchasedItemsList,new ItemsComparator());

      for (PurchasedItems purchasedItems : purchasedItemsList){
          Gson gson = new Gson();
          outPutList.add(gson.toJson(purchasedItems));
          jsArray.add(gson.toJson(purchasedItems));
      }
    }

    private void toProductClass(Object obj,Integer quantity){
          Gson gson = new Gson();
          PurchasedItems purchasedItems = gson.fromJson(String.valueOf(obj), PurchasedItems.class);
          purchasedItems.setQuantity(quantity);
          purchasedItemsList.add(purchasedItems);
    }

}
