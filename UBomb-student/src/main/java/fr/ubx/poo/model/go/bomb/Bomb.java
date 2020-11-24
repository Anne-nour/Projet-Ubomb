package fr.ubx.poo.model.go.bomb;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.go.GameObject;

public class Bomb extends GameObject implements Movable {

    Direction direction;
    private boolean moveRequested = false;

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

    @Override
    public boolean canMove(Direction direction) {
        return false;
    }

    @Override
    public void doMove(Direction direction) {
        // TODO Auto-generated method stub

    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }
}
