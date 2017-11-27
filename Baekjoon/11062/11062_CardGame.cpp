#include <cstdio>
#include <algorithm>

using namespace std;

int reg[1000][1000][2];
int num[1000];
int t, n;

int find(int left, int right, bool flag) {
    if(reg[left][right][flag] != -1)
        return reg[left][right][flag];
    if(left == right) {
        if(flag) {
            return reg[left][right][flag] = num[left];
        } else {
            return reg[left][right][flag] = 0;
        }
    }

    int ret;
    if(flag) {
        ret = max(find(left + 1, right, !flag) + num[left], find(left, right - 1, !flag) + num[right]);
    } else {
        ret = min(find(left + 1, right, !flag), find(left, right - 1, !flag));
    }
    return reg[left][right][flag] = ret;
}

int main() {

    scanf("%d", &t);
    for(int testcase = 0; testcase < t; testcase++) {
        scanf("%d", &n);
        for(int i = 0; i < n; i++) {
            scanf("%d", &num[i]);
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 2; k++) {
                    reg[i][j][k] = -1;
                }
            }
        }

        printf("%d\n", find(0, n - 1, true));
    }
    return 0;
}