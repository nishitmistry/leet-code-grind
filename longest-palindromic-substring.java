import java.util.*;
//  Difficulty :- Medium
//  https://leetcode.com/problems/longest-palindromic-substring/
//  Approach 
//  let say the string is "bacabad", we are tasked to find the longest palindome substring
//  we first list down all the occurences of each character in the form of a hashmap 
//  so, map = {b:[0,4], a:[1,3,5], c:[2], d:[6]}
//  after we find each character's occurence, we loop over the keys of map ( unique charactes in the string)
//  and find the largest range from the listed occurences

class Solution {
    // if the string is palindrome between the start and end 
    public boolean isPalindrome(String s, int start, int end )
    {
        try{
            for (int i = start, j = end; i <= j; i++, j--) 
            {
                if(s.charAt(i) != s.charAt(j))
                {
                    return false;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        //hold the map of  { character : occurences index list }
        HashMap<Character,List<Integer>> map = new HashMap();
        Solution sol = new Solution();
        //store the start and end of the longest substring
        int start = -1;
        int end = -1;
        //map all the occurences of characters
        for(int i = 0; i< s.length(); i++)
        {
            if(map.get(s.charAt(i)) == null)
            {
                List<Integer> a = new ArrayList<>();
                a.add(i);
                map.put(s.charAt(i), a);
            }
            else{
                map.get(s.charAt(i)).add(i);
            }
        }
        //loop over the keys of the map 
        for (char i : map.keySet())
        {
            List<Integer> list = map.get(i);
            int list_length = list.size();
            //  if the list lenght is 1 or less than 1, 
            //  then its a unique character and cannot be considered for palindrome substring range finding
            // you cannot find substring from just c:[2], we need a range like b:[0,4]
            if(list_length <=1)
            {
                continue;
            }
            // we loop over all the combination of two elment from array of occurence index we found earlier
            for(int j = 0; j < list_length - 1; j++)
            {
                // we don't want to reiterate for already iterated combination 
                for(int k = j+1; k< list_length; k++)
                {
                    int curr_start = list.get(j);
                    int curr_end = list.get(k);
                    // here if the curr_start and curr_end is in between the already found range then we will continue 
                    // doing the reverse, that is continue if the curr_start and curr_end are outside the found range is not good
                    // because there can be some case that greater range. 
                    if(start != -1 && curr_start > start && curr_start < end && curr_end > start && curr_end < end )
                    {
                        continue;
                    }
                    // if the current range size is greater than the already found one then only check for palindrome.
                    if((start == -1 || (curr_end - curr_start) > (end - start)) && sol.isPalindrome(s, curr_start, curr_end) )
                    {
                        start = curr_start;
                        end = curr_end;
                    }
                }

            }
        }
        // if start is -1 that mean no string was found.
        if( start == -1)
        {
            return s.substring(0,1);
        }
        return s.substring(start, end +1);
    }
}
public class p1
{
    public static void main(String[] args)
    {
        Solution s = new Solution();
        System.out.println(s.longestPalindrome("bacabab"));

    }
}