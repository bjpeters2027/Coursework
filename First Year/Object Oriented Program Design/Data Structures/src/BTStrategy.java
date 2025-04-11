/**
 * The strategy for adding and removing integers from an integer binary tree
 */
public interface BTStrategy {

    /**
     * Produces a new tree by adding i to the elements of the old tree (respecting any invariants)
     * @param i the element to add to the tree
     * @param b a tree assumed to respect any invariants needed by the strategy
     * @return the new (immutable) tree now containing i
     */
    public NodeBT addInt(int i, IBinTree b);

    /**
     * Produces a new tree by removing i from the elements of the old tree (respecting any invariants)
     * @param i the element to remove from the tree
     * @param b a tree assumed to respect any invariants needed by the strategy
     * @return the new (immutable) tree no longer containing i
     */
    public IBinTree removeInt(int i, IBinTree b);


}
