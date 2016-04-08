public class bit4TobitN {

	public static int bit4_AdderCarry = 0; // initialize adderCarry for overflow value


	public static int [] bit4_Adder(int carry, int[] A, int[] B){

		int temp_carry = carry; // copy carry value to temp_carry

		int bit4_result[] = new int[4]; // declare size 4 array for saving result of adding 4bit

		for (int i=3;i>=0;i--) {
			bit4_result[i] = A[i] + B[i] + temp_carry;
			temp_carry = bit4_result[i]/2;
			bit4_result[i] %= 2;
		}

		bit4_AdderCarry = temp_carry;

		/*************************************************************************************************/
			// you will code in here for bit4_adder


			// hint 1: use 'for' to assign result of adding each bit to each bit in bit4_result[]
			// hint 2: use 'if' to check temp_carry before adding each bit of A and B
			// hint 3: think about case temp_carry + same bit of A and B, 0??? 1??? 2??? 3???
			// hint 4: temp_carry will be updated after calculation of each bit
			// hint 5: update 4bitAdderCarry at adding last bit of A and B
			//
			// usage: bit4_result[i] = ???
			// usage: temp_carry = ???
			// usage: bit4_AdderCarry = ???
		/*************************************************************************************************/

		return bit4_result; // this function return result formed of size 4 array
	}
	public static int [] bit8_Adder(int carry, int[] A, int[] B){


		int bit8_result[] = new int[8]; // declare size 8 array for saving result of adding 8bit

		int A_low4bit[] = new int[4]; // declare size 4 array for saving low 4bit of A
		int B_low4bit[] = new int[4]; // declare size 4 array for saving low 4bit of B

		int A_high4bit[] = new int[4]; // declare size 4 array for saving high 4bit of A
		int B_high4bit[] = new int[4]; // declare size 4 array for saving high 4bit of B

		for (int i=0;i<4;i++) {
			A_low4bit[i] = A[i+4];
			A_high4bit[i] = A[i];

			B_low4bit[i] = B[i+4];
			B_high4bit[i] = B[i];
		}

		/*************************************************************************************************/
		// you will code in here for save respectively low 4 bit of A[], B[] to A_low4bit[] and B_low4bit[]
		//
		// hint 1: use 'for' to assign low 4 bit of A[], B[] to A_low4bit[] and B_low4bit[]
		// hint 2: think about different index between low 4bit of A[], B[] and A_low4bit[] and B_low4bit[]


		// you will code in here for save respectively high 4bit of A[], B[] to A_high4bit[] and B_high4bit[]
		//
		// hint 1: code priniple may be similar with above for low 4bit.
		/*************************************************************************************************/

		int result_low4bit[] = new int [4];
		int result_high4bit[] = new int [4];

		result_low4bit = bit4_Adder(0,A_low4bit,B_low4bit);
		result_high4bit = bit4_Adder(bit4_AdderCarry,A_high4bit,B_high4bit);
		for (int i = 7; i>=4; i--){

			bit8_result[i] = result_low4bit[i-4];
		}
		// assign low 4bit result of bit4_adder to low 4bit of bit8_result
		// do not touch this line and it may be hint for above


		for (int i =3; i>=0; i--){

			bit8_result[i] = result_high4bit[i];

		}
		// assign high 4bit result of bit4_adder to high 4bit of bit8_result
		// also do not touch this line and it may be hint for above

		return bit8_result; // this function return result formed of size 8 array
	}


	public static void main(String[] args){

	  int test_A4bit[] = {1,0,1,0}; // declare some size 4 array for test. you can revised this line for testing other things
	  int test_B4bit[] = {0,1,1,0}; // declare some size 4 array for test. you can revised this line for testing other things


	  System.out.print("test for 4bit adder : ");

	  int result_test4bit[] = new int [4];
	  result_test4bit = bit4_Adder(0,test_A4bit,test_B4bit);

	  for(int i = 0; i<4; i++){

		  System.out.print(result_test4bit[i] + " ");

	  }
	  System.out.println("");
	  // print result of 4bit adder

	  bit4_AdderCarry = 0; // initialize for removing remain value for past work

	  int test_A8bit[] = {0,0,1,0,1,1,1,0};
	  int test_B8bit[] = {0,1,1,1,1,1,1,1};

	  int result_test8bit[] = new int [8];
	  result_test8bit = bit8_Adder(0,test_A8bit,test_B8bit);

	  System.out.print("test for 8bit adder : ");
	  for(int i = 0; i<8;i++){

		  System.out.print(result_test8bit[i] + " ");

	  }
	  System.out.println("");
      // print result of 8bit adder
	}


}
