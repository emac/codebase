#!/usr/bin/env python
# -*- coding: utf-8 -*-


# Non-OOP
def get_words(idx):
    if idx > 1:
        if idx > 2:
            tail = '%s bottles' % idx
        else:
            tail = '1 bottle'
        return ["%s bottles" % idx, "%s bottles" % idx, 'Take one down and pass it around', tail]
    elif idx == 1:
        return ['1 bottle', '1 bottle', 'Take one down and pass it around', "no more bottles"]
    else:
        return ["No more bottles", 'no more bottles', 'Go to the store and buy some more', "99 bottles"]


for i in range(99, -1, -1):
    words = get_words(i)
    print "%s of beer on the wall, %s of beer.\n%s, %s of beer on the wall.\n" % (
        words[0], words[1], words[2], words[3])


# OOP
class Bottle:
    @staticmethod
    def get_instance(idx):
        if idx > 1:
            return NBottle(idx)
        elif idx == 1:
            return OneBottle(idx)
        else:
            return ZeroBottle(idx)

    def __init__(self, idx):
        self.idx = idx

    def sing(self):
        words = self.get_words()
        print "%s of beer on the wall, %s of beer.\n%s, %s of beer on the wall.\n" % (
            words[0], words[1], words[2], words[3])


class NBottle(Bottle):
    def get_words(self):
        idx = self.idx
        if idx > 2:
            tail = '%s bottles' % idx
        else:
            tail = '1 bottle'
        return ["%s bottles" % idx, "%s bottles" % idx, 'Take one down and pass it around', tail]


class OneBottle(Bottle):
    def get_words(self):
        return ['1 bottle', '1 bottle', 'Take one down and pass it around', "no more bottles"]


class ZeroBottle(Bottle):
    def get_words(self):
        return ["No more bottles", 'no more bottles', 'Go to the store and buy some more', "99 bottles"]


for i in range(99, -1, -1):
    Bottle.get_instance(i).sing()
