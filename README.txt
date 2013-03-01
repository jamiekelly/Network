Delete the 'NEW*' lines after you read them to keep the the file clean.  
Replace them with your own response if you want.
I'm going to use 'NEW*' sort of like a /*comment*/
If I put a '*' at the beginning of a line it means it's new so look at it and delete the line/star.


--==TODO LIST!//BUG LIST!==-- [mark with an 'X' if completed]

-=PRIORITY #1 [DO IMMEDIATELY]=-

- Add Dynamic GUI. By this I mean GUI that is possible to be added anywhere with one, or two lines of code and no hassle
- Finish & implement the paddle class. - I'm on it... Rob 
										*NEW [I'll do the paddle class first thing this weekend, YOU FOCUS ON THE GUI! 
												Seriously I want to do this part Lol :p (Tim)]
												
X- Fix the bounce off the paddle [I seriously cannot figure out the math for this]

-=Priority #2 [Do soon!]=-

*- Make the 'numOfFollowers' in the 'ballfollowers' class defined by parameter, or other way.  [Explained further in notes]
*- Better edge detection for the paddle.  It looks like the edge of the paddle will hit the ball and then it doesn't 
- Add 2 player support within an offline game (non server) NOTE* Do once GUI is finished
- Draw font so it's possible to write things on screen (most likely draw with a stylesheet and then load from that
- edit the difficulties since they're too hard with the fixed paddle bounce 
		(Or even better, make the difficulties dependent on the dX and dY)
- Do something about the ball hitting the top or bottom of the paddle 
		and have a statement for if the ball is stuck in the paddle
		
X- Fix so that the ball does shoot off to the left and score a point for player 2 immediately on start.

-=Priority #3 [Do whenever]=-

- Create graphics for the game (paddles, ball etc)
- Create multiple "States" e.g. When one player wins, when other loses etc. Possibly create different game modes? 




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

Thursday 28th. My Time

Great job with the balltrailing!  Beautiful implementation of it, I never would have thought to do it that way!
I edited the class just to put all the variables at the top and encapsulated them.  (Meaning I just set them to private)

I also changed it so that the 'accept' thread doesn't get called for single player 
and just made directly called the 'onUpdate' thread.  It was pointless the other way.

I was pretty busy with homework today.  So I couldn't get too much done.  

I might take a break tomorrow and just relax and play games since I've been working on this every night this week, 
and then this weekend I'll work on this the whole time!  Except I have a couple things to do though, they shouldn't take too long.

Side-note: Don't touch my paddle class, I've been planning on doing it all week :p 
	Since I enjoyed the ball class so much I want to do the paddle class too :p
	
Ohh and I would change the ballfollower class so that when you construct it you can declare (as a parameter)
	how many balls will follow.  That will make for quick adjustments in the future, and then we can make it vary in the code.
	Instead of it always being stuck at five. I would have changed it but I was too busy.

I just realized this but did ballfollowers really need to be a superClass?  
	I don't really know anything about superClasses so I'm curious.