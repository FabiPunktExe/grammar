package de.fabiexe.grammar

object EmptyGrammar : Grammar<Unit> {
    override fun parse(input: String): ParseResult<Unit> {
        return ParseResult.Token(Unit, 0)
    }
}