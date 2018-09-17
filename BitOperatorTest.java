package BitOperator;

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
		int n = 2147483646;
		System.out.println(getNext(n));
		System.out.println(getNext2(n));
		System.out.println(getNext3(n));
		System.out.println(Integer.MAX_VALUE);
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
}
