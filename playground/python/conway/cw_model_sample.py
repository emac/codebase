#!/usr/bin/env python
# -*- coding: utf-8 -*-

import random


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

        self.X = width - 1
        self.Y = height - 1
        self.__map = []
        self.__alives = set()
        for i in range(width):
            self.__map.append([])
            for j in range(height):
                self.__map[i].append(Cell(i, j))
        if random_gen:
            for i in range(width):
                for j in range(height):
                    if random.random() > 0.7:
                        self.born(i, j)

    def is_alive(self, x, y):
        return self.__map[x][y].is_alive()

    def born(self, x, y):
        """细胞复活"""
        cell = self.__map[x][y]
        cell.born()
        self.__alives.add(cell)

    def die(self, x, y):
        """细胞死亡"""
        cell = self.__map[x][y]
        cell.die()
        self.__alives.remove(cell)

    def dots_around(self, x, y):
        around = []
        if 0 < x < self.X:
            around.append(Dot(x - 1, y))
            around.append(Dot(x + 1, y))
            if 0 < y < self.Y:
                around.append(Dot(x - 1, y - 1))
                around.append(Dot(x, y - 1))
                around.append(Dot(x + 1, y - 1))

                around.append(Dot(x - 1, y + 1))
                around.append(Dot(x, y + 1))
                around.append(Dot(x + 1, y + 1))
            elif y == 0:
                around.append(Dot(x - 1, y + 1))
                around.append(Dot(x, y + 1))
                around.append(Dot(x + 1, y + 1))
            else:
                around.append(Dot(x - 1, y - 1))
                around.append(Dot(x, y - 1))
                around.append(Dot(x + 1, y - 1))
        elif x == 0:
            around.append(Dot(x + 1, y))
            if 0 < y < self.Y:
                around.append(Dot(x, y - 1))
                around.append(Dot(x + 1, y - 1))

                around.append(Dot(x, y + 1))
                around.append(Dot(x + 1, y + 1))
            elif y == 0:
                around.append(Dot(x, y + 1))
                around.append(Dot(x + 1, y + 1))
            else:
                around.append(Dot(x, y - 1))
                around.append(Dot(x + 1, y - 1))
        else:
            around.append(Dot(x - 1, y))
            if 0 < y < self.Y:
                around.append(Dot(x - 1, y - 1))
                around.append(Dot(x, y - 1))

                around.append(Dot(x - 1, y + 1))
                around.append(Dot(x, y + 1))
            elif y == 0:
                around.append(Dot(x - 1, y + 1))
                around.append(Dot(x, y + 1))
            else:
                around.append(Dot(x - 1, y - 1))
                around.append(Dot(x, y - 1))
        return around

    def count_alive_around(self, x, y):
        """计算(x,y)周边活着的细胞"""
        around = self.dots_around(x, y)
        count = 0
        for d in around:
            if self.is_alive(d.x, d.y):
                count += 1
        return count

    def refresh(self):
        """重新计算地图上所有细胞的生存状态"""
        to_born = []
        to_dead = []
        for c in self.__alives:
            # 检查自己
            count = self.count_alive_around(c.x, c.y)
            if count < 2 or count > 3:
                to_dead.append(Dot(c.x, c.y))
            # 检查周边死亡的细胞
            around = self.dots_around(c.x, c.y)
            for d in around:
                if (not self.is_alive(d.x, d.y)) and self.count_alive_around(d.x, d.y) == 3:
                    to_born.append(d)
        for d in to_born:
            self.born(d.x, d.y)
        for d in to_dead:
            self.die(d.x, d.y)

    def alives(self):
        """返回当前活着的细胞的坐标点"""
        return set(map(Cell.to_dot, self.__alives))
