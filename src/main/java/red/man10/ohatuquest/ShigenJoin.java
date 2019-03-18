package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class ShigenJoin implements Listener {
    MQuest quest;
    int id;

    public ShigenJoin(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("ShigenJoin Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("初めて資源に入る","/warp shigenで資源に行ってみましょう！","","FirstJoin_Shigen",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l初めて資源に入るクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("ShigenJoin Quest already Created ID: "+quest.qm.getQuestID("初めて資源に入る"));
                id = quest.qm.getQuestID("初めて資源に入る");
            }else{
                mQuest.getLogger().info("ShigenJoin Quest Created! ID: "+i);
                id = i;
            }
        });
    }

}
