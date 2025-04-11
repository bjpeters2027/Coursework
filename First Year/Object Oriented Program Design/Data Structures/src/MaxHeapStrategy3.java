import java.util.Random;
/**
 * An example max heap strategy for adding an int to an existing max heap
 */
public class MaxHeapStrategy3 extends AbsBTStrategy implements BTStrategy{
    @Override
    public NodeBT addInt(int i, IBinTree b) {
        if(b.isEmpty()){
            return new NodeBT(i, new EmptyBT(), new EmptyBT());
        }
        else{
            Random r = new Random();
            if(i < b.getRoot()){
                if(r.nextBoolean()) {
                    return new NodeBT(b.getRoot(), b.getLeft(), this.addInt(i, b.getRight()));
                }
                else{
                    return new NodeBT(b.getRoot(), this.addInt(i, b.getLeft()), b.getRight());
                }
            }
            else{
                if(r.nextBoolean()) {
                    return new NodeBT(i, b.getLeft(), this.addInt(b.getRoot(), b.getRight()));
                }
                else{
                    return new NodeBT(i, this.addInt(b.getRoot(), b.getLeft()), b.getRight());
                }
            }
        }
    }
}
