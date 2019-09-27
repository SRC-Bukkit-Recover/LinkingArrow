package me.hsgamer.linkingarrows;

import org.bukkit.plugin.java.JavaPlugin;

public final class LinkingArrows extends JavaPlugin {
    public static LinkingArrows instance;
    public boolean ASYNC = true;
    public double SPACE = 0.2;
    public double DAMAGE = 1;
    private TaskManager manager;

    @Override
    public void onEnable() {
        instance = this;
        manager = new TaskManager();

        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public TaskManager getManager() {
        return manager;
    }
}
