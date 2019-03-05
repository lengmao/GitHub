package com.springboot.demo.util;

import com.springboot.demo.sys.entity.SysMenu;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: TreeListUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 16:03
 */

public class TreeListUtil {

    public static List<SysMenu> listTree(List<SysMenu> rootList) {

        //最后返回的结果
        List<SysMenu> treeList = new ArrayList<>();

        //先找出所有的一级菜单
        for (int i = 0; i < rootList.size(); i++) {
            if ("-1".equals(rootList.get(i).getParentCode())) {
                treeList.add(rootList.get(i));
            }
        }

        //为一级菜单设置子菜单，getChild()是递归调用
        for (SysMenu sysMenu : treeList) {
            sysMenu.setChilds(getChild(sysMenu.getCode(), rootList));
        }
        return treeList;
    }

    /**
     * 递归查找子菜单
     *
     * @param code 当前菜单的code
     * @param rootList 菜单列表
     * @return
     */
    private static List<SysMenu> getChild(String code, List<SysMenu> rootList) {
        List<SysMenu> childList = new ArrayList<>();

        //遍历所有菜单，将所有菜单与传过来的code进行比较
        for (SysMenu sysMenu : rootList) {
            if (StringUtils.isNotBlank(sysMenu.getParentCode())) {
                if (sysMenu.getParentCode().equals(code)) {
                    childList.add(sysMenu);
                }
            }
        }

        for (SysMenu sysMenu : childList) {
            //如果没有url,说明子菜单下边还有子菜单
            if ("" == sysMenu.getUrl() || "".equals(sysMenu.getUrl())) {
                sysMenu.setChilds(getChild(sysMenu.getCode(), rootList));
            }
        }

        //推出递归
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
