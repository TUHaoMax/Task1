import com.google.gson.Gson
import com.google.gson.JsonArray

class InputProcessor {
    var gson = Gson()
    var mappings = mutableMapOf<String,String>()
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

        for (key in mappings.keys ){
            var temp = gson.fromJson(gson.toJson(mappings[key]),PurchasedItems::class.java)
            temp.quantity=productsList.count { it.equals(key) }
            outputList.add(temp)
        }

        outputList.sortBy { PurchasedItems -> PurchasedItems.version }
        createOutputJson()
    }

    fun createOutputJson(){
        for (temp in outputList){
            outputJson.add(gson.toJson(temp))
            jsArray.add(gson.toJson(temp))
        }
    }
}