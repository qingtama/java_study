package Algorithm;

/**
 * Created by hidden on 14-6-2.
 */
public class DuplicateArray {

    public int[] dedupliArray(int[] array){
        if(array==null || array.length==0)
            return null;
        int index = 0;
        int[] temp = new int[array.length];
        int[] hash = new int[array.length];
        for(int i : array){
            if(checkAndAdd(i,hash)) {
                temp[index] = i;
                index++;
            }
        }
        int[] result = new int[index];
        for(int k=0; k<index; k++){
            result[k] = temp[k];
        }
        return result;
    }

    private boolean checkAndAdd(int i, int[] hash){
        boolean res = false;
        if(i>hash.length){
            int[] newHash = new int[2*hash.length];
            for(int j =0; j<hash.length; j++){
                newHash[j] = hash[j];
            }
            newHash[i-1] = 1;
            hash = newHash;
            res = true;
        }else if(hash[i-1]==0){
            hash[i-1]=1;
            res = true;
        }
        return res;
    }

    public static void  main(String[] args){
        int[] array = {1,2,4,3,6,4,2,15,7,8,5};
        DuplicateArray d = new DuplicateArray();
        int[] result = d.dedupliArray(array);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
