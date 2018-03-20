#include <vector>
#include <cstdio>
#include <stack>

using namespace std;

int n, m;

void makeTree(int *tree, int *depth, vector<int> *rel) {
	stack<pair<int, int> > st;
	bool check[n + 1] = {0, };
	int root = 1;

	check[1] = true;
	depth[1] = 0;
	st.push(make_pair(root, 0));

	do {
		pair<int, int> tmp = st.top();
		root = tmp.first;
		st.pop();
		
		for(int i = 0; i < rel[root].size(); i++) {
			int next = rel[root][i];
			
			if(!check[next]) {
				tree[next] = root;
				depth[next] = tmp.second + 1;
				st.push(make_pair(next, tmp.second + 1));
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
	int depth[n + 1];
	for(int i = 0; i < n + 1; i++) {
		tree[i] = -1;
		depth[i] = -1;
	}
	depth[1] = 0;
	makeTree(tree, depth, tmp);

	scanf("%d", &m);
	for(int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b]) {
				a = tree[a];
			} else {
				b = tree[b];
			}
		}
		while(a != b) {
			a = tree[a];
			b = tree[b];
		}
		printf("%d\n", a);
	}

	return 0;
}
