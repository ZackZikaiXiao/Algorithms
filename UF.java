public class UF{
    public static void main(String[] args){
        StdOut.println("请输入节点个数");
        int N = StdIn.readInt();
        //***********在此选择算法***********//
        // QuickFind uf = new QuickFind(N);
        // QuickUnion uf = new QuickUnion(N);
        WeightedQuickUnion uf = new WeightedQuickUnion(N);
        //********************************//
        StdOut.println("请输入节点");
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(q>N-1 || p>N-1) {
                StdOut.println("超出范围，请重新输入！");
                continue;
            }
            if(!uf.connected(p, q)){
                StdOut.println("未连接");
                uf.union(p, q);
            }else{StdOut.println("已连接");}
        }
    }
}

class QuickFind {
    private int[] id;
    public QuickFind(int N){
            id = new int[N];
            for(int i=0;i<N;i++){
                id[i]=i;
            }
        }
    void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0;i<id.length;i++){
            if(id[i]==pid) id[i] = qid;
        }
    };
    boolean connected(int p, int q){
        return id[p] == id[q];
    };
    int find(int p){
        return id[p];
    };
    int count(){
        return id.length;
    };
}

class QuickUnion {//初始化
    private int[] id;
    public QuickUnion(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    private int root(int i){
        while(i!=id[i]) i = id[i];
        return i;
    }
    void union(int p, int q){
        int j  = root(p);
        int k = root(q);
        id[j] = k; //j是k的子树
    };
    boolean connected(int p, int q){
        return root(p)==root(q);
    };
    int count(){
        return id.length;
    };
}


class WeightedQuickUnion {//初始化
    private int[] id;
    private int[] sz;//储存树的大小
    public WeightedQuickUnion(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
        sz = new int[N];
    }
    private int root(int i){
        while(i!=id[i]) i = id[id[i]]; //带路径压缩，两层压缩最小树高为2，三层压缩最小数高为3
        return i;
    }
    void union(int p, int q){
        int j  = root(p);
        int k = root(q);
        if(sz[p]>sz[q]){id[p]=k;sz[p]+=sz[q];} //q树连接到p数上
        else{id[q]=j;sz[q]+=sz[p];}

    };
    boolean connected(int p, int q){
        return root(p)==root(q);
    };
    int count(){
        return id.length;
    };
}