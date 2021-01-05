/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Box;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.Key;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.decor.Monster;
import fr.ubx.poo.model.decor.bonus.BombNumberInc;
import fr.ubx.poo.model.decor.bonus.BombRangeInc;
import fr.ubx.poo.model.decor.bonus.Heart;
import fr.ubx.poo.model.decor.malus.BombNumberDec;
import fr.ubx.poo.model.decor.malus.BombRangeDec;
import fr.ubx.poo.view.image.ImageFactory;
import fr.ubx.poo.view.sprite.SpriteFactory;
import fr.ubx.poo.view.sprite.Sprite;
import fr.ubx.poo.model.decor.Princess;

import static fr.ubx.poo.view.image.ImageResource.BOX;


public class Player extends GameObject implements Movable {

    private final boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives = 3;
    private int bombsValue = 3;
    private int rangeValue = 1;
    private int keyValue = 0;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
        this.rangeValue = game.getrangeValue();
        this.bombsValue = game.getbombsValue();
        this.keyValue = game.getkeyValue();
    }

    public int getLives() {
        return lives;
    }

    public int getRangeValue() {
		return rangeValue;
	}

	public int getBombsValue() {
		return bombsValue;
	}

	public int getKeyValue() {
		return keyValue;
	}
    public Direction getDirection() {
        return direction;
    }

    public void requestMove(Direction direction) {

        if (direction != this.direction) {
            this.direction = direction;
        }

        moveRequested = true;
    }

    @Override
    public boolean canMove(Direction direction) {

        Position nextPos = direction.nextPosition(getPosition());
        Decor decor = game.getWorld().get(nextPos);

        if ( !nextPos.inside( this.game.getWorld().dimension ) ){
            return false;
        }


        if ( (decor instanceof Stone ) || ( decor instanceof Tree )){
            return false;
        }

        if ( decor instanceof Monster){
            this.lives-=1;
        }

        if ( decor instanceof Heart){
            this.lives+=1;
        }

        if ( game.getWorld().get(nextPos) instanceof Princess){
            this.winner = true;
        }
        
        if(decor instanceof BombRangeInc){
            this.rangeValue +=1;
        }

        if (decor instanceof BombRangeDec){
            this.rangeValue -=1;
        }

        if (decor instanceof BombNumberInc){
            this.bombsValue +=1;
        }

        if (decor instanceof BombNumberDec){
            this.bombsValue -=1;
        }

        if (decor instanceof Key){
            this.keyValue +=1;
        }

        if (decor instanceof Box){
            System.out.println(game.getWorld());
            game.getWorld().clear(nextPos);

            game.getWorld().set(direction.nextPosition(nextPos), decor);
            System.out.println("===================");
            System.out.println(game.getWorld());
            return true;
        }

        return true;
    }

    @Override
    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        return alive;
    }

	

}
