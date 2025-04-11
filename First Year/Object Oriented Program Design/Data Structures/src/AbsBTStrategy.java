/**
 * An abstract binary tree removal strategy which is defined in terms of addition
 */
public abstract class AbsBTStrategy implements BTStrategy {


    /**
     * Adding an element to a tree is a subclass responsibility
     * @param i the element to add
     * @param b the tree to add it to
     * @return a new tree with the element added
     */
    @Override
    public abstract NodeBT addInt(int i, IBinTree b);

    /**
     * A generic removal process defined in terms of addition by:
     * - searching for the element
     * - replacing the node with that element at the root with the left subtree merged into the right using repeated addInt()
     * @param i the element to remove
     * @param b the tree from which to remove it
     * @return a new tree with all the elements from the old tree except i
     */
    @Override
    public IBinTree removeInt(int i, IBinTree b) {
        if(b.isEmpty()){
            return b;
        }
        else if(b.getRoot() == i){
            return this.merge(b.getLeft(), b.getRight());
        }
        else{
            return new NodeBT(b.getRoot(),this.removeInt(i, b.getLeft()), this.removeInt(i,b.getRight()));
        }
    }

    /**
     * A helper method used to move all of the elements from one tree into another
     * @param from the tree whose elements we are taking from
     * @param to the tree that we are building up
     * @return a new tree with all the elements of `from` added to `to`
     */
    protected IBinTree merge(IBinTree from, IBinTree to){
        if(from.isEmpty()){
            return to;
        }
        else if(to.isEmpty()) {
            return from;
        }
        else{
            IBinTree addedRoot = this.addInt(from.getRoot(),to);
            IBinTree mergeLeft = this.merge(from.getLeft(),addedRoot);
            IBinTree mergeRight = this.merge(from.getRight(), mergeLeft);
            return mergeRight;
        }
    }
}
