package de.fabiexe.grammar

class RecursiveGrammar<T>(val definition: (self: Grammar<T>) -> Grammar<T>) : Grammar<T> {
    private val delegate: Grammar<T> by lazy { definition(this) }

    override fun parse(input: String): ParseResult<T> = delegate.parse(input)
}