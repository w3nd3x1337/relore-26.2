package wtf.relore.structure.managers.impl.helpers;

import wtf.relore.structure.utils.color.ColorUtil;
import wtf.relore.structure.utils.interfaces.IClient;

public class Theme implements IClient {
    private String name;
    public int[] color;

    public Theme(String name, int... color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public int getColor(int index) {
        if (this.name.equals("Rainbow")) {
            return ColorUtil.rainbow(10, index, 0.6f, 1, 1);
        }
        return ColorUtil.gradient(5, index, color);
    }
}
