package de.fabiexe.grammar

class ParseException(message: String, val position: Int) : RuntimeException("$message at position $position")