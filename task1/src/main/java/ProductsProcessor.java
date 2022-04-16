import lombok.Data;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Data
public class ProductsProcessor {
    private HashMap<String, Integer> productsMap = new HashMap<>();
    private ArrayList<String> products = new ArrayList<>();
    @SneakyThrows
    public void readProducts (String path){

        String file = Files.readString(Path.of(path));
        String[] datas = file.split("\"");
        parseInput(datas);

        Set<String> uniqueProducts = new HashSet<>(products);
        for ( String P: uniqueProducts ) {
            int count = Collections.frequency(products,P);
            productsMap.put(P,count);
        }
    }
   private void parseInput(String[] datas){
        for (int i=0; i< datas.length;i++){
            if (i%2!=0){
                products.add(datas[i]);
            }
        }
    }

}
