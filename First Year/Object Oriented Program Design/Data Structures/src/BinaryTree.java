import java.util.Collection;

/**
 * A class acting as a mutable, encapsulated binary tree
 * parameterized by the strategy used to add and remove data
 * and an object to validate any invariants
 * For the purposes of this class: assume the data in the tree is unique
 */
public class BinaryTree {

    /**
     * The current immutable binary tree which this object encapsulates
     */
    private IBinTree data = new EmptyBT();

    /**
     * The strategy by which this tree will add and remove integers from itself
     */
    private BTStrategy strategy;

    /**
     * The validator which will say whether an add was valid or not
     */
    private BTValidator invariantValidator;

    /**
     * The default constructor which provides a default strategy and an no invariant (always valid) validator
     */
    public BinaryTree(){
        strategy = new DefaultBTStrategy();
        invariantValidator = new DefaultBTValidator();
    }

    /**
     * A constructor which takes in a strategy and a validator to customize the binary tree
     * @param strategy the strategy used to add and remove integers to this tree
     * @param validator the validator which reports success after add and remove is called
     */
    public BinaryTree(BTStrategy strategy, BTValidator validator){
        this.strategy = strategy;
        this.invariantValidator = validator;
    }

    /**
     * A copy constructor
     * @param bt the binary tree which we copy the data, strategy, and validator from
     */
    public BinaryTree(BinaryTree bt){
        this.data = bt.data;  // this is ok if we assume the IBinTree objects are immutable
        this.strategy = bt.strategy; //We assume a strategy here is stateless
        this.invariantValidator = bt.invariantValidator; //We assume a validator is stateless
    }

    /**
     * A convenience constructor which takes a collection (i.e. List) of numbers and populates the tree
     * @param numbers the initial numbers to add to the tree with the default strategy.
     */
    public BinaryTree(Collection<Integer> numbers){
        this();
        for(int n : numbers){
            this.addInt(n);
        }
    }

    /**
     * A constructor for initializing with a particular tree
     * @param bt the particular immutable tree to start with
     */
    public BinaryTree(IBinTree bt){
        this.data = bt;
        strategy = new DefaultBTStrategy();
        invariantValidator = new DefaultBTValidator();
    }

    /**
     * Uses the current strategy to make a tree by adding a number to the current tree
     * @param i the integer to add
     * @return true if the current validator says the add was valid
     */
    public boolean addInt(int i){
        IBinTree nextTree = this.strategy.addInt(i, data);
        boolean validAdd = this.invariantValidator.validAdd(data,i,nextTree);
        this.data = nextTree;
        return validAdd;
    }

    /**
     * Uses the current strategy to make a tree by removing a particular number from the current tree
     * @param i the number to remove
     * @return true if the current validator says the remove was valid
     */
    public boolean removeInt(int i){
        IBinTree nextTree = this.strategy.removeInt(i, data);
        boolean validRemove = this.invariantValidator.validRemove(this.data,i,nextTree);
        this.data = nextTree;
        return validRemove;
    }

    /**
     * Uses the current strategy to make a tree by removing the current root number from the current tree
     * @return true if the current validator says the remove was valid
     */
    public boolean removeRoot(){
        if(this.data.isEmpty()){
            return false;
        }
        else {
            return this.removeInt(this.data.getRoot());
        }
    }


    /**
     * gets the number of nodes (non-leaves) in the tree;
     * @return
     */
    public int size(){
        return this.data.size();
    }


    /**
     * mutates this object to use a different strategy from now on
     * @param nextStrategy a strategy for adding and removing numbers from an IBinTree
     */
    public void setStrategy(BTStrategy nextStrategy){
        this.strategy = nextStrategy;
    }

    /**
     * mutates this object to use a different validator from now on
     * @param nextValidator a validator for checking if a particular operation respected the invariants of this tree
     */
    public void setValidator(BTValidator nextValidator){
        this.invariantValidator = nextValidator;
    }

    /**
     * Pretty-Prints out the tree
     * @return a string representation of the tree
     */
    @Override
    public String toString(){
        return this.data.toString();
    }

    /**
     * Two BinaryTrees are equal if their immutable tree fields are equal
     * @param o another tree, ideally
     * @return true iff both trees have the same elements in the same shape
     */
    @Override
    public boolean equals(Object o){
        if(! (o instanceof BinaryTree))
            return false;
        return ((BinaryTree) o).data.equals(this.data);
    }
}
