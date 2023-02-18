import java.util.*;  
public class threepartition  
{  
    // Helper method for computing the 3-partition problem.
    // If it returns true, then there exist three disjoint subsets with the sum  
    public boolean disjointSubsetSum(int arr[], int size, int i, int j, int k, Map<String, Boolean> dp, int store[])  
    {  
        // return true if the subset is found  
        if (i == 0 && j == 0 && k == 0)   
        {  
            return true;  
        }  

        // handling the base case: when no items are left  
        if (size < 0)   
        {  
            return false;  
        }  

        // constructing a unique key  
        String key = i + "|" + j + "|" + j + "|" + size;  
          
        if(!dp.containsKey(key))  
        {  
            // Case 1: The first subset contains the current element  
            boolean c1 = false;  
            if (i - arr[size] >= 0) 
            {  
                store[size] = 1;

                c1 = disjointSubsetSum(arr, size - 1, i - arr[size], j, k, dp, store);  
            }  

            // Case 2: The second subset contains the current element  
            boolean c2 = false;  
            if (!c1 && (j - arr[size] >= 0))   
            {  
                store[size] = 2;

                c2 = disjointSubsetSum(arr, size - 1, i, j - arr[size], k, dp, store);  
            }  

            // Case 3: The third subset contains the current element  
            boolean c3 = false;  

            if ((!c1 && !c2) && (k - arr[size] >= 0))   
            {  
                store[size] = 3;

                c3 = disjointSubsetSum(arr, size - 1, i, j, k - arr[size], dp, store);  
            }  

            // if either of the three cases is true, then we can return true  
            dp.put(key, c1 || c2 || c3) ;  
        }  

        return dp.get(key);  
    }  

    // method for computing the 3 partitions problem. It returns true if the given  
    // set `arr[0 ... n - 1]` can be split into a set of three disjoint subsets with the equal sum.  
    public boolean partitionInThree(int arr[], int store[])  
    {  
        // it is obvious that for the three disjoint subsets  
        // there have to be at least three elements present in the given array  
        if (arr.length < 3)   
        {  
            return false;  
        }  

        // get the sum of all of the elements of the given array  
        int SumOfElement = 0;  
        for(int i = 0; i < arr.length; i++)  
        {  
            SumOfElement = SumOfElement + arr[i];  
        }  

        // creating a map for keeping the solution of the subproblems   
        // so that they are not computed  
        Map<String, Boolean> dp = new HashMap<>();

        // return true if the sum of all the elements is divisible by 3 and   
        // the input array can be divided into the three disjoint subsets with the equal sum  
        if((SumOfElement % 3) == 0)  
        {  
            if (disjointSubsetSum(arr, arr.length - 1, SumOfElement / 3, SumOfElement / 3, SumOfElement / 3, dp, store))  
            {  
                return true;  
            }

            return false;  
        }  

        return false;  
    }  

    void display(int arr[], int store[])  
    {  
        // print the partitions  
        for (int j = 0; j < 3; j++)  
        {  
            System.out.print("Partition " + j + " is ");

            for (int k = 0; k < arr.length; k++)  
            {  
                if (store[k] == j + 1) 
                {  
                    System.out.print(arr[k] + " ");  
                }  
            }  

            System.out.println();  
        }  
    } 

    // main method   
    public static void main(String argvs[])  
    {  
        // creating an object of the class   
        threepartition obj = new threepartition(); 

        // Input 1  
        int arr[] = {6, 2, 2, 5, 3, 2, 4, 4, 2};  

        int size = arr.length; 

        int[] store = new int[arr.length];  

        System.out.println("For the input array 1 : ");  

        for(int i = 0; i < size; i++)  
        {  
            System.out.print(arr[i]+ " "); 
        }  

        System.out.println("\n");    

        if (obj.partitionInThree(arr, store))   
        {  
            System.out.println("It is possible to partition it into three disjoint subsets.");  

            obj.display(arr, store); 
        }  

        else   
        {  
            System.out.println("It is not possible to partition it into three disjoint subsets.");  
        }   

        System.out.println();

        // Input 2  
        int arr2[] = {6, 2, 2, 5, 3, 2, 4, 3, 2};  

        size = arr2.length;  

        System.out.println("For the input array 2 : ");  

        for(int i = 0; i < size; i++)  
        {  
            System.out.print(arr2[i]+ " ");  
        }  

        System.out.println();  

        if (obj.partitionInThree(arr2, store))   
        {  
            System.out.println("It is possible to partition it into three disjoint subsets.");

            obj.display(arr, store);   
        }  

        else   
        {  
            System.out.println("It is not possible to partition it into three disjoint subsets.");  
        }  
    }  
}  
