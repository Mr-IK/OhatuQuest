package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

import java.util.HashMap;
import java.util.UUID;

public class PVPkill10 implements Listener {

    MQuest quest;
    int id;

    HashMap<UUID,Integer> killcount;

    public PVPkill10(MQuest mQuest){
        killcount = new HashMap<>();
        quest = mQuest;
        mQuest.getLogger().info("PVPkill10 Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("PVPで10人倒す","プレイヤーを10回倒してみましょう！","","PVPkill10",
                    "Diamond","mquest say §e§l<player_name>さんが§a§lプレイヤーを10回倒すクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("PVPkill10 Quest already Created ID: "+quest.qm.getQuestID("PVPで10人倒す"));
                id = quest.qm.getQuestID("PVPで10人倒す");
            }else{
                mQuest.getLogger().info("PVPkill10 Quest Created! ID: "+i);
                id = i;
            }
        });
        quest.getServer().getPluginManager().registerEvents(this,quest);
    }

    @EventHandler
    public void enemyKill(PlayerDeathEvent e){
        Player korosita = e.getEntity().getKiller();

        if(korosita == null){
            return;
        }

        if (killcount.containsKey(korosita.getUniqueId())) {
            int i = killcount.get(korosita.getUniqueId());
            if (i == 10) {
                return;
            }
            i = i + 1;
            if (i == 10) {
                Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
                    quest.fm.upFlag("EnemyKill10", korosita);
                    korosita.sendMessage(quest.prefix + "§aプレイヤーを倒す 10回目達成！ /mquestから報酬をGETしよう！");
                });
            } else {
                korosita.sendMessage(quest.prefix + "§aプレイヤーを倒す " + i + "回目！");
            }
        }else{
            int i = 1;
            if(quest.fm.containFlag("EnemyKill10",korosita.getUniqueId())){
                i = 10;
            }
            killcount.put(korosita.getUniqueId(),i);
            if(i != 10){
                korosita.sendMessage(quest.prefix + "§aプレイヤーを倒す " + i + "回目！");
            }
        }
    }
}
