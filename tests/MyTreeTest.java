import domain.Leaf;
import domain.Node;
import main.LeafList;
import main.LeafListImpl;
import main.MyTreeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTreeTest {
    private MyTreeImpl myTree;
    private LeafList leafList;
    private Node head;
    private int W;
    private Leaf leaf1 ;
    private Leaf leaf2 ;
    private Leaf leaf3 ;
    private Leaf leaf4 ;
    private Leaf leaf5 ;
    private int size;

    @BeforeEach
    void init() {
        leaf1 = new Leaf(1);
        leaf2 = new Leaf(2);
        leaf3 = new Leaf(3);
        leaf4 = new Leaf(4);
        leaf5 = new Leaf(5);
        W=5;
        leafList = new LeafListImpl();
        head= new Node("head");
        myTree = new MyTreeImpl(head, W);
        size=0;

    }

    @Test
    public void testManageLeafs()  {

        leafList.addLastLeaf(leaf1);
        leafList.addLastLeaf(leaf3);
        leafList.addLastLeaf(leaf4);

        LeafList tempList = new LeafListImpl(leaf2);
        tempList.addLastLeaf(leaf5);

        LeafList goalList = new LeafListImpl();
        goalList.addLastLeaf(leaf3);
        goalList.addLastLeaf(leaf4);
        goalList.addLastLeaf(leaf5);

        assertEquals(goalList, myTree.manageLeafs(new Node(leafList), tempList));

    }
    @Test
    public void testMyTree(){

        Node a= new Node("a", LeafList.getRandomList(3, 5));
        Node a1= new Node("a1", LeafList.getRandomList(3, 10));
        Node a2= new Node("a2", LeafList.getRandomList(3, 5));
        Node a3= new Node("a3", LeafList.getRandomList(3, 5));
        Node b= new Node("b", LeafList.getRandomList(3, 5));
        Node b1= new Node("b1", LeafList.getRandomList(3, 5));
        Node b2= new Node("b2", LeafList.getRandomList(3, 5));
        Node b3= new Node("b3", LeafList.getRandomList(3, 5));
        Node c= new Node("c", LeafList.getRandomList(3, 5));
        Node c1= new Node("c1", LeafList.getRandomList(3, 5));
        Node c2= new Node("c2", LeafList.getRandomList(3, 5));
        Node c3= new Node("c3", LeafList.getRandomList(3, 5));

        head.setLeftNode(a);
        a.setRightNode(b);
        b.setRightNode(c);

        a.setLeftNode(a1);
        a1.setRightNode(a2);
        a2.setRightNode(a3);

        b.setLeftNode(b1);
        b1.setRightNode(b2);
        b2.setRightNode(b3);

        c.setLeftNode(c1);
        c1.setRightNode(c2);
        c2.setRightNode(c3);

        myTree.setHeadNode(head);
        myTree.doTask();

        while(myTree.hasNext()){
            Node current = myTree.next();
            checkSort(current);
            checkWeight(current);
            size++;
        }

        assertEquals(13, size);
    }

    //проверка суммы весов листьев каждого нода на предмет превышения заданной константы W
    private void checkWeight(Node node){
        int totalWeight =node.getTotalWeightOfLeafs();
        assertTrue(totalWeight<=W);
    }

    //проверка листьев каждого узла на предмет отсортированности по весу
    private void checkSort(Node node){
        LeafList leafList = node.getLeafList();
        if(!leafList.isEmpty()){
            Leaf leaf = leafList.getHeadLeaf();
            while(leaf.hasNext()){
                assertTrue(leaf.compareTo(leaf.getNext())<=0);
                leaf=leaf.getNext();
            }
        }
    }

}