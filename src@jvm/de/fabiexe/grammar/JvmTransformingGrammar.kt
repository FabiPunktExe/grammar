package de.fabiexe.grammar

import java.util.function.Function

class JvmTransformingGrammar<T, U>(val grammar: Grammar<T>, val transform: Function<T, U>) : Grammar<U> {
    override fun parse(input: String): ParseResult<U> {
        return when (val result = grammar.parse(input)) {
            is ParseResult.Token -> ParseResult.Token(transform.apply(result.token), result.position)
            is ParseResult.Failure -> ParseResult.Failure(result.error, result.position)
        }
    }
}
