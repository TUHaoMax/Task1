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

        outputProcessor.creatOutput(productsProcessor.getProductsMap(),mapProcessor.getMappingsMap());

        System.out.println(outputProcessor.getOutPut());

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("MM-HH:mm");
        String outTime = formatObj.format(dateTime);
        File file = new File(path+"output"+outTime+".out");
        FileWriter Writer =new FileWriter(file);
        Writer.write(outputProcessor.getOutPut().toString());
        Writer.close();
    }

}
