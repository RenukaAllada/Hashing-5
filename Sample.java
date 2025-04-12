class Sample{
    /*****************PROBLEM-1****************/
    class Solution {
        Map<Character,Integer> map;
        public boolean isAlienSorted(String[] words, String order) {
            if(words.length==0 || words==null){
                return false;
            }
            map=new HashMap<>();

            for(int i=0;i<order.length();i++){
                map.put(order.charAt(i),i);
            }

            for(int i=0;i<words.length-1;i++){
                String word1=words[i];
                String word2=words[i+1];
                if(!compare(word1,word2)){
                    return false;
                }
            }
            return true;
        }

        private boolean compare(String word1,String word2){
            if(word1.equals(word2)){
                return true;
            }
            int l1=word1.length(),l2=word2.length();
            int min=Math.min(l2,l1);
            for(int i=0;i<min;i++){
                if(map.get(word1.charAt(i)) < map.get(word2.charAt(i))){
                    return true;
                }else if(map.get(word1.charAt(i)) > map.get(word2.charAt(i))){
                    return false;
                }
            }
            if(l2<l1){
                return false;
            }else{
                return true;
            }
        }
    }

    /**********************PROBLEM-2*********************/
    //TC:0(N*K)
//SC:0(N)
    class Solution {
        Map<Character,List<Character>> map;
        int[] indegrees;
        public String alienOrder(String[] words) {
            if(words.length==0 || words==null){
                return "";
            }
            map=new HashMap<>();
            indegrees=new int[26];

            buildGraph(words);
            Queue<Character> q=new LinkedList<>();
            StringBuilder sb=new StringBuilder();
            for(char key:map.keySet()){
                if(indegrees[key-'a']==0){
                    q.add(key);
                }
            }

            while(!q.isEmpty()){
                char curr=q.poll();
                sb.append(curr);
                List<Character> allChars=map.get(curr);
                if(allChars==null){
                    continue;
                }
                for(char each:allChars){
                    indegrees[each-'a']--;
                    if(indegrees[each-'a']==0){
                        q.add(each);
                    }
                }
            }
            if(sb.length()!=map.size()){
                return "";
            }
            return sb.toString();
        }

        private void buildGraph(String[] words){
            for(String word:words){
                for(int i=0;i<word.length();i++){
                    char c=word.charAt(i);
                    if(!map.containsKey(c)){
                        map.put(c,new ArrayList<>());
                    }
                }
            }
            for(int i=0;i<words.length-1;i++){
                String first=words[i];
                String second=words[i+1];
                int m=first.length(),n=second.length();
                if(first.startsWith(second) && m>n){
                    map.clear();
                    return;
                }
                for(int j=0;j<m && j<n;j++){
                    char firstChar=first.charAt(j);
                    char secondChar=second.charAt(j);
                    if(firstChar!=secondChar){
                        indegrees[secondChar-'a']++;
                        map.get(firstChar).add(secondChar);
                        break;
                    }
                }
            }
        }
    }

}