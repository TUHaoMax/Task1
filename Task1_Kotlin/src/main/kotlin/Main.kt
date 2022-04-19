import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val path = "src/main/resources/"
    var inputProcessor = InputProcessor()

    val productsDatas = File(path+"List of products_input.in").readText()
    val mappingDatas  = File(path+"Mappings_input.in").readText()

    inputProcessor.readInput(productsDatas,mappingDatas)

    println(inputProcessor.outputJson.toString())
    //println(inputProcessor.jsArray.toString())

    val format = DateTimeFormatter.ofPattern("MMdd_HH,mm")
    var currentTime = LocalDateTime.now().format(format)
    val outputFile = File(path+currentTime+"_out.out")

    outputFile.bufferedWriter().use {
        out->
        out.write(inputProcessor.outputJson.toString())
    }
}