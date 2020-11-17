# 220project
## Group Members
-	Tim DeMember – demembertm19@gcc.edu
-	Riley Truitt – truittrt19@gcc.edu
-	Nick Sparks - sparksnc18@gcc.edu

## Feature Descriptions
### General Description:
	Our project is a two-player strategy combat game played on a board consisting of 10x10 squares. Each player controls unique game pieces on the board that they can move individually and fight the enemy team with. The winner of the game is determined when only one team remains.
Functional Description:
	Our program should generate a gridded game board, initialize an assortment of game pieces onto two teams, place those game pieces onto the board, and allow them to interact with each other at the players discretion in a manner such that one team’s game pieces will end up entirely out of hit points, and the other player will be the victor. Inputs would be handled through the console and the game board would be represented in the console. 

### These functions can be summed up in the following lists:
-	¬Create a 10x10 gridded game board
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
-	Mage: can cast spells that buff attacks of other pieces or heal other pieces. 

## UML Diagram
![UML Diagram](/UML_Diagram.png)
