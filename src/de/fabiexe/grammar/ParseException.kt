package de.fabiexe.grammar

class ParseException(message: String, val position: Int) : Exception("$message at position $position")