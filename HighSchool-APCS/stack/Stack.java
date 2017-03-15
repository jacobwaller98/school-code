package apcs.stack;

public interface Stack {
	
	public boolean isEmpty();
	
	public Object push(Object o);
	
	public Object pop();
	
	public Object peek();
	
	public int search(Object o); // returns position of element on stack, top element = 1 ...return -1 if not found

}
