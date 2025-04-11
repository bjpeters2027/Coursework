public class MaxHeapValidator implements BTValidator{
    /**
     * Check if adding i to the old tree and getting the new tree is possible with the current invariants
     *
     * @param oldTree the given tree we assume respects the invariants
     * @param i       the element to add
     * @param newTree the new tree which we are validating
     * @return true if we determine that the new tree respects the invariants
     */
    @Override
    public boolean validAdd(IBinTree oldTree, int i, IBinTree newTree) {
       if (!(oldTree.isEmpty()) && newTree.isEmpty()){
           return false;
       }
        if(newContains(oldTree, i)){
           return false;
       }
       if(!(newContains(newTree, i))){
            return false;
       }

       if (!(validMaxHeap(newTree))){
           return false;
       }
       if(!(newTree.size() == (oldTree.size() + 1))){
           return false;
       }
       if (!(newContainsAll(newTree, oldTree))){
           return false;
       }

       return true;
    }

    /**
     * Check if removing i from the old tree and getting the new tree is possible with the current invariants
     *
     * @param oldTree the given tree we assume respects the invariants
     * @param i       the element to remove
     * @param newTree the new tree which we are validating
     * @return true if we determine that the new tree respects the invariants
     */
    @Override
    public boolean validRemove(IBinTree oldTree, int i, IBinTree newTree) {
        if(newContains(newTree, i)){
            return false;
        }
        if(!(newContains(oldTree, i))){
            return false;
        }
        if((oldTree.size() == 1) && !(newTree.isEmpty())){
            return false;
        }
        if (!(validMaxHeap(newTree))){
            return false;
        }
        if(!(newTree.size() == (oldTree.size() - 1))){
            return false;
        }
        if(i > oldTree.getRoot()){
            if(!(newTree.getRoot() == i)){
                return false;
            }
        }
        if(!(newContainsAll(oldTree, newTree))){
            return false;
        }

        return true;
    }


    /**
     * Checks if a IBinTree contains a certain int
     * @param node the tree to be searched
     * @param i what is being searched for
     * @return true if the tree contains the given int and false otherwise
     */

    public boolean newContains(IBinTree node, int i) {
        if (node.isEmpty()) {
            return false;
        }
        if ((node.getRoot() == i)) {
            return true;
        }
        return newContains(node.getLeft(), i) || newContains(node.getRight(), i);
    }

    /**
     * Checks to see if one tree contains all data from another
     * @param container the tree being checked
     * @param containee the tree with the data that hould be in container
     * @return true if container has all of containee
     */
    public boolean newContainsAll(IBinTree container, IBinTree containee){

        if(containee.isEmpty()){
            return true;
        }
            return (newContains(container, containee.getRoot())) && (newContainsAll(container, containee.getLeft())) && (newContainsAll(container, containee.getRight()));
    }

    /**
     * Checks to make sure the tree is ordered properly and respects the invariant
     *
     * @param node the tree to be parsed
     * @return true if tree respects the invariant
     */

    public boolean validMaxHeap(IBinTree node) {
        if(node.isEmpty()){
            return true;
        }
        if(((node.getLeft()).isEmpty()) || (node.getRoot() > (node.getLeft()).getRoot())){
            if(((node.getRight()).isEmpty()) || (node.getRoot() > (node.getRight()).getRoot())){
                return validMaxHeap(node.getLeft()) && validMaxHeap(node.getRight());
            }
        }

        return false;


    }

}

