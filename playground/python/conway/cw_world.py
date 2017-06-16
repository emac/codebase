#!/usr/bin/env python
# -*- coding: utf-8 -*-

from Tkinter import *
from cw_model_sample import *

DELAY = 100
DOT_SIZE = 10


class World(Canvas):
    def __init__(self, parent, map):
        Canvas.__init__(self, width=(map.X + 1) * DOT_SIZE, height=(map.Y + 1) * DOT_SIZE)

        self.parent = parent
        self.map = map
        self.last_alives = set()
        self.dots = {}

        self.redraw()
        self.pack()

    def redraw(self):
        new_alives = self.map.alives()
        # 擦除死亡的
        for d in self.last_alives - new_alives:
            self.erase(d)
        # 重绘新生的
        for d in new_alives - self.last_alives:
            self.draw(d)
        # 刷新
        self.last_alives = new_alives
        self.map.refresh()
        self.after(DELAY, self.redraw)

    def erase(self, d):
        print "Erase: %s, %s" % (d.x, d.y)
        if self.dots.has_key(d):
            self.delete(self.dots[d])
            del self.dots[d]

    def draw(self, d):
        print "Draw: %s, %s" % (d.x, d.y)
        rect = self.create_rectangle(d.x * DOT_SIZE, d.y * DOT_SIZE, (d.x + 1) * DOT_SIZE - 1, (d.y + 1) * DOT_SIZE - 1,
                                     fill="green")
        self.dots[d] = rect


class MyFrame(Frame):
    def __init__(self, parent, map):
        Frame.__init__(self, parent)

        parent.title("Conway's Game")
        World(parent, map)
        self.pack()


def main():
    # 读取地图
    map = init_map_from_file()
    # map = init_random_map()
    # 初始化
    root = Tk()
    MyFrame(root, map)
    root.mainloop()


def init_map_from_file():
    data = open("map32.data", "r")
    size = data.readline().split("x")
    map = Map(int(size[0]), int(size[1]))
    for i, line in enumerate(data):
        for j, c in enumerate(line):
            if c == "1":
                map.born(j, i)
    data.close()
    return map


def init_random_map():
    return Map(32, 32, True)


if __name__ == '__main__':
    main()
