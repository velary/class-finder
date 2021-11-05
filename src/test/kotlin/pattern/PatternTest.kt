package pattern

import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class PatternTest {

    @Test
    fun `pattern without wildcards and space`() {
        val pattern = Pattern("FooBar")
        val expectedParts = listOf<PatternPart>(Letters("Foo"), Letters("Bar"))

        assertContentEquals(expectedParts, pattern.patternParts)
    }

    @Test
    fun `pattern with wildcard in the middle of the word`() {
        val pattern = Pattern("FooBar*az")
        val expectedParts = listOf<PatternPart>(Letters("Foo"), Letters("Bar"), Wildcard, Letters("az"))

        assertContentEquals(expectedParts, pattern.patternParts)
    }

    @Test
    fun `pattern with space`() {
        val pattern = Pattern("FBar ")
        val expectedParts = listOf<PatternPart>(Letters("F"), End("Bar"))

        assertEquals(expectedParts, pattern.patternParts)
    }

    @Test
    fun `pattern with wildcard and space at the end`() {
        val pattern = Pattern("FBa* ")
        val expectedParts = listOf<PatternPart>(Letters("F"), Letters("Ba"), Wildcard, End(""))

        assertEquals(expectedParts, pattern.patternParts)
    }
}