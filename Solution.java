import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,5,3,2,2,7,6,4,8,9};
        System.out.println("Error Nums = " + Arrays.toString(sol.findErrorNumsV2(nums)));
    }
    public int[] findErrorNums(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int elt : nums) {
            hashSet.add(elt);
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>(hashSet.stream().toList());
        System.out.println("nums = " + Arrays.toString(nums) + " List = "+list);
        List<Integer> returnList = new ArrayList<>();
        System.out.println("returnList = " + returnList);
        for (int i = list.get(0); i >= 1; i--) {
            if(!returnList.contains(1)) returnList.add(i);
        }
        System.out.println("returnList = " + returnList);
        int[] returnArray = new int[returnList.size()];
        if(returnList.get(0) != 1){
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = returnList.get(i);
            }
            return returnArray;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("nums = " + nums[i] + " List = "+list.get(i));
            if(nums[i] != list.get(i) || (list.get(i) == 1 && list.size() == i + 1))
                return new int[]{nums[i], nums[i] + 1};
            if(!list.contains(1) || list.get(i) != i + 1) return new int[]{nums[i], nums[i] - 1};
        }

        return new int[]{list.getLast(), list.getLast() + 1};
    }
    public int[] findErrorNumsV1(int[] nums){
        Set<Integer> hashSet = new HashSet<>();
        for (int elt : nums) {
            hashSet.add(elt);
        }
        List<Integer> list = new ArrayList<>(hashSet.stream().toList());
        int[] array = new int[nums.length];
        int j = 1;
        boolean flag = false;
        array[0] = findDuplicate(nums);
        for (int i = 0; i < list.size() && j < nums.length ; i++) {
            if(!hashSet.contains(i + 1)){
                array[j] = i + 1;
                j++;
                flag = true;
            }
        }
        if(!flag) array[1] = list.getLast() + 1;
        if(array[0] == 1 && hashSet.size() == 1) array[1]  = 2;
        return Arrays.stream(array).filter(e->e!=0).toArray();
    }

    public int[] findErrorNumsV2(int[] nums){
        int[] freq = new int[nums.length + 1];
        System.out.println("nums = " + Arrays.toString(nums));
        for (int elt : nums
             ) {
            freq[elt]++;
            System.out.println("freq = " + Arrays.toString(freq));
        }
        int dup = 0, miss = 0;
        for (int i = 0; i < freq.length; i++) {
            if(freq[i] == 2) dup = i;
            if(freq[i] == 0) miss = i;
            if(dup != 0 && miss != 0) break;
        }
        return new int[]{dup, miss};
    }
    public int findDuplicate(int[] nums){
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer elt : nums) {
            if(!hashSet.add(elt)) return elt;
        }
        return 0;
    }
}
