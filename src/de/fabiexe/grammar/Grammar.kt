package de.fabiexe.grammar

import kotlin.jvm.JvmStatic

interface Grammar<T> {
    fun parse(input: String): ParseResult<T>

    fun <U> transform(transform: (T) -> U): Grammar<U> = TransformingGrammar(this, transform)

    companion object {
        @JvmStatic
        fun empty(): Grammar<Unit> = EmptyGrammar

        @JvmStatic
        fun symbol(symbols: Collection<Char>): Grammar<Char> = CharSymbolGrammar(symbols)

        @JvmStatic
        fun symbol(vararg symbols: Char): Grammar<Char> = CharSymbolGrammar(symbols.toSet())

        @JvmStatic
        fun symbol(string: String): Grammar<String> = StringSymbolGrammar(string)

        @JvmStatic
        fun <T> sequence(grammars: List<Grammar<T>>): Grammar<List<T>> = SequenceGrammar(grammars)

        @JvmStatic
        fun <T> sequence(vararg grammars: Grammar<out T>): Grammar<List<T>> = SequenceGrammar(grammars.toList())

        @JvmStatic
        fun <T> choice(grammars: List<Grammar<T>>): Grammar<T> = ChoiceGrammar(grammars)

        @JvmStatic
        fun <T> choice(vararg grammars: Grammar<out T>): Grammar<T> = ChoiceGrammar(grammars.toList())
    }
}