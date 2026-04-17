class UnionFind {
    private int[] parent;
    public UnionFind(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);  
        return parent[x];
    }
    public void unify(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx < ry) {
            parent[ry] = parent[rx];
        } else {
            parent[rx] = parent[ry];
        }
    }
}
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int N = accounts.size();
        UnionFind uf = new UnionFind(N);
        Map<String, Integer> emails = new HashMap<>();  
        Map<Integer, String> name = new HashMap<>();    
        Map<String, Integer> seen = new HashMap<>(); 
        for (int id = 0; id < N; id++) {
            name.put(id, accounts.get(id).get(0));
            for (int i = 1; i < accounts.get(id).size(); i++) {
                String email = accounts.get(id).get(i);
                emails.put(email, id);
                if (seen.containsKey(email)) {
                    uf.unify(id, seen.get(email));
                } else {
                    seen.put(email, id);
                }
            }
        }
        Map<Integer, Set<String>> filtered = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emails.entrySet()) {
            String email = entry.getKey();
            int id = entry.getValue();
            int root = uf.find(id);
            filtered.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : filtered.entrySet()) {
            int k = entry.getKey();
            Set<String> v = entry.getValue();
            List<String> cur = new ArrayList<>();
            cur.add(name.get(k));
            cur.addAll(v);
            res.add(cur);
        }       
        return res;
    }
}