import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.Data;

import java.util.*;

@Data
public class OutputProcessor  {
    private ArrayList<PurchasedItems> purchasedItemsList = new ArrayList<>();
    private ArrayList<String> outPutList= new ArrayList<>();
    private JsonArray jsArray = new JsonArray();
    private Set<String>  outValues = new HashSet<>();

    public void creatOutput(HashMap<String, Integer> products,HashMap<String, Object> mappings){
        createOutValues(mappings);
        for (String outValue: outValues){
            ArrayList<String> keys=getKeys(mappings,outValue);
            int count=0;
            for (String key: keys){
                count=count+products.get(key);
            }
            toProductClass(outValue,count);
        }
      Collections.sort(purchasedItemsList,new ItemsComparator());

      for (PurchasedItems purchasedItems : purchasedItemsList){
          Gson gson = new Gson();
          outPutList.add(gson.toJson(purchasedItems));
          jsArray.add(gson.toJson(purchasedItems));
      }
    }
    private void createOutValues(HashMap<String, Object> mappings){
        for (Map.Entry<String,Object> entry: mappings.entrySet()){
            outValues.add(entry.getValue().toString());
        }
    }

    private ArrayList<String> getKeys(HashMap<String, Object> mappings,String value){
        ArrayList<String> keys= new ArrayList<>();
        for (String key: mappings.keySet()){
            if (mappings.get(key).toString().equals(value)){
                 keys.add(key);
            }
        }
        return keys;
    }

    private void toProductClass(Object obj,Integer quantity){
          Gson gson = new Gson();
          PurchasedItems purchasedItems = gson.fromJson(String.valueOf(obj), PurchasedItems.class);
          purchasedItems.setQuantity(quantity);
          purchasedItemsList.add(purchasedItems);
    }

}
