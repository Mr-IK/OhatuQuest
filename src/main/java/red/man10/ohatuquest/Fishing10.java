package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

import java.util.HashMap;
import java.util.UUID;

public class Fishing10 implements Listener {

    MQuest quest;
    int id;

    HashMap<UUID,Integer> killcount;

    public Fishing10(MQuest mQuest){
        killcount = new HashMap<>();
        quest = mQuest;
        mQuest.getLogger().info("Fishing10 Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("釣りで10匹釣る","釣りで10匹釣ってみましょう！","","Fishing10",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l釣りで10匹釣るクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("Fishing10 Quest already Created ID: "+quest.qm.getQuestID("釣りで10匹釣る"));
                id = quest.qm.getQuestID("釣りで10匹釣る");
            }else{
                mQuest.getLogger().info("Fishing10 Quest Created! ID: "+i);
                id = i;
            }
        });
        quest.getServer().getPluginManager().registerEvents(this,quest);
    }

    @EventHandler
    public void enemyKill(PlayerFishEvent e){
        Player kill = e.getPlayer();

        if (killcount.containsKey(kill.getUniqueId())) {
            int i = killcount.get(kill.getUniqueId());
            if (i == 10) {
                return;
            }
            i = i + 1;
            if (i == 10) {
                Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
                    quest.fm.upFlag("Fishing10", kill);
                    kill.sendMessage(quest.prefix + "§a釣りで10匹釣る 10匹目達成！ /mquestから報酬をGETしよう！");
                });
            } else {
                kill.sendMessage(quest.prefix + "§a釣りで10匹釣る " + i + "匹目！");
            }
        }else{
            int i = 1;
            if(quest.fm.containFlag("Fishing10",kill.getUniqueId())){
                i = 10;
            }
            killcount.put(kill.getUniqueId(),i);
            if(i != 10){
                kill.sendMessage(quest.prefix + "§a釣りで10匹釣る " + i + "匹目！");
            }
        }
    }
}
