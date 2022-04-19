import com.google.gson.Gson
import com.google.gson.JsonArray
import java.util.Objects

class InputProcessor {
    var gson = Gson()
    var mappings = mutableMapOf<String,String>()
    var mapValues = mutableSetOf<String>()
    var productsList = mutableListOf<String>()
    var outputList = mutableListOf<PurchasedItems>()
    var outputJson = mutableListOf<String>()
    var jsArray = JsonArray()

    data class PurchasedItems(var version: Int?, var edition: String?, var quantity: Int?)

    fun readInput(productsDatas:String,mappingsDatas: String){
         productsList = ((productsDatas.replace("[\"" ,"")
                                       .replace("\"]","")
                                       .split("\", \"").toMutableList())  )

        createOutput(mappingsDatas)
    }

    fun createOutput(datas: String){
        mappings = gson.fromJson(datas,mappings::class.java)
        createMapValues()
        for (outValue in mapValues){
            var temp = gson.fromJson(outValue,PurchasedItems::class.java)
            var keys = getKeys(outValue)
            var count=0
            for (key in keys){
                count=count+productsList.count { it.equals(key) }
            }
            temp.quantity=count
            outputList.add(temp)
        }

        outputList.sortBy { PurchasedItems -> PurchasedItems.version }
        createOutputJson()
    }

    fun getKeys(outValue: String): MutableList<String> {
        var keys = mutableListOf<String>()
        for (key in mappings.keys){
            if (mappings[key].toString().equals(outValue)){
                keys.add(key)
            }
        }
        return keys
    }

    fun createMapValues(){
        for(key in mappings.keys){
            mapValues.add(mappings[key].toString())
        }
    }

    fun createOutputJson(){
        for (temp in outputList){
            outputJson.add(gson.toJson(temp))
            jsArray.add(gson.toJson(temp))
        }
    }
}