package ml.e2z1.skyblockmod.commands;


import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.SubCommand;
import ml.e2z1.skyblockmod.SkyBlockMod;

import java.util.HashSet;
import java.util.Set;

public class EsbCommand extends Command {
    private final Set<Alias> hashSet = new HashSet<>();


    public EsbCommand() {
        super("esb");
        hashSet.add(new Alias("e2z1skyblock"));
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(SkyBlockMod.instance.getConfig().gui());
    }

    @Override
    public Set<Alias> getCommandAliases() {
        return hashSet;
    }




}
