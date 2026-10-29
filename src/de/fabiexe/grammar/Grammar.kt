package de.fabiexe.grammar

import kotlin.jvm.JvmStatic

interface Grammar<T> {
    fun parse(input: String): ParseResult<T>

    companion object {
        @JvmStatic
        fun symbol(symbols: Set<Char>): Grammar<Char> = CharSymbolGrammar(symbols)

        @JvmStatic
        fun symbol(string: String): Grammar<Unit> = StringSymbolGrammar(string)
    }
}