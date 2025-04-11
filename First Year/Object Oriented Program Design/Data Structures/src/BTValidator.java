/**
 * An object (essentially a strategy) describing how to check the invariants of a particular kind of binary tree
 * (E.g. BST, AVL, MinHeap, MaxHeap, etc.)
 */
public interface BTValidator {

    /**
     * Check if adding i to the old tree and getting the new tree is possible with the current invariants
     * @param oldTree the given tree we assume respects the invariants
     * @param i the element to add
     * @param newTree the new tree which we are validating
     * @return true if we determine that the new tree respects the invariants
     */
    public boolean validAdd(IBinTree oldTree, int i, IBinTree newTree);
    /**
     * Check if removing i from the old tree and getting the new tree is possible with the current invariants
     * @param oldTree the given tree we assume respects the invariants
     * @param i the element to remove
     * @param newTree the new tree which we are validating
     * @return true if we determine that the new tree respects the invariants
     */
    public boolean validRemove(IBinTree oldTree, int i, IBinTree newTree);





}
