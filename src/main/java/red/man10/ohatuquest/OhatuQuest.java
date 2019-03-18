package red.man10.ohatuquest;

import org.bukkit.plugin.java.JavaPlugin;
import red.man10.mquest.MQuest;

public final class OhatuQuest extends JavaPlugin {

    MQuest quest;

    WikiView wiki;
    ShigenJoin shigen;
    CasinoJoin casino;
    FishGo fish;
    PVEGo pve;
    PVPGo pvp;
    ShopGo shop;
    EnemyKill10 enemyKill10;
    Fishing10 fish10;
    PVPkill10 kill10;

    @Override
    public void onEnable() {
        // Plugin startup logic
        quest = MQuest.getInstance();

        wiki = new WikiView(quest);
        shigen = new ShigenJoin(quest);
        casino = new CasinoJoin(quest);
        fish = new FishGo(quest);
        pve = new PVEGo(quest);
        pvp = new PVPGo(quest);
        shop = new ShopGo(quest);
        enemyKill10 = new EnemyKill10(quest);
        fish10 = new Fishing10(quest);
        kill10 = new PVPkill10(quest);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
