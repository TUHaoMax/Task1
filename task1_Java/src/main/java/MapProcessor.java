import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Data

public class MapProcessor {
        private HashMap<String, Object> mappingsMap = new HashMap<>();

        @SneakyThrows
        public void readMappings (String path){
            String input = Files.readString(Path.of(path));
            parseInput(input);
        }
        @SneakyThrows
        public void parseInput(String input){
            Gson gson = new Gson();
            mappingsMap = gson.fromJson(input,HashMap.class);
        }

}
