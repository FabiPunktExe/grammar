package de.fabiexe.grammar

class TransformingGrammar<T, U>(val grammar: Grammar<T>, val transform: (token: T) -> U) : Grammar<U> {
    override fun parse(input: String): ParseResult<U> {
        return when (val result = grammar.parse(input)) {
            is ParseResult.Token -> ParseResult.Token(transform(result.token), result.position)
            is ParseResult.Failure -> result
        }
    }
}
