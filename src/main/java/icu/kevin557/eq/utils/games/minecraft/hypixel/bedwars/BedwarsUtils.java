package icu.kevin557.eq.utils.games.minecraft.hypixel.bedwars;

/**
 * @author 557
 */
public class BedwarsUtils {

    private static final int EAST_LEVELS = 4;
    private static final int[] EAST_LEVELS_XP = {500, 1000, 2000, 3500};
    private static final int EAST_LEVELS_XP_TOTAL = 7000;
    private static final int XP_PER_LEVEL = 5000;
    private static final int XP_PRE_PRESTIGE = 96 * XP_PER_LEVEL + EAST_LEVELS_XP_TOTAL;
    private static final int LEVELS_PRE_PRESTIGE = 100;

    public static int getLevelForExp(int exp) {
        int prestige = exp / XP_PRE_PRESTIGE;

        int level = prestige * LEVELS_PRE_PRESTIGE;

        int expWithoutPrestige = exp - (prestige * XP_PRE_PRESTIGE);


        for (int i = 0; i < EAST_LEVELS; i++) {
            int expForEasyLevel = getExpForLevel(i);

            if (expWithoutPrestige < expForEasyLevel) {
                break;
            }

            level++;
            expWithoutPrestige -= expForEasyLevel;
        }

        level += expWithoutPrestige / XP_PER_LEVEL;

        return level;
    }

    public static int getExpForLevel(int level) {

        int respectedLevel = getLevelRespectingPrestige(level);

        if (respectedLevel < EAST_LEVELS) {
            return EAST_LEVELS_XP[respectedLevel];
        }

        return XP_PER_LEVEL;
    }

    public static int getLevelRespectingPrestige(int level) {
        if (level > BedwarsPrestige.FIRE.getId() * LEVELS_PRE_PRESTIGE) {
            return level - BedwarsPrestige.FIRE.getId() * LEVELS_PRE_PRESTIGE;
        }
        else {
            return level % LEVELS_PRE_PRESTIGE;
        }
    }

    public static BedwarsPrestige getPrestigeForLevel(int level) {
        return BedwarsPrestige.fromId(level / LEVELS_PRE_PRESTIGE);
    }

    public static int getOverflowExp(int exp) {
        int prestige = exp / XP_PRE_PRESTIGE;

        int level = prestige * LEVELS_PRE_PRESTIGE;

        int expWithoutPrestige = exp - (prestige * XP_PRE_PRESTIGE);


        for (int i = 0; i < EAST_LEVELS; i++) {
            int expForEasyLevel = getExpForLevel(i);

            if (expWithoutPrestige < expForEasyLevel) {
                break;
            }

            level++;
            expWithoutPrestige -= expForEasyLevel;
        }

        return expWithoutPrestige % XP_PER_LEVEL;
    }
}
