package me.hsgamer.linkingarrows;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (LinkingArrows.instance.getManager().isAvailable(event.getPlayer())) {
            LinkingArrows.instance.getManager().clear(event.getPlayer());
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Entity projectile = event.getProjectile();
        Player player = (Player) event.getEntity();
        TaskManager manager = LinkingArrows.instance.getManager();
        if (event.getBow().hasItemMeta() && event.getBow().getItemMeta().getLore().contains(ChatColor.WHITE + "Linking")) {
            if (manager.isAvailable(player) && !manager.getLastArrow(player).isDead() && projectile.getLocation().getWorld().equals(manager.getLastArrow(player).getLocation().getWorld())) {
                Entity lastProjectile = manager.getLastArrow(player);
                new ArrowTask(lastProjectile, projectile, LinkingArrows.instance.ASYNC);
            }
            manager.setLastArrow(player, projectile);
        }
    }
}
