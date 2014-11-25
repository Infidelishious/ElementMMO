package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CurrentPlayer extends Player{

	int money, moveDirection = NOT_MOVING;

	int usingCount;
	boolean blocked = false;
	// should only enter store
	// from the edge!!!
	boolean shouldEnterStore;
	ArrayList<Boolean> owned;
	ArrayList<Boolean> using;
	float dist = 0;
	int currX, currY;
	
	public CurrentPlayer()
	{
		owned = new ArrayList<Boolean>();
		using = new ArrayList<Boolean>();
		money = 200;
		for(int i = 0; i < 12; i++)
		{
			owned.add(false);
			using.add(false);
		}
		owned.set(0,true);
		using.set(0,true);
		owned.set(1,true);
		using.set(1,true);
		owned.set(2,true);
		using.set(2,true);
		
		currX = (int) x;
		currY = (int) y;
		Game.getInstance().dX = currX;
		Game.getInstance().dY = currY;
	}
	
	public CurrentPlayer(int type, String name)
	{
		this();
		this.type = type;
		this.name = name;
		
	}
	
	public CurrentPlayer(int type, String name, boolean team1)
	{
		this(type, name);
		this.team1 = team1;
		sendToSpawn();
	}
	
	public void sendToSpawn() {
		if(team1)
		{
			x = Game.WIDTH/2;
			y = 5;
		}
		else
		{
			x = Game.WIDTH/2;
			y = Game.HEIGHT - 5;
		}
		
	}

	protected void moveNudge()
	{
		if(moveDirection == UP)
			y = dist + currY;
		if(moveDirection == DOWN)
			y = currY - dist;
		if(moveDirection == RIGHT)
			x = currX + dist;
		if(moveDirection == LEFT)
			x = currX - dist;
		dist += SPEED;
	}
	
	public void move(int direction){
		if(moving) return;
		currX = (int) x;
		currY = (int) y;
		dist = SPEED;
		blocked = false;
		moveDirection = direction;
		// standing in the store!!
		if(Game.getInstance().grid[currX][currY].type != Cell.STORE)
			shouldEnterStore = true;
		
		if(direction == UP)
		{
			if(Game.getInstance().grid[currX][currY+1].type == Cell.STORE)
			{
				if(shouldEnterStore)
				{
					Game.getInstance().store = new Store(-Store.storeWidth/2,-Store.storeHeight/2);
					shouldEnterStore = false;
				}
			}
			else // not standing in store anymore, kill it
			{
				if(Game.getInstance().store != null)
					Game.getInstance().store.dispose();
			}
			if(Game.getInstance().grid[currX][currY+1].type == Cell.TREE ||
					Game.getInstance().grid[currX][currY+1].type == Cell.BUSH)
			{
				// System.out.println("tree!");
				blocked = true;
				py--;
			}
			if(team1)
			{
				if(currY + 1 >= 3 * Game.getInstance().HEIGHT / 4)
				{
					blocked = true;
					py--;
				}
				
			}
			
		}
		else if(direction == DOWN)
		{
			if(Game.getInstance().grid[currX][currY-1].type == Cell.STORE)
			{
				if(shouldEnterStore)
				{
					Game.getInstance().store = new Store(-Store.storeWidth/2,-Store.storeHeight/2);
					shouldEnterStore = false;
				}
			}
			else // not standing in store anymore, kill it
			{
				if(Game.getInstance().store != null)
					Game.getInstance().store.dispose();
			}
			if(Game.getInstance().grid[currX][currY-1].type == Cell.TREE ||
					Game.getInstance().grid[currX][currY-1].type == Cell.BUSH)
			{
				// System.out.println("tree!");
				blocked = true;
				py++;
			}
			if(!team1) // so you are team2 lol
			{
				if(currY - 1 <= Game.getInstance().HEIGHT / 4)
				{
					blocked = true;
					py++;
				}
			}
			
		}
		else if(direction == LEFT)
		{
			if(Game.getInstance().grid[currX-1][currY].type == Cell.STORE)
			{
				if(shouldEnterStore)
				{
					Game.getInstance().store = new Store(-Store.storeWidth/2,-Store.storeHeight/2);
					shouldEnterStore = false;
				}
			}
			else // not standing in store anymore, kill it
			{
				if(Game.getInstance().store != null)
					Game.getInstance().store.dispose();
			}
			if(Game.getInstance().grid[currX-1][currY].type == Cell.TREE ||
					Game.getInstance().grid[currX - 1][currY].type == Cell.BUSH)
			{
				// System.out.println("tree!");
				blocked = true;
				px++;
			}
			

		}
		else if(direction == RIGHT)
		{
			if(Game.getInstance().grid[currX+1][currY].type == Cell.STORE)
			{
				if(shouldEnterStore)
				{
					Game.getInstance().store = new Store(-Store.storeWidth/2,-Store.storeHeight/2);
					shouldEnterStore = false;
				}
			}
			else // not standing in store anymore, kill it
			{
				if(Game.getInstance().store != null)
					Game.getInstance().store.dispose();
			}
			if(Game.getInstance().grid[currX+1][currY].type == Cell.TREE ||
					Game.getInstance().grid[currX+1][currY].type == Cell.BUSH)
			{
				// System.out.println("tree!");
				blocked = true;
				px--;
			}

		}
		
		if(blocked)
		{
			dist = 0;
			// this lets us wiggle against the wall
			// otherwise we just don't move :(
		}
		if(!blocked)
		{
			moving = true;
//			System.out.println("move");
		}
		
		moveNudge();
		
	}
	

	@Override
	public void draw(SpriteBatch sb) {
		updateSprite();
		for(int i = 0; i < Game.getInstance().otherPlayers.size(); i++)
		{
			if(Game.getInstance().otherPlayers.get(i).x == this.x &&
					Game.getInstance().otherPlayers.get(i).y == this.y &&
					Game.getInstance().otherPlayers.get(i).team1 != this.team1) // only fight the bad guys now
			{
				if(Game.getInstance().battle == null)
				{
					Game.getInstance().battle = new Battle(this, Game.getInstance().otherPlayers.get(i));
					// start the other player
					EventMessage message = new EventMessage();
					message.from = Game.getInstance().player.name;
					message.to = Game.getInstance().otherPlayers.get(i).name;
					message.event = "StartBattle";
					System.out.println("sentMessage in currentPlayer for battle Entering");
					System.out.println("sm.to = " + message.to);
					System.out.println("sm.from = " + message.from);
					System.out.println("sm.event = " + message.event);
					MessageManager.getInstance().sendMessageToServer(message);
				}
			}
		}
		
		// this makes sure that if your opponent has started a fight,
		
		// you start a fight
		EventMessage receivedMessage = MessageManager.getInstance().getEventMessage();
		if(receivedMessage != null)
		{
			System.out.println("receivedMessage in currentPlayer for battle Entering");
			System.out.println("rm.to = " + receivedMessage.to);
			System.out.println("rm.from = " + receivedMessage.from);
			System.out.println("rm.event = " + receivedMessage.event);
			if(receivedMessage.to.equals(Game.getInstance().player.name) )
			{
				// he's called you out boyo!!
				// figure out which other
				for(int i = 0; i < Game.getInstance().otherPlayers.size(); i++)
				{
					if(Game.getInstance().otherPlayers.get(i).name.equals(receivedMessage.from))
					{
						
						// we found the mofo
						if(Game.getInstance().battle == null)
						{
							Game.getInstance().battle = new Battle(this, Game.getInstance().otherPlayers.get(i));
						}
					}
				}
			}
					
		}
		
		if(!blocked)
		{
			if(moving)
			{
				moveNudge();
				if(dist >= 1.0f)
				{
					moving = false;
					dist = 0;
					y = currY;
					x = currX;

					if(moveDirection == UP)
						y++;
					if(moveDirection == DOWN)
						y--;
					if(moveDirection == RIGHT)
						x++;
					if(moveDirection == LEFT)
						x--;
				}
			}
		}
		
		Game.getInstance().dX = x;
		Game.getInstance().dY = y;
		
		if(team1)
			sb.setColor(0.5f,0.5f,1f,1f);
		else
			sb.setColor(1f,0.5f,0.5f,1f);

		sb.draw(spr, - WIDTH / 2.0f, - HEIGHT / 2.0f, WIDTH, HEIGHT);

		sb.setColor(Color.WHITE);

		sendPosToServer();
	}

	private void sendPosToServer() {
		MovmentMessage mm = new MovmentMessage();
		mm.direction = moveDirection;
		mm.x = x;
		mm.y = y;
		mm.moving = moving;
		mm.from = name;
		mm.type = type;
		mm.team1 = team1;
		MessageManager.getInstance().sendMessageToServer(mm);
		
	}
}
