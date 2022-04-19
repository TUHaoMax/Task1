import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    static String path =  "src/main/resources/";
    @SneakyThrows
    public static void main(String[] args) {

        ProductsProcessor productsProcessor = new ProductsProcessor();
        MapProcessor mapProcessor = new MapProcessor();
        OutputProcessor outputProcessor = new OutputProcessor();

        productsProcessor.readProducts(path+"List of products_input.in");
        mapProcessor.readMappings(path+"Mappings_input.in");
        /*
        * Or can give the input directly
        *
        productsProcessor.parseInput("input");
        mapProcessor.parseInput("input");
        *
        */

        outputProcessor.creatOutput(productsProcessor.getProductsMap(),mapProcessor.getMappingsMap());

        System.out.println(outputProcessor.getOutPutList().toString());
        //System.out.println(outputProcessor.getJsArray().toString());

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("MMdd_HH,mm");
        String outTime = formatObj.format(dateTime)+"_output.out";

        File file = new File(path+outTime);
        FileWriter Writer =new FileWriter(file);
        Writer.write(outputProcessor.getOutPutList().toString());
        Writer.close();
    }

}
