import sys

def findAns(arr, start, end):
    global ans

    if start >= end:
        return

    mid = (start + end) // 2
    index = 0
    lp = start
    rp = mid + 1
    reg = [0] * (end - start + 1)

    findAns(arr, start, mid)
    findAns(arr, mid + 1, end)

    while start + index <= end:
        if lp > mid or rp > end:
            if lp > mid:
                reg[index] = arr[rp]
                rp += 1
            else:
                reg[index] = arr[lp]
                lp += 1
            index += 1
            continue

        if arr[lp] < arr[rp]:
            reg[index] = arr[lp]
            lp += 1
        else:
            reg[index] = arr[rp]
            rp += 1
            ans += (mid + 1 - lp)
        index += 1

    arr[start : end + 1] = reg

n = int(sys.stdin.readline().strip())
a = list(map(int, sys.stdin.readline().split()))
b = list(map(int, sys.stdin.readline().split()))
ans = 0
INF = 987654321
arr = [0] * n
reg = [INF] * 1000001

for i in range(n):
    if reg[a[i]] == INF:
        reg[a[i]] = i
    else:
        arr[i] = reg[a[i]]
    if reg[b[i]] == INF:
        reg[b[i]] = i
    else:
        arr[reg[b[i]]] = i

findAns(arr, 0, n - 1)
print(ans)
