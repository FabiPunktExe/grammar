package de.fabiexe.grammar

import kotlin.jvm.JvmOverloads

class TransformingGrammar<T, U> @JvmOverloads constructor(
    val grammar: Grammar<T>,
    val catch: Boolean = false,
    val transform: (token: T) -> U
) : Grammar<U> {
    override fun parse(input: String): ParseResult<U> {
        return try {
            return when (val result = grammar.parse(input)) {
                is ParseResult.Token -> ParseResult.Token(transform(result.token), result.position)
                is ParseResult.Failure -> result
            }
        } catch (e: Exception) {
            if (catch) {
                ParseResult.Failure(e.message ?: "Unknown error", 0)
            } else {
                throw e
            }
        }
    }
}
