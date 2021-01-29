import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class RadixSort {
	
	public static ArrayList<Integer> radixSort(ArrayList<String> list, int flag) {
		
		// cream cate un queue pentru fiecare cifra
		
		Queue<String> radix0 = new ArrayDeque<String>();
		Queue<String> radix1 = new ArrayDeque<String>();
		Queue<String> radix2 = new ArrayDeque<String>();
		Queue<String> radix3 = new ArrayDeque<String>();
		Queue<String> radix4 = new ArrayDeque<String>();
		Queue<String> radix5 = new ArrayDeque<String>();
		Queue<String> radix6 = new ArrayDeque<String>();
		Queue<String> radix7 = new ArrayDeque<String>();
		Queue<String> radix8 = new ArrayDeque<String>();
		Queue<String> radix9 = new ArrayDeque<String>();
		
		ArrayList<Queue<String>> radixList = new ArrayList<Queue<String>>();
		radixList.add(radix0);
		radixList.add(radix1);
		radixList.add(radix2);
		radixList.add(radix3);
		radixList.add(radix4);
		radixList.add(radix5);
		radixList.add(radix6);
		radixList.add(radix7);
		radixList.add(radix8);
		radixList.add(radix9);
		
		// cautam cel mai mare element din lista pentru a determina numarul de pasi
		
		int steps = 0;
		for (String s : list) {
			if (s.length() > steps) {
				steps = s.length();
			}
		}
		
		// adaugam zerouri pana la dimensiunea celui mai mare element din lista
		
		for (int i = 0; i < list.size(); i++) {
			int dif = steps - list.get(i).length();
			String aux = list.get(i);
			while (dif != 0) {
				aux = "0" + aux;
				dif--;
			}
			list.set(i, aux);
		}
		
		// Radix Sort
		
		for (int i = 0; i < steps; i++) {
			for (String s : list) {
				char radix = s.charAt(steps - 1 - i);
				switch (radix) {
				case '0':
					radix0.offer(s);
					break;
				case '1':
					radix1.offer(s);
					break;
				case '2':
					radix2.offer(s);
					break;
				case '3':
					radix3.offer(s);
					break;
				case '4':
					radix4.offer(s);
					break;
				case '5':
					radix5.offer(s);
					break;
				case '6':
					radix6.offer(s);
					break;
				case '7':
					radix7.offer(s);
					break;
				case '8':
					radix8.offer(s);
					break;
				case '9':
					radix9.offer(s);
					break;
				default:
					System.out.println("Eroare: Nu este o cifra.");
				}	
				
			}
			
			list.clear();
			
			/* La fiecare pas actualizam lista de elemente sortate
			 * 
			 * Radix Sort: parcurgem lista de queues (0 -> 9)
			 * Radix Sort descrescator: parcurgem lista de queues (9 -> 0)
			 * la metoda de sortare am setat ca parametru un flag care decide in ce sens se va face aceasta
			 *
			 */
			if (flag >= 0) {
				for (int j = 0; j < radixList.size(); j++) {
					while (!radixList.get(j).isEmpty()) {
						list.add((String)radixList.get(j).poll());
					}	
				}
			} else {
				for (int j = radixList.size() - 1; j >= 0 ; j--) {
					while (!radixList.get(j).isEmpty()) {
						list.add((String)radixList.get(j).poll());
					}
					
				}
			}
			
			for (int j = 0; j < radixList.size(); j++) {
				while (!radixList.get(j).isEmpty()) {
					list.add((String)radixList.get(j).poll());
				}	
			}
			
		}
		
		// convertim elementele din lista de la String la int pentru a elimina zerourile in plus
		
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		
		for (String s : list) {
			sortedList.add(Integer.valueOf(s));
		}
		
		
	return sortedList;	
	}
	

	public static void main(String[] args) {
		int[] list = {1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713};
		ArrayList<String> aList = new ArrayList<String>(list.length);
		
		for (int i = 0; i< list.length; i++) {
			aList.add("" + list[i]);
		}
		
		System.out.println("Lista:");
		System.out.println(aList.toString());
		
		System.out.println("\nLista dupa Radix Sort:");
		System.out.println(radixSort(aList, 1).toString());
		
		System.out.println("\nLista dupa Radix Sort descrescator:");
		System.out.println(radixSort(aList, -1).toString());

	}

}
