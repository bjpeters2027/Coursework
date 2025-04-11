public class FaultyMinHeapStrategy extends AbsBTStrategy implements BTStrategy{
    /**
     * Adding an element to a tree is a subclass responsibility
     *
     * @param i the element to add
     * @param b the tree to add it to
     * @return a new tree with the element added
     */
    @Override
    public NodeBT addInt(int i, IBinTree b) {
        if(b.isEmpty()){
            return new NodeBT(i, new EmptyBT(), new EmptyBT());
        }
        else{
            if(i > b.getRoot()){
                return new NodeBT(b.getRoot(), new EmptyBT(), new NodeBT(i, b.getLeft(), b.getRight()));
            }
            else{
                return new NodeBT(i, b.getLeft(), b.getRight());
            }
        }
    }
}
