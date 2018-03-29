let condition = readLine()!.split(separator: " ").flatMap {Int($0)!}
var capacity = [[Int]](repeating: [Int](repeating: 0, count: condition[0] + condition[1] + 2), count: condition[0] + condition[1] + 2)
var flow = [[Int]](repeating: [Int](repeating: 0, count: condition[0] + condition[1] + 2), count: condition[0] + condition[1] + 2)
var rel = [[Int]](repeating: [Int](), count: condition[0] + condition[1] + 2)

let start = 0
let end = condition[0] + condition[1] + 1

for i in 1 ... condition[0] {
	let tmp = readLine()!.split(separator: " ").flatMap {Int($0)!}
	for j in 0 ..< tmp[0] {
		capacity[i][tmp[j + 1] + condition[0]] = 1
		rel[i].append(tmp[j + 1] + condition[0])
		rel[tmp[j + 1] + condition[0]].append(i)
	}

	capacity[start][i] = 2
	rel[start].append(i)
	rel[i].append(start)
}

for i in 0 ..< condition[1] {
	capacity[condition[0] + i + 1][end] = 1
	rel[condition[0] + i + 1].append(end)
	rel[end].append(condition[0] + i + 1)
}

var ans = 0
while true {
	var parent = [Int](repeating: -1, count: condition[0] + condition[1] + 2)
	var qu = [Int]()
	qu.append(start)

	while qu.count != 0 && parent[end] == -1 {
		let here = qu.removeFirst()

		for there in rel[here] {
			if capacity[here][there] - flow[here][there] > 0 && parent[there] == -1 {
				parent[there] = here
				qu.append(there)
				
				if there == end {
					break
				}
			}
		}
	}

	if parent[end] == -1 {
		break
	}

	var pos = end
	while pos != start {
		flow[parent[pos]][pos] += 1
		flow[pos][parent[pos]] -= 1
		pos = parent[pos]
	}

	ans += 1
}

print(ans)
