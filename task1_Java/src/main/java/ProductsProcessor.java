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

        String input = Files.readString(Path.of(path));
        parseInput(input);

        Set<String> uniqueProducts = new HashSet<>(products);
        for ( String P: uniqueProducts ) {
            int count = Collections.frequency(products,P);
            productsMap.put(P,count);
        }
    }
    public void parseInput(String input){
        String[] datas = input.split("\"");
        for (int i=0; i< datas.length;i++){
            if (i%2!=0){
                products.add(datas[i]);
            }
        }
    }

}
