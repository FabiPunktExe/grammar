@file:JvmName("Parser")

package de.fabiexe.grammar

import kotlin.jvm.JvmName

fun <T> parse(input: String, grammar: Grammar<T>): T {
    when (val result = grammar.parse(input)) {
        is ParseResult.Token -> return result.token
        is ParseResult.Failure -> throw IllegalArgumentException("Failed to parse input: ${result.error} at position")
    }
}