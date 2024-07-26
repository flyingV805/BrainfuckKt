
fun main(args: Array<String>) {

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val program = Parser().parseProgram(Test.helloWorld)
    val memory = Array<Byte>(30000, init = {0x00})

    var instructionIndex = 0
    var memoryPointer = 0
    var loopStartIndex: Int? = null
    var loopEndIndex: Int? = null

    //event loop ? (lol)
    while (instructionIndex < program.size) {
        when(program[instructionIndex]){
            Forward -> memoryPointer++
            Backward -> memoryPointer--
            Increment -> memory[memoryPointer]++
            Decrement -> memory[memoryPointer]--
            Output -> print(memory[memoryPointer].toInt().toChar())
            Input -> {}
            StartLoop -> {
                loopStartIndex = instructionIndex
                if( memory[memoryPointer] == 0x00.toByte() ){

                    if(loopEndIndex != null){
                        instructionIndex = loopEndIndex
                        continue
                    }

                    for (le in instructionIndex until program.size){
                        if( program[le] == EndLoop) {
                            instructionIndex = le
                            continue
                        }
                    }
                }
            }
            EndLoop -> {
                loopEndIndex = instructionIndex
                if( memory[memoryPointer] != 0x00.toByte() ){
                    instructionIndex = loopStartIndex!!
                    continue
                }
            }
        }

        instructionIndex++
    }

}
