# Tarjan's Algorithm

import sys

sys.setrecursionlimit(10000)

def dfs(pos):
    global rel, discover, scc, ans, st, count

    discover[pos] = count
    ret = discover[pos]
    st.append(pos)
    count += 1

    for ne in rel[pos]:
        if discover[ne] == -1:
            ret = min(ret, dfs(ne))
        elif scc[ne] == -1:
            ret = min(ret, discover[ne])

    if ret == discover[pos]:
        tmp = []

        while True:
            t = st.pop()
            scc[t] = ret
            tmp.append(t)

            if t == pos:
                break

        tmp.sort()
        ans.append(tmp)

    return ret

v, e = map(int, sys.stdin.readline().split())
rel = [[] for _ in range(v)]
discover = [-1] * v
scc = [-1] * v
ans = []
st = []
count = 0

for i in range(e):
    a, b = map(int, sys.stdin.readline().split())
    rel[a - 1].append(b - 1)

for i in range(v):
    if discover[i] == -1:
        dfs(i)

ans.sort()
print(len(ans))
for e in ans:
    for i in e:
        print(i + 1, end=" ")
    print(-1)
