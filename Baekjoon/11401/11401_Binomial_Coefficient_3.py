import sys

def modulus(num, up):
    global DIV

    if up == 1:
        return num
    if up == 0:
        return 1;

    tmp = modulus(num, up // 2)
    if up % 2:
        return (((tmp * tmp) % DIV) * num) % DIV
    else:
        return (tmp * tmp) % DIV

DIV = 1000000007
n, k = map(int, sys.stdin.readline().split())
factorial = [1]
for i in range(1, 4000001):
    factorial.append((i * factorial[-1]) % DIV)

upper = factorial[n]
lower = factorial[k] * factorial[n - k]

print(((upper % DIV) * modulus(lower, DIV - 2)) % DIV)
