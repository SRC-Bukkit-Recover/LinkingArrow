package me.hsgamer.linkingarrows;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private Map<Player, Entity> lastProjectileList = new HashMap<>();

    public Entity getLastArrow(Player player) {
        return lastProjectileList.get(player);
    }

    public boolean isAvailable(Player player) {
        return lastProjectileList.containsKey(player);
    }

    public void clear(Player player) {
        lastProjectileList.remove(player);
    }

    public void setLastArrow(Player player, Entity arrow) {
        lastProjectileList.put(player, arrow);
    }
}
