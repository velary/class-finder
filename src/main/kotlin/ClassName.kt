class ClassName(val fullName: String) {
    val simpleName: String

    init {
        val lastDotIndex = fullName.lastIndexOf('.')
        simpleName =
            if (lastDotIndex == -1) {
                fullName
            } else {
                fullName.substring(lastDotIndex + 1)
            }
    }

    fun matches(pattern: String): Boolean {
        return doMatch(preparePattern(pattern))
    }

    private fun preparePattern(pattern: String): String =
        if (pattern.all { it.isLowerCase() }) {
            pattern.uppercase()
        } else {
            pattern
        }

    private fun doMatch(pattern: String): Boolean {
        val patternParts = splitPattern(pattern)

        var simpleNameIndex = 0

        for (patternPart in patternParts) {
            if (simpleNameIndex >= simpleName.length) return false

            when (patternPart) {
                is Wildcard -> simpleNameIndex++
                is End -> {
                    val part = patternPart.string
                    if (part.isNotEmpty()) {
                        if (!simpleName.substring(simpleNameIndex).endsWith(part)) {
                            return false
                        }
                    }
                }
                is Letters -> {
                    val part = patternPart.string
                    val firstOccurrenceIndex = simpleName.indexOf(part, simpleNameIndex)
                    if (firstOccurrenceIndex == -1) {
                        return false
                    } else {
                        simpleNameIndex = firstOccurrenceIndex + part.length
                    }
                }
            }

        }

        return true
    }
}


fun splitPattern(pattern: String): List<PatternPart> {
    val patterParts = mutableListOf<PatternPart>()
    val currentPart: StringBuilder = StringBuilder()

    for (char in pattern) {
        if ((char == '*' || char.isUpperCase()) && currentPart.isNotEmpty()) {
            patterParts += Letters(currentPart.toString())
            currentPart.setLength(0)
        }

        when (char) {
            '*' -> patterParts += Wildcard
            ' ' -> {
                patterParts += End(currentPart.toString())
                currentPart.setLength(0)
            }
            else -> currentPart.append(char)
        }
    }

    if (currentPart.isNotEmpty()) {
        patterParts += Letters(currentPart.toString())
        currentPart.setLength(0)
    }
    return patterParts
}
