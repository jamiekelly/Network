Delete the 'NEW*' lines after you read them to keep the the file clean.  
Replace them with your own response if you want.
I'm going to use 'NEW*' sort of like a /*comment*/
If I put a '*' at the beginning of a line it means it's new so look at it and delete the line.


--==TODO LIST!//BUG LIST!==-- [mark with an 'X' if completed]

<h1>-=PRIORITY #1 [DO IMMEDIATELY]=-</h1>

X- Fix the bounce off the paddle [I seriously cannot figure out the math for this]
- Add Dynamic GUI. By this I mean GUI that is possible to be added anywhere with one, or two lines of code and no hassle
- Finish & implement the paddle class. - I'm on it... Rob

<h1>-=Priority #2 [Do soon!]=- </h1>

- Add 2 player support within an offline game (non server) NOTE* Do once GUI is finished
- Draw font so it's possible to write things on screen (most likely draw with a stylesheet and then load from that
*X- Fix so that the ball does shoot off to the left and score a point for player 2 immediately on start.
*- edit the difficulties since they're too hard with the fixed paddle bounce 
		(Or even better, make the difficulties dependent on the dX and dY)
*- Do something about the ball hitting the top or bottom of the paddle 
		and have a statement for if the ball is stuck in the paddle

-=Priority #3 [Do whenever]=-

- Create graphics for the game (paddles, ball etc)
- Create multiple "States" e.g. When one player wins, when other loses etc. Possibly create different game modes? 




--==IDEAS!==-- [mark with an 'X' if completed]
	[Let's rate these ideas on a 1-5 scale and figure out if we like them.  Then based on that we'll figure out which ones to do]

-Different "game modes". Things like First to five, or one life(Rob)
	If you put this in the GUI and make it call a blank method I'll implement when I can. (Tim)
	NEW* I'm working on the GUI class and will talk you through the thought process of how I plan
	on doing things with the GUI.. So far the idea is to have all GUI objects in one container
	but then add stuff to them, like either text, or just buttons or input. Depending on what
	the GUI type. Will update later on	


	^RATINGS: Rob - 5/5
			 Tim - 5/5

-Increase in difficulty when no one has scored for large periods of time (server side mostly) (Rob)
	Counter Proposal-- We have a difficulty setting that get's increasingly harder as more points are scored 
		and then we fully implement the ball speed increasing as the rounds persists.(Tim)
		
	^RATINGS: Rob - 4/5
			 Tim - 4/5


-Possibly add 4 player support? and if only 3 players then maybe substitute 1 with AI?(Rob)
	That would be hard but do-able (Tim)
	
	^RATINGS: Rob - 2/5
			 Tim - 2/5

-I'd like to maybe see if I could make the AI behave like a proper AI? I mean more advanced movements (Rob)
	I have an idea on how to do this.  It might be a little tricky but I think I can do it. (Tim)
	
	^RATINGS: Rob - 4/5
			 Tim - 3.5/5

-Creating awesome intro screen. Most likely like an image or something fading in at the start (Rob)
	You can do that part! (Tim)
	
	NEW* I've started drawing the intro logo! :D

	^RATINGS: Rob - 5/5 
			 Tim - 4/5

- I would love to add power-ups and such that randomly appear on the board.  I don't think it'd be too hard to implement.
	But the paddle class would need to be completed first!  And probably a powerup class.(Tim)
	
	^RATINGS: Rob - 4/5
			 Tim - 4/5

- Just throwing it out there, but make the game 3D.  Just an idea.(Tim)

	^RATINGS: Rob - 1/5 - I don't know, that would require on a large engine re-write
			 Tim - 2/5

*- Creating multiple ball modes? I've already made a class called BallFollower that is basically
just an array that holds all of the followers, should be possible to create more than one ball from that
I can give it a go (Rob)

	^RATINGS: Rob - 2/5
			Tim - ?/5



--==NOTES!==-- [Delete my notes and replace with your's when you update]

- Rob

Thursday 28th. Too late at night


Okay, so I messed about with some of the code and I added the "speed effect" when the ball is moving
it looks like it's moving quickly as there is a trail following it. It's quite easy to do and could
maybe be used when one of the "power ups" is used (so like everything goes in that "super speed" mode
could be cool :D

Started working on GUI, you can update other classes while I'm doing so, I will only be working with 
new classes.

Have a nice day!
