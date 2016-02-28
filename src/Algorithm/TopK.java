package Algorithm;

/**
 * Created by hidden on 14-6-2.
 */
public class TopK {
    public int[] getTopK(int[] array, int k) {
        if (array == null || array.length == 0)
            return null;
        //Heap h = new Heap(k + 1);
        int[] items = new int[k];
        for (int i : array) {
            if(i > items[0])
            {
                items[0] = i;
                int p = 0, q=0;
                while(p < k)
                {
                    q = 2 * p + 1;
                    if(q >= k)
                        break;
                    if((q < k-1) && (items[q + 1] < items[q]))
                        q = q + 1;
                    if(items[q] < items[p])
                    {
                        int temp = items[p];
                        items[p] = items[q];
                        items[q] = temp;
                        p = q;
                    }
                    else
                        break;
                }
            }
            //h.add(i);
        }
//        int[] temp = h.getItems();
//        int[] result = new int[k];
//        for (int i = 0; i < k; i++) {
//            result[i] = temp[i];
//        }
        return items;
    }

    class Heap {
        private int[] items;
        private int cursor; //用于计数

        public Heap(int size) {
            items = new int[size];
            cursor = 0;
        }

        void siftUp(int index) {
            int intent = items[index];
            while (index > 0) {
                int pindex = (index - 1) / 2;
                int parent = items[pindex];
                if (intent < parent) {
                    items[index] = parent;
                    index = pindex;
                } else break;
            }
            items[index] = intent;
        }

        public void add(int item) {



            items[cursor] = item;
            siftUp(cursor);
            if (cursor < items.length-1)
                cursor++;
        }

        public int[] getItems() {
            return items.clone();
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 6, 8, 10, 23, 56, 43, 13, 75, 24, 97, 12, 45, 46, 22, 33, 76, 12};
        TopK topk = new TopK();
        int[] res = topk.getTopK(array, 5);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
