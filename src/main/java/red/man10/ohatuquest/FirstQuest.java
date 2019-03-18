package red.man10.ohatuquest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import red.man10.mquest.MQuest;

public class FirstQuest {
    MQuest quest;
    int id;

    public FirstQuest(MQuest mQuest){
        quest = mQuest;
        mQuest.getLogger().info("First Quest Enabled");
        Bukkit.getScheduler().runTaskAsynchronously(quest, () -> {
            quest.im.addItem("Diamond",new ItemStack(Material.DIAMOND));
            int i = quest.qm.createQuest("クエストを完了してみる","/mquest からこのクエストを完了しましょう！","","",
                    "Diamond","mquest say §e§l<player_name>さんが§a§lクエストを完了してみるを完了しました！;;mquest msg <player_name> §e報酬として10万とダイヤモンドを取得しました！",100000);
            if(i == -1){
                mQuest.getLogger().info("First Quest already Created ID: "+quest.qm.getQuestID("クエストを完了してみる"));
                id = quest.qm.getQuestID("クエストを完了してみる");
            }else{
                mQuest.getLogger().info("First Quest Created! ID: "+i);
                id = i;
            }
        });
    }
}
