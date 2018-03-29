# 11376. Passionate GangHo 2  
Network Flow Algorithm, Bipartite Matching Algorithm  

## Brief  
사람당 처리 가능한 일이 주어지고 최대 2개씩 처리가 가능 할 때 최대 처리 할 수 있는 일의 수는?  

> Input  
> N M (1 <= N, M <= 1,000)  
> S<sub>1</sub> a<sub>11</sub> a<sub>12</sub> ...  
> S<sub>2</sub> a<sub>21</sub> a<sub>22</sub> ...  
> ...  
> S<sub>N</sub> a<sub>N1</sub> a<sub>N2</sub> ...  

## Approch  
1. 사람당 하나의 일을 처리하는 경우엔 이분 매칭으로 풀이가 가능한데, 두개씩 일을 처리 할 수 있으므로 기본적인 이분 매칭 알고리즘은 사용할 수 없다.  

2. 처리 할 수 있는 일과의 관계가 동일한 사람 노드를 두개씩 만들어 이분 매칭으로 처리 했으나 Time Limit에 걸림.  

3. 기본적인 Network Flow Algorithm을 이해하기 위해 해당 Algorithm으로 접근. (이분 매칭은 edge의 용량이 1인 Network flow와 동일)  

## Solution  
1. Network Flow Algorithm  
    - 그래프의 최단거리 알고리즘과 다르게 간선에 용량이 있어, 최대 용량을 찾는 알고리즘 (라우터에서 패킷 경로를 찾을 때 쓰임)  

    > u 노드에서 v 노드로 흘려 보낼 수 있는 용량을 c(), 흐르는 유량을 f()로 할 때,  
    > f(u, v) <= c(u, v) (흐르는 유량은 용량을 초과할 수 없다)  
    > sig(f(l, u)) = sig(f(u, k)) (한 노드에서 들어오는 유량과 나가는 유량은 같다)  
    > f(u, v) = -f(v, u) (흐르는 유량은 역방향으로 음의 유량이 흐르는 것으로 표시할 수 있다)  
    
    1. Source에서 Sink로 가는 아무 경로를 찾는다.  
    2. 경로에서 가장 작은 용량만큼 총 용량을 증가시킨다.  
    3. 경로상에 증가한 유량만큼 증가시키고, 역방향은 감소시킨다.  
    4. 더 이상 증가 경로가 없을 때까지 반복한다.  

    - Ford-Fulkerson Algorithm이 대표적  
    - 경로를 찾을 때, BFS로 찾으면 Edmonds-Karp Algorithm 이라고도 한다.  
    - Time Complexity : DFS(O(V + E)f), BFS(O(VE<sup>2</sup>)) (V: 노드, E: 간선, f: 유량)  

    ```cpp
    int capacity[MAX][MAX], flow[MAX][MAX];

    int NetworkFlow(int source, int sink) {

        memset(flow, 0, sizeof(flow));
        int totalFlow = 0;

        while(true) {
            vector<int> parent(MAX, -1);
            queue<int> qu;

            parent[source] = source;
            qu.push(source);

            while(!qu.empty() && parent[sink] == -1) {
                int here = qu.front(); qu.pop();

                for(int there = 0; there < V; there++) {
                    if(capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
                        qu.push(there);
                        parent[there] = here;
                    }
                }
            }

            if(parent[sink] == -1) break;

            int amount = INF;
            for(int p = sink; p != source; p = parent[p]) {
                amount = min(capacity[parent[p]][p] - flow[parent[p]][p], amount);
            }

            for(int p = sink; p != source; p = parent[p]) {
                flow[parent[p]][p] += amount;
                flow[p][parent[p]] -= amount;
            }

            totalFlow += amount;
        }

        return totalFlow;
    }
    ```

2. Bipartite Matching Algorithm  
    - Network Flow Algorithm에서 간선 용량이 1이면 Bipartite Matching Algorithm이 된다.  
    
    ```cpp
    int N, M;       // N: aGroup size, M: bGroup size
    bool adj[MAX][MAX];
    vector<int> aGroup, bGroup;
    vector<bool> visited;

    bool dfs(int here) {
        if(visited[here]) return false;
        visited[here] = true;

        for(int there = 0; there < M; there++) {
            if(adj[here][there]) {
                if(bGroup[there] == -1 || dfs(bGroup[there])) {
                    aGroup[here] = there;
                    bGroup[there] = here;
                    return true;
                }
            }
        }

        return false;
    }

    int BipartiteMatching() {
        aGroup = vector<int>(N, -1);
        bGroup = vector<int>(M, -1);
        int size = 0;

        for(int start = 0; start < N; start++) {
            visited = vector<bool>(n, false);
            if(dfs(start)) size++;
        }

        return size;
    }
    ```

## Link  
[11376 Passionate GangHo 2](https://www.acmicpc.net/problem/11376)  