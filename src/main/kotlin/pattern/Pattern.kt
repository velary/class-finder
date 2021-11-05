package pattern

class Pattern(pattern: String) {
    val patternParts: List<PatternPart>

    init {
        val preparedPattern = if (pattern.all { !it.isLetter() || it.isLowerCase() }) {
            pattern.uppercase()
        } else {
            pattern
        }
        patternParts = splitPattern(preparedPattern)
    }

    override fun toString(): String {
        return patternParts.joinToString(separator = "") {
            when (it) {
                is End -> it.string + " "
                is Letters -> it.string
                Wildcard -> "*"
            }
        }
    }

    private fun splitPattern(pattern: String): List<PatternPart> {
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

}