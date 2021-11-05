package pattern

object PatternMatcher {
    fun matches(pattern: Pattern, simpleName: String): Boolean {
        var currentPosition = 0

        for (patternPart in pattern.patternParts) {
            if (currentPosition >= simpleName.length) return false

            when (patternPart) {
                is Wildcard -> currentPosition++
                is End -> {
                    val part = patternPart.string
                    if (part.isNotEmpty()) {
                        if (!simpleName.substring(currentPosition).endsWith(part)) {
                            return false
                        }
                    }
                }
                is Letters -> {
                    val part = patternPart.string
                    val firstOccurrenceIndex = simpleName.indexOf(part, currentPosition)
                    if (firstOccurrenceIndex == -1) {
                        return false
                    } else {
                        currentPosition = firstOccurrenceIndex + part.length
                    }
                }
            }
        }
        return true
    }
}