class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int [] indegree_groups=new int [m];
        int [] indegree_items=new int[n];
        HashMap<Integer,HashSet<Integer>> adj_group = new HashMap<>();
        List<Integer> adj_item[]=new List[n];
        for(int i=0;i<n;i++){
            adj_item[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            if(adj_group.containsKey(group[i])){
                adj_group.get(group[i]).add(i);
            }else{
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                adj_group.put(group[i],set);
            }
        }
        for(int i=0;i<n;i++){
            HashSet<Integer> temp = adj_group.getOrDefault(group[i],new HashSet<Integer>());
            for(int x:beforeItems.get(i)){
                if(group[i]!=-1&&!temp.contains(x)){
                    indegree_groups[group[i]]++;
                }
                indegree_items[i]++;
                adj_item[x].add(i);
            }
        }
        int ans[] = new int [n];
        int ans_idx=0;
        Queue<Integer> gq = new LinkedList<>();
        Queue<Integer> iq = new LinkedList<>();
        Queue<Integer> groupless = new LinkedList<>();

        for(int i=0;i<m;i++){
            if(indegree_groups[i]==0){
                gq.add(i);
            }
        }
        for(int x:adj_group.getOrDefault(-1,new HashSet<>())){
            if(indegree_items[x]==0){
                groupless.add(x);
            }
        }
        while(!groupless.isEmpty()){
            int cur_item=groupless.poll();
            System.out.println(cur_item);
            ans[ans_idx++]=cur_item;
            for(int x:adj_item[cur_item]){
                indegree_items[x]--;
                if(group[x]==-1){
                    if(indegree_items[x]==0){
                        groupless.add(x);
                    }
                }else{
                    indegree_groups[group[x]]--;
                    if(indegree_groups[group[x]]==0) {
                        gq.add(group[x]);
                    }
                }
            }
        }
        while(!gq.isEmpty()){
            int cur_grp=gq.poll();
            for(int temp:adj_group.getOrDefault(cur_grp,new HashSet<>())){
                if(indegree_items[temp]==0){
                    iq.add(temp);
                }
            }
            while(!groupless.isEmpty()){
                int cur_item=groupless.poll();
                System.out.println(cur_item);
                ans[ans_idx++]=cur_item;
                for(int x:adj_item[cur_item]){
                    indegree_items[x]--;
                    if(group[x]==-1){
                        if(indegree_items[x]==0){
                            groupless.add(x);
                        }
                    }else{
                        indegree_groups[group[x]]--;
                        if(indegree_groups[group[x]]==0) {
                            gq.add(group[x]);
                        }
                    }
                }
            }
            while(!iq.isEmpty()){
                int cur_item=iq.poll();
                System.out.println(cur_item);
                ans[ans_idx++]=cur_item;
                HashSet<Integer> temp = adj_group.get(cur_grp);
                for(int x:adj_item[cur_item]){
                    indegree_items[x]--;
                    if(!temp.contains(x)){
                        if(group[x]==-1){
                            if(indegree_items[x]==0){
                                groupless.add(x);
                            }
                        }else{
                            indegree_groups[group[x]]--;
                            if(indegree_groups[group[x]]==0) {
                                gq.add(group[x]);
                            }
                        }
                    }else{
                        if(indegree_items[x]==0){
                            iq.add(x);
                        }
                    }
                }
            }
        }
        while(!groupless.isEmpty()){
            int cur_item=groupless.poll();
            System.out.println(cur_item);
            ans[ans_idx++]=cur_item;
            for(int x:adj_item[cur_item]){
                indegree_items[x]--;
                if(group[x]==-1){
                    if(indegree_items[x]==0){
                        groupless.add(x);
                    }
                }else{
                    indegree_groups[group[x]]--;
                    if(indegree_groups[group[x]]==0) {
                        gq.add(group[x]);
                    }
                }
            }
        }
        return (ans_idx==n)?ans:new int[]{};
    }
}