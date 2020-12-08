# 220project - Call of Coding: Medieval Warfare
## Group Members
-	Tim DeMember – demembertm19@gcc.edu
-	Riley Truitt – truittrt19@gcc.edu
-	Nick Sparks - sparksnc18@gcc.edu

## Feature Descriptions
### General Description:
Call of Coding: Medieval Warfare is a two-player strategy combat game played on a 15x10 board of squares. Each player controls unique game pieces on the board that they can move individually and fight the enemy team with. The winner of the game is determined when the other player has no more pieces alive.

### Functional Description:
Our program should generate a gridded game board, initialize an assortment of game pieces onto two teams, place those game pieces onto the board, and allow them to interact with each other at the players' discretion in a manner such that one team’s game pieces will end up entirely out of hit points, and the other player will be the victor. The board woud be represented through a GUI and inputs would handled by clicking on elements of the GUI. 

### These functions can be summed up in the following lists:
-	Create a 15x10 gridded game board
-	Create two teams of game pieces
-	Place the game pieces on the game board, game pieces represented by letters
-	Allow movement of the pieces around the board
-	Allow interaction between the game pieces resulting in hit point decrements
-	Get the current stats of a specific piece
-	Declare a winner when one team is eliminated
### Board Pieces:
-	Swordsman: Standard combat unit.
-	Archer: Can attack from a longer range.
-	Shield Bearer: Chance to “block” incoming attacks reducing the damage taken.
-	Knight: A horse riding swordsman. After the knight is “killed” it loses its horse and becomes a swordsman.
-	Assassin: Can deal a “sneak strike” where additional damage is dealt if an enemy piece is isolated. 
-	Mage: can cast spells that heal friendly pieces. 

## UML Diagram
![UML Diagram](/UML_Diagram.png)
