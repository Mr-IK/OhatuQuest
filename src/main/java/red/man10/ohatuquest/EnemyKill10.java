package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

import java.util.HashMap;
import java.util.UUID;

public class EnemyKill10 implements Listener {

    MQuest quest;
    int id;

    HashMap<UUID,Integer> killcount;

    public EnemyKill10(MQuest mQuest){
        killcount = new HashMap<>();
        quest = mQuest;
        mQuest.getLogger().info("EnemyKill10 Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("MOBを10体倒す","MOBを10体倒してみましょう！","","EnemyKill10",
                    "Diamond","mquest say §e§l<player_name>さんが§a§lMOBを10体倒すクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("EnemyKill10 Quest already Created ID: "+quest.qm.getQuestID("MOBを10体倒す"));
                id = quest.qm.getQuestID("MOBを10体倒す");
            }else{
                mQuest.getLogger().info("EnemyKill10 Quest Created! ID: "+i);
                id = i;
            }
        });
        quest.getServer().getPluginManager().registerEvents(this,quest);
    }

    @EventHandler
    public void enemyKill(EntityDeathEvent e){
        Entity entity = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        if(killer == null){
            return;
        }

        if (killer instanceof Player) {
            //your code
            Player kill = (Player) killer;
            if (killcount.containsKey(kill.getUniqueId())) {
                int i = killcount.get(kill.getUniqueId());
                if (i == 10) {
                    return;
                }
                i = i + 1;
                if (i == 10) {
                    Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
                        quest.fm.upFlag("EnemyKill10", kill);
                        kill.sendMessage(quest.prefix + "§aMOBを倒す 10体目達成！ /mquestから報酬をGETしよう！");
                    });
                } else {
                    kill.sendMessage(quest.prefix + "§aMOBを倒す " + i + "体目！");
                }
            }else{
                int i = 1;
                if(quest.fm.containFlag("EnemyKill10",kill.getUniqueId())){
                    i = 10;
                }
                killcount.put(kill.getUniqueId(),i);
                if(i != 10){
                    kill.sendMessage(quest.prefix + "§aMOBを倒す " + i + "体目！");
                }
            }
        }
    }
}
