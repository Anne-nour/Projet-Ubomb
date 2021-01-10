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
import fr.ubx.poo.model.decor.Bonus;
import fr.ubx.poo.model.decor.malus.BombRangeDec;
import fr.ubx.poo.model.decor.Princess;

public class Player extends GameObject implements Movable {

    private boolean alive = true;
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

    public void setLives() {
        this.lives -= 1;
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
        if (decor != null)
            System.out.println(decor.getClass());
        Decor decor_next = game.getWorld().get(direction.nextPosition(nextPos));

        if (!nextPos.inside(this.game.getWorld().dimension)) {

            return false;
        }

        if ((decor instanceof Stone) || (decor instanceof Tree)) {

            return false;
        }

        if (decor instanceof Monster) {
            this.lives -= 1;
        }

        if (decor instanceof Heart) {
            this.lives += 1;

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (game.getWorld().get(nextPos) instanceof Princess) {
            this.winner = true;
        }

        if (decor instanceof BombRangeInc) {
            this.rangeValue += 1;

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (decor instanceof BombRangeDec) {
            if (this.rangeValue != 1) {
                this.rangeValue -= 1;
            }

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (decor instanceof BombNumberInc) {
            this.bombsValue += 1;

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (decor instanceof BombNumberDec) {
            if (this.bombsValue != 0) {
                this.bombsValue -= 1;
            }

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (decor instanceof Key) {
            this.keyValue += 1;

            Position posBonus = game.getWorld().findPosition(decor);
            game.getWorld().clear(posBonus);
            game.getWorld().setChanged();
        }

        if (this.lives == 0) {
            this.alive = false;
        }

        if (decor instanceof Box){
            
            if ( decor_next instanceof Stone ||  decor_next instanceof Tree ||  decor_next instanceof Box ||  decor_next instanceof Bonus
                    ||  decor_next instanceof Monster ||  ! direction.nextPosition(nextPos).inside( this.game.getWorld().dimension )  ) {
                return  false;
            }

            Position positionBox = game.getWorld().findPosition(decor);
            direction.setNextPosition( positionBox) ;
            game.getWorld().set(positionBox ,decor);
            return true;
        }
        
        return true;
    }


    @Override
    public void doMove(Direction direction) {
        direction.setNextPosition(getPosition());
        /*setPosition(nextPos);*/
    }
    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public void plantBomb() {
        /* Il faut créer une bombe ici */
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        return alive;
    }

	

}
