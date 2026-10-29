package de.fabiexe.grammar

class CharSymbolGrammar(val symbols: Collection<Char>) : Grammar<Char> {
    init {
        require(symbols.isNotEmpty()) { "Symbol set cannot be empty" }
    }

    override fun parse(input: String): ParseResult<Char> {
        return if (input.isNotEmpty()) {
            val first = input[0]
            if (first in symbols) {
                ParseResult.Token(first, 1)
            } else {
                ParseResult.Failure("Expected a symbol", 0)
            }
        } else {
            ParseResult.Failure("Expected a symbol", 0)
        }
    }
}