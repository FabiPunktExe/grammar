package de.fabiexe.grammar

class SequenceGrammar<T>(val grammars: List<Grammar<out T>>) : Grammar<List<T>> {
    init {
        require(grammars.isNotEmpty()) { "SequenceGrammar requires at least one grammar" }
    }

    override fun parse(input: String): ParseResult<List<T>> {
        var position = 0
        val tokens = List(grammars.size) { index ->
            when (val result = grammars[index].parse(input.substring(position))) {
                is ParseResult.Token -> {
                    position += result.position
                    result.token
                }
                is ParseResult.Failure -> return ParseResult.Failure(result.error, position + result.position)
            }
        }
        return ParseResult.Token(tokens, position)
    }
}