import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@Data

public class MapProcessor {
        private HashMap<String, Object> mappingsMap = new HashMap<>();
        @SneakyThrows
        public void readMappings (String path){
            String file = Files.readString(Path.of(path));
            parseInput(file);
        }
        @SneakyThrows
        private void parseInput(String datas){
            Gson gson = new Gson();
            mappingsMap = gson.fromJson(datas,HashMap.class);
        }

}
