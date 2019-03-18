package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class ShopGo {
    MQuest quest;
    int id;

    public ShopGo(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("ShopGo Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("初めてショップに行く","/warp shopでショップに行ってみましょう！","","FirstGo_Shop",
                    "Diamond","mquest say §e§l<player_name>さんが§a§l初めてショップに行くクエストを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("ShopGo Quest already Created ID: "+quest.qm.getQuestID("初めてショップに行く"));
                id = quest.qm.getQuestID("初めてショップに行く");
            }else{
                mQuest.getLogger().info("ShopGo Quest Created! ID: "+i);
                id = i;
            }
        });
    }
}
