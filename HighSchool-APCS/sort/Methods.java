import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author JacobFreakingHarris
 */
public class Methods{
	public static ArrayList<Comparable> tmp;
	public static MediaPlayer media;
	public static JFrame frame;
	public static JPanel panel;
	public static GridBagConstraints constraints;
	public static JMenu menu;
	public static ArrayList<Component> components;
	
	public static void main(String[] args){
		calculator();
		//swagishTranslator();
	}
	
	/**
	 * runs a simple postfix calculator in the console.
	 * operations supported: +, -, *, /, ^.
	 * 
	 * 			How to use:
	 * typing "off" or "o" stops calculator.
	 * typing "duplicate" or "d" duplicates top.
	 * typing "removeall" or "ra" removes all.
	 * typing "remove" or "r" removes top.
	 * typing "storeall" or "sa" stores all.
	 * typing "store" or "s" stores top.
	 * typing "getall" or "ga" gets all.
	 * typing "get#" or "g#" gets stored.
	 * 
	 * 			Constants you can enter: 
	 * "PI" or "pi"
	 * "naturallog" or "e"
	 * "lightspeed" or "c"
	 * "gravitationalconstant" or "G"
	 */
	public static void calculator(){
		class Stack {
			class ListNode {
				private Object   value;
				private ListNode next;
				
				ListNode(Object value, ListNode next){
					this.value = value;
					this.next  = next;
				}
				
				public Object getValue(){
					return value;
				}
				
				public ListNode getNext(){
					return next;
				}
				
				public void setValue(Object value){
					this.value = value;
				}
			}
			
			private ListNode start = new ListNode(null,null);
			
			public boolean isEmpty(){
				if(start.getValue() == null)
					return true;
				return false;
			}
			
			public Object push(Object o){
				if(isEmpty())
					start.setValue(o);
				else
					start = new ListNode(o,start);
				return peek();
			}
			
			public Object pop() {
				Object tmp = peek();
				if(start.getNext()==null)
					start.setValue(null);
				else
					start = start.getNext();
				return tmp;
			}
			
			public Object peek() {
				return start.getValue();
			}
			
			public String toString(){
				try{
					ListNode tmp = start.getNext();
					String   s   = start.getValue().toString();
					while(tmp!=null){
						s  += ", "+tmp.getValue();
						tmp = tmp.getNext();
					}
					return s;
				}
				catch(NullPointerException e){
					return "null";
				}
			}
		}
		
		Stack   		  list    = new Stack();
		ArrayList<Object> list2   = new ArrayList<Object>();
		Scanner 		  scanner = new Scanner(System.in);
		while(null==null){
			String s = scanner.next();
			if(s.equals("off") || s.equals("o"))
				System.exit(0);
			else if(s.equals("duplicate") || s.equals("d"))
				list.push(list.peek());
			else if(s.equals("removeall") || s.equals("ra"))
				list = new Stack();
			else if(s.equals("remove") || s.equals("r"))
				list.pop();
			else if(s.equals("storeall") || s.equals("sa"))
				while(!list.isEmpty())
					list2.add(list.pop());
			else if(s.equals("store") || s.equals("s"))
				list2.add(list.pop());
			else if(s.equals("getall") || s.equals("ga"))
				while(!list2.isEmpty())
					list.push(list2.remove(0));
			else if(s.length()==4 && s.substring(0,3).equals("get") && is("Integer",s.substring(3))){
				try{
					Integer i = Integer.parseInt(s.substring(3));
					list.push(list2.remove(i-1));
				}
				catch(IndexOutOfBoundsException e){
				}
			}
			else if(s.length()==2 && s.substring(0,1).equals("g") && is("Integer",s.substring(1))){
				try{
					Integer i = Integer.parseInt(s.substring(1));
					list.push(list2.remove(i-1));
				}
				catch(IndexOutOfBoundsException e){
				}
			}
			else if(s.equals("PI") || s.equals("pi"))
				list.push(Math.PI);
			else if(s.equals("naturallog") || s.equals("e"))
				list.push(Math.E);
			else if(s.equals("lightspeed") || s.equals("c"))
				list.push(299792458.0);
			else if(s.equals("gravitationalconstant") || s.equals("G"))
				list.push(0.0000000000667384);
			else if(is("Double",s)==true){
				Double d = Double.parseDouble(s);
				list.push(d);
			}
			else{
				Double d2 = (Double) list.pop();
				Double d  = (Double) list.pop();
				try{
					if(s.equals("+"))
						d2 = d + d2;
					else if(s.equals("-"))
						d2 = d - d2;
					else if(s.equals("*"))
						d2 = d * d2;
					else if(s.equals("/"))
						d2 = d / d2;
					else if(s.equals("^"))
						d2 = Math.pow(d,d2);
					else{
						System.out.println("Error, invalid operation \""+s
								+"\".");
						list.push(d);
					}
				}
				catch(NullPointerException e){
					System.out.println("Error, tried to use operation \""+
								s+"\" without two numbers.");
				}
				Double n = 0.0/0.0;
				if(d2!=null)
					if(new Double(1)/0 == d2 || n.equals(d2))
						System.out.println("Error, can't divide by 0.");
					else
						list.push(d2);
			}
			System.out.println(list.toString());
			System.out.println(list2.toString());
			System.out.println();
		}
	}
	
	/**
	 * translates between English and my language, Swagish, which is a rearrangement of the words "swag" and "yolo"
	 */
	public static void swagishTranslator(){
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to the Swagish Translator.");
		while(null==null){
			System.out.println("Do you want to translate from Swagish to English, or from English to Swagish?");
			String temp = "", temp2 = "", temp3 = s.nextLine();
			System.out.println("Enter the sentence or word you would like to translate.");
			temp = s.nextLine();
			if(temp3.substring(0,1).equals("S") || temp3.substring(0,1).equals("s")){
				while(null==null){
					int x = temp.indexOf("-");
					if(x==-1)
						break;
					temp3 = temp.substring(0,x);
					if(temp3.equals("Aa"))
						temp2+="A";
					else if(temp3.equals("aa"))
						temp2+="a";
					else if(temp3.equals("Sw"))
						temp2+="B";
					else if(temp3.equals("sw"))
						temp2+="b";
					else if(temp3.equals("Gs"))
						temp2+="C";
					else if(temp3.equals("gs"))
						temp2+="c";
					else if(temp3.equals("Sa"))
						temp2+="D";
					else if(temp3.equals("sa"))
						temp2+="d";
					else if(temp3.equals("S"))
						temp2+="E";
					else if(temp3.equals("s"))
						temp2+="e";
					else if(temp3.equals("Oo"))
						temp2+="F";
					else if(temp3.equals("oo"))
						temp2+="f";
					else if(temp3.equals("Gg"))
						temp2+="G";
					else if(temp3.equals("gg"))
						temp2+="g";
					else if(temp3.equals("Sg"))
						temp2+="H";
					else if(temp3.equals("sg"))
						temp2+="h";
					else if(temp3.equals("W"))
						temp2+="I";
					else if(temp3.equals("w"))
						temp2+="i";
					else if(temp3.equals("Gw"))
						temp2+="J";
					else if(temp3.equals("gw"))
						temp2+="j";
					else if(temp3.equals("Ws"))
						temp2+="K";
					else if(temp3.equals("ws"))
						temp2+="k";
					else if(temp3.equals("L"))
						temp2+="L";
					else if(temp3.equals("l"))
						temp2+="l";
					else if(temp3.equals("Wa"))
						temp2+="M";
					else if(temp3.equals("wa"))
						temp2+="m";
					else if(temp3.equals("Ga"))
						temp2+="N";
					else if(temp3.equals("ga"))
						temp2+="n";
					else if(temp3.equals("A"))
						temp2+="O";
					else if(temp3.equals("a"))
						temp2+="o";
					else if(temp3.equals("Wg"))
						temp2+="P";
					else if(temp3.equals("wg"))
						temp2+="p";
					else if(temp3.equals("Lo"))
						temp2+="Q";
					else if(temp3.equals("lo"))
						temp2+="q";
					else if(temp3.equals("As"))
						temp2+="R";
					else if(temp3.equals("as"))
						temp2+="r";
					else if(temp3.equals("Ss"))
						temp2+="S";
					else if(temp3.equals("ss"))
						temp2+="s";
					else if(temp3.equals("Y"))
						temp2+="T";
					else if(temp3.equals("y"))
						temp2+="t";
					else if(temp3.equals("G"))
						temp2+="U";
					else if(temp3.equals("g"))
						temp2+="u";
					else if(temp3.equals("Aw"))
						temp2+="V";
					else if(temp3.equals("aw"))
						temp2+="v";
					else if(temp3.equals("Ww"))
						temp2+="W";
					else if(temp3.equals("ww"))
						temp2+="w";
					else if(temp3.equals("Yo"))
						temp2+="X";
					else if(temp3.equals("yo"))
						temp2+="x";
					else if(temp3.equals("Ag"))
						temp2+="Y";
					else if(temp3.equals("ag"))
						temp2+="y";
					else if(temp3.equals("O"))
						temp2+="Z";
					else if(temp3.equals("o"))
						temp2+="z";
					else{
						temp2+=temp3.substring(0,1);
						temp = temp.substring(1);
						continue;
					}
					temp = temp.substring(x+1);
				}
			}
			else{
				for(int x = 0; x < temp.length(); x++){
					if(temp.substring(x,x+1).equals("A"))
						temp2+="Aa-";
					else if(temp.substring(x,x+1).equals("a"))
						temp2+="aa-";
					else if(temp.substring(x,x+1).equals("B"))
						temp2+="Sw-";
					else if(temp.substring(x,x+1).equals("b"))
						temp2+="sw-";
					else if(temp.substring(x,x+1).equals("C"))
						temp2+="Gs-";
					else if(temp.substring(x,x+1).equals("c"))
						temp2+="gs-";
					else if(temp.substring(x,x+1).equals("D"))
						temp2+="Sa-";
					else if(temp.substring(x,x+1).equals("d"))
						temp2+="sa-";
					else if(temp.substring(x,x+1).equals("E"))
						temp2+="S-";
					else if(temp.substring(x,x+1).equals("e"))
						temp2+="s-";
					else if(temp.substring(x,x+1).equals("F"))
						temp2+="Oo-";
					else if(temp.substring(x,x+1).equals("f"))
						temp2+="oo-";
					else if(temp.substring(x,x+1).equals("G"))
						temp2+="Gg-";
					else if(temp.substring(x,x+1).equals("g"))
						temp2+="gg-";
					else if(temp.substring(x,x+1).equals("H"))
						temp2+="Sg-";
					else if(temp.substring(x,x+1).equals("h"))
						temp2+="sg-";
					else if(temp.substring(x,x+1).equals("I"))
						temp2+="W-";
					else if(temp.substring(x,x+1).equals("i"))
						temp2+="w-";
					else if(temp.substring(x,x+1).equals("J"))
						temp2+="Gw-";
					else if(temp.substring(x,x+1).equals("j"))
						temp2+="gw-";
					else if(temp.substring(x,x+1).equals("K"))
						temp2+="Ws-";
					else if(temp.substring(x,x+1).equals("k"))
						temp2+="ws-";
					else if(temp.substring(x,x+1).equals("L"))
						temp2+="L-";
					else if(temp.substring(x,x+1).equals("l"))
						temp2+="l-";
					else if(temp.substring(x,x+1).equals("M"))
						temp2+="Wa-";
					else if(temp.substring(x,x+1).equals("m"))
						temp2+="wa-";
					else if(temp.substring(x,x+1).equals("N"))
						temp2+="Ga-";
					else if(temp.substring(x,x+1).equals("n"))
						temp2+="ga-";
					else if(temp.substring(x,x+1).equals("O"))
						temp2+="A-";
					else if(temp.substring(x,x+1).equals("o"))
						temp2+="a-";
					else if(temp.substring(x,x+1).equals("P"))
						temp2+="Wg-";
					else if(temp.substring(x,x+1).equals("p"))
						temp2+="wg-";
					else if(temp.substring(x,x+1).equals("Q"))
						temp2+="Lo-";
					else if(temp.substring(x,x+1).equals("q"))
						temp2+="lo-";
					else if(temp.substring(x,x+1).equals("R"))
						temp2+="As-";
					else if(temp.substring(x,x+1).equals("r"))
						temp2+="as-";
					else if(temp.substring(x,x+1).equals("S"))
						temp2+="Ss-";
					else if(temp.substring(x,x+1).equals("s"))
						temp2+="ss-";
					else if(temp.substring(x,x+1).equals("T"))
						temp2+="Y-";
					else if(temp.substring(x,x+1).equals("t"))
						temp2+="y-";
					else if(temp.substring(x,x+1).equals("U"))
						temp2+="G-";
					else if(temp.substring(x,x+1).equals("u"))
						temp2+="g-";
					else if(temp.substring(x,x+1).equals("V"))
						temp2+="Aw-";
					else if(temp.substring(x,x+1).equals("v"))
						temp2+="aw-";
					else if(temp.substring(x,x+1).equals("W"))
						temp2+="Ww-";
					else if(temp.substring(x,x+1).equals("w"))
						temp2+="ww-";
					else if(temp.substring(x,x+1).equals("X"))
						temp2+="Yo-";
					else if(temp.substring(x,x+1).equals("x"))
						temp2+="yo-";
					else if(temp.substring(x,x+1).equals("Y"))
						temp2+="Ag-";
					else if(temp.substring(x,x+1).equals("y"))
						temp2+="ag-";
					else if(temp.substring(x,x+1).equals("Z"))
						temp2+="O-";
					else if(temp.substring(x,x+1).equals("z"))
						temp2+="o-";
					else
						temp2+=temp.substring(x,x+1);
				}
			}
			System.out.println(temp2);
			System.out.println("Do you want to continue?");
			temp = s.nextLine();
			if(temp.length()!=0 && (temp.substring(0,1).equals("N") || temp.substring(0,1).equals("n")))
				break;
		}
	}
	
	/**
	 * checks if a String is something else
	 */
	public static boolean is(String type,String string){
		try{
			if(type.equals("Integer"))
				Integer.parseInt(string);
			else if(type.equals("Even Integer")){
				Integer i = Integer.parseInt(string);
				if(i%2==0)
					return true;
				else
					return false;
			}
			else if(type.equals("Odd Integer")){
				Integer i = Integer.parseInt(string);
				if(i%2!=0)
					return true;
				else
					return false;
			}
			else if(type.equals("Double"))
				Double.parseDouble(string);
			else if(type.equals("Boolean"))
				Boolean.parseBoolean(string);
			else{
				System.out.println("is(String type,String string) failed. Unknown type \""+type+"\" entered. Please add code to method.");
				return false;
			}
	        return true;
	    }
	    catch(NumberFormatException e){
	        return false;
	    }
	}
	
	/**
	 * returns a random int ranging from Integer.MIN_VALUE (-2147483648) 
	 * to Integer.MAX_VALUE (2147483647)
	 */
	public static int randomInt(){
		Random r = new Random();
		int x = (int)Math.round((r.nextInt(2147483647)+1)*2*Math.random());
		if(x==0){
			x = r.nextInt(2);
			if(x==0)
				return -2147483647;
			return -2147483648;
		}			
		return x - 2147483647;
	}
	
	public static void bubbleSort(int array[]){
		for(int x = 0; x < array.length; x++){
			for(int slot = 0; slot < array.length-1; slot++){
				int nextSlot = slot + 1;
				if(array[slot] > array[nextSlot]){
					int temp = array[slot];
					array[slot] = array[nextSlot];
					array[nextSlot] = temp;
				}
			}
		}
	}
	
	/**
	 * sorts by taking the elements one at a time, and figuring out where
	 * the next one fits in with the already sorted elements
	 */
	public static void insertionSort(int array[]){
		for(int i = 1; i < array.length; i++){
            int next = array[i];
            int j = i;
            while(j > 0 && array[j-1] > next){
                array[j] = array[j-1];
                j--;
            }
            array[j] = next;
        }
	}
	
	/**
	 * sorts by searching the unsorted part of the elements for the
	 * smallest one, then inserts it right after the sorted elements
	 */
	public static void selectionSort(int array[]){
		 for(int i = 0; i < array.length - 1; i++){
			 int minPos = i;
             for(int j = i + 1; j < array.length; j++){
            	 if(array[j] < array[minPos])
            		 minPos = j;
             }
             int temp = (Integer) array[minPos];
             array[minPos] = array[i];
             array[i] = temp;
		 }
	}
	
	/**
	 * sorts a list by breaking it down into smaller and smaller lists,
	 * sorting them, and merging them back together
	 * @param left - set to 0
	 * @param right - set to list.size()-1
	 */
	public static void mergeSort(List<Comparable> list, int left, int right){
		if( left < right ){
			tmp = new ArrayList<Comparable>();
			int center = (left + right) / 2;
			mergeSort(list, left, center);
			mergeSort(list, center + 1, right);
			merge(list, left, center + 1, right);
		}
	}
	
	/**
	 * called by the mergeSort method while it breaks the list down using
	 * recursion. sorts the broken down parts & merges them together
	 */
	private static void merge(List<Comparable> list, int left, int right, int rightEnd){
        int leftEnd = right - 1;
        int num     = rightEnd-left+1; // # of elements to be in tmp list
        while(left <= leftEnd && right <= rightEnd){
        	/*
        	 * adds list's left index element or right index element to 
        	 * the tmp list depending on which one is smaller. then 
        	 * increments the left or right index (whichever had the 
        	 * smaller element). 
        	 */
            if(list.get(left).compareTo(list.get(right)) <= 0)
                tmp.add(list.get(left++));
            else
            	tmp.add(list.get(right++));
        }
        while(left <= leftEnd)
        	tmp.add(list.get(left++));
        while(right <= rightEnd)
        	tmp.add(list.get(right++));
        for(int i = 0; i < num; i++){
            list.set(rightEnd,tmp.get(num-1-i));
            rightEnd--;
        }
        tmp.clear();
    }
	
	/**
	 * searches for a target by splitting an array in half repeatedly
	 */
	public static boolean binarySearch(int array[],int target){
		int left = 0, right = array.length, middle = (left+right)/2; 
		boolean found = false; 
		while((!found) && (left <= right)){
			if(array[middle]!=target){
				if(target < array[middle]) 
					right = middle - 1;  
				left = middle + 1; 
				middle = (left+right)/2; 
			}
			return true;
		} 
		return false;
	}
	
	public static double distance(int x1, int y1, int x2, int y2){
		return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
	}
	
	public static int greatestCommonFactor(int a,int b){
		for(int x = a; x > 1; x--){
			if(a%x==0 && b%x==0) 
				return x; 
		}
		return 1;
	}
	
	public static int leastCommonMultiple(int a,int b){
		int possibleLCM = a; 
		while(possibleLCM < a*b){
			if(possibleLCM%b==0) 
				return b; 
			possibleLCM += a; 
		} 
		return a*b;
	}
	
	public static double areaRect(double length,double width){
		return length * width;
	}
	
	public static double areaCirle(double radius){
		return Math.PI*(radius*radius);
	}
	
	public static double areaOval(double majorAxis,double minorAxis){
		return (majorAxis/2) * (minorAxis/2) * Math.PI;
	}
	
	public static void swap(int array[], int index1, int index2){
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	public static void swap(Object array[], int index1, int index2){
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	public static void swap(List list, int index1, int index2){
		Object temp = list.get(index1);
		list.set(index1,list.get(index2));
		list.set(index2,temp);
	}
	
	public static void save(String data,String fileName,boolean overwrite) throws Exception{
	     FileWriter fw;
	     if(overwrite == true)
	    	 fw = new FileWriter(new File(fileName),false);
	     else
	    	 fw = new FileWriter(new File(fileName),true);
	     BufferedWriter w = new BufferedWriter(fw);
        w.write(data+" ");
        w.close();
	}
	
	public static void save(String[] data,String fileName,boolean overwrite) throws Exception{
		FileWriter fw;
	    if(overwrite == true)
	    	fw = new FileWriter(new File(fileName),false);
	    else
	    	fw = new FileWriter(new File(fileName),true);
	    BufferedWriter w = new BufferedWriter(fw);
       for(int x = 0; x<data.length; x++){
       	w.write(data[x]+" ");
       }
       w.close();
	}
	
	public static void save(ArrayList<String> data,String fileName,boolean overwrite) throws Exception{
		FileWriter fw;
	    if(overwrite == true)
	    	fw = new FileWriter(new File(fileName),false);
	    else
	    	fw = new FileWriter(new File(fileName),true);
	    BufferedWriter w = new BufferedWriter(fw);
       for(int x = 0; x<data.size(); x++){
       	w.write(data.get(x)+" ");
       }
       w.close();
	}
	
	/**
	 * @param a - specifies that you want to load integers from a file
	 * @param numToLoad - number of integers to load from file 
	 */
	public static ArrayList<Integer> load(int a,String fileName,int numToLoad) throws FileNotFoundException{
		Scanner s = new Scanner(new File(fileName));
		ArrayList<Integer> list = new ArrayList<Integer>();
       for(int x = 0; x<numToLoad; x++){
       	list.add(s.nextInt());
       }
       s.close();
       return list;
	}
	
	/**
	 * @param a - specifies that you want to load strings from a file
	 * @param numToLoad - number of strings to load from file 
	 */
	public static ArrayList<String> load(String a,String fileName,int numToLoad) throws FileNotFoundException{
		Scanner s = new Scanner(new File(fileName));
		ArrayList<String> list = new ArrayList<String>();
       for(int x = 0; x<numToLoad; x++){
       	list.add(s.next());
       }
       s.close();
       return list;
	}
	
	/**
	 * @param loop - true, if you want media to keep on looping
	 */
	public static void playMedia(String fileName,boolean loop){
		JFXPanel fxPanel = new JFXPanel();
	    String uriString = new File(fileName).toURI().toString();
	    Media m = new Media(uriString);
	    media = new MediaPlayer(m);
		if(loop == true)
			media.setCycleCount(MediaPlayer.INDEFINITE);
		media.play();
	}
	
	public static void stopMedia(){
		media.stop();
	}
	
	public static void window(String frameText,KeyListener listener){
		panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		panel.setLayout(layout);
		components = new ArrayList<Component>();
		frame = new JFrame(frameText);
		frame.setContentPane(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(listener);
     	frame.setVisible(true);
	}
	
	public static void menu(String menuText){
		JMenuBar menuBar = new JMenuBar();
		menu = new JMenu(menuText);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.pack();
	}
	
	public static void menuItem(String menuItemText, ActionListener listener){
		JMenuItem menuItem = new JMenuItem(menuItemText);
		menuItem.addActionListener(listener);
		components.add(menuItem);
		menu.add(menuItem);
		frame.pack();
	}
	
	public static void textField(String text,int size,boolean setEditable,int gridSize,int x_coordinate,int y_coordinate){
		JTextField textField = new JTextField(text,size);
		textField.setEditable(setEditable);
		constraints.gridwidth = gridSize;
		constraints.gridx = x_coordinate;
		constraints.gridy = y_coordinate;
		components.add(textField);
		panel.add(textField,constraints);
		frame.pack();
	}
	
	public static void label(ImageIcon image,String toolTipText,int gridSize,int x_coordinate,int y_coordinate){
		JLabel label = new JLabel(image, SwingConstants.LEFT);
		label.setToolTipText(toolTipText);
		constraints.gridwidth = gridSize;
		constraints.gridx = x_coordinate;
		constraints.gridy = y_coordinate;
		components.add(label);
		panel.add(label, constraints);
		frame.pack();
	}
	
	public static void button(ImageIcon image,ActionListener listener,int gridSize,int x_coordinate,int y_coordinate){
		JButton button = new JButton(image);
		button.addActionListener(listener);
		constraints.gridwidth = gridSize;
		constraints.gridx = x_coordinate;
		constraints.gridy = y_coordinate;
		components.add(button);
		panel.add(button,constraints);
		frame.pack();
	}
}