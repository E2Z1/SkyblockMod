package ml.e2z1.skyblockmod.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;


import java.io.File;


public class Settings extends Vigilant {
    @Property(type = PropertyType.SWITCH, name = "AutoAccept", description = "Automatically accept partys", category = "AutoAccept")
    public static boolean autoAccept = true;
    @Property(type = PropertyType.SWITCH, name = "AutoWarp", description = "Warps players when they write 'warp'", category = "AutoAccept")
    public static boolean autoWarp = true;
    @Property(type = PropertyType.TEXT, name = "Whose partys to accept", description = "Seperate names by a space", category = "AutoAccept")
    public static String acceptPlayers = "Hypixel Technoblade";

    @Property(type = PropertyType.SWITCH, name = "Play Skyblock on join", description = "Immediately joins Skyblock on join", category = "Quality of Life", subcategory = "Skyblock")
    public static boolean skyblockOnJoin = true;
    @Property(type = PropertyType.SWITCH, name = "Bit Profit", description = "Calculates Bit Profit", category = "Quality of Life", subcategory = "Skyblock")
    public static boolean bitProfit = true;







    public Settings() {
        super(new File("./config/esb/esb.toml"));
        initialize();
/*        addDependency("autoAccept", "autoAccept");
        addDependency("acceptPlayers", "acceptPlayers");
        addDependency("skyblockOnJoin", "skyblockOnJoin");
        addDependency("bitProfit", "bitProfit");*/
    }
}
