import java.util.Random;

//create random integer array class 
public class RAndom {
	private Random random=new Random();
	private int[] array=new int[51];
	
	//constructor make Non-duplicate numbers array up to 50
	RAndom(){
		for (int i=0;i<51;++i) {
			array[i]=random.nextInt(51);
			for (int j=0;j<i;++j) {
				if (array[i]==array[j]) {
					i--;
				}
			}
		}
	}
	
	//random integer of index i get method
	public int getNum(int i) {
		return array[i];
	}
}
