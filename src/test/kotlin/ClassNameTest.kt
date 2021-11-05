import org.junit.Test
import kotlin.test.assertEquals

class ClassNameTest {

    @Test
    fun `package name is correctly separated`() {
        val className = ClassName("a.b.FooBar")

        assertEquals("FooBar", className.simpleName)
    }

    @Test
    fun `className without package is correctly initialized`() {
        val className = ClassName("FooBar")

        assertEquals("FooBar", className.simpleName)
    }
}