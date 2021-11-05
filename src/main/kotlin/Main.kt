import pattern.Pattern
import pattern.PatternMatcher
import java.io.File

fun main(args: Array<String>) {
    val file = File(args[0])
    val pattern = Pattern(args[1])

    val classNames = mutableListOf<ClassName>()

    file.useLines { lines ->
        lines.forEach {
            val className = ClassName(it)
            if (PatternMatcher.matches(pattern, className.simpleName)) {
                classNames.add(className)
            }
        }
    }

    classNames.sortedBy { it.simpleName }
    classNames.forEach { println(it.fullName) }
}



