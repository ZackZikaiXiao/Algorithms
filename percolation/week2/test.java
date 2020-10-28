/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package week2;

public class
test {
    private int[] array1;
    private int[] array2;
    public void foo() {
        array1 = new int[]{1,2,3,4,5};
        array2 = new int[]{5,4,3,2,1,6,7,8,9,10};
        for(int i = 0;i < array1.length; i++) {
            array1[i] = array2[i];
        }
    }
    public static void main(String[] args) {
        test a = new test();
        a.foo();

    }
}
