import java.lang.RuntimeException

class Parser {

    fun parseProgram(program: String): List<Operator> {
        return program.mapIndexed{ index, char ->
            when(char){
                '>' -> Forward
                '<' -> Backward
                '+' -> Increment
                '-' -> Decrement
                '.' -> Output
                ',' -> Input
                '[' -> StartLoop
                ']' -> EndLoop
                else -> throw RuntimeException("Syntax error at $index")
            }
        }
    }

}