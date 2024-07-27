
fun main(args: Array<String>) {

    val program = Parser().parseProgram(BrainfuckExamples.helloWorld)
    val memory = Array<Byte>(30000, init = {0x00})

    Interpreter(program, memory).run()

}
