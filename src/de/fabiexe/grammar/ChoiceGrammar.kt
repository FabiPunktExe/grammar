package de.fabiexe.grammar

class ChoiceGrammar<T>(val grammars: List<Grammar<out T>>) : Grammar<T> {
    init {
        require(grammars.isNotEmpty()) { "ChoiceGrammar requires at least one grammar" }
    }

    override fun parse(input: String): ParseResult<T> {
        var bestFailure: ParseResult.Failure? = null
        for (grammar in grammars) {
            when (val result = grammar.parse(input)) {
                is ParseResult.Token -> return ParseResult.Token(result.token, result.position)
                is ParseResult.Failure -> {
                    if (bestFailure == null || result.position > bestFailure.position) {
                        bestFailure = result
                    }
                }
            }
        }
        return bestFailure!!
    }
}