#include <vector>
#include <cstdio>
#include <stack>

using namespace std;

int n, m;

void makeTree(int *tree, vector<int> *rel) {
	stack<int> st;
	bool check[n + 1] = {0, };
	int root = 1;

	check[1] = true;
	st.push(1);

	do {
		root = st.top();
		st.pop();
		
		for(int i = 0; i < rel[root].size(); i++) {
			int next = rel[root][i];
			
			if(!check[next]) {
				tree[next] = root;
				st.push(next);
				check[next] = true;
			}
		}
	} while(!st.empty());
}

int main() {

	scanf("%d", &n);

	vector<int> tmp[n + 1];
	for(int i = 0; i < n - 1; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		tmp[a].push_back(b);
		tmp[b].push_back(a);
	}

	int tree[n + 1];
	for(int i = 0; i < n + 1; i++) {
		tree[i] = -1;
	}
	makeTree(tree, tmp);

	scanf("%d", &m);
	for(int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		
		bool flag[n + 1] = {0, };
		flag[a] = true;
		while(tree[a] != -1) {
			a = tree[a];
			flag[a] = true;
		}

		do {
			if(flag[b]) {
				printf("%d\n", b);
				break;
			}

			b = tree[b];
		} while(b != -1);
	}

	return 0;
}
