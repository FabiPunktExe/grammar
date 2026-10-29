package de.fabiexe.grammar

import kotlin.test.Test

class EmptyGrammarTest {
    @Test
    fun testEmptyGrammar() {
        assertToken(Unit, 0, Grammar.empty().parse(""))
        assertToken(Unit, 0, Grammar.empty().parse("abc"))
    }
}