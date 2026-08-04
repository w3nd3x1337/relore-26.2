package wtf.relore.structure.managers.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import wtf.relore.structure.utils.interfaces.IClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager implements IClient {
    private final Map<String, String> translations = new HashMap<>();
    private LanguageType currentLanguage = LanguageType.RU;
    private final Gson gson = new Gson();

    public void initialize() {
        this.setLanguage(LanguageType.RU);
    }

    public void setLanguage(LanguageType type) {
        this.currentLanguage = type;
        this.translations.clear();

        String resourcePath = "/assets/relore/lang/" + type.name().toLowerCase() + ".json";
        try (InputStream stream = getClass().getResourceAsStream(resourcePath)) {
            if (stream == null) return;

            Map<String, String> readMap = gson.fromJson(
                    new InputStreamReader(stream, StandardCharsets.UTF_8),
                    new TypeToken<Map<String, String>>() {}.getType()
            );

            if (readMap != null) {
                this.translations.putAll(readMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LanguageType getCurrentLanguage() {
        return currentLanguage;
    }

    public String translate(String key) {
        return translations.getOrDefault(key, key);
    }

    public enum LanguageType {
        RU, EN
    }
}
