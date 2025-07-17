class Solution {
    public int calculate(String s) {
        char[] chs = s.toCharArray();
        int curRes=0, val=0, res=0;
        char sign='+';

        for (int i=0; i<chs.length; i++) {
            char c=chs[i];

            if(Character.isDigit(c))
            val=val*10+c-'0';
            
            if ( c=='+' ||c=='-' ||c=='*' ||c=='/' ||i==chs.length-1){
                switch(sign){
                    case('+'):
                    res+=curRes;
                    curRes=val;
                    break;

                    case('-'):
                    res+=curRes;
                    curRes=-val;
                    break;

                    case('*'):
                    curRes*=val;
                    break;

                    case('/'):
                    curRes/=val;
                    break;
                }
                sign=c;
                val=0;
            }
        }
        return res+curRes;
    }    
}