import kotlin.test.Test
import kotlin.test.assertEquals
import com.github.stefanbirkner.systemlambda.SystemLambda.*

internal class InterpreterTests {

    @Test
    fun testHelloWorldToConsole(){
        val program = Parser().parseProgram(BrainfuckExamples.helloWorld)
        val memory = Array<Byte>(30000, init = {0x00})
        val output = tapSystemOut { Interpreter(program, memory).run() }
        assertEquals( "Hello World!", output.trim())
    }



}