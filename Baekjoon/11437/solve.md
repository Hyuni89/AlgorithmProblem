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

## Link  
[11437 LCA](https://www.acmicpc.net/problem/11437)

## Warning  
기존 코드로 재체점에 들어가니 시간초과가 발생  
depth를 사용하지 않은 코드...  
