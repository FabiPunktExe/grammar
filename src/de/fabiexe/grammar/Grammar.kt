package de.fabiexe.grammar

import kotlin.jvm.JvmStatic

interface Grammar<T> {
    fun parse(input: String): ParseResult<T>

    companion object {
        @JvmStatic
        fun symbol(symbols: Collection<Char>): Grammar<Char> = CharSymbolGrammar(symbols)

        @JvmStatic
        fun symbol(vararg symbols: Char): Grammar<Char> = CharSymbolGrammar(symbols.toSet())

        @JvmStatic
        fun symbol(string: String): Grammar<Unit> = StringSymbolGrammar(string)

        @JvmStatic
        fun <T> sequence(grammars: List<Grammar<T>>): Grammar<List<T>> = SequenceGrammar(grammars)

        @JvmStatic
        fun <T> sequence(vararg grammars: Grammar<out T>): Grammar<List<T>> = SequenceGrammar(grammars.toList())
    }
}