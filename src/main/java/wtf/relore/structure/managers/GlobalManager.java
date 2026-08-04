package wtf.relore.structure.managers;

import wtf.relore.structure.managers.impl.LanguageManager;
import wtf.relore.structure.managers.impl.ModuleManager;
import wtf.relore.structure.managers.impl.ThemeManager;

public class GlobalManager {
    private static final ModuleManager moduleManager = new ModuleManager();
    private static final ThemeManager themeManager = new ThemeManager();
    private static final LanguageManager languageManager = new LanguageManager();

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static ThemeManager getThemeManager() {
        return themeManager;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }

    public static void register() {
        languageManager.initialize();
        moduleManager.initialize();
        themeManager.initialize();
    }

    public static String t(String key) {
        if (languageManager == null) {
            return key;
        }
        return languageManager.translate(key);
    }
}
