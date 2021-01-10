package fr.ubx.poo.model.go.bomb;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.go.GameObject;

public class Bomb extends GameObject implements Movable {

    Direction direction;
    private boolean moveRequested = false;
    private boolean bombExplose = false;
    private boolean isBombDestroy = false;
    private long timer = 0;
    private long timer2 = 0;


    public Bomb(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
    }

    @Override
    public String toString() {
        return "Bomb";
    }

    public Direction getDirection(){
        return direction;
    }

    public boolean hasExplosed (){
        return bombExplose;
    }

    @Override
    public boolean canMove(Direction direction) {
        return false;
    }

    @Override
    public void doMove(Direction direction) {
        // TODO Auto-generated method stub

    }

    public void update(long now) {
        if ((bombExplose == false) && (timer == 0)) {
            timer = now;
        }
        else if (timer > timer + 4000000000L){
            /* Si le timer atteint 4 secondes, la bombe explose*/
            bombExplose = true;
        }
    }

    private void Explosion() {
        bombExplose = true;
        Direction direction [] = {Direction.W, Direction.E, Direction.N , Direction.S};
        Position nextPosition = this.getPosition();
        if (nextPosition.equals(game.getPlayer().getPosition())){
            game.getPlayer().setLives();
        }
    }



/*public void Invincible(){
    if (safe == true){
        if(timer == 60){
            safe = false;
            timer = 0;
        }
        else 
        {
            timer += 1;
        }
    }
    Cette fonction devait rendre le joueur invincible pendant quelques temps 
    */

    }
