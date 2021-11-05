
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ClassNameTest {

    @Test
    fun `uppercase letters in the right order`() {
        assertMatches("FooBar", "FB")
    }

    @Test
    fun `uppercase letters in the wrong order`() {
        assertNotMatches("FooBar", "BF")
    }

    @Test
    fun `pattern ends with space`() {
        val pattern = "FBar "

        assertMatches(className = "FooBar", pattern)
        assertMatches(className = "FBarFooBar", pattern)
        assertNotMatches(className = "FooBarBaz", pattern)
        assertNotMatches(className = "FooBars", pattern)

    }

    @Test
    fun `pattern longer than className`() {
        assertNotMatches("FBar", "FBarBaz")
    }

    @Test
    fun `pattern with wildcard and space at the end`() {
        val pattern = "FBar* "
        assertNotMatches("FBar", pattern)
        assertMatches("FBarfoo", pattern)
    }

    @Test
    fun `multiple wildcard in pattern`() {
        val pattern = "F***"

        assertNotMatches("F", pattern)
        assertNotMatches("Fo", pattern)
        assertNotMatches("Foo", pattern)
        assertMatches("Food", pattern)
    }

    private fun assertMatches(className: String, pattern: String) {
        assertTrue(ClassName(className).matches(pattern), "'$className' should match '$pattern'")
    }

    private fun assertNotMatches(className: String, pattern: String) {
        assertFalse(ClassName(className).matches(pattern), "'$className' shouldn't match '$pattern'")
    }

}