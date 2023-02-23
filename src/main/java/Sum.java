public class Sum {

    public static void main(String[] args) {
        System.out.println("Sum of 1 through 5 (R): "+sumRecursive(5));
        System.out.println("Sum of 1 through 5 (I): "+sumIterative(5));
    }

    public static int sumRecursive(int n){
        if(n <= 1){
            return 1;
        }
        else{
            return n+sumRecursive(n-1);
        }
    }

    public static int sumIterative(int n){
        int result=0,i=1;
        while(i<=n){
            result=result+i;
            i++;
        }

        return result;
    }
}
