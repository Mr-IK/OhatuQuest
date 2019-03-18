package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class PVEGo {
    MQuest quest;
    int id;

    public PVEGo(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("PVEGo Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("初めてPVEに行く","/warp pveでPVEに行ってみましょう！","","FirstGo_PVE",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l初めてPVEに行くクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("PVEGo Quest already Created ID: "+quest.qm.getQuestID("初めてPVEに行く"));
                id = quest.qm.getQuestID("初めてPVEに行く");
            }else{
                mQuest.getLogger().info("PVEGo Quest Created! ID: "+i);
                id = i;
            }
        });
    }
}
