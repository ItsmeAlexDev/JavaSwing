package challenges.easy;

public class FirstFactorial {

	/*
	 * Challenge: First Factorial
	 * 
	 * Have the function FirstFactorial(num) take the num parameter
	 * being passed and return the factorial of it.
	 * For example: if num = 4, then your program 
	 * should return (4 * 3 * 2 * 1) = 24. 
	 * For the test cases, the range will be between 
	 * 1 and 10 and the input will always be an integer.
	 */

	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Test for num = " + i + ":");
			System.out.println(FirstFactorial(i));
		}
	}
	
	public static int FirstFactorial(int num) {
		return num == 0 ? 1 : num * FirstFactorial(num - 1);
	}
}