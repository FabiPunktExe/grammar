package de.fabiexe.grammar

import java.util.function.Function

class JvmResultTransformingGrammar<T, U>(val grammar: Grammar<T>, val transform: Function<T, ParseResult<U>>) : Grammar<U> {
    override fun parse(input: String): ParseResult<U> {
        return when (val result = grammar.parse(input)) {
            is ParseResult.Token -> transform.apply(result.token)
            is ParseResult.Failure -> result
        }
    }
}