import sys

def calc(num, ex):
    global reg
    n = str(num)
    for i in n:
        reg[int(i)] += ex

left, right = 1, int(sys.stdin.readline())
mul = 1
reg = [0] * 10

while left <= right:
    while left % 10 != 0 and left <= right:
        calc(left, mul)
        left += 1
    if left > right:
        break
    while right % 10 != 9 and left <= right:
        calc(right, mul)
        right -= 1
    if left > right:
        break

    for i in range(10):
        reg[i] += (right // 10 - left // 10 + 1) * mul

    left //= 10
    right //= 10
    mul *= 10

for i in reg: print(i, end=' ')
