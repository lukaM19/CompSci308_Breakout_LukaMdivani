# Breakout Plan
### NAME Luka Mdivani



## Interesting Breakout Variants
There were two breakout variants which especially caught my eye.

 * One interesting variation of breakout which I have played before had an interesting concept, it 
let you start with one ball on an easy level, if you managed to clear the level, the difficulty would
increase but you would also get twice the amount of balls each level. The goal was to complete ("clear")
as many levels as possible. you would lose if you lost all balls without managing to clear a level.

 * Also, the pinball breakout variant, which combines two classic arcade games, pinball and breakout. It is an interesting 
combination. The version which I saw let you choose the starting position and power of you ball at the top of the screen,
the ball would fall and bounce among many blocks, which needed different amount of hits to be broken. According to the number of points your ball managed to collect, you were given a chance to throw another pinball, as well as buy interesting upgrades. Such as burst balls which spawned more than one ball etc.


## Paddle Ideas

 * Paddle will reflect the ball differently depending on where the ball touches tha paddle.

 * Paddle will teleport to the other side if it leaves the screen.


## Block Ideas

 * Block which drops a random power up when hit

 * Block which is permanent and can't be destroyed

 * Block which takes 2 hits to be destroyed


## Power-up Ideas

 * Doubles available lives

 * makes paddle larger by 2%

 * Speeds up the ball by 5%


## Cheat Key Ideas

 * Drops a power up instantly

 * adds 3 lives

 * make paddle larger

 * makes the ball slower


## Level Descriptions

 * Level 1- Basic level, with only default block in 4 rows and 4 columns.

 * Level 2- Same level design, but with 3 diagonally placed permanent blocks, but 2 power up blocks are also available.

 * Level 3- Structure will have islands of destroyable blocks in each corner and 2 indestructible balls in the middle, new blocks which take longer to destroy, the ball will move faster and the paddle will be 
higher up on the screen.


## Class Ideas
Some Necessary classes are listed below:
 1. A class for the ball, the class will be used to track the ball, its features(like speed) and direction. One fo the 
 methods of the class will detect collisions and change direction of the ball accordingly.

 * A class for the paddle. One of the methods in the class will be a method for registering key input
from the player and making the paddle move accordingly.

 * A class for block. This class will be for the "blocks" which need to be destroyed. An example of a method is 
a method for registering when it is destroyed and respond to the event, like spawn a power up.

 * I think would need a class for setting up the scene depending on the level. A method in the class would be
a method for detecting whether the current level has been cleared and loading the next one.

