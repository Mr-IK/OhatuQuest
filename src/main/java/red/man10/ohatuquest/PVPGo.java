package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class PVPGo {
    MQuest quest;
    int id;

    public PVPGo(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("PVPGo Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("初めてPVPに行く","/warp pvpでPVPに行ってみましょう！","","FirstGo_PVP",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l初めてPVPに行くクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("PVPGo Quest already Created ID: "+quest.qm.getQuestID("初めてPVPに行く"));
                id = quest.qm.getQuestID("初めてPVPに行く");
            }else{
                mQuest.getLogger().info("PVPGo Quest Created! ID: "+i);
                id = i;
            }
        });
    }
}
