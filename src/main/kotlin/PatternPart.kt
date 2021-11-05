sealed class PatternPart

class Letters(val string: String): PatternPart()
class End(val string: String): PatternPart()
object Wildcard : PatternPart()

