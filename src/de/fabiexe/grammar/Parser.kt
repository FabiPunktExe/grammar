@file:JvmName("Parser")

package de.fabiexe.grammar

import kotlin.jvm.JvmName

fun <T> parse(input: String, grammar: Grammar<T>): T {
    when (val result = grammar.parse(input)) {
        is ParseResult.Token -> {
            if (result.position == input.length) {
                return result.token
            } else {
                throw ParseException("Input not fully consumed (input length: ${input.length})", result.position)
            }
        }

        is ParseResult.Failure -> throw ParseException("Failed to parse input: ${result.error}", result.position)
    }
}