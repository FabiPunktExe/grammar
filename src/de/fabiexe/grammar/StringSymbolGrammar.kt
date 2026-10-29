package de.fabiexe.grammar

class StringSymbolGrammar(val string: String) : Grammar<Unit> {
    override fun parse(input: String): ParseResult<Unit> {
        var position = 0
        while (position < string.length) {
            if (position < input.length && input[position] == string[position]) {
                position++
            } else {
                return ParseResult.Failure("Expected '$string'", 0)
            }
        }
        return ParseResult.Token(Unit, position)
    }
}