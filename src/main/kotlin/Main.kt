
fun main(args: Array<String>) {

    val program = Parser().parseProgram(Test.echo)
    val memory = Array<Byte>(30000, init = {0x00})

    Interpreter(program, memory).run()

}
