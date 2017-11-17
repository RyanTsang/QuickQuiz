package pers.ryan.quickquiz.test;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.ryan.quickquiz.frame.ServerFrame;
import pers.ryan.quickquiz.server.QuestionServer;

public class Test2 {
	public static void main(String[] args) {

	}
	
	@Test
	public void client() {
	}
	
	@Test
	public void arraySortTest() {
		Date startTime = new Date();
		insertSort();
		Integer[] intArray = randomIntegerArray(1000000);
		Arrays.sort(intArray);
		Date endTime = new Date();
		System.out.println(startTime.getTime()-endTime.getTime());
	}
	
	@Test
	/**
	 * Sort Algorithm Sample
	 */
	public void bubbleSort() {
		Date startTime = new Date();
		Integer[] intArray = randomIntegerArray(100000);
//		Integer[] intArray = { 10, 14, 73, 25, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45 };

		for (int j = 0; j < intArray.length - 1; j++) {
			for (int i = j; i < intArray.length - 1; i++) {
				if (intArray[i] > intArray[i + 1]) {
					swapPosition(intArray, i, i + 1);
				}
			}
		}
		Date endTime = new Date();
		System.out.println(startTime.getTime()-endTime.getTime());
	}

	private void swapPosition(Object[] objArray, int firstIndex, int secondIndex) {
		Object temp = objArray[firstIndex];
		objArray[firstIndex] = objArray[secondIndex];
		objArray[secondIndex] = temp;
	}
	
	@Test
	public void insertSort() {
//		Date startTime = new Date();
//		Integer[] intArray = randomIntegerArray(100000);
		Integer[] intArray = { 10, 14, 73, 25, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45,0 };
		
		for (int i = 1; i < intArray.length; i++) {
			Integer temp = intArray[i];
			for(int j = i - 1; j >= 0; j --){
				if(temp < intArray[j]){
					intArray[j + 1] = intArray[j];
				}else{
					intArray[j + 1] = temp;
					break;
				}
				//intArray[j] = temp;
			}
		}
		
		return;
//		Date endTime = new Date();
//		System.out.println(startTime.getTime()-endTime.getTime());
	}
	
	@Test
	public void mergeSort(){
		Date startTime = new Date();
		Integer[] intArray = randomIntegerArray(63);
		//		Integer[] intArray = { 10, 14, 73, 25, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45 };
//		Integer[] intArray = { 73, 25, 45 };
		Integer[][] int2DArray = new Integer[intArray.length][];
		for(int i = 0; i < intArray.length; i++){
			int2DArray[i] = new Integer[1];
			int2DArray[i][0] = intArray[i];
		}
		
		while(int2DArray.length != 1){
			int2DArray = merge2DIntArray(int2DArray);
		}
		Date endTime = new Date();
		System.out.println(startTime.getTime()-endTime.getTime());
		
	}
	
	private Integer[] mergeIntArray(Integer[] aArray, Integer[] bArray){
		Integer[] res = new Integer[aArray.length + bArray.length];
		Iterator<Integer> aArrayIter = Arrays.asList(aArray).iterator();
		Iterator<Integer> bArrayIter = Arrays.asList(bArray).iterator();
		int resIndex = 0;
		Integer a = null;
		Integer b = null;
		while(aArrayIter.hasNext() || bArrayIter.hasNext() || a != null || b != null){
			if(!bArrayIter.hasNext() && b == null){
				res[resIndex++] = (a == null? aArrayIter.next() : a);
				a = null;
			}else if(!aArrayIter.hasNext() && a == null){
				res[resIndex++] = (b == null? bArrayIter.next() : b);
				b = null;
			}else{
				if(a == null) a = aArrayIter.next();
				if(b == null) b = bArrayIter.next();
				
				if(a > b){
					res[resIndex++] = b;
					b = null;
				}else{
					res[resIndex++] = a;
					a = null;
				}
			}
		}
		
		return res;
	}
	
	private Integer[][] merge2DIntArray(Integer[][] intArray){
		Integer[][] res = new Integer[intArray.length/2][];
		for(int i = 0,j = 0; i < intArray.length; i++, j++){
			if(res.length == 1){
				if(j == 0){
					res[j] = mergeIntArray(intArray[i], intArray[++i]);
				}
				if(j > 0){
					res[j-1] = mergeIntArray(res[j-1], intArray[i]);
				}
			}else{
				if(i != intArray.length -1){
					res[j] = mergeIntArray(intArray[i], intArray[++i]);
				}else{
					res[j-1] = mergeIntArray(res[j-1], intArray[i]);
				}
			}
		}
		return res;
	}
	
	private Integer[] randomIntegerArray(int number){
		Integer[] res = new Integer[number];
		Random r = new Random();
		for(int i = 0; i < number; i++){
			res[i] = r.nextInt(1000);
		}
		return res;
	}
}