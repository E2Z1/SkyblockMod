/*
 * SkyclientCosmetics - Cool cosmetics for a mod installer Skyclient!
 * Copyright (C) koxx12-dev [2021 - 2021]
 *
 * This program comes with ABSOLUTELY NO WARRANTY
 * This is free software, and you are welcome to redistribute it
 * under the certain conditions that can be found here
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 *
 * If you have any questions or concerns, please create
 * an issue on the github page that can be found under this url
 * https://github.com/koxx12-dev/Skyclient-Cosmetics
 *
 * If you have a private concern, please contact me on
 * Discord: Koxx12#8061
 */

package ml.e2z1.skyblockmod.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;


import java.io.File;


public class Settings extends Vigilant {
    @Property(type = PropertyType.SWITCH, name = "Main", description = "Enable AutoAccept", category = "AutoAccept")
    public static boolean autoAccept = true;
    @Property(type = PropertyType.TEXT, name = "Whose partys to accept", description = "Seperate names by a space", category = "AutoAccept")
    public static String acceptPlayers = "Hypixel Technoblade";

    @Property(type = PropertyType.SWITCH, name = "Play Skyblock on join", description = "Immediatly joins Skyblock on join", category = "Main", subcategory = "Skyblock")
    public static boolean skyblockOnJoin = true;
    @Property(type = PropertyType.SWITCH, name = "Bit Profit", description = "Calculates Bit Profit", category = "Main")
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
