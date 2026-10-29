package de.fabiexe.grammar

import java.util.function.Function

class JvmRecursiveGrammar<T>(val definition: Function<Grammar<T>, Grammar<T>>) : Grammar<T> {
    private val delegate: Grammar<T> by lazy { definition.apply(this) }

    override fun parse(input: String): ParseResult<T> = delegate.parse(input)
}