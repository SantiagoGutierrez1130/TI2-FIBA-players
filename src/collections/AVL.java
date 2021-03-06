package collections;
import java.util.Comparator;
public class AVL<T> extends BST<T>{
	public AVL(Comparator<T> c) {
		super(c);
	}
	@Override
	public Node<T> add(T item) {
		Node<T> added = super.add(item);
		if ((added.getLeft() == null && added.getRight() == null) && added.getValue().size() == 1)
			rebalance(added.getParent());
		return added;
	}
	/*@Override
	public boolean delete(T item) {
		boolean value = super.delete(item);
		return value;
	}*/
	public void rebalance(Node<T> current) {
		if (current != null && current.getParent() != null) {
			boolean balanced = false;
			while(current.getParent() != null && !balanced){
				current = current.getParent();
				int bf = balanceFactor(current);
				if (bf == 2) {
					if (balanceFactor(current.getRight()) == -1)
						rightRotate(current.getRight());
					leftRotate(current);
					balanced = true;
				}else if (bf == -2) {
					if (balanceFactor(current.getLeft()) == 1)
						leftRotate(current.getLeft());
					rightRotate(current);
					balanced = true;
				}
			}
		}
	}
	public void leftRotate(Node<T> p) {
		Node<T> parent = p.getParent();
		Node<T> q = p.getRight();
		Node<T> r = q.getLeft();
		q.setParent(parent);
		if (parent != null) {
			if (parent.getLeft() != null && parent.getLeft().equals(p))
				parent.setLeft(q);
			else
				parent.setRight(q);
		}
		p.setRight(r);
		if (r != null)
			r.setParent(p);
		p.setParent(q);
		q.setLeft(p);
	}
	public void rightRotate(Node<T> p) {
		Node<T> parent = p.getParent();
		Node<T> q = p.getLeft();
		Node<T> r = q.getRight();
		q.setParent(parent);
		if  (parent != null) {
			if (parent.getLeft() != null && parent.getLeft().equals(p))
				parent.setLeft(q);
			else
				parent.setRight(q);
		}
		p.setLeft(r);
		if (r != null)
			r.setParent(p);
		p.setParent(q);
		q.setRight(p);
	}
	public int balanceFactor(Node<T> current){
		if (current == null)
			return 0;
		else
			return getHeight(current.getRight())-getHeight(current.getLeft());
	}
}