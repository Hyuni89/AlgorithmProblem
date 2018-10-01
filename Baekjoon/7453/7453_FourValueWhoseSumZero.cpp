#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n;
long long ans = 0;

int lowerBound(int num, int *cd) {
	int l = 0, r = n * n;
	while(l < r) {
		int m = (l + r) / 2;
		if(num + cd[m] < 0) {
			l = m + 1;
		} else {
			r = m;
		}
	}

	return l;
}

int upperBound(int num, int *cd) {
	int l = 0, r = n * n;
	while(l < r) {
		int m = (l + r) / 2;
		if(num + cd[m] <= 0) {
			l = m + 1;
		} else {
			r = m;
		}
	}

	return l;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n;
	int A[n], B[n], C[n], D[n];
	for(int i = 0; i < n; i++) {
		cin >> A[i] >> B[i] >> C[i] >> D[i];
	}

	int	ab[n * n], cd[n * n];
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			ab[i * n + j] = A[i] + B[j];
			cd[i * n + j] = C[i] + D[j];
		}
	}
	sort(ab, ab + n * n);
	sort(cd, cd + n * n);

	for(int i = 0; i < n * n; i++) {
		int li = lowerBound(ab[i], cd);
		int ri = upperBound(ab[i], cd);
		ans += ri - li;
	}

	cout << ans << "\n";
	return 0;
}
