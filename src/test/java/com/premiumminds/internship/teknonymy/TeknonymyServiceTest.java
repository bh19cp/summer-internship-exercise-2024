package com.premiumminds.internship.teknonymy;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {
  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonThreeGrandchilds() {
    Person catarina = new Person("Catarina",
            'F',
            null,
            LocalDateTime.of(1998, 12, 20, 0, 0));

    Person pai = new Person("Jorge",
            'M',new Person[]{catarina},
            LocalDateTime.of(1968,
                    1, 12, 0, 0));
    Person joao = new Person(
            "Joao",
            'M',
            null,
            LocalDateTime.of(1997, 12, 20, 0, 0));
    Person helio = new Person(
            "Helio",
            'M',
            null,
            LocalDateTime.of(1996, 12, 20, 0, 0));
    Person fernando = new Person(
            "Fernando",
            'M',
            new Person[]{joao,helio}, LocalDateTime.of(1960, 1, 12, 0, 0));
    Person gracinda = new Person(
            "Gracinda",
            'F',
            new Person[]{pai,fernando},
            LocalDateTime.of(1917, 1, 11, 0, 0));

    String result = new TeknonymyService().getTeknonymy(gracinda);
    String expected = "grandmother of Helio";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoGreatGrandsons() {
    Person rita = new Person(
            "Rita",
            'F',
            null,
            LocalDateTime.of(2009, 12, 20, 0, 0));
    Person helio = new Person(
            "Helio",
            'M',
            null,
            LocalDateTime.of(2010, 12, 20, 0, 0));

    Person catarina = new Person("Catarina",
            'F',
            new Person[]{helio,rita},
            LocalDateTime.of(1989, 12, 20, 0, 0));

    Person jorge = new Person(
            "Jorge",
            'M',
            new Person[]{catarina},
            LocalDateTime.of(1968,
                    1, 12, 0, 0));
    Person joao = new Person(
            "Joao",
            'M',
            null,
            LocalDateTime.of(1997, 12, 20, 0, 0));
    Person fernando = new Person(
            "Fernando",
            'M',
            new Person[]{joao}, LocalDateTime.of(1960, 1, 12, 0, 0));
    Person gracinda = new Person(
            "Gracinda",
            'F',
            new Person[]{jorge,fernando},
            LocalDateTime.of(1917, 1, 11, 0, 0));

    String result = new TeknonymyService().getTeknonymy(gracinda);
    String expected = "great-grandmother of Rita";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoGreatGreatGrandsons() {
    Person bruno = new Person(
            "Bruno",
            'M',
            null,
            LocalDateTime.of(1995, 12, 20, 0, 0));
    Person ana = new Person(
            "Ana",
            'F',
            null,
            LocalDateTime.of(1996, 12, 20, 0, 0));
    Person rita = new Person(
            "Rita",
            'F',
            new Person[]{ana},
            LocalDateTime.of(1985, 12, 20, 0, 0));
    Person helio = new Person(
            "Helio",
            'M',
            new Person[]{bruno},
            LocalDateTime.of(1984, 12, 20, 0, 0));

    Person catarina = new Person("Catarina",
            'F',
            new Person[]{helio,rita},
            LocalDateTime.of(1968, 12, 20, 0, 0));

    Person jorge = new Person(
            "Jorge",
            'M',
            new Person[]{catarina},
            LocalDateTime.of(1945,
                    1, 12, 0, 0));
    Person joao = new Person(
            "Joao",
            'M',
            null,
            LocalDateTime.of(1967, 12, 20, 0, 0));
    Person fernando = new Person(
            "Fernando",
            'M',
            new Person[]{joao}, LocalDateTime.of(1940, 1, 12, 0, 0));
    Person gracinda = new Person(
            "Artur",
            'M',
            new Person[]{jorge,fernando},
            LocalDateTime.of(1917, 1, 11, 0, 0));

    String result = new TeknonymyService().getTeknonymy(gracinda);
    String expected = "4th grandfather of Bruno";
    assertEquals(result, expected);
  }
}