# 3rd-year-game
A 2D platformer written in pure java.
#Game
* **Step 1** - You play as the main individual player.
* **Step 2** - You are required to move the player through the level and pick up 3 items while fighting off enemies.
* **Step 3** – After you collect all the 3 items you need to find a door which will check if you have the items in order. If the items are in order you win the game. If you do not have the items in order, you will need to rearrange them into another order by dropping the items and picking them up again. Alternatively, you will need to pick up other items as you proceed.

The objective of the game is to follow a specific order. If the order is incorrect or not systematic, you will be unable to proceed to win the game. There can be multiple number of arrangements however, the first element must be a ‘battery.’
Data structures

#Data structures
* **Stack** - There are 5 stacks used. They are used for positions of the game objects for the enemies and the items. There reason it was appropriate to use a stack for this is because the order of the positions of the items and enemies do not need to be in order and you can also get O(1) insertions and removals. This is ideal because when an item or enemy is instantiated the position stack can be used to set positions of the items and enemies. This is the only usage for stacks in my implementation of the game.
* **Queue** – There is one queue used which is responsible for the storing of items that you pick up as you play the game. This is identified as the “bag” of the player. It is appropriate to use this data structure because you can get O(1) insertions and deletions. The added benefit of using a queue is that all items that you pick up will be in a logical understandable order, and when you drop items the logical understandable order is still experienced.
* **Binary tree** – The binary tree is used for the main logic of the game. The tree is instantiated
with pre-defined elements. These can be several different items in the tree. The player must
pick up items which will go down the tree, until you reach a ‘door’ on the lowest level of the
tree. On the lowest level there are only four (4) doors which the player can reach by picking up
items. If you get to a door you win and if not, you do not win. The tree is as follows:



#Screens
