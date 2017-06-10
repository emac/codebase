#!/usr/bin/env python
# -*- coding: utf-8 -*-


class Dot:
    """坐标点"""

    def __init__(self, x, y):
        self.x = x
        self.y = y


class Cell(Dot):
    """生命细胞"""

    def __init__(self, x, y):
        Dot.__init__(self, x, y)
        self.__alive = False

    def is_alive(self):
        return self.__alive

    def born(self):
        self.__alive = True

    def die(self):
        self.__alive = False

    def to_dot(self):
        return Dot(self.x, self.y)


class Map:
    """生命地图
    
    width: 宽度
    height: 高度
    random_gen: 是否生成随机地图
    """

    def __init__(self, width, height, random_gen=False):
        assert width >= 3
        assert height >= 3

        # 最大坐标值
        self.X = width - 1
        self.Y = height - 1

    def born(self, x, y):
        """细胞复活"""
        None

    def die(self, x, y):
        """细胞死亡"""
        None

    def count_alive_around(self, x, y):
        """计算(x,y)周边活着的细胞数"""
        None

    def refresh(self):
        """重新计算地图上所有细胞的生存状态"""
        None

    def alives(self):
        """返回当前活着的细胞的坐标点"""
        None
