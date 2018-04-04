// Korasaju's Algorithm

let condition = readLine()!.split(separator: " ").map {Int($0)!}
var rel = [[Int]](repeating: [Int](), count: condition[0])
var revRel = [[Int]](repeating: [Int](), count: condition[0])
var stack = [Int]()

for _ in 0 ..< condition[1] {
	let tmp = readLine()!.split(separator: " ").map {Int($0)!}
	rel[tmp[0] - 1].append(tmp[1] - 1)
	revRel[tmp[1] - 1].append(tmp[0] - 1)
}

var ans = [[Int]]()

func dfs(visited: inout [Bool], pos: Int) {
	visited[pos] = true

	for i in rel[pos] {
		if !visited[i] {
			dfs(visited: &visited, pos: i)
		}
	}

	stack.append(pos)
}

func scc(visited: inout [Bool], pos: Int, history: inout [Int]) {
	visited[pos] = true
	history.append(pos)

	for i in revRel[pos] {
		if !visited[i] {
			scc(visited: &visited, pos: i, history: &history)
		}
	}
}

var visited = [Bool](repeating: false, count: condition[0])
for i in 0 ..< condition[0] {
	if !visited[i] {
		dfs(visited: &visited, pos: i)
	}
}

visited = [Bool](repeating: false, count: condition[0])
var history = [Int]()
for i in 0 ..< stack.count {
	if !visited[stack[stack.count - i - 1]] {
		scc(visited: &visited, pos: stack[stack.count - i - 1], history: &history)
		history.sort(by: <)
		ans.append(history)
		history = [Int]()
	}
}

ans.sort {$0[0] < $1[0]}
print(ans.count)
for e in ans {
	for i in e {
		print(i + 1, terminator: " ")
	}
	print(-1)
}
