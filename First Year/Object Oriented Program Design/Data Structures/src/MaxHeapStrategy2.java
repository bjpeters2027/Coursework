/**
 * An example max heap strategy for adding an int to an existing max heap
 */
public class MaxHeapStrategy2 extends AbsBTStrategy implements BTStrategy{
    @Override
    public NodeBT addInt(int i, IBinTree b) {
        if(b.isEmpty()){
            return new NodeBT(i, new EmptyBT(), new EmptyBT());
        }
        else{
            if(i < b.getRoot()){
                return new NodeBT(b.getRoot(), b.getRight(), this.addInt(i, b.getLeft()));
            }
            else{
                return new NodeBT(i, this.addInt(b.getRoot(),b.getRight()), b.getLeft());
            }
        }
    }
}
