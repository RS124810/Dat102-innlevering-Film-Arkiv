package no.hvl.dat102.kjedetstruktur;


public class LinearNode<T> {

	private LinearNode<T> neste;
	private T element;
	
	//tom node
	public LinearNode () {
		neste = null;
		element = null;
	}
	// node med et element
	
	public LinearNode (T elem) {
		neste = null;
		element = elem;
	}
//	@Override
//	public String toString() {
//		return "LinearNode [element = " + element + "]";
//	}

	//Returnerer etterfølger
	public LinearNode<T> getNeste () {
		return neste;
	}
	//setter neste til node
	public void setNeste (LinearNode<T> node) {
		neste = node;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T elem) {
		element = elem;
	}
	
}
