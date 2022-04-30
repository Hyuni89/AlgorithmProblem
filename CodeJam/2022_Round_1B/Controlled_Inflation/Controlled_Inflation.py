import sys

input = sys.stdin.readline

t = int(input())
for tcase in range(t):
    n, p = map(int, input().split())
    arr = [sorted(list(map(int, input().split()))) for _ in range(n)]
    reg = [[0] * 2 for _ in range(n + 1)]
    l0 = l1 = 0
    
    for i in range(1, n + 1):
        reg[i][0] = min(reg[i - 1][0] + abs(l0 - arr[i - 1][0]) + arr[i - 1][-1] - arr[i - 1][0],
                        reg[i - 1][1] + abs(l1 - arr[i - 1][0]) + arr[i - 1][-1] - arr[i - 1][0])
        reg[i][1] = min(reg[i - 1][0] + abs(l0 - arr[i - 1][-1]) + arr[i - 1][-1] - arr[i - 1][0],
                        reg[i - 1][1] + abs(l1 - arr[i - 1][-1]) + arr[i - 1][-1] - arr[i - 1][0])
        l0 = arr[i - 1][-1]
        l1 = arr[i - 1][0]
    
    print(f"Case #{tcase + 1}: {min(reg[n])}")