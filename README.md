# Séance 2 - Case Classes et Pattern Matching Avancé

**Durée** : 3h  
**Date** : 28 avril 2025

## Objectifs d'apprentissage

À la fin de cette séance, vous serez capables de :

- Créer et utiliser des **case classes**
- Comprendre les avantages des case classes vs tuples
- **Déstructurer** des données avec le pattern matching
- Modéliser des domaines avec **sealed traits** et **enums**
- Composer des fonctions pour résoudre des problèmes complexes

## Rappel : Séance 1

- `val` vs `var` (immutabilité)
- Types simples : `Int`, `Double`, `String`, `Boolean`
- Récursion
- Pattern matching sur valeurs simples

## Mise en route

```bash
cd seance-2
sbt compile
sbt test
```

## Organisation de la séance

### Partie 1 : Introduction aux Case Classes (30 min)

**Qu'est-ce qu'une case class ?**

Une case class est une classe spéciale qui génère automatiquement :
- Un constructeur (pas besoin de `new`)
- Des accesseurs pour chaque champ
- `equals`, `hashCode`, `toString`
- Une méthode `copy` pour créer des copies modifiées

```scala
// Définition d'une case class
case class Voiture(marque: String, annee: Int)

// Création (pas besoin de new)
val maVoiture = Voiture("Peugeot", 2020)

// Accès aux champs par leur nom
maVoiture.marque    // "Peugeot"
maVoiture.annee     // 2020

// Copie modifiée (l'original ne change pas !)
val voitureNeuve = maVoiture.copy(annee = 2024)
// maVoiture.annee est toujours 2020

// Comparaison par valeur (pas par référence)
Voiture("Peugeot", 2020) == Voiture("Peugeot", 2020)  // true
```

**Case class vs Tuple : pourquoi préférer les case classes ?**

```scala
// Tuple : anonyme, accès par position (_1, _2...)
val t: (String, Int) = ("Peugeot", 2020)
t._1  // "Peugeot" - mais que représente _1 ?
t._2  // 2020 - et _2 ?

// Case class : nommé, code auto-documenté
val v: Voiture = Voiture("Peugeot", 2020)
v.marque  // "Peugeot" - clair !
v.annee   // 2020 - explicite !
```

**Exercices 1.1 - 1.6** : Manipulation de `Personne`

### Partie 2 : Pattern Matching sur Case Classes (45 min)

**Déstructuration : extraire les composants**

Le pattern matching permet d'extraire les champs d'une case class :

```scala
case class Coordonnees(latitude: Double, longitude: Double)

def hemisphere(c: Coordonnees): String = c match
  case Coordonnees(lat, _) if lat > 0 => "Nord"
  case Coordonnees(lat, _) if lat < 0 => "Sud"
  case Coordonnees(0, _) => "Équateur"
```

**Valeurs spécifiques dans le pattern**

```scala
case class Produit(nom: String, prix: Double)

def categoriePrix(p: Produit): String = p match
  case Produit(_, 0) => "Gratuit"
  case Produit(_, prix) if prix < 10 => "Pas cher"
  case Produit(_, prix) if prix < 100 => "Moyen"
  case _ => "Cher"
```

**Pattern matching imbriqué**

Quand une case class contient une autre case class :

```scala
case class Adresse(ville: String, codePostal: String)
case class Entreprise(nom: String, siege: Adresse)

def estParisienne(e: Entreprise): Boolean = e match
  case Entreprise(_, Adresse("Paris", _)) => true
  case Entreprise(_, Adresse(_, cp)) if cp.startsWith("75") => true
  case _ => false
```

**Exercices 2.1 - 2.5** : Manipulation de `Point` et `Rectangle`

### Partie 3 : Sealed Traits et Enums (45 min)

**Enum simple : un ensemble fini de valeurs**

```scala
enum Jour:
  case Lundi, Mardi, Mercredi, Jeudi, Vendredi, Samedi, Dimanche

def estWeekend(j: Jour): Boolean = j match
  case Jour.Samedi | Jour.Dimanche => true
  case _ => false
```

**Sealed trait : types algébriques**

Un `sealed trait` définit un ensemble **fermé** de sous-types.
Le compilateur vérifie que vous gérez tous les cas !

```scala
sealed trait Animal
case class Chien(nom: String, race: String) extends Animal
case class Chat(nom: String, interieur: Boolean) extends Animal
case class Oiseau(nom: String, envergure: Double) extends Animal

def decrireAnimal(a: Animal): String = a match
  case Chien(nom, race) => s"$nom est un chien de race $race"
  case Chat(nom, true) => s"$nom est un chat d'intérieur"
  case Chat(nom, false) => s"$nom est un chat d'extérieur"
  case Oiseau(nom, env) => s"$nom est un oiseau avec ${env}cm d'envergure"
  // Si vous oubliez un cas, le compilateur vous avertit !
```

**Pourquoi "sealed" ?**

```scala
// Sans sealed : n'importe qui peut ajouter un sous-type
// Le compilateur ne peut pas vérifier l'exhaustivité

// Avec sealed : seuls les sous-types dans ce fichier existent
// Le compilateur peut vérifier que tous les cas sont gérés
```

**Exercices 3.1 - 3.5** : `Feu` (tricolore) et `Forme` (géométrique)

### Partie 4 : Enum avec Données (30 min)

Les enums Scala 3 peuvent contenir des données différentes selon le cas :

```scala
enum Message:
  case Texte(contenu: String)
  case Image(url: String, largeur: Int, hauteur: Int)
  case Fichier(nom: String, taille: Long)

import Message.*

def decrireMessage(m: Message): String = m match
  case Texte(c) => s"Message texte: $c"
  case Image(url, l, h) => s"Image ${l}x${h} depuis $url"
  case Fichier(nom, taille) => s"Fichier $nom (${taille} octets)"

// Création
val m1 = Texte("Bonjour")
val m2 = Image("http://...", 800, 600)
```

**Exercices 4.1 - 4.2** : `Expression` mathématique

### Partie 5 : Mini-Projet - Système de Notes (30 min)

Vous allez construire un système d'évaluation d'étudiants en utilisant :

```scala
case class Note(matiere: String, score: Int)
case class Etudiant(nom: String, notes: List[Note])

enum Mention:
  case TresBien, Bien, AssezBien, Passable, Insuffisant
```

**Exercices 5.1 - 5.8** : créer, valider, calculer moyennes, mentions, rapports

## Opérations sur les listes (aide-mémoire)

Pour le mini-projet, voici les opérations `List` utiles :

```scala
val nombres = List(10, 20, 30, 40)

// Transformer chaque élément
nombres.map(n => n * 2)           // List(20, 40, 60, 80)

// Filtrer selon une condition
nombres.filter(n => n > 15)       // List(20, 30, 40)

// Somme des éléments
nombres.sum                        // 100

// Nombre d'éléments
nombres.size                       // 4

// Maximum (retourne Option)
nombres.maxByOption(n => n)        // Some(40)

// Trier
nombres.sortBy(n => n)             // List(10, 20, 30, 40)
nombres.sortBy(n => -n)            // List(40, 30, 20, 10)

// Liste de tuples vers Map
List(("a", 1), ("b", 2)).toMap     // Map("a" -> 1, "b" -> 2)
```

## Structure du projet

```
seance-2/
├── src/
│   ├── main/scala/
│   │   └── Exercices.scala   # À compléter
│   └── test/scala/
│       └── ExercicesSpec.scala
├── build.sbt
└── README.md
```

## Commandes utiles

```bash
sbt test              # Lance tous les tests
sbt "testOnly *1.1*"  # Teste un exercice spécifique
sbt ~test             # Mode watch
sbt console           # REPL
```

## Points clés à retenir

| Concept | Syntaxe |
|---------|---------|
| Case class | `case class Nom(champ: Type)` |
| Création | `Nom(valeur)` (pas de `new`) |
| Accès | `instance.champ` |
| Copie | `instance.copy(champ = nouvelleValeur)` |
| Enum simple | `enum Nom: case A, B, C` |
| Sealed trait | `sealed trait X` + `case class Y extends X` |
| Déstructuration | `x match { case Nom(a, b) => ... }` |

## Prochaine séance

**Séance 3** : Monades et Fonctions d'Ordre Supérieur (1)
- Implémenter `MyList`
- Comprendre `map`, `flatMap`, `filter`, `fold`

---

*FP with Scala - V2 - Séance 2*
