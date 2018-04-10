# 11437. LCA  
LCA Algorithm  

## Brief  
Tree에서 두 Node의 공통 Root Node를 구한다.  

> Input  
> N (2 <= N <= 50,000)  
> a<sub>1, 1</sub> (1 <= a<sub>1, 1</sub> <= 50,000) a<sub>1, 2</sub> (1 <= a<sub>1, 2</sub> <= 50,000)  
> ...  
> a<sub>N-1, 1</sub> (1 <= a<sub>N-1, 1</sub> <= 50,000) a<sub>N-1, 2</sub> (1 <= a<sub>N-1, 2</sub> <= 50,000)  
> M (1 <= M <= 10,000)  
> b<sub>1, 1</sub> (1 <= b<sub>1, 1</sub> <= 50,000) b<sub>1, 2</sub> (1 <= b<sub>1, 2</sub> <= 50,000)  
> ...  
> b<sub>M, 1</sub> (1 <= b<sub>M, 1</sub> <= 50,000) b<sub>M, 2</sub> (1 <= b<sub>M, 2</sub> <= 50,000)  

## Approach  
1. Tree 구조를 잘 만들어 내고,  
2. 각 Node의 Root를 구하면 쉽게 풀린다.  

## Solution  
1. LCA (Lowest Common Ancestor) Algorithm  
    - Tree를 만들 때, Node의 Depth도 함께 기록한다.  
    - Depth를 같에 만들며 Root Node로 이동한다.  
    - Depth가 같아지면 두 Node의 Root를 따라 이동한다.  
    - 두 Node의 Root Node가 같을 때 까지 반복한다.  

    ```cpp
    int depth[N];   // 각 Node의 Depth를 기록  
    int tree[N];    // 각 Node의 관계를 기록  
    int LCA(int a, int b) {
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
        return a;
    }
    ```

2. LCA2를 풀며...  
	- 부모 노드를 저장할 때, 바로 상위 부모 노드만 저장하게 되면 노드가 많고 depth 차이가 많이 나면 시간 복잡도가 굉장히 늘어난다. (LCA2 시간 초과 발생)  
	- parent[N] 배열을 확장해 parent[N][k]로 변형. (N 노드의 2<sup>k</sup>번째 부모 노드)  
	- depth 차이가 많이 나면 하나씩 부모 노드를 타고 올라가는 것이 아니라 한번에 성큼성큼 부모 노드를 이동  

	```java
	int k = (int)Math.floor(Math.log10(N) / Math.log10(2)) + 1; // log(N, 2) + 1
	int[][] parent = new int[N][k];

	// 부모 노드 배열 채우기  
	for(int i = 1; i < k; i++) {
		for(int j = 0; j < N; j++) {
			parent[j][i] = parent[parent[j][i - 1]][i - 1];
		}
	}

	// 공통 조상 찾는 로직  
	// depth 차를 구하고 그 차를 이용해 부모 노드를 O(log) 만에 이동  
	if(depth[a] < depth[b]) swap(a, b);
	int diff = depth[a] - depth[b];

	for(int i = 0; diff > 0; i++) {
		if(diff % 2 == 1) a = parent[a][i];
		diff /= 2;
	}

	// depth가 같아졌지만 부모 노드가 다를 때  
	if(a != b) {
		for(int i = k - 1; i >= 0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		a = parent[a][0];
	}

	return a;
	```

## Link  
[11437 LCA](https://www.acmicpc.net/problem/11437)  
[11438 LCA 2](https://www.acmicpc.net/problem/11438)  

## Warning  
기존 코드로 재체점에 들어가니 시간초과가 발생  
depth를 사용하지 않은 코드...  

LCA2 문제를 풀면서 다시 문제 발생...  
부모 노드를 저장하는 방식에 대한 내용을 업데이트  
