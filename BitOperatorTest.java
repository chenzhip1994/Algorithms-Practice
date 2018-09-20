package BitOperator;

import java.util.ArrayList;

public class BitOperatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BitOperatorTest().run();
	}
	
	public void run() {
//		int a = 0b101010101010101010101010101;
//		int b = 0b11111;
//		
//		int c = updateBits(a,b,2,8);
//		System.out.println(c);
		int n = 1024;
//		
//		int c = n;
//		while(c!= 0) {
//			System.out.println(Integer.toBinaryString(c));
//			c = c & c-1;
//		}
//		
		
//		ArrayList<Integer> values = new ArrayList<Integer>();
//		values.add(0);
//		values.add(1);
//		values.add(2);
//		values.add(3);
//		values.add(4);
//		values.add(5);
//		values.add(6);
//		values.add(7);
//		values.add(8);
//		values.add(9);
//		values.add(10);
//		System.out.println(findMissingR(values,0));
		
//		System.out.println(Integer.toBinaryString(n));
//		System.out.println(Integer.toBinaryString(this.swapOddEvenBits(n)));
//		
//		System.out.println(getNext(n));
//		System.out.println(getNext2(n));
//		System.out.println(getNext3(n));
//		System.out.println(getNext4(n));
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(this.countDiffBits(3, 4));
		
		byte[] screen = {(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00};
		printScreen(screen, 32);
		drawHorilontalLine(screen,32,1,2,1);
		printScreen(screen, 32);
		
	}
	
	public int updateBits(int n,int m,int i,int j) {
		m = m<<i;
		int maskn = ((1<<(j+1)) - 1) & ~((1<<i) - 1);
		System.out.println(maskn);
		n = n & ~maskn;
		return m | n;
	}
	
	public String DecimaltoBinary(double num) {
		
		double tempNum = num;
		StringBuffer sb = new StringBuffer();
		sb.append("0.");
		while(tempNum > 0) {
			tempNum = tempNum * 2.0;
			if(tempNum >=1) {
				sb.append("1");
				tempNum = tempNum - 1.0;
			}else {
				sb.append("0");
			}
			if(sb.length() > 32) {
				sb.toString();
			}
		}
		
		return sb.toString();
	}
	
	public int getNext(int n) {
		int c0 = 0, c1=0;
		int tempn = n;
		while((tempn & 1) == 0 && tempn != 0) {
			c0++;
			tempn = tempn >> 1;
		}
		
		while((tempn & 1) == 1) {
			c1++;
			tempn = tempn >>1;
		}
		if(c1+c0 >=31 || c1+c0 ==0) {
			return -1;
		}
		int p = c0+c1;
		int mask1 = ((1<<c1-1)-1);
		int mask0 = ~((1<<(p)) - 1) ;
		
		return ((n | (1<<p)) & mask0) | mask1;
	}
	
	public int count1bits(int n) {
		int count = 0;
		while(n != 0) {
			if((n & 1) == 1) {
				++count;
			}
			n = n>>1;
		}
		return count;
	}
	public int getNext2(int n) {
		
		if(n == 0) return -1;

		int countn = count1bits(n);
		
		int nextN = n;
		do {
			++nextN;
			if(count1bits(nextN) == countn) {
				return nextN;
			}
		}while(nextN < Integer.MAX_VALUE);
		
		return -1;
	}
	
	public int getNext3(int n) {
		if(n == 0) return -1;
		int tempN = n;
		int preBit = tempN & 1;
		int currentBit ;
		int p = 0;
		boolean flag = false;
		int count1 = preBit==1? 1:0;
		while(tempN != 0){
			tempN = tempN >>1;
			currentBit = tempN & 1;
			++p;
			count1 = currentBit == 1? (count1+1):count1;
			if(preBit == 1 && currentBit == 0) {
				flag = true;
				break;
			}
			preBit = currentBit;
		}
		if (flag == false  || p >= 31) {
			return -1;
		}
		n = n | (1<<p) ;
		n = n & ~((1<<p)-1);
		n = n | ((1<<count1-1)-1);
		return n;
	}
	
	public int getNext4(int n) {
		int c0 = 0, c1=0;
		int tempn = n;
		while((tempn & 1) == 0 && tempn != 0) {
			c0++;
			tempn = tempn >> 1;
		}
		
		while((tempn & 1) == 1) {
			c1++;
			tempn = tempn >>1;
		}
		if(c1+c0 >=31 || c1+c0 ==0) {
			return -1;
		}
		int p = c0+c1;
		return ((n | ((1<<p) -1)) + 1) | ((1<<(c1-1)) -1);
	}
	
	
	public int countDiffBits(int a, int b) {
		int count = 0;
		int c = a^b;
		while(c!= 0) {
			if((c & 1) == 1) {
				count++;
			}
			c = c>>1;
		}
		return count;
	}
	public int getPrev2(int n) {
		
		if(n == 0) return -1;

		int countn = count1bits(n);
		
		int nextN = n;
		do {
			--nextN;
			if(count1bits(nextN) == countn) {
				return nextN;
			}
		}while(nextN > Integer.MIN_VALUE);
		
		return -1;
	}
	
	public int swapOddEvenBits(int n) {
		return (n&0xaaaaaaaa)>>1 | ((n & (0xaaaaaaaa>>1))<<1);
	}
	
	
	public int findMissingR(ArrayList<Integer> array, int position) {
		if(position >= Integer.BYTES * 8 - 1) {
			return 0;
		}
		if(array.size() <= 0) {
			return 0;
		}
		int oneBits = 0, zeroBits = 0;
		for(Integer num : array) {
			int bitValue = (num >> position) & 1;
			if(bitValue == 0) {
				++zeroBits;
			}else {
				++oneBits;
			}
		}
		int valueBit = zeroBits <=oneBits ? 0:1 ;
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		for(Integer num : array) {
			if(((num >> position) & 1) == valueBit) {
				newArray.add(num);
			}
		}
		
		return (valueBit<<position) | findMissingR(newArray, position + 1);
	}
	
	public void drawHorilontalLine(byte[] screen, int width, int x1, int x2, int y) {
		int locX1 = (y*width + x1) /8 + 1;
		int offsetX1 = (y*width + x1) %8;
		int locX2 = (y*width + x2)/8;
		int offsetX2 = (y*width +x2) %8;
		
		
		if(locX1 > locX2) {
			int mask = ((1<< (offsetX2 - offsetX1 + 1)) - 1)<<(7-offsetX2);
			screen[locX2] |=mask;
		}else {
			for(int i=locX1;i<locX2;++i) {
				screen[i] = (byte) 0xFF;
			}
			int mask1 = (1<< (7 -offsetX1 + 1)) - 1;
			int mask2 = ((1<< (7 -offsetX1 + 1)) - 1) << (7-offsetX2);
			screen[locX1 - 1] |= mask1;
			screen[locX2] |= mask2;
		}
	}
	
	public void printScreen(byte[] screen, int width) {
		for(int i=0;i<screen.length;++i) {
			if((i*8)%width == 0) System.out.println();
			System.out.print(Integer.toBinaryString(screen[i]) + " ");
		}
	}
}
