# Breakout Design
## Luka Mdivani


## Design Goals
My design goal in this project was to make a breakout game variant
using JavaFx. This was my first software engineering project using java.
So my goal was to execute the features discussed in the planning section.This
included:
1. A paddle which deflected ball in different ways depending on where it hit.
2. Ball which interacted with block and walls, deflected from both and destroyed the blocks.
3. 3 different levels with different designs and different block combinations, like
regular blocks, powerUp-dropping balls, permanent blocks, and block with more HP.
4. Game mechanics to handle,win,loss and level transitions. As well as some Cheat keys.

## High-Level Design
There were 6 classes in my program which interacted with each other. As I learned from the lecture on 01/18,
some classes(PowerUp, could have made a Class for Bricks ) could have been made more abstract and simplified further to make the game easier to
build upon and extend.

The classes and their purposes were:

Main(): This class handled the Stage,scene and root setup, the animation timeline and had the step funtion.
Since I decided it was fit to leave the step() function in the main class, because it was the 
main part of the game, I also handled and checked game winning/losing/level and menu transitions here.

LevelSetup(): This Class is called in the main function, and it handles tasks concerning bricks and their proper loading into the level, as well
as creating the boundary walls which are an integral part of each level. It has methods for reading the .txt files for levels and extracting necessary information for them. It also has a method 
which checks the block destruction conditions, as well as powerUp spawning condition when a relevant block is destroyed.
Now that I look back at my work I should have made a separate class for the blocks, this would have also made addition of new types of block easier.

Paddle(): This class handles the creation of the paddle, it also has the methods for moving the ball
according to player input, seamless travel between borders. It also has all the methods to apply cheatkey/powerup
effects on the paddle.

Wall(): Class which creates a class for the boundary walls, has methods for their creation.

Ball(): Class which creates the ball, it has methods for moving the ball, handling ball deflection
events, as well as methods for applying cheats/powerUps on the ball.

PowerUp(): This class is extension of the ball class, since the basic animation movement, and paddle interactions
of the powerup, as well as the shape of the balls was the same as the ball I decided to extend the Ball class.
I also added methods for selecting a powerUp type randomly,and handle powerUp effect applications in game.
After the tuesday lecture I realized I should have made the class more abstract and added separate subclasses for different
powerUp types, this would have made it easier to extend the game.

## Assumptions or Simplifications
I did not make any simplifications of the plan, although I do assume that there are only 
pre-planned powerUps and block types in the game. After the tuesday lecture I think I should 
have added more subclasses for powerUp and block types, this would have made the code easier to build upon.





## Changes from the Plan
One change I made was that I didn't create a separate class for the bricks, 
especially after the Tuesday class I realize that this was a mistake, and I should have stuck 
to my original approach which would have made things easier and the code would have better quality.I did not
get enough time to execute these changes.

## How to Add New Levels

Adding new levels is very easy if you use the 4 types of blocks implemented in the code.
you only have to make the new map in a txt file, according to the notations defined in PLAN. 
And the whole process, block size selection, info extraction, drawing the map, is automated using methods. 

If you need to add new types of blocks or powerUps, it would be harder. Since I have not made
abstract superclasses for these objects, and the exsiting types are hard-coded into the game using a 
bunch of conditional statements. But this will be easy to fix after the knowledge I gathered the past week.

