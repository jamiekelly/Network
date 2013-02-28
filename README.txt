Delete the 'NEW*' lines after you read them to keep the the file clean.  
Replace them with your own response if you want.
I'm going to use 'NEW*' sort of like a /*comment*/
If I put a '*' at the beginning of a line it means it's new so look at it and delete the line.


--==TODO LIST!//BUG LIST!==-- [mark with an 'X' if completed]

-=PRIORITY #1 [DO IMMEDIATELY]=-

*X- Fix the bounce off the paddle [I seriously cannot figure out the math for this]
- Add Dynamic GUI. By this I mean GUI that is possible to be added anywhere with one, or two lines of code and no hassle
*- Finish & implement the paddle class.

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
	NEW* If you put this in the GUI and make it call a blank method I'll implement when I can. (Tim)
	
	^RATINGS: Rob - (?)/5
			 Tim - 5/5

-Increase in difficulty when no one has scored for large periods of time (server side mostly) (Rob)
	NEW* Counter Proposal-- We have a difficulty setting that get's increasingly harder as more points are scored 
		and then we fully implement the ball speed increasing as the rounds persists.(Tim)
		
	^RATINGS: Rob - (?)/5
			 Tim - 4/5


-Possibly add 4 player support? and if only 3 players then maybe substitute 1 with AI?(Rob)
	NEW* That would be hard but do-able (Tim)
	
	^RATINGS: Rob - (?)/5
			 Tim - 2/5

-I'd like to maybe see if I could make the AI behave like a proper AI? I mean more advanced movements (Rob)
	New* I have an idea on how to do this.  It might be a little tricky but I think I can do it. (Tim)
	
	^RATINGS: Rob - (?)/5
			 Tim - 3.5/5

-Creating awesome intro screen. Most likely like an image or something fading in at the start (Rob)
	NEW* You can do that part! (Tim)
	
	^RATINGS: Rob - (?)/5
			 Tim - 4/5

*- I would love to add power-ups and such that randomly appear on the board.  I don't think it'd be too hard to implement.
	But the paddle class would need to be completed first!  And probably a powerup class.(Tim)
	
	^RATINGS: Rob - (?)/5
			 Tim - 4/5

*- Just throwing it out there, but make the game 3D.  Just an idea.(Tim)

	^RATINGS: Rob - (?)/5
			 Tim - 2/5




--==NOTES!==-- [Delete my notes and replace with your's when you update]

- Tim

Wed. 27th

I was under the impression that (Thread).destroy(); and (Thread).stop(); were problematic 
	and caused systems problems (depreciated).  That's why I didn't use it.

Line 566:
	bX & bY are now getter methods and the center of the ball is now defined in the ball class.
	if you want it call (object name).getCenterOfBall();

The setter of dX and dY when the ball hits a paddle is fixed but will need more adjusting to make the settings perfect.

The .md and .txt file are the same with GitHub, it reads them both the exact same way!
