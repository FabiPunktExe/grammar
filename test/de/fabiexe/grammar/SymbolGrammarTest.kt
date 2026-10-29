package de.fabiexe.grammar

import kotlin.test.Test

class SymbolGrammarTest {
    @Test
    fun test() {
        val grammar = Grammar.symbol(setOf('a', 'b', 'c'))
        assertToken('a', 1, grammar.parse("abc"))
        assertToken('b', 1, grammar.parse("bcd"))
        assertToken('c', 1, grammar.parse("cde"))
        assertFailure("Expected a symbol", 0, grammar.parse("def"))
        assertFailure("Expected a symbol", 0, grammar.parse(""))
    }
}