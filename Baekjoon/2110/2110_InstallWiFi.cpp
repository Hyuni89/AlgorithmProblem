#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int n, c;
    cin >> n >> c;

    long long houses[n];
    for (int i = 0; i < n; i++) {
        cin >> houses[i];
    }

    sort(houses, houses + n);
    
    long long m = 1;
    long long M = houses[n - 1] - houses[0] + 1;

    while (m + 1 < M) {
        long long mid = m + (M - m) / 2;
        int cnt = 1;
        long long last = houses[0];

        for (int i = 1; i < n; i++) {
            if (houses[i] - last >= mid) {
                cnt++;
                last = houses[i];
            }
        }

        if (cnt >= c) {
            m = mid;
        } else {
            M = mid;
        }
    }
    
    cout << m << endl;
    
    return 0; 
}
