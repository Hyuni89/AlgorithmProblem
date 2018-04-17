import sys

class IceBerg:

    piece = int()
    icePosition = []

    def __init__(self, n, m, ice):
        self.n = n
        self.m = m
        self.ice = ice

        for i in range(1, self.n - 1):
            for j in range(1, self.m - 1):
                if self.ice[i][j] > 0:
                    self.icePosition.append([i, j])

    def icebergCount(self):
        ret = 0
        visit = [[False] * m for _ in range(n)]
        for i, j in self.icePosition:
            if visit[i][j] is False:
                ret += 1
                self.bfs(visit, i, j)

        self.piece = ret
        return ret

    def bfs(self, visit, y, x):
        qu = [[y, x]]
        visit[y][x] = True

        while len(qu):
            posy, posx = qu.pop()

            if self.ice[posy][posx - 1] > 0 and visit[posy][posx - 1] is False:
                visit[posy][posx - 1] = True
                qu.append([posy, posx - 1])
            if self.ice[posy][posx + 1] > 0 and visit[posy][posx + 1] is False:
                visit[posy][posx + 1] = True
                qu.append([posy, posx + 1])
            if self.ice[posy - 1][posx] > 0 and visit[posy - 1][posx] is False:
                visit[posy - 1][posx] = True
                qu.append([posy - 1, posx])
            if self.ice[posy + 1][posx] > 0 and visit[posy + 1][posx] is False:
                visit[posy + 1][posx] = True
                qu.append([posy + 1, posx])

    def melting(self):
        melt = []
        tmpPosition = []

        for i, j in self.icePosition:
            cnt = 0
            if self.ice[i][j - 1] == 0:
                cnt += 1
            if self.ice[i][j + 1] == 0:
                cnt += 1
            if self.ice[i - 1][j] == 0:
                cnt += 1
            if self.ice[i + 1][j] == 0:
                cnt += 1

            if cnt > 0:
                melt.append([i, j, cnt])
            else:
                tmpPosition.append([i, j])

        for i, j, cnt in melt:
            self.ice[i][j] -= cnt
            if self.ice[i][j] < 0:
                self.ice[i][j] = 0
            if self.ice[i][j] != 0:
                tmpPosition.append([i, j])

        self.icePosition = tmpPosition

n, m = map(int, sys.stdin.readline().split())
ice = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

iceberg = IceBerg(n, m, ice)
ans = 0

while iceberg.icebergCount() == 1:
    ans += 1
    iceberg.melting()

if iceberg.piece >= 2:
    print(ans)
else:
    print(0)
