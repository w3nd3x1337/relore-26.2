package wtf.relore.structure.managers.impl;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import wtf.relore.structure.managers.impl.helpers.Theme;
import wtf.relore.structure.utils.color.ColorUtil;

import java.util.Arrays;

public class ThemeManager {

    private ObjectArrayList<Themes> themeList = new ObjectArrayList<>();
    private Themes themes;

    public ObjectArrayList<Themes> getThemeList() {
        return themeList;
    }

    public void setThemeList(ObjectArrayList<Themes> themeList) {
        this.themeList = themeList;
    }

    public Themes getThemes() {
        return themes;
    }

    public void setThemes(Themes themes) {
        this.themes = themes;
    }

    public void initialize() {
        this.themeList.addAll(Arrays.asList(
                Themes.Green,
                Themes.Blue,
                Themes.Red,
                Themes.Orange,
                Themes.Purple
        ));
        this.themes = this.themeList.get(0);
    }

    public enum Themes {
        Green(new Theme("Default", ColorUtil.rgba(203, 255, 130, 255), ColorUtil.rgba(132, 163, 89, 255))),
        Blue(new Theme("Blue", ColorUtil.rgba(130, 155, 255, 255), ColorUtil.rgba(89, 99, 163, 255))),
        Red(new Theme("Red", ColorUtil.rgba(255, 130, 130, 255), ColorUtil.rgba(163, 89, 89, 255))),
        Orange(new Theme("Orange", ColorUtil.rgba(255, 178, 130, 255), ColorUtil.rgba(163, 123, 89, 255))),
        Purple(new Theme("Purple", ColorUtil.rgba(197, 130, 255, 255), ColorUtil.rgba(136, 89, 163, 255)));

        public final Theme theme;

        Themes(Theme theme) {
            this.theme = theme;
        }

        public Theme getTheme() {
            return theme;
        }
    }
}
