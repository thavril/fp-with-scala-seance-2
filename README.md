# Session 2 - Case Classes and Advanced Pattern Matching

**Duration**: 3h  
**Date**: April 28, 2025

## Learning Objectives

By the end of this session, you will be able to:

- Create and use **case classes**
- Understand the advantages of case classes vs tuples
- **Destructure** data with pattern matching
- Model domains with **sealed traits** and **enums**
- Compose functions to solve complex problems

## Recap: Session 1

- `val` vs `var` (immutability)
- Simple types: `Int`, `Double`, `String`, `Boolean`
- Recursion
- Pattern matching on simple values

## Getting Started

```bash
cd seance-2
sbt compile
sbt test
```

## Session Organization

### Part 1: Introduction to Case Classes (30 min)

**What is a case class?**

A case class is a special class that automatically generates:
- A constructor (no need for `new`)
- Accessors for each field
- `equals`, `hashCode`, `toString`
- A `copy` method to create modified copies

```scala
// Definition of a case class
case class Car(brand: String, year: Int)

// Creation (no need for new)
val myCar = Car("Peugeot", 2020)

// Access fields by name
myCar.brand    // "Peugeot"
myCar.year     // 2020

// Modified copy (the original doesn't change!)
val newCar = myCar.copy(year = 2024)
// myCar.year is still 2020

// Comparison by value (not by reference)
Car("Peugeot", 2020) == Car("Peugeot", 2020)  // true
```

**Case class vs Tuple: why prefer case classes?**

```scala
// Tuple: anonymous, access by position (_1, _2...)
val t: (String, Int) = ("Peugeot", 2020)
t._1  // "Peugeot" - but what does _1 represent?
t._2  // 2020 - and _2?

// Case class: named, self-documenting code
val v: Car = Car("Peugeot", 2020)
v.brand  // "Peugeot" - clear!
v.year   // 2020 - explicit!
```

**Exercises 1.1 - 1.6**: Manipulating `Person`

### Part 2: Pattern Matching on Case Classes (45 min)

**Destructuring: extracting components**

Pattern matching allows extracting fields from a case class:

```scala
case class Coordinates(latitude: Double, longitude: Double)

def hemisphere(c: Coordinates): String = c match
  case Coordinates(lat, _) if lat > 0 => "North"
  case Coordinates(lat, _) if lat < 0 => "South"
  case Coordinates(0, _) => "Equator"
```

**Specific values in patterns**

```scala
case class Product(name: String, price: Double)

def priceCategory(p: Product): String = p match
  case Product(_, 0) => "Free"
  case Product(_, price) if price < 10 => "Cheap"
  case Product(_, price) if price < 100 => "Medium"
  case _ => "Expensive"
```

**Nested pattern matching**

When a case class contains another case class:

```scala
case class Address(city: String, postalCode: String)
case class Company(name: String, headquarters: Address)

def isParisian(c: Company): Boolean = c match
  case Company(_, Address("Paris", _)) => true
  case Company(_, Address(_, pc)) if pc.startsWith("75") => true
  case _ => false
```

**Exercises 2.1 - 2.5**: Manipulating `Point` and `Rectangle`

### Part 3: Sealed Traits and Enums (45 min)

**Simple enum: a finite set of values**

```scala
enum Day:
  case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday

def isWeekend(d: Day): Boolean = d match
  case Day.Saturday | Day.Sunday => true
  case _ => false
```

**Sealed trait: algebraic types**

A `sealed trait` defines a **closed** set of subtypes.
The compiler verifies that you handle all cases!

```scala
sealed trait Animal
case class Dog(name: String, breed: String) extends Animal
case class Cat(name: String, indoor: Boolean) extends Animal
case class Bird(name: String, wingspan: Double) extends Animal

def describeAnimal(a: Animal): String = a match
  case Dog(name, breed) => s"$name is a $breed dog"
  case Cat(name, true) => s"$name is an indoor cat"
  case Cat(name, false) => s"$name is an outdoor cat"
  case Bird(name, ws) => s"$name is a bird with ${ws}cm wingspan"
  // If you forget a case, the compiler warns you!
```

**Why "sealed"?**

```scala
// Without sealed: anyone can add a subtype
// The compiler cannot verify exhaustiveness

// With sealed: only subtypes in this file exist
// The compiler can verify that all cases are handled
```

**Exercises 3.1 - 3.5**: `TrafficLight` and `Shape` (geometric)

### Part 4: Enum with Data (30 min)

Scala 3 enums can contain different data depending on the case:

```scala
enum Message:
  case Text(content: String)
  case Image(url: String, width: Int, height: Int)
  case File(name: String, size: Long)

import Message.*

def describeMessage(m: Message): String = m match
  case Text(c) => s"Text message: $c"
  case Image(url, w, h) => s"Image ${w}x${h} from $url"
  case File(name, size) => s"File $name ($size bytes)"

// Creation
val m1 = Text("Hello")
val m2 = Image("http://...", 800, 600)
```

**Exercises 4.1 - 4.2**: Mathematical `Expression`

### Part 5: Mini-Project - Grading System (30 min)

You will build a student evaluation system using:

```scala
case class Grade(subject: String, score: Int)
case class Student(name: String, grades: List[Grade])

enum Mention:
  case VeryGood, Good, Satisfactory, Passing, Insufficient
```

**Exercises 5.1 - 5.8**: create, validate, calculate averages, mentions, reports

## List Operations (cheat sheet)

For the mini-project, here are useful `List` operations:

```scala
val numbers = List(10, 20, 30, 40)

// Transform each element
numbers.map(n => n * 2)           // List(20, 40, 60, 80)

// Filter by condition
numbers.filter(n => n > 15)       // List(20, 30, 40)

// Sum of elements
numbers.sum                        // 100

// Number of elements
numbers.size                       // 4

// Maximum (returns Option)
numbers.maxByOption(n => n)        // Some(40)

// Sort
numbers.sortBy(n => n)             // List(10, 20, 30, 40)
numbers.sortBy(n => -n)            // List(40, 30, 20, 10)

// List of tuples to Map
List(("a", 1), ("b", 2)).toMap     // Map("a" -> 1, "b" -> 2)
```

## Project Structure

```
seance-2/
├── src/
│   ├── main/scala/
│   │   └── Exercices.scala   # To complete
│   └── test/scala/
│       └── ExercicesSpec.scala
├── build.sbt
└── README.md
```

## Useful Commands

```bash
sbt test              # Run all tests
sbt "testOnly *1.1*"  # Test a specific exercise
sbt ~test             # Watch mode
sbt console           # REPL
```

## Key Points to Remember

| Concept | Syntax |
|---------|--------|
| Case class | `case class Name(field: Type)` |
| Creation | `Name(value)` (no `new`) |
| Access | `instance.field` |
| Copy | `instance.copy(field = newValue)` |
| Simple enum | `enum Name: case A, B, C` |
| Sealed trait | `sealed trait X` + `case class Y extends X` |
| Destructuring | `x match { case Name(a, b) => ... }` |

## Next Session

**Session 3**: Monads and Higher-Order Functions (1)
- Implement `MyList`
- Understand `map`, `flatMap`, `filter`, `fold`

---

*FP with Scala - V2 - Session 2*
