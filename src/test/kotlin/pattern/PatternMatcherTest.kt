package pattern

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PatternMatcherTest {

    @Test
    fun `pattern with uppercase letters in the right order`() {
        val pattern = Pattern("FB")
        assertMatches(pattern, "FooBar")
    }

    @Test
    fun `pattern with uppercase letters in the wrong order`() {
        val pattern = Pattern("FooBar")
        assertNotMatches(pattern, "BF")
    }

    @Test
    fun `pattern ends with space`() {
        val pattern = Pattern("FBar ")

        assertMatches(pattern, "FooBar")
        assertMatches(pattern, "FBarFooBar")

        assertNotMatches(pattern, "FooBarBaz")
        assertNotMatches(pattern, "FooBars")
    }

    @Test
    fun `pattern longer than className`() {
        val pattern = Pattern("FBarBaz")
        assertNotMatches(pattern, "FBar")
    }

    @Test
    fun `pattern with wildcard and space at the end`() {
        val pattern = Pattern("FBar* ")

        assertNotMatches(pattern, "FBar")
        assertMatches(pattern, "FBarfoo")
    }

    @Test
    fun `multiple wildcard in pattern`() {
        val pattern = Pattern("F***")

        assertMatches(pattern, "Food")

        assertNotMatches(pattern, "F")
        assertNotMatches(pattern, "Fo")
        assertNotMatches(pattern, "Foo")
    }

    @Test
    fun `pattern with only wildcards`() {
        val pattern = Pattern("***")

        assertMatches(pattern, "Foofffffffffff")
        assertMatches(pattern, "Foo")

        assertNotMatches(pattern, "F")
        assertNotMatches(pattern, "Fo")
    }


    @Test
    fun `pattern with only lower case characters`() {
        val className = "FooBarBaz"
        assertMatches(Pattern("fbb"), className)

        assertNotMatches(Pattern("fBb"), className)
        assertNotMatches(Pattern("fobaba"), className)
    }

    @Test
    fun `pattern with only lower case characters and wildcards`() {
        val className = "FooBarBaz"

        assertMatches(Pattern("*b*b"), className)
        assertNotMatches(Pattern("*b*B"), className)
    }

    @Test
    fun `pattern with only lower case characters and space`() {
        val className = "FooBarB"

        assertMatches(Pattern("*b*b "), className)
        assertNotMatches(Pattern("*b*B "), className)
    }

    @Test
    fun `pattern with repeating parts`() {
        assertMatches(Pattern("BaBa"), "FooBarBaz")
    }

    @Test
    fun `empty pattern`() {
        val pattern = Pattern("")

        assertMatches(pattern, "fbb")
        assertMatches(pattern, "F**")
        assertMatches(pattern, "FooBarBaz")
    }

    private fun assertMatches(pattern: Pattern, className: String) {
        assertTrue(PatternMatcher.matches(pattern, className), "'$className' should match '$pattern'")
    }

    private fun assertNotMatches(pattern: Pattern, className: String) {
        assertFalse(PatternMatcher.matches(pattern, className), "'$className' should match '$pattern'")
    }

}