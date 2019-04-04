package repo;

import interfaces.IGamer;
import models.Gamer;

import java.util.Map;


public abstract class GamerRepo implements IGamer {

    @Override
    public Gamer getGamerByName(Map<String, Gamer> gamers, String name) {
        return gamers.get(name);
    }
}
