import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Examples {
    public BinaryTree someTree1;
    public BinaryTree someTree2;
    public IBinTree emptyTree;

    public BinaryTree minHeap;

    public Examples(){
        someTree1 = new BinaryTree(List.of(5,2,7,1,3,6,9));
        someTree2 = new BinaryTree(List.of(5,1));
        emptyTree = new EmptyBT();
        minHeap = new BinaryTree(new NodeBT(2, new NodeBT(3, new NodeBT(4, emptyTree,emptyTree),  new NodeBT(5, emptyTree,emptyTree)), new NodeBT(6, new NodeBT(7, emptyTree,emptyTree),  new NodeBT(8, emptyTree,emptyTree))));
    }

    //max at root
    @Test
    public void testMaxElement(){
        BinaryTree tree = new BinaryTree(someTree1);
        tree.setValidator(new MaxHeapValidator());
        assertFalse(tree.addInt(4));
    }

    @Test
    public void testMaxElement1(){
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setValidator(new MaxHeapValidator());
        assertTrue(tree.addInt(4));
    }

    @Test
    public void tester1228(){
        MaxHeapValidator balidator = new MaxHeapValidator();
        assertFalse(balidator.validAdd(new NodeBT(12, new NodeBT(8, emptyTree, emptyTree), new NodeBT(5, emptyTree, new NodeBT(2, emptyTree, emptyTree))), 28, new NodeBT(12, new NodeBT(8, new NodeBT(28, emptyTree, emptyTree), emptyTree), new NodeBT(5, emptyTree, new NodeBT(2, emptyTree, emptyTree)))));
    }

    @Test
    public void minValaddF() {
        MinHeapValidator balidator = new MinHeapValidator();
        BinaryTree tree = new BinaryTree(minHeap);
        tree.setValidator(balidator);
        tree.setStrategy(new FaultyMinHeapStrategy());
        assertFalse(tree.addInt(27));
    }

    @Test
    public void minValR() {
        MinHeapValidator balidator = new MinHeapValidator();
        assertFalse(balidator.validRemove(new NodeBT(2, new NodeBT(3, new NodeBT(4, emptyTree,emptyTree),  new NodeBT(5, emptyTree,emptyTree)), new NodeBT(6, new NodeBT(7, emptyTree,emptyTree),  new NodeBT(8, emptyTree,emptyTree))), 9, new NodeBT(2, new NodeBT(3, new NodeBT(4, emptyTree,emptyTree),  new NodeBT(5, emptyTree,emptyTree)), new NodeBT(6, new NodeBT(7, emptyTree,emptyTree),  new NodeBT(8, new NodeBT(9, emptyTree,emptyTree),emptyTree)))));
    }

    @Test
    public void minValAddT() {
        MinHeapValidator balidator = new MinHeapValidator();
        BinaryTree tree = new BinaryTree(minHeap);
        tree.setValidator(balidator);
        tree.setStrategy(new MinHeapStrategy());
        assertTrue(tree.addInt(27));
    }

    @Test
    public void testFaultyAdd(){
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setStrategy(new FaultyMaxHeapStrategy1());
        tree.setValidator(new MaxHeapValidator());
        assertFalse(tree.addInt(27));
    }

    @Test
    public void TestAdd1(){
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setStrategy(new MaxHeapStrategy2());
        tree.setValidator(new MaxHeapValidator());
        assertTrue(tree.addInt(27));
    }

    @Test
    public void TestFaultyRemoval(){
        MaxHeapValidator balidator = new MaxHeapValidator();
        assertTrue(balidator.validRemove(new NodeBT(4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree), emptyTree), 4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree)));
    }

    @Test
    public void TestFaultyRemovalB(){
        DefaultBTValidator balidator = new DefaultBTValidator();
        assertTrue(balidator.validRemove(new NodeBT(4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree), emptyTree), 4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree)));
    }

    @Test
    public void TestFaultyRemovalC(){
        MaxHeapValidator balidator = new MaxHeapValidator();
        assertFalse(balidator.validRemove(new NodeBT(4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree), emptyTree), 4, new NodeBT(4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree), emptyTree)));
    }

    @Test
    public void TestFaultyRemovalD(){
        MaxHeapValidator balidator = new MaxHeapValidator();
        assertFalse(balidator.validRemove(new NodeBT(4, new NodeBT(3, new NodeBT(2, emptyTree, emptyTree), emptyTree), emptyTree), 4, emptyTree));
    }

    @Test
    public void validateAddedContainsElt(){
        MaxHeapValidator validator = new MaxHeapValidator();
        assertFalse(validator.validAdd(emptyTree, 3, new NodeBT(4, emptyTree,emptyTree)));
    }

    @Test
    public void ltester(){
        MaxHeapValidator validator = new MaxHeapValidator();
        assertTrue(validator.validAdd(emptyTree, 3, new NodeBT(3, emptyTree, emptyTree)));
    }


    @Test
    public void validateAddedContainsElt2(){
        BinaryTree bt = new BinaryTree();
        bt.addInt(3);
        bt.setValidator(new MaxHeapValidator());

        BinaryTree goodHeap = new BinaryTree(bt);
        goodHeap.setStrategy(new MaxHeapStrategy1());

        BinaryTree badHeap = new BinaryTree(bt);
        badHeap.setStrategy(new FaultyMaxHeapStrategy1());

        assertTrue(goodHeap.addInt(2));
        assertFalse(badHeap.addInt(4));
    }
}
