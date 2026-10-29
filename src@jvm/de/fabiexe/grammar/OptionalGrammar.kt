package de.fabiexe.grammar

import java.util.*

class OptionalGrammar<T : Any>(val grammar: Grammar<T>) : Grammar<Optional<T>> {
    override fun parse(input: String): ParseResult<Optional<T>> {
        return when (val result = grammar.parse(input)) {
            is ParseResult.Token -> ParseResult.Token(Optional.of<T>(result.token), result.position)
            is ParseResult.Failure -> ParseResult.Token(Optional.empty<T>(), 0)
        }
    }
}