#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;

int n, w;
pair<int, int> event[1001];
int reg[1001][1001];
int bt[1001][1001];

int find(int c1, int c2) {
	if(c1 == w || c2 == w) return 0;
	if(reg[c1][c2] != -1) return reg[c1][c2];

	int next = max(c1, c2) + 1;
	int move1 = find(next, c2) + (c1 == 0 ? abs(event[next].first - 1) + abs(event[next].second - 1) : abs(event[c1].first - event[next].first) + abs(event[c1].second - event[next].second));
	int move2 = find(c1, next) + (c2 == 0 ? abs(event[next].first - n) + abs(event[next].second - n) : abs(event[c2].first - event[next].first) + abs(event[c2].second - event[next].second));

	int ret = -1;
	if(move1 < move2) {
		ret = move1;
		bt[c1][c2] = 1;
	} else {
		ret = move2;
		bt[c1][c2] = 2;
	}

	reg[c1][c2] = ret;
	return ret;
}

int main() {

	memset(reg, -1, sizeof(reg));
	memset(bt, -1, sizeof(bt));
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;
	cin >> w;
	for(int i = 0; i < w; i++) {
		int a, b;
		cin >> a >> b;
		event[i + 1] = make_pair(a, b);
	}

	cout << find(0, 0) << "\n";
	int c1 = 0;
	int c2 = 0;
	for(int i = 0; i < w; i++) {
		cout << bt[c1][c2] << "\n";
		if(bt[c1][c2] == 1) {
			c1 = i + 1;
		} else if(bt[c1][c2] == 2) {
			c2 = i + 1;
		}
	}

	return 0;
}

