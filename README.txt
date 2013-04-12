Delete the 'NEW*' lines after you read them to keep the the file clean.  
Replace them with your own response if you want.
I'm going to use 'NEW*' sort of like a /*comment*/
If I put a '*' at the beginning of a line it means it's new so look at it and delete the line/star.


--==TODO LIST!//BUG LIST!==-- [mark with an 'X' if completed]

-=PRIORITY #1 [DO IMMEDIATELY]=-
										

-=Priority #2 [Do soon!]=-

- Make the 'numOfFollowers' in the 'ballfollowers' class defined by parameter, or other way.  [Explained further in notes]
- edit the difficulties since they're too hard with the fixed paddle bounce 
		(Or even better, make the difficulties dependent on the dX and dY)

-=Priority #3 [Do whenever]=-
 
- Possibly create different game modes? 




--==IDEAS!==-- [mark with an 'X' if completed]
	[Let's rate these ideas on a 1-5 scale and figure out if we like them.  Then based on that we'll figure out which ones to do]

- Different "game modes". Things like First to five, or one life(Rob)
	If you put this in the GUI and make it call a blank method I'll implement when I can. (Tim)

	^RATINGS: Rob - 5/5
			 Tim - 5/5

- Increase in difficulty when no one has scored for large periods of time (server side mostly) (Rob)
	Counter Proposal-- We have a difficulty setting that get's increasingly harder as more points are scored 
		and then we fully implement the ball speed increasing as the rounds persists.(Tim)
		
	^RATINGS: Rob - 4/5
			 Tim - 4/5


- Possibly add 4 player support? and if only 3 players then maybe substitute 1 with AI?(Rob)
	That would be hard but do-able (Tim)
	
	^RATINGS: Rob - 2/5
			 Tim - 2/5

- I'd like to maybe see if I could make the AI behave like a proper AI? I mean more advanced movements (Rob)
	I have an idea on how to do this.  It might be a little tricky but I think I can do it. (Tim)
	
	^RATINGS: Rob - 4/5
			 Tim - 3.5/5

- Creating awesome intro screen. Most likely like an image or something fading in at the start (Rob)

	^RATINGS: Rob - 5/5 
			 Tim - 4/5

- I would love to add power-ups and such that randomly appear on the board.  I don't think it'd be too hard to implement.
	But the paddle class would need to be completed first!  And probably a powerup class.(Tim)
	
	^RATINGS: Rob - 4/5
			 Tim - 4/5

*DENIED! - Just throwing it out there, but make the game 3D.  Just an idea.(Tim)

	^RATINGS: Rob - 1/5
			 Tim - 2/5

- Creating multiple ball modes? I've already made a class called BallFollower that is basically
	just an array that holds all of the followers, should be possible to create more than one ball from that
	I can give it a go (Rob)

	^RATINGS: Rob - 2/5
			Tim - ?/5  *NEW[I'm not exactly sure what you mean?  But if you want to try something, go ahead!]



--==NOTES!==-- [Delete my notes and replace with your's when you update]

- Tim

Mar, 12, 2013

Fixed all known bugs with the AI.  Added the difficulties to the GUI.

