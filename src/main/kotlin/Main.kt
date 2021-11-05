import java.io.File

fun main(args: Array<String>) {
    val className = ClassName("FBar")
    val pattern = "FBa* "

    println(pattern.indexOf("", 4))



//    val file = File(args[0])
//    var pattern: String = args[1]
//
//    if (pattern.all { it.isLowerCase() }) { // TODO 'fbb' finds FooBarBaz but 'fBb' will not
//        pattern = pattern.uppercase()
//    }
//
//    val answer = mutableListOf<ClassName>() // TODO classNames
//
//    file.useLines { lines ->
//        lines.forEach {
//            val className = ClassName(it)
//            if (className.matches(pattern)) { // хз как сделать фильтр
//                answer.add(className)
//            }
//        }
//    }
//
//
//
//    answer.sortedBy { it.simpleName }
//    answer.forEach { println(it.fullName) }
}



