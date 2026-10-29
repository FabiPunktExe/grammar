package de.fabiexe.grammar

import kotlin.test.Test
import kotlin.test.assertEquals

class RecursiveGrammarTest {
    private val boolean = Grammar.choice(
        Grammar.symbol("true").transform { true },
        Grammar.symbol("false").transform { false }
    )

    private val expression: Grammar<Boolean> = Grammar.recursive { self ->
        val value = Grammar.choice(
            boolean,
            Grammar.sequence(Grammar.symbol("("), self, Grammar.symbol(")")).transform { tokens ->
                tokens[1] as Boolean
            }
        )
        val andExpression: Grammar<Boolean> = Grammar.recursive { andSelf ->
            Grammar.choice(
                Grammar.sequence(value, Grammar.symbol(" and "), andSelf).transform { tokens ->
                    tokens[0] as Boolean && tokens[2] as Boolean
                },
                value
            )
        }
        Grammar.choice(
            Grammar.sequence(andExpression, Grammar.symbol(" or "), self).transform { tokens ->
                tokens[0] as Boolean || tokens[2] as Boolean
            },
            andExpression
        )
    }

    @Test
    fun testSingleValue() {
        assertToken(true, 4, expression.parse("true"))
        assertToken(false, 5, expression.parse("false"))
    }

    @Test
    fun testAndExpression() {
        assertToken(false, 14, expression.parse("true and false"))
        assertToken(true, 13, expression.parse("true and true"))
    }

    @Test
    fun testNestedAndExpression() {
        assertToken(false, 23, expression.parse("true and false and true"))
        assertToken(false, 23, expression.parse("true and true and false"))
    }

    @Test
    fun testParenthesizedExpression() {
        assertToken(true, 6, expression.parse("(true)"))
        assertToken(false, 16, expression.parse("(true and false)"))
        assertToken(true, 15, expression.parse("(true and true)"))
        assertToken(true, 8, expression.parse("((true))"))
    }

    @Test
    fun testNestedParenthesizedExpressions() {
        assertToken(false, 36, expression.parse("(true and true) and (false and true)"))
        assertToken(false, 25, expression.parse("true and (false and true)"))
    }

    @Test
    fun testOrExpression() {
        assertToken(true, 13, expression.parse("true or false"))
        assertToken(false, 14, expression.parse("false or false"))
        assertToken(true, 13, expression.parse("false or true"))
    }

    @Test
    fun testAndBindsTighterThanOr() {
        assertToken(true, 22, expression.parse("false and true or true"))
        assertToken(true, 23, expression.parse("true or false and false"))
        assertToken(true, 22, expression.parse("true and false or true"))
        assertToken(true, 22, expression.parse("false or true and true"))
    }

    @Test
    fun testParenthesesOverridePrecedence() {
        assertToken(false, 25, expression.parse("(true or false) and false"))
        assertToken(true, 24, expression.parse("true or (false and true)"))
    }

    @Test
    fun testUnclosedParenthesis() {
        assertFailure("Expected ')'", 5, expression.parse("(true"))
    }

    @Test
    fun testFailure() {
        assertFailure("Expected 'true'", 0, expression.parse("yes"))
    }

    @Test
    fun testParseHelper() {
        assertEquals(true, parse("true and true", expression))
        assertEquals(false, parse("true and false and true", expression))
        assertEquals(true, parse("false and true or true", expression))
        assertEquals(false, parse("(true or false) and false", expression))
    }

    @Test
    fun testLeftRecursion() {
        assertToken(false, 36, expression.parse("((true and false) or true) and false"))
    }
}