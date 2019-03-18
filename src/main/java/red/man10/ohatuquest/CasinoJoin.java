package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class CasinoJoin implements Listener {
    MQuest quest;
    int id;

    public CasinoJoin(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("CasinoJoin Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("初めてカジノに入る","/warp casinoでカジノに行ってみましょう！","","FirstJoin_Casino",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l初めてカジノに入るクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("CasinoJoin Quest already Created ID: "+quest.qm.getQuestID("初めてカジノに入る"));
                id = quest.qm.getQuestID("初めてカジノに入る");
            }else{
                mQuest.getLogger().info("CasinoJoin Quest Created! ID: "+i);
                id = i;
            }
        });
    }
}
