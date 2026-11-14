package de.fabiexe.grammar

import kotlin.jvm.JvmStatic

interface Grammar<T> {
    fun parse(input: String): ParseResult<T>

    fun <U> map(transform: (token: T) -> U): Grammar<U> = MappingGrammar(this, false, transform)

    @Deprecated("Use map instead", ReplaceWith("map(transform)"))
    fun <U> transform(transform: (token: T) -> U): Grammar<U> = MappingGrammar(this, false, transform)

    fun <U> mapCatching(transform: (token: T) -> U): Grammar<U> = MappingGrammar(this, true, transform)

    @Deprecated("Use mapCatching instead", ReplaceWith("mapCatching(transform)"))
    fun <U> transformCatching(transform: (token: T) -> U): Grammar<U> = MappingGrammar(this, true, transform)

    fun repeat(): Grammar<List<T>> = RepeatingGrammar(this, 0)

    fun repeat(min: Int): Grammar<List<T>> = RepeatingGrammar(this, min)

    companion object {
        @JvmStatic
        fun empty(): Grammar<Unit> = EmptyGrammar

        @JvmStatic
        fun symbol(symbols: Collection<Char>): Grammar<Char> = CharSymbolGrammar(symbols)

        @JvmStatic
        @JvmSafeVarargs
        fun symbol(vararg symbols: Char): Grammar<Char> = CharSymbolGrammar(symbols.toSet())

        @JvmStatic
        fun symbol(string: String): Grammar<String> = StringSymbolGrammar(string)

        @JvmStatic
        @JvmSafeVarargs
        fun anySymbol(excludedSymbols: Collection<Char>): Grammar<Char> = AnyCharSymbolGrammar(excludedSymbols)

        @JvmStatic
        @JvmSafeVarargs
        fun anySymbol(vararg excludedSymbols: Char): Grammar<Char> = AnyCharSymbolGrammar(excludedSymbols.toSet())

        @JvmStatic
        fun <T> sequence(grammars: List<Grammar<T>>): Grammar<List<T>> = SequenceGrammar(grammars)

        @JvmStatic
        @JvmSafeVarargs
        fun <T> sequence(vararg grammars: Grammar<out T>): Grammar<List<T>> = SequenceGrammar(grammars.toList())

        @JvmStatic
        fun <T> choice(grammars: List<Grammar<T>>): Grammar<T> = ChoiceGrammar(grammars)

        @JvmStatic
        @JvmSafeVarargs
        fun <T> choice(vararg grammars: Grammar<out T>): Grammar<T> = ChoiceGrammar(grammars.toList())

        @JvmStatic
        fun <T> recursive(definition: (self: Grammar<T>) -> Grammar<T>): Grammar<T> = RecursiveGrammar(definition)
    }
}