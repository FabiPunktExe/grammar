package de.fabiexe.grammar

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParserTest {
    @Test
    fun testReturnsTokenForFullyConsumedInput() {
        assertEquals('a', parse("a", Grammar.symbol('a')))
        assertEquals("abc", parse("abc", Grammar.symbol("abc")))
        val abbcGrammar = Grammar.sequence(
            Grammar.symbol("a"),
            Grammar.symbol('b'),
            Grammar.symbol("bc")
        )
        assertEquals(listOf("a", 'b', "bc"), parse("abbc", abbcGrammar))
    }

    @Test
    fun testThrowsForTrailingInput() {
        assertFailsWith<IllegalArgumentException>("Input not fully consumed (input length: 3, consumed: 1)") {
            parse("abc", Grammar.symbol('a'))
        }
        assertFailsWith<IllegalArgumentException>("Input not fully consumed (input length: 3, consumed: 2)") {
            parse("abc", Grammar.symbol("ab"))
        }
    }

    @Test
    fun testThrowsOnFailure() {
        assertFailsWith<IllegalArgumentException>("Failed to parse input: Expected a symbol at position 0") {
            parse("x", Grammar.symbol('a'))
        }
    }

    @Test
    fun testEmptyInput() {
        assertEquals(Unit, parse("", Grammar.empty()))
        assertFailsWith<IllegalArgumentException> {
            parse("", Grammar.symbol('a'))
        }
    }
}
