# 2150. Strongly Connected Component  
Korasaju's Algorithm  
Tarjan's Algorithm  

## Brief  
유향 그래프가 주어진다.  
임의의 두 정점을 u, v라고 할 때, u에서 v로 가는 경로와 v에서 u로 가는 경로가 모두 존재하는 경우를 강한 결합이라고 한다. (Loop)  
강한 결합의 숫자와 내부 정점을 오름차순으로 출력  

> Input  
> V E (1 <= V <= 10,000, 1 <= E <= 100,000)  
> N<sub>e11</sub> N<sub>e12</sub> (1 <= N<sub>en</sub> <= 10,000)  
> ...  
> N<sub>eE1</sub> N<sub>eE2</sub>  

## Approach  
1. DFS로 정점이 이루는 Loop를 찾고, history를 기록하려고 했지만 Loop를 이루지 않는 정점 처리 등 어려움이 많았다.  

## Solution  
1. Korasaju's Algorithm  
	- 정 방향 그래프, 역 방향 그래프, 스택이 필요.  
	- 정 방향 그래프로 DFS를 하면서 후위로 스택에 방문한 정점을 기록한다.  
	- 스택에서 pop을 하며 그 정점부터 역 방향 DFS를 하고 기록한다.  
	- pop한 정점이 방문한 정점이면 넘어간다.  

2. Tarjan's Algorithm  
	- DFS로 정점을 스택에 넣으면서 순회하며 새로운 번호를 매긴다. (visited flag 역할도 겸용)  
	- 순회를 한 정점 중, 새로 매긴 번호에서 가장 작은 번호를 기준으로 다시 돌아오면 스택에 들어있던 정점을 하나의 SCC로 처리한다.  

## Link  
[2150 Strongly Connected Component](https://www.acmicpc.net/problem/2150)  
