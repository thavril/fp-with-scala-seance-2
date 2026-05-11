package seance2

import munit.FunSuite
import Exercices.*

class ExercicesSpec extends FunSuite:
  
  // ============================================
  // Tests Part 1: Introduction to Case Classes
  // ============================================
  
  test("1.1 - createPerson: creates a Person"):
    val p = createPerson("Alice", 25)
    assertEquals(p.name, "Alice")
    assertEquals(p.age, 25)
  
  test("1.2 - getName: returns the name"):
    assertEquals(getName(Person("Bob", 30)), "Bob")
    assertEquals(getName(Person("Charlie", 45)), "Charlie")
  
  test("1.3 - getAge: returns the age"):
    assertEquals(getAge(Person("Alice", 25)), 25)
    assertEquals(getAge(Person("Bob", 17)), 17)
  
  test("1.4 - isAdult: checks if adult"):
    assert(isAdult(Person("Alice", 18)))
    assert(isAdult(Person("Bob", 25)))
    assert(!isAdult(Person("Charlie", 17)))
    assert(!isAdult(Person("David", 0)))
  
  test("1.5 - birthday: increments age"):
    assertEquals(birthday(Person("Alice", 25)), Person("Alice", 26))
    assertEquals(birthday(Person("Bob", 0)), Person("Bob", 1))
  
  test("1.6 - describe: formats the description"):
    assertEquals(describe(Person("Alice", 25)), "Alice is 25 years old")
    assertEquals(describe(Person("Bob", 30)), "Bob is 30 years old")
  
  
  // ============================================
  // Tests Part 2: Pattern Matching on Case Classes
  // ============================================
  
  test("2.1 - sumCoordinates: adds x and y"):
    assertEquals(sumCoordinates(Point(3, 4)), 7)
    assertEquals(sumCoordinates(Point(-1, 5)), 4)
    assertEquals(sumCoordinates(Point(0, 0)), 0)
  
  test("2.2 - distanceFromOrigin: calculates the distance"):
    assertEqualsDouble(distanceFromOrigin(Point(3, 4)), 5.0, 0.01)
    assertEqualsDouble(distanceFromOrigin(Point(0, 0)), 0.0, 0.01)
    assertEqualsDouble(distanceFromOrigin(Point(1, 1)), math.sqrt(2), 0.01)
  
  test("2.3 - quadrant: identifies the quadrant"):
    assertEquals(quadrant(Point(1, 1)), "I")
    assertEquals(quadrant(Point(-1, 1)), "II")
    assertEquals(quadrant(Point(-1, -1)), "III")
    assertEquals(quadrant(Point(1, -1)), "IV")
    assertEquals(quadrant(Point(0, 0)), "Origin")
    assertEquals(quadrant(Point(0, 5)), "Axis")
    assertEquals(quadrant(Point(5, 0)), "Axis")
  
  test("2.4 - area: calculates rectangle area"):
    assertEquals(area(Rectangle(Point(0, 0), Point(4, 3))), 12)
    assertEquals(area(Rectangle(Point(1, 1), Point(5, 4))), 12)
    assertEquals(area(Rectangle(Point(0, 0), Point(0, 0))), 0)
  
  test("2.5 - contains: checks if point is in rectangle"):
    val rect = Rectangle(Point(0, 0), Point(10, 10))
    assert(contains(rect, Point(5, 5)))
    assert(contains(rect, Point(0, 0)))
    assert(contains(rect, Point(10, 10)))
    assert(!contains(rect, Point(11, 5)))
    assert(!contains(rect, Point(-1, 5)))
  
  
  // ============================================
  // Tests Part 3: Sealed Traits and Enums
  // ============================================
  
  test("3.1 - nextLight: traffic light cycle"):
    assertEquals(nextLight(TrafficLight.Red), TrafficLight.Green)
    assertEquals(nextLight(TrafficLight.Green), TrafficLight.Yellow)
    assertEquals(nextLight(TrafficLight.Yellow), TrafficLight.Red)
  
  test("3.2 - lightAction: action based on light"):
    assertEquals(lightAction(TrafficLight.Red), "Stop")
    assertEquals(lightAction(TrafficLight.Yellow), "Slow down")
    assertEquals(lightAction(TrafficLight.Green), "Go")
  
  test("3.3 - shapeArea: calculates shape area"):
    assertEqualsDouble(shapeArea(Circle(1.0)), math.Pi, 0.01)
    assertEqualsDouble(shapeArea(Circle(2.0)), 4 * math.Pi, 0.01)
    assertEqualsDouble(shapeArea(Square(3.0)), 9.0, 0.01)
    assertEqualsDouble(shapeArea(RectangleShape(4.0, 2.0)), 8.0, 0.01)
  
  test("3.4 - shapePerimeter: calculates perimeter"):
    assertEqualsDouble(shapePerimeter(Circle(1.0)), 2 * math.Pi, 0.01)
    assertEqualsDouble(shapePerimeter(Square(3.0)), 12.0, 0.01)
    assertEqualsDouble(shapePerimeter(RectangleShape(4.0, 2.0)), 12.0, 0.01)
  
  test("3.5 - shapeDescription: describes the shape"):
    assertEquals(shapeDescription(Circle(5.0)), "Circle with radius 5.0")
    assertEquals(shapeDescription(Square(3.0)), "Square with side 3.0")
    assertEquals(shapeDescription(RectangleShape(4.0, 2.0)), "Rectangle of 4.0 x 2.0")
  
  
  // ============================================
  // Tests Part 4: Enum with Data
  // ============================================
  
  import Expression.*
  
  test("4.1 - evaluate: evaluates an expression"):
    assertEquals(evaluate(Number(5)), 5)
    assertEquals(evaluate(Addition(Number(3), Number(4))), 7)
    assertEquals(evaluate(Subtraction(Number(10), Number(3))), 7)
    assertEquals(evaluate(Multiplication(Number(2), Number(5))), 10)
    assertEquals(evaluate(Multiplication(Number(2), Addition(Number(3), Number(1)))), 8)
  
  test("4.2 - exprToString: converts to string"):
    assertEquals(exprToString(Number(5)), "5")
    assertEquals(exprToString(Addition(Number(3), Number(4))), "(3 + 4)")
    assertEquals(exprToString(Multiplication(Number(2), Number(3))), "(2 * 3)")
    assertEquals(
      exprToString(Multiplication(Number(2), Addition(Number(3), Number(1)))),
      "(2 * (3 + 1))"
    )
  
  
  // ============================================
  // Tests Part 5: Mini-Project
  // ============================================
  
  val alice = Student("Alice", List(
    Grade("Math", 85),
    Grade("Physics", 90),
    Grade("Chemistry", 80)
  ))
  
  val bob = Student("Bob", List(
    Grade("Math", 70),
    Grade("Physics", 65)
  ))
  
  val charlie = Student("Charlie", List())
  
  test("5.1 - createStudent: creates a student"):
    val s = createStudent("Test", List(Grade("Math", 80)))
    assertEquals(s.name, "Test")
    assertEquals(s.grades.size, 1)
  
  test("5.2 - isValidGrade: validates a grade"):
    assert(isValidGrade(Grade("Math", 85)))
    assert(isValidGrade(Grade("Math", 0)))
    assert(isValidGrade(Grade("Math", 100)))
    assert(!isValidGrade(Grade("Math", -1)))
    assert(!isValidGrade(Grade("Math", 101)))
  
  test("5.3 - studentAverage: calculates the average"):
    assertEqualsDouble(studentAverage(alice), 85.0, 0.01)
    assertEqualsDouble(studentAverage(bob), 67.5, 0.01)
    assertEqualsDouble(studentAverage(charlie), 0.0, 0.01)
  
  test("5.4 - mentionForAverage: determines the mention"):
    assertEquals(mentionForAverage(85.0), Mention.VeryGood)
    assertEquals(mentionForAverage(75.0), Mention.Good)
    assertEquals(mentionForAverage(65.0), Mention.Satisfactory)
    assertEquals(mentionForAverage(55.0), Mention.Passing)
    assertEquals(mentionForAverage(45.0), Mention.Insufficient)
  
  test("5.5 - studentMention: student's mention"):
    assertEquals(studentMention(alice), Mention.VeryGood)
    assertEquals(studentMention(bob), Mention.Satisfactory)
  
  test("5.6 - bestGrade: finds the best grade"):
    assertEquals(bestGrade(alice), Some(Grade("Physics", 90)))
    assertEquals(bestGrade(charlie), None)
  
  test("5.7 - gradesAboveAverage: filters grades"):
    val aliceGrades = gradesAboveAverage(alice)
    assertEquals(aliceGrades.size, 2)
    assert(aliceGrades.contains(Grade("Math", 85)))
    assert(aliceGrades.contains(Grade("Physics", 90)))
  
  test("5.8 - studentReport: generates a report"):
    val report = studentReport(alice)
    assert(report.contains("Alice"))
    assert(report.contains("85.00"))
    assert(report.contains("VeryGood"))
  
  
  // ============================================
  // Tests BONUS
  // ============================================
  
  test("BONUS 1 - betterStudent: compares two students"):
    assertEquals(betterStudent(alice, bob), "Alice")
    assertEquals(betterStudent(bob, alice), "Alice")
  
  test("BONUS 2 - sortedGrades: sorts by score descending"):
    val sorted = sortedGrades(alice)
    assertEquals(sorted.head.score, 90)
    assertEquals(sorted.last.score, 80)
  
  test("BONUS 3 - bySubject: creates a map"):
    val map = bySubject(alice)
    assertEquals(map("Math"), 85)
    assertEquals(map("Physics"), 90)
    assertEquals(map("Chemistry"), 80)
