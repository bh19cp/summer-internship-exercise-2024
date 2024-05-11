package com.premiumminds.internship.teknonymy;

/**
 * A person and its level of generation in a given family tree
 * @param person the person
 * @param generation the level of generation
 */
public record GenerationPerson(Person person, Integer generation) {
}
