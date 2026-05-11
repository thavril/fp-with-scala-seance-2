package seance2

/**
 * Session 2 - Case Classes and Advanced Pattern Matching
 * 
 * Objectives:
 * - Create and use case classes
 * - Understand the difference between tuples and case classes
 * - Master pattern matching on structures
 * - Use sealed traits and enums to model domains
 * 
 * Instructions:
 * 1. Complete the functions marked with ???
 * 2. Do NOT use var, only val
 * 3. Run tests with: sbt test
 */

object Exercices:
  
  // ============================================
  // Part 1: Introduction to Case Classes
  // ============================================
  // A case class is like a NAMED tuple with named fields.
  // It automatically generates: equals, hashCode, toString, copy, apply
  
  /**
   * Definition of a simple case class.
   * Compare with a tuple: (String, Int) vs Person(name, age)
   */
  case class Person(name: String, age: Int)
  
  /**
   * Exercise 1.1: Create a Person
   * Create an instance of Person with the given name and age.
   * 
   * Example: createPerson("Alice", 25) == Person("Alice", 25)
   */
  def createPerson(name: String, age: Int): Person =
    ???
  
  /**
   * Exercise 1.2: Access fields
   * Return the person's name.
   * With a case class, you access fields by their name: person.name
   * 
   * Example: getName(Person("Alice", 25)) == "Alice"
   */
  def getName(person: Person): String =
    ???
  
  /**
   * Exercise 1.3: Access age
   * Return the person's age.
   */
  def getAge(person: Person): Int =
    ???
  
  /**
   * Exercise 1.4: Check if adult
   * Returns true if the person is 18 years or older.
   */
  def isAdult(person: Person): Boolean =
    ???
  
  /**
   * Exercise 1.5: Use copy
   * Case classes have a copy() method to create a modified copy.
   * Create a new Person with the same name but age increased by 1.
   * 
   * Example: birthday(Person("Alice", 25)) == Person("Alice", 26)
   */
  def birthday(person: Person): Person =
    ???
  
  /**
   * Exercise 1.6: Formatted description
   * Returns "X is Y years old" where X is the name and Y the age.
   */
  def describe(person: Person): String =
    ???
  
  
  // ============================================
  // Part 2: Pattern Matching on Case Classes
  // ============================================
  // Pattern matching allows you to "destructure" a case class
  // to extract its components.
  
  /**
   * Case class for a 2D point
   */
  case class Point(x: Int, y: Int)
  
  /**
   * Exercise 2.1: Simple destructuring
   * Use pattern matching to extract x and y, then calculate x + y.
   * 
   * Syntax: point match { case Point(x, y) => ... }
   * 
   * Example: sumCoordinates(Point(3, 4)) == 7
   */
  def sumCoordinates(point: Point): Int =
    ???
  
  /**
   * Exercise 2.2: Distance from origin
   * Calculate the distance from the point to the origin (0, 0).
   * Distance = sqrt(x² + y²)
   * Use math.sqrt and pattern matching.
   */
  def distanceFromOrigin(point: Point): Double =
    ???
  
  /**
   * Exercise 2.3: Quadrant of a point
   * Determine in which quadrant the point is located:
   * - "I" if x > 0 and y > 0
   * - "II" if x < 0 and y > 0
   * - "III" if x < 0 and y < 0
   * - "IV" if x > 0 and y < 0
   * - "Origin" if x == 0 and y == 0
   * - "Axis" if on an axis (x == 0 or y == 0 but not both)
   */
  def quadrant(point: Point): String =
    ???
  
  /**
   * Case class for a rectangle defined by two points
   */
  case class Rectangle(bottomLeft: Point, topRight: Point)
  
  /**
   * Exercise 2.4: Area of a rectangle
   * Calculate the area of the rectangle.
   * Use nested pattern matching to extract coordinates.
   * 
   * Syntax: rect match { case Rectangle(Point(x1, y1), Point(x2, y2)) => ... }
   */
  def area(rect: Rectangle): Int =
    ???
  
  /**
   * Exercise 2.5: Point in rectangle
   * Check if a point is inside a rectangle (including borders).
   */
  def contains(rect: Rectangle, point: Point): Boolean =
    ???
  
  
  // ============================================
  // Part 3: Sealed Traits and Enums
  // ============================================
  // A sealed trait defines a CLOSED set of cases.
  // The compiler can verify exhaustiveness of pattern matching.
  
  /**
   * Enum to represent a traffic light color
   */
  enum TrafficLight:
    case Red, Yellow, Green
  
  /**
   * Exercise 3.1: Next light state
   * Red -> Green -> Yellow -> Red -> ...
   */
  def nextLight(light: TrafficLight): TrafficLight =
    ???
  
  /**
   * Exercise 3.2: Action based on light
   * Red -> "Stop"
   * Yellow -> "Slow down"
   * Green -> "Go"
   */
  def lightAction(light: TrafficLight): String =
    ???
  
  /**
   * Sealed trait to represent a geometric shape
   */
  sealed trait Shape
  case class Circle(radius: Double) extends Shape
  case class Square(side: Double) extends Shape
  case class RectangleShape(width: Double, height: Double) extends Shape
  
  /**
   * Exercise 3.3: Area of a shape
   * Calculate the area according to the type of shape.
   * - Circle: π * r²
   * - Square: s²
   * - Rectangle: w * h
   */
  def shapeArea(shape: Shape): Double =
    ???
  
  /**
   * Exercise 3.4: Perimeter of a shape
   * - Circle: 2 * π * r
   * - Square: 4 * s
   * - Rectangle: 2 * (w + h)
   */
  def shapePerimeter(shape: Shape): Double =
    ???
  
  /**
   * Exercise 3.5: Description of a shape
   * Returns a textual description.
   * - Circle(5.0) -> "Circle with radius 5.0"
   * - Square(3.0) -> "Square with side 3.0"
   * - RectangleShape(4.0, 2.0) -> "Rectangle of 4.0 x 2.0"
   */
  def shapeDescription(shape: Shape): String =
    ???
  
  
  // ============================================
  // Part 4: Enum with Data
  // ============================================
  // Scala 3 enums can contain data.
  
  /**
   * Enum to represent a simple mathematical expression
   */
  enum Expression:
    case Number(value: Int)
    case Addition(left: Expression, right: Expression)
    case Subtraction(left: Expression, right: Expression)
    case Multiplication(left: Expression, right: Expression)
  
  import Expression.*
  
  /**
   * Exercise 4.1: Evaluate an expression
   * Calculate the result of the expression recursively.
   * 
   * Example: evaluate(Addition(Number(3), Number(4))) == 7
   *          evaluate(Multiplication(Number(2), Addition(Number(3), Number(1)))) == 8
   */
  def evaluate(expr: Expression): Int =
    ???
  
  /**
   * Exercise 4.2: Expression to String
   * Convert an expression to infix notation with parentheses.
   * 
   * Example: toString(Addition(Number(3), Number(4))) == "(3 + 4)"
   *          toString(Multiplication(Number(2), Number(3))) == "(2 * 3)"
   */
  def exprToString(expr: Expression): String =
    ???
  
  
  // ============================================
  // Part 5: Mini-Project - Grading System
  // ============================================
  // Let's use case classes to model a grading system.
  
  /**
   * Data model for the grading system
   */
  case class Grade(subject: String, score: Int)
  case class Student(name: String, grades: List[Grade])
  
  enum Mention:
    case VeryGood, Good, Satisfactory, Passing, Insufficient
  
  /**
   * Exercise 5.1: Create a student
   * Create a student with their name and a list of grades.
   */
  def createStudent(name: String, grades: List[Grade]): Student =
    ???
  
  /**
   * Exercise 5.2: Valid grade
   * A grade is valid if the score is between 0 and 100 inclusive.
   */
  def isValidGrade(grade: Grade): Boolean =
    ???
  
  /**
   * Exercise 5.3: Student average
   * Calculate the average of the student's scores.
   * If the student has no grades, return 0.0.
   * 
   * Hint: use .map and .sum on lists
   */
  def studentAverage(student: Student): Double =
    ???
  
  /**
   * Exercise 5.4: Mention based on average
   * >= 80: VeryGood
   * >= 70: Good
   * >= 60: Satisfactory
   * >= 50: Passing
   * < 50: Insufficient
   */
  def mentionForAverage(average: Double): Mention =
    ???
  
  /**
   * Exercise 5.5: Student mention
   * Calculate the student's mention from their grades.
   * Use the previous functions (composition).
   */
  def studentMention(student: Student): Mention =
    ???
  
  /**
   * Exercise 5.6: Best grade
   * Find the grade with the highest score.
   * Returns None if the student has no grades.
   * 
   * Hint: use .maxByOption on lists
   */
  def bestGrade(student: Student): Option[Grade] =
    ???
  
  /**
   * Exercise 5.7: Grades above average
   * Return the list of grades whose score is >= the student's average.
   * 
   * Hint: use .filter on lists
   */
  def gradesAboveAverage(student: Student): List[Grade] =
    ???
  
  /**
   * Exercise 5.8: Student report
   * Generate a text report for the student:
   * "Student: X
   *  Average: Y
   *  Mention: Z"
   * 
   * Where Y is rounded to 2 decimal places.
   */
  def studentReport(student: Student): String =
    ???
  
  
  // ============================================
  // BONUS: Advanced Exercises
  // ============================================
  
  /**
   * BONUS 1: Compare two students
   * Return the name of the student with the higher average.
   * In case of a tie, return the first one.
   */
  def betterStudent(s1: Student, s2: Student): String =
    ???
  
  /**
   * BONUS 2: Sort grades by score descending
   * Return grades sorted from highest to lowest score.
   */
  def sortedGrades(student: Student): List[Grade] =
    ???
  
  /**
   * BONUS 3: Summary by subject
   * Return a Map where the key is the subject name
   * and the value is the score.
   */
  def bySubject(student: Student): Map[String, Int] =
    ???
