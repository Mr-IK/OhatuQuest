package red.man10.ohatuquest;

import org.bukkit.plugin.java.JavaPlugin;
import red.man10.mquest.MQuest;

public final class OhatuQuest extends JavaPlugin {

    MQuest quest;

    SayOhaman ohaman;
    WikiView wiki;

    @Override
    public void onEnable() {
        // Plugin startup logic
        quest = MQuest.getInstance();

        ohaman = new SayOhaman(quest);
        wiki = new WikiView(quest);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
