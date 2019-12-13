package com.example.customlistview;
import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public String getDescStr() {
        return descStr;
    }
}
