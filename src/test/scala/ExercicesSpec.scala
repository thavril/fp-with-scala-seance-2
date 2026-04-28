package seance2

import munit.FunSuite
import Exercices.*

class ExercicesSpec extends FunSuite:
  
  // ============================================
  // Tests Partie 1: Introduction aux Case Classes
  // ============================================
  
  test("1.1 - creerPersonne: crée une Personne"):
    val p = creerPersonne("Alice", 25)
    assertEquals(p.nom, "Alice")
    assertEquals(p.age, 25)
  
  test("1.2 - getNom: retourne le nom"):
    assertEquals(getNom(Personne("Bob", 30)), "Bob")
    assertEquals(getNom(Personne("Charlie", 45)), "Charlie")
  
  test("1.3 - getAge: retourne l'âge"):
    assertEquals(getAge(Personne("Alice", 25)), 25)
    assertEquals(getAge(Personne("Bob", 17)), 17)
  
  test("1.4 - estMajeur: vérifie la majorité"):
    assert(estMajeur(Personne("Alice", 18)))
    assert(estMajeur(Personne("Bob", 25)))
    assert(!estMajeur(Personne("Charlie", 17)))
    assert(!estMajeur(Personne("David", 0)))
  
  test("1.5 - anniversaire: incrémente l'âge"):
    assertEquals(anniversaire(Personne("Alice", 25)), Personne("Alice", 26))
    assertEquals(anniversaire(Personne("Bob", 0)), Personne("Bob", 1))
  
  test("1.6 - decrire: formate la description"):
    assertEquals(decrire(Personne("Alice", 25)), "Alice a 25 ans")
    assertEquals(decrire(Personne("Bob", 30)), "Bob a 30 ans")
  
  
  // ============================================
  // Tests Partie 2: Pattern Matching sur Case Classes
  // ============================================
  
  test("2.1 - sommeCoordonnees: additionne x et y"):
    assertEquals(sommeCoordonnees(Point(3, 4)), 7)
    assertEquals(sommeCoordonnees(Point(-1, 5)), 4)
    assertEquals(sommeCoordonnees(Point(0, 0)), 0)
  
  test("2.2 - distanceOrigine: calcule la distance"):
    assertEqualsDouble(distanceOrigine(Point(3, 4)), 5.0, 0.01)
    assertEqualsDouble(distanceOrigine(Point(0, 0)), 0.0, 0.01)
    assertEqualsDouble(distanceOrigine(Point(1, 1)), math.sqrt(2), 0.01)
  
  test("2.3 - quadrant: identifie le quadrant"):
    assertEquals(quadrant(Point(1, 1)), "I")
    assertEquals(quadrant(Point(-1, 1)), "II")
    assertEquals(quadrant(Point(-1, -1)), "III")
    assertEquals(quadrant(Point(1, -1)), "IV")
    assertEquals(quadrant(Point(0, 0)), "Origine")
    assertEquals(quadrant(Point(0, 5)), "Axe")
    assertEquals(quadrant(Point(5, 0)), "Axe")
  
  test("2.4 - aire: calcule l'aire d'un rectangle"):
    assertEquals(aire(Rectangle(Point(0, 0), Point(4, 3))), 12)
    assertEquals(aire(Rectangle(Point(1, 1), Point(5, 4))), 12)
    assertEquals(aire(Rectangle(Point(0, 0), Point(0, 0))), 0)
  
  test("2.5 - contient: vérifie si un point est dans le rectangle"):
    val rect = Rectangle(Point(0, 0), Point(10, 10))
    assert(contient(rect, Point(5, 5)))
    assert(contient(rect, Point(0, 0)))
    assert(contient(rect, Point(10, 10)))
    assert(!contient(rect, Point(11, 5)))
    assert(!contient(rect, Point(-1, 5)))
  
  
  // ============================================
  // Tests Partie 3: Sealed Traits et Enums
  // ============================================
  
  test("3.1 - prochainFeu: cycle des feux"):
    assertEquals(prochainFeu(Feu.Rouge), Feu.Vert)
    assertEquals(prochainFeu(Feu.Vert), Feu.Orange)
    assertEquals(prochainFeu(Feu.Orange), Feu.Rouge)
  
  test("3.2 - actionFeu: action selon le feu"):
    assertEquals(actionFeu(Feu.Rouge), "Arrêter")
    assertEquals(actionFeu(Feu.Orange), "Ralentir")
    assertEquals(actionFeu(Feu.Vert), "Passer")
  
  test("3.3 - aireForme: calcule l'aire des formes"):
    assertEqualsDouble(aireForme(Cercle(1.0)), math.Pi, 0.01)
    assertEqualsDouble(aireForme(Cercle(2.0)), 4 * math.Pi, 0.01)
    assertEqualsDouble(aireForme(Carre(3.0)), 9.0, 0.01)
    assertEqualsDouble(aireForme(RectangleForme(4.0, 2.0)), 8.0, 0.01)
  
  test("3.4 - perimetreForme: calcule le périmètre"):
    assertEqualsDouble(perimetreForme(Cercle(1.0)), 2 * math.Pi, 0.01)
    assertEqualsDouble(perimetreForme(Carre(3.0)), 12.0, 0.01)
    assertEqualsDouble(perimetreForme(RectangleForme(4.0, 2.0)), 12.0, 0.01)
  
  test("3.5 - descriptionForme: décrit la forme"):
    assertEquals(descriptionForme(Cercle(5.0)), "Cercle de rayon 5.0")
    assertEquals(descriptionForme(Carre(3.0)), "Carré de côté 3.0")
    assertEquals(descriptionForme(RectangleForme(4.0, 2.0)), "Rectangle de 4.0 x 2.0")
  
  
  // ============================================
  // Tests Partie 4: Enum avec Données
  // ============================================
  
  import Expression.*
  
  test("4.1 - evaluer: évalue une expression"):
    assertEquals(evaluer(Nombre(5)), 5)
    assertEquals(evaluer(Addition(Nombre(3), Nombre(4))), 7)
    assertEquals(evaluer(Soustraction(Nombre(10), Nombre(3))), 7)
    assertEquals(evaluer(Multiplication(Nombre(2), Nombre(5))), 10)
    assertEquals(evaluer(Multiplication(Nombre(2), Addition(Nombre(3), Nombre(1)))), 8)
  
  test("4.2 - versString: convertit en string"):
    assertEquals(versString(Nombre(5)), "5")
    assertEquals(versString(Addition(Nombre(3), Nombre(4))), "(3 + 4)")
    assertEquals(versString(Multiplication(Nombre(2), Nombre(3))), "(2 * 3)")
    assertEquals(
      versString(Multiplication(Nombre(2), Addition(Nombre(3), Nombre(1)))),
      "(2 * (3 + 1))"
    )
  
  
  // ============================================
  // Tests Partie 5: Mini-Projet
  // ============================================
  
  val alice = Etudiant("Alice", List(
    Note("Math", 85),
    Note("Physique", 90),
    Note("Chimie", 80)
  ))
  
  val bob = Etudiant("Bob", List(
    Note("Math", 70),
    Note("Physique", 65)
  ))
  
  val charlie = Etudiant("Charlie", List())
  
  test("5.1 - creerEtudiant: crée un étudiant"):
    val e = creerEtudiant("Test", List(Note("Math", 80)))
    assertEquals(e.nom, "Test")
    assertEquals(e.notes.size, 1)
  
  test("5.2 - noteValide: valide une note"):
    assert(noteValide(Note("Math", 85)))
    assert(noteValide(Note("Math", 0)))
    assert(noteValide(Note("Math", 100)))
    assert(!noteValide(Note("Math", -1)))
    assert(!noteValide(Note("Math", 101)))
  
  test("5.3 - moyenneEtudiant: calcule la moyenne"):
    assertEqualsDouble(moyenneEtudiant(alice), 85.0, 0.01)
    assertEqualsDouble(moyenneEtudiant(bob), 67.5, 0.01)
    assertEqualsDouble(moyenneEtudiant(charlie), 0.0, 0.01)
  
  test("5.4 - mentionPourMoyenne: détermine la mention"):
    assertEquals(mentionPourMoyenne(17.0), Mention.TresBien)
    assertEquals(mentionPourMoyenne(15.0), Mention.Bien)
    assertEquals(mentionPourMoyenne(13.0), Mention.AssezBien)
    assertEquals(mentionPourMoyenne(11.0), Mention.Passable)
    assertEquals(mentionPourMoyenne(8.0), Mention.Insuffisant)
  
  test("5.5 - mentionEtudiant: mention d'un étudiant"):
    assertEquals(mentionEtudiant(alice), Mention.TresBien)
    assertEquals(mentionEtudiant(bob), Mention.Insuffisant)
  
  test("5.6 - meilleureNote: trouve la meilleure note"):
    assertEquals(meilleureNote(alice), Some(Note("Physique", 90)))
    assertEquals(meilleureNote(charlie), None)
  
  test("5.7 - notesAuDessusMoyenne: filtre les notes"):
    val notesAlice = notesAuDessusMoyenne(alice)
    assertEquals(notesAlice.size, 2)
    assert(notesAlice.contains(Note("Math", 85)))
    assert(notesAlice.contains(Note("Physique", 90)))
  
  test("5.8 - rapportEtudiant: génère un rapport"):
    val rapport = rapportEtudiant(alice)
    assert(rapport.contains("Alice"))
    assert(rapport.contains("85.00"))
    assert(rapport.contains("TresBien"))
  
  
  // ============================================
  // Tests BONUS
  // ============================================
  
  test("BONUS 1 - meilleurEtudiant: compare deux étudiants"):
    assertEquals(meilleurEtudiant(alice, bob), "Alice")
    assertEquals(meilleurEtudiant(bob, alice), "Alice")
  
  test("BONUS 2 - notesTries: trie par score décroissant"):
    val tries = notesTries(alice)
    assertEquals(tries.head.score, 90)
    assertEquals(tries.last.score, 80)
  
  test("BONUS 3 - parMatiere: crée une map"):
    val map = parMatiere(alice)
    assertEquals(map("Math"), 85)
    assertEquals(map("Physique"), 90)
    assertEquals(map("Chimie"), 80)
