class Interpreter(
    private val program: List<Operator>,
    private val memory: Array<Byte>
){

    private var instructionIndex = 0
    private var memoryPointer = 0
    private var loopStartIndex: Int? = null
    private var loopEndIndex: Int? = null

    fun run(){

        while (instructionIndex < program.size) {
            when(program[instructionIndex]){
                Forward -> memoryPointer++
                Backward -> memoryPointer--
                Increment -> memory[memoryPointer]++
                Decrement -> memory[memoryPointer]--
                Output -> print(memory[memoryPointer].toInt().toChar())
                Input -> {
                    readlnOrNull()?.first()?.code?.toByte()?.let {
                        memory[memoryPointer] = it
                    }
                }
                StartLoop -> {
                    loopStartIndex = instructionIndex
                    if( memory[memoryPointer] == 0x00.toByte() ){

                        //have loop end index, no need to search
                        if(loopEndIndex != null){
                            instructionIndex = loopEndIndex!!
                            continue
                        }

                        //have loop end index, no need to search
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

}