/**
 * A Strategy for adding integers in breadth first order
 */
public class DefaultBTStrategy extends AbsBTStrategy implements BTStrategy
{
    /**
     * Used as a helper method used to measure the shortest path between the root node and leaf
     * @param b the tree to measure
     * @return how many non-leaf nodes are between the root node and the closest leaf
     */
    private int minDepth(IBinTree b){
        if(b.isEmpty()){
            return 0;
        }
        else{
            return 1 + Math.min(this.minDepth(b.getLeft()), this.minDepth(b.getRight()));
        }
    }

    /**
     * Get a tree by replacing the nearest leaf (closest to root, left to right) with a node containing i
     * @param i the element to add to the tree
     * @param b a tree (no invariants)
     * @return a new (immutable) tree with i added
     */
    @Override
    public NodeBT addInt(int i, IBinTree b) {
        if(b.isEmpty()){
            return new NodeBT(i, new EmptyBT(), new EmptyBT());
        }
        else {
            if(this.minDepth(b.getLeft()) <= this.minDepth(b.getRight())){
                return new NodeBT(b.getRoot(), this.addInt(i,b.getLeft()), b.getRight());
            }
            else {
                return new NodeBT(b.getRoot(), b.getLeft(), this.addInt(i,b.getRight()));
            }
        }
    }



}
