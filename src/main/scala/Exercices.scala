package seance2

/**
 * Séance 2 - Case Classes et Pattern Matching Avancé
 * 
 * Objectifs :
 * - Créer et utiliser des case classes
 * - Comprendre la différence entre tuples et case classes
 * - Maîtriser le pattern matching sur des structures
 * - Utiliser sealed traits et enums pour modéliser des domaines
 * 
 * Instructions :
 * 1. Complétez les fonctions marquées avec ???
 * 2. N'utilisez PAS de var, uniquement val
 * 3. Lancez les tests avec: sbt test
 */

object Exercices:
  
  // ============================================
  // Partie 1: Introduction aux Case Classes
  // ============================================
  // Une case class est comme un tuple NOMMÉ avec des champs nommés.
  // Elle génère automatiquement: equals, hashCode, toString, copy, apply
  
  /**
   * Définition d'une case class simple.
   * Comparez avec un tuple: (String, Int) vs Personne(nom, age)
   */
  case class Personne(nom: String, age: Int)
  
  /**
   * Exercice 1.1: Créer une Personne
   * Créez une instance de Personne avec le nom et l'âge donnés.
   * 
   * Exemple: creerPersonne("Alice", 25) == Personne("Alice", 25)
   */
  def creerPersonne(nom: String, age: Int): Personne =
    ???
  
  /**
   * Exercice 1.2: Accéder aux champs
   * Retournez le nom de la personne.
   * Avec une case class, on accède aux champs par leur nom: personne.nom
   * 
   * Exemple: getNom(Personne("Alice", 25)) == "Alice"
   */
  def getNom(personne: Personne): String =
    ???
  
  /**
   * Exercice 1.3: Accéder à l'âge
   * Retournez l'âge de la personne.
   */
  def getAge(personne: Personne): Int =
    ???
  
  /**
   * Exercice 1.4: Vérifier la majorité
   * Retourne true si la personne a 18 ans ou plus.
   */
  def estMajeur(personne: Personne): Boolean =
    ???
  
  /**
   * Exercice 1.5: Utiliser copy
   * Les case classes ont une méthode copy() pour créer une copie modifiée.
   * Créez une nouvelle Personne avec le même nom mais un âge augmenté de 1.
   * 
   * Exemple: anniversaire(Personne("Alice", 25)) == Personne("Alice", 26)
   */
  def anniversaire(personne: Personne): Personne =
    ???
  
  /**
   * Exercice 1.6: Description formatée
   * Retourne "X a Y ans" où X est le nom et Y l'âge.
   */
  def decrire(personne: Personne): String =
    ???
  
  
  // ============================================
  // Partie 2: Pattern Matching sur Case Classes
  // ============================================
  // Le pattern matching permet de "déstructurer" une case class
  // pour extraire ses composants.
  
  /**
   * Case class pour un point 2D
   */
  case class Point(x: Int, y: Int)
  
  /**
   * Exercice 2.1: Déstructuration simple
   * Utilisez le pattern matching pour extraire x et y, puis calculez x + y.
   * 
   * Syntaxe: point match { case Point(x, y) => ... }
   * 
   * Exemple: sommeCoordonnees(Point(3, 4)) == 7
   */
  def sommeCoordonnees(point: Point): Int =
    ???
  
  /**
   * Exercice 2.2: Distance à l'origine
   * Calculez la distance du point à l'origine (0, 0).
   * Distance = sqrt(x² + y²)
   * Utilisez math.sqrt et le pattern matching.
   */
  def distanceOrigine(point: Point): Double =
    ???
  
  /**
   * Exercice 2.3: Quadrant d'un point
   * Déterminez dans quel quadrant se trouve le point:
   * - "I" si x > 0 et y > 0
   * - "II" si x < 0 et y > 0
   * - "III" si x < 0 et y < 0
   * - "IV" si x > 0 et y < 0
   * - "Origine" si x == 0 et y == 0
   * - "Axe" si sur un axe (x == 0 ou y == 0 mais pas les deux)
   */
  def quadrant(point: Point): String =
    ???
  
  /**
   * Case class pour un rectangle défini par deux points
   */
  case class Rectangle(coinBasGauche: Point, coinHautDroit: Point)
  
  /**
   * Exercice 2.4: Aire d'un rectangle
   * Calculez l'aire du rectangle.
   * Utilisez le pattern matching imbriqué pour extraire les coordonnées.
   * 
   * Syntaxe: rect match { case Rectangle(Point(x1, y1), Point(x2, y2)) => ... }
   */
  def aire(rect: Rectangle): Int =
    ???
  
  /**
   * Exercice 2.5: Point dans rectangle
   * Vérifiez si un point est à l'intérieur d'un rectangle (inclus les bords).
   */
  def contient(rect: Rectangle, point: Point): Boolean =
    ???
  
  
  // ============================================
  // Partie 3: Sealed Traits et Enums
  // ============================================
  // Un sealed trait permet de définir un ensemble FERMÉ de cas.
  // Le compilateur peut vérifier l'exhaustivité du pattern matching.
  
  /**
   * Enum pour représenter une couleur de feu tricolore
   */
  enum Feu:
    case Rouge, Orange, Vert
  
  /**
   * Exercice 3.1: Prochain état du feu
   * Rouge -> Vert -> Orange -> Rouge -> ...
   */
  def prochainFeu(feu: Feu): Feu =
    ???
  
  /**
   * Exercice 3.2: Action selon le feu
   * Rouge -> "Arrêter"
   * Orange -> "Ralentir"
   * Vert -> "Passer"
   */
  def actionFeu(feu: Feu): String =
    ???
  
  /**
   * Sealed trait pour représenter une forme géométrique
   */
  sealed trait Forme
  case class Cercle(rayon: Double) extends Forme
  case class Carre(cote: Double) extends Forme
  case class RectangleForme(largeur: Double, hauteur: Double) extends Forme
  
  /**
   * Exercice 3.3: Aire d'une forme
   * Calculez l'aire selon le type de forme.
   * - Cercle: π * r²
   * - Carré: c²
   * - Rectangle: l * h
   */
  def aireForme(forme: Forme): Double =
    ???
  
  /**
   * Exercice 3.4: Périmètre d'une forme
   * - Cercle: 2 * π * r
   * - Carré: 4 * c
   * - Rectangle: 2 * (l + h)
   */
  def perimetreForme(forme: Forme): Double =
    ???
  
  /**
   * Exercice 3.5: Description d'une forme
   * Retourne une description textuelle.
   * - Cercle(5.0) -> "Cercle de rayon 5.0"
   * - Carre(3.0) -> "Carré de côté 3.0"
   * - RectangleForme(4.0, 2.0) -> "Rectangle de 4.0 x 2.0"
   */
  def descriptionForme(forme: Forme): String =
    ???
  
  
  // ============================================
  // Partie 4: Enum avec Données
  // ============================================
  // Les enums Scala 3 peuvent contenir des données.
  
  /**
   * Enum pour représenter une expression mathématique simple
   */
  enum Expression:
    case Nombre(valeur: Int)
    case Addition(gauche: Expression, droite: Expression)
    case Soustraction(gauche: Expression, droite: Expression)
    case Multiplication(gauche: Expression, droite: Expression)
  
  import Expression.*
  
  /**
   * Exercice 4.1: Évaluer une expression
   * Calculez le résultat de l'expression récursivement.
   * 
   * Exemple: evaluer(Addition(Nombre(3), Nombre(4))) == 7
   *          evaluer(Multiplication(Nombre(2), Addition(Nombre(3), Nombre(1)))) == 8
   */
  def evaluer(expr: Expression): Int =
    ???
  
  /**
   * Exercice 4.2: Expression vers String
   * Convertissez une expression en notation infixe avec parenthèses.
   * 
   * Exemple: versString(Addition(Nombre(3), Nombre(4))) == "(3 + 4)"
   *          versString(Multiplication(Nombre(2), Nombre(3))) == "(2 * 3)"
   */
  def versString(expr: Expression): String =
    ???
  
  
  // ============================================
  // Partie 5: Mini-Projet - Système de Notes
  // ============================================
  // Utilisons les case classes pour modéliser un système d'évaluation.
  
  /**
   * Modèle de données pour le système de notes
   */
  case class Note(matiere: String, score: Int)
  case class Etudiant(nom: String, notes: List[Note])
  
  enum Mention:
    case TresBien, Bien, AssezBien, Passable, Insuffisant
  
  /**
   * Exercice 5.1: Créer un étudiant
   * Créez un étudiant avec son nom et une liste de notes.
   */
  def creerEtudiant(nom: String, notes: List[Note]): Etudiant =
    ???
  
  /**
   * Exercice 5.2: Note valide
   * Une note est valide si le score est entre 0 et 20 inclus.
   */
  def noteValide(note: Note): Boolean =
    ???
  
  /**
   * Exercice 5.3: Moyenne d'un étudiant
   * Calculez la moyenne des scores de l'étudiant.
   * Si l'étudiant n'a pas de notes, retournez 0.0.
   * 
   * Indice: utilisez .map et .sum sur les listes
   */
  def moyenneEtudiant(etudiant: Etudiant): Double =
    ???
  
  /**
   * Exercice 5.4: Mention selon la moyenne
   * >= 16: TresBien
   * >= 14: Bien
   * >= 12: AssezBien
   * >= 10: Passable
   * < 10: Insuffisant
   */
  def mentionPourMoyenne(moyenne: Double): Mention =
    ???
  
  /**
   * Exercice 5.5: Mention d'un étudiant
   * Calculez la mention de l'étudiant à partir de ses notes.
   * Utilisez les fonctions précédentes (composition).
   */
  def mentionEtudiant(etudiant: Etudiant): Mention =
    ???
  
  /**
   * Exercice 5.6: Meilleure note
   * Trouvez la note avec le score le plus élevé.
   * Retourne None si l'étudiant n'a pas de notes.
   * 
   * Indice: utilisez .maxByOption sur les listes
   */
  def meilleureNote(etudiant: Etudiant): Option[Note] =
    ???
  
  /**
   * Exercice 5.7: Notes au-dessus de la moyenne
   * Retourne la liste des notes dont le score est >= à la moyenne de l'étudiant.
   * 
   * Indice: utilisez .filter sur les listes
   */
  def notesAuDessusMoyenne(etudiant: Etudiant): List[Note] =
    ???
  
  /**
   * Exercice 5.8: Rapport d'étudiant
   * Génère un rapport textuel pour l'étudiant:
   * "Étudiant: X
   *  Moyenne: Y
   *  Mention: Z"
   * 
   * Où Y est arrondi à 2 décimales.
   */
  def rapportEtudiant(etudiant: Etudiant): String =
    ???
  
  
  // ============================================
  // BONUS: Exercices Avancés
  // ============================================
  
  /**
   * BONUS 1: Comparer deux étudiants
   * Retourne le nom de l'étudiant avec la meilleure moyenne.
   * En cas d'égalité, retourne le premier.
   */
  def meilleurEtudiant(e1: Etudiant, e2: Etudiant): String =
    ???
  
  /**
   * BONUS 2: Trier les notes par score décroissant
   * Retourne les notes triées du plus haut au plus bas score.
   */
  def notesTries(etudiant: Etudiant): List[Note] =
    ???
  
  /**
   * BONUS 3: Résumé par matière
   * Retourne une Map où la clé est le nom de la matière
   * et la valeur est le score.
   */
  def parMatiere(etudiant: Etudiant): Map[String, Int] =
    ???
