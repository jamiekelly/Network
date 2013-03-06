Delete the 'NEW*' lines after you read them to keep the the file clean.  
Replace them with your own response if you want.
I'm going to use 'NEW*' sort of like a /*comment*/
If I put a '*' at the beginning of a line it means it's new so look at it and delete the line/star.


--==TODO LIST!//BUG LIST!==-- [mark with an 'X' if completed]

-=PRIORITY #1 [DO IMMEDIATELY]=-

X- Add Dynamic GUI. By this I mean GUI that is possible to be added anywhere with one, or two lines of code and no hassle
- Add 2 player support within an offline game (non server) NOTE* Do once GUI is finished												
X- Fix the bounce off the paddle [I seriously cannot figure out the math for this]

-=Priority #2 [Do soon!]=-

- Fix hit detection, for paddle and ceiling.
- Have the game pause for a second after every score.
- Make the 'numOfFollowers' in the 'ballfollowers' class defined by parameter, or other way.  [Explained further in notes]
- Better edge detection for the paddle.  It looks like the edge of the paddle will hit the ball and then it doesn't 
X- Draw font so it's possible to write things on screen (most likely draw with a stylesheet and then load from that
- edit the difficulties since they're too hard with the fixed paddle bounce 
		(Or even better, make the difficulties dependent on the dX and dY)
- Do something about the ball hitting the top or bottom of the paddle 
		and have a statement for if the ball is stuck in the paddle
		
X- Fix so that the ball does shoot off to the left and score a point for player 2 immediately on start.

-=Priority #3 [Do whenever]=-

X- Create graphics for the game (paddles, ball etc)
X- Create multiple "States" e.g. When one player wins, when other loses etc. Possibly create different game modes? 




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

- Rob

Okay, so the whole weekend I haven't been updating the Readme and I should have! I'm silly for
doing so. Anyway. As you can see there is a lot of new changes, I pretty much moved a lot of the
code about to make it more organised and easier to access for later use. 

I also added 2 forms of GUI, buttons and text, the text is a little messed up but that's because
of the scaling as the original images are only 8x8 pixels, I'm going to probably try and load them into
32x32 or something along those lines as 8x8 is just too small of a font and if we try to
make the text larger then it doesn't look good at all with the text. Resizing incorrectly is a problem.

I've made it so that the buttons will not be rendered or updated if they are not in their appropriate state.
This will how ever not work if you aren't naming the states of the buttons upon creating them
If you define the state of the gui object when you create it the game won't have any problems and will just 
draw it/update it when actually appropriate. Also try to setUp all the objects in the setUp methods of each state
class as it's only being run once to avoid getting massive errors and the code being multiplied over and over again.

All right, so there have been a few or many bug fixes actually and I will try to work on some
of the bugs stated above. Thanks!

