package com.harukaze.front.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.front.entity.Menu;
import com.harukaze.front.mapper.MenuMapper;
import com.harukaze.front.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.service.RoleService;
import com.harukaze.front.util.RedisUtil;
import com.harukaze.front.vo.MenuVo;
import com.harukaze.front.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result findAllMenu() {
        String str = (String) redisUtil.get("menuList");
        List<MenuVo> menuVoList;
        if (StrUtil.isBlank(str)) {
            List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>().orderByDesc(Menu::getWeight));
            menuVoList = copyList(menuList);
            redisUtil.set("menuList", JSONUtil.toJsonStr(menuVoList), 60*60*24*7);
            log.info("=====================走mysql=======================");
        } else {
            menuVoList = JSONUtil.parse(str).toBean(List.class);
            log.info("=====================走缓存=======================");
        }

        return Result.succ(menuVoList, menuVoList.size());
    }

    private List<MenuVo> copyList(List<Menu> menuList) {
        List<MenuVo> menuVoList = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuVo menuVo = copy(menu, menuList);
            if (menuVo.getParentId() == 0) {
                menuVoList.add(menuVo);
            }
        }
        return menuVoList;
    }

    private MenuVo copy(Menu menu, List<Menu> menuList) {
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(menu, menuVo);
        if (menuVo.getParentId() == 0) {
            List<MenuVo> children = new ArrayList<>();
            for (Menu menu1 : menuList) {
                if (menu1.getParentId().equals(menu.getId())) {
                    children.add(copy(menu1, menuList));
                }
            }
            menuVo.setChildrens(children);
        }
        return menuVo;
    }
}
