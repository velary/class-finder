package pattern

sealed class PatternPart

data class Letters(val string: String): PatternPart()
data class End(val string: String): PatternPart()
object Wildcard : PatternPart()

