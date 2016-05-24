#!/usr/bin/python3
class Lg:
    def __init__(self, lgmap, role=[(2, 3), (3)]):
        self.lgmap = lgmap

class Cell:
    def __init__(self, st, num=None, role=[(2, 3), (3, )]):
        self.st = st
        self.nst = 0
        self.links = []
        self.num = num

        self.role(role)

    def role(self, role):
        self.lfrole = role[0]
        self.rbrole = role[1]
        self.mx = max(max(role[1]), max(role[0])) + 1


    def link(self, cell):
        if(not cell is self):
            self.links.append(cell)

    def link_reset(self):
        self.links = []

    def __str__(self):
        return str(self.st if self.st is 0 else "[38;5;200m{}[0m".format(self.st))

    def judge(self):
        count = 0
        for c in self.links:
            if c.st is 1:
                count += 1
                if count is self.mx:
                    break
        if self.st is 0:
            for i in self.rbrole:
                if count is i:
                    self.nst = 1
        elif self.st is 1:
            if self.lfrole[0] <= count <= self.lfrole[1]:
                self.nst = 1

    def update(self):
        self.st = self.nst
        self.nst = 0

class WCell(Cell):
    def judge(self):
        pass

    def update(self):
        pass

class Field:
    def __init__(self, fie, role=None):
        self.fie = fie
        if role:
            for l in self.fie:
                for c in l:
                    c.role(role)

    def update(self):
        for line in self.fie:
            for col in line:
                col.judge()

        for line in self.fie:
            for col in line:
                col.update()

    def link(self):
        for lindex, line in enumerate(self.fie):
            for cindex, col in enumerate(line):
                Field.linkalg(self.fie, col, lindex, cindex)

    @staticmethod
    def linkalg(field, cell, lindex, cindex):
        for l in range(-1, 2):
            for c in range(-1, 2):
                lres = lindex + l
                cres = cindex + c
                cell.link(field[lres][cres] if
                        lres >= 0 and
                        cres >= 0 and
                        lres < len(field) and
                        cres < len(field[lres])
                        else WCell(0))

    def __str__(self):
        res = ""
        for line in self.fie:
            res += str("".join([str(x) for x in line]))
            res += "\n"
        return res


if __name__ == '__main__':
    ledg = 30
    cedg = 30
    import random
    fie = [[Cell(random.randint(0, 1), [x, y]) for y in range(ledg)] for x in range(cedg)]
    field = Field(fie, role=[(2, 3), (3, )])
    field.link()
    # print([x.num for x in fie[9][9].links])
    # fie[2][1].st = 1
    # fie[2][2].st = 1
    # fie[2][3].st = 1
    # fie[1][3].st = 1
    # fie[0][2].st = 1

    import json
    while True:
        print("[0;0H{}".format(field))
        field.update()
        input()

    # print(field)
    # field.update()
    # print(field)
