/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;



import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.decor.Monster;
import fr.ubx.poo.model.decor.Princess;



public class Player extends GameObject implements Movable {

    private final boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives = 1;
    private boolean winner;



    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives =  game.getInitPlayerLives();
    }

    public int getLives() {
        return lives;
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
        if ( !nextPos.inside( this.game.getWorld().dimension ) ){
            return false;
        }


        if ( (game.getWorld().get(nextPos) instanceof Stone ) || (game.getWorld().get(nextPos) instanceof Tree )){
            return false;
        }

        if ( game.getWorld().get(nextPos) instanceof Monster){
            this.lives-=1;
        }

        /*if ( game.getWorld().get(nextPos) instanceof Princess){
            return ;
        }*/


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
