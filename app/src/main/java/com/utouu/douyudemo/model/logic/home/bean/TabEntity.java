package com.utouu.douyudemo.model.logic.home.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Create by 黄思程 on 2016/12/12  8:41
 * Function： 用于初始化TabLayout底部的Tab和文字的封装类
 * Desc：TabLayout的实体类,用来设置底部Tab选中和没选中的图表的封装类
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
