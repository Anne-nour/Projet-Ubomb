/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.Decor;

import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

public class World {
    private final Map<Position, Decor> grid;
    private final WorldEntity[][] raw;
    public final Dimension dimension;

    public World(WorldEntity[][] raw) {
        this.raw = raw;
        dimension = new Dimension(raw.length, raw[0].length);
        grid = WorldBuilder.build(raw, dimension);
    }

    public Position findPlayer() throws PositionNotFoundException {
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Player) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("Player");
    }

    public Position findPosition( Decor decor){
        for (Position position : grid.keySet() ){
            if ( grid.get(position)  == decor ){
                return position;
            }
        }
        return null;
    }

    public Decor get(Position position) {
        for (Position position1 : grid.keySet() ){
            if ( (position1.x == position.x) && (position1.y == position.y)){
                return grid.get(position1);
            }
        }
        return null;

    }

    public void set(Position position, Decor decor) {
        grid.put(position, decor);
    }

    public void clear(Position position) {
        grid.remove(position);
    }

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        return true; // to update
    }

    public boolean isEmpty(Position position) {
        return grid.get(position) == null;
    }

    public String toString() {
        String s = new String();
        Iterator<Map.Entry<Position, Decor>> iterator = grid.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Position, Decor> entry = iterator.next();
            s += entry.getKey().toString() + " : " + entry.getValue().toString() + " | ";
        }
        return s;
    }
}
