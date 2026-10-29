package de.fabiexe.grammar

class ResultTransformingGrammar<T, U>(val grammar: Grammar<T>, val transform: (token: T) -> ParseResult<U>) : Grammar<U> {
    override fun parse(input: String): ParseResult<U> {
        return when (val result = grammar.parse(input)) {
            is ParseResult.Token -> transform(result.token)
            is ParseResult.Failure -> result
        }
    }
}