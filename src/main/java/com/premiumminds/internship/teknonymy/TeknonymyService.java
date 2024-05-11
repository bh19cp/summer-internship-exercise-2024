package com.premiumminds.internship.teknonymy;

import java.util.*;

class TeknonymyService implements ITeknonymyService {

  /**
   * Method to get a Person Teknonymy Name
   *
   * @param person person
   * @return String which is the Teknonymy Name
   */
  public String getTeknonymy(Person person) {
    if (person == null || person.children() == null || person.children().length == 0) {
      return "";
    }
    GenerationPerson distantOldest =  getAllPersonsWithNoChildren(person);
    return buildTeknonymy(person, distantOldest);
  }
  /**
   * Performs a depth first search
   * in the family tree of a given person
   *
   * @param root the person
   * @return the most distant and oldest of that tree
   */
  public GenerationPerson getAllPersonsWithNoChildren(Person root) {
    Stack<GenerationPerson> stack = new Stack<>();
    stack.push(new GenerationPerson(root,0));
    GenerationPerson distantOldest = null;

    /**
     * Using depth first search since
     * we want the distant relative
     */
    while(!stack.isEmpty()){
      GenerationPerson current = stack.pop();
      if(current.person().children()!=null) {
        for (Person child : current.person().children()) {
          stack.push(new GenerationPerson(child, current.generation() + 1));
        }
      }
      else if(isMoreDistantAndOlder(current, distantOldest)){
        distantOldest = current;
      }
    }
    return distantOldest;
  }

  /**
   * Checks if current person has a higher or equal level in the tree
   * and its older than the distantOldest
   * @param current the person to compare
   * @param distantOldest the current distantOldest
   * @return True if current is more distant and older, otherwise False
   */
  private boolean isMoreDistantAndOlder(GenerationPerson current, GenerationPerson distantOldest) {
    return (distantOldest == null
            || (distantOldest.generation() < current.generation()
            || (distantOldest.generation().equals(current.generation())
            && current.person().dateOfBirth().isBefore(distantOldest.person().dateOfBirth()))));
  }

  /**
   * Builds the teknonymy for a person given its descendant
   * and the level in the tree of the descendant
   * @param person the person who the teknonymy belongs to
   * @param distantOldest the most distant and oldest person in the family tree
   * @return the tekononymy
   */
  private String buildTeknonymy(Person person, GenerationPerson distantOldest) {
    char sex = person.sex();
    String result = "";
    Integer generation = distantOldest.generation();

    switch (generation) {
      case 1:
        if (sex == 'M') {
          result = "father of " + distantOldest.person().name();
        } else if (sex == 'F') {
          result = "mother of " + distantOldest.person().name();
        }
        break;
      case 2:
        if (sex == 'M') {
          result = "grandfather of " + distantOldest.person().name();
        } else if (sex == 'F') {
          result = "grandmother of " + distantOldest.person().name();
        }
        break;
      case 3:
        if (sex == 'M') {
          result = "great-grandfather of " + distantOldest.person().name();
        } else if (sex == 'F') {
          result = "great-grandmother of " + distantOldest.person().name();
        }
        break;
      default:
        if (sex == 'M') {
          result = generation +"th grandfather of " + distantOldest.person().name();
        } else if (sex == 'F') {
          result = generation + "th grandmother of " + distantOldest.person().name();
        }
        break;
    }

    return result;
  }

}
