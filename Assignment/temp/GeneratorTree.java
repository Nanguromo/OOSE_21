package electricityusage.app.model;
import java.util.*;


public class GeneratorTree
{
    private Node root;


    private class Node
    {
        private ICity data;
        private int depth;
        private LinkedList<Node> children;

        public Node(ICity inData, int currDepth)
        {
            data = inDepth;
            depth = currDepth;
            children = new LinkedList<ICity>();
        }

    }



    public GeneratorTree()
    {
        //nodes = new LinkedList<Node>();
        
        // 1. Randomly generate the tree depth, k, between 1 and 5 inclusive. k = 1 means the tree consists only of a root node.
        int k = ThreadLocalRandom.current().nextInt(1, 5+1); //nextInt(origin, bound). bount is exclusive, hence, make it 6 so that it takes 5 as its max!
        int numChildNodes;
        int randLeafOrComposite;
        root = new Node(new CompositeCity("Perth"), 1);
        int ctr = 1;
        int ctr2 = 1;

        
        for(int i=1; i<k; i++)
        {
            numChildNodes = ThreadLocalRandom.current().nextInt(2,5+1);
            randLeafOrComposite = ThreadLocalRandom.current().nextInt(1, 2+1);//if 1-> create a leaf. If 2-> create a composite.

            for(Node currNode: children)
            {

            }
            
            for(j=0; j < numChildNodes; j++)
            {
                if(i == k-1) //at this point, every child node should be a leaf, so they can have a power consumption category and values.
                {
                    for(Node currNode: children)
                    {
                        if(currNode instanceof CompositeCity)
                        {
                            CompositeCity temp = (CompositeCity)currNode;
                            if()
                        }
                    }
                    compCity.addSubcity(new PartOfCity("building" + ctr++));
                }
                else
                {
                    if(randLeafOrComposite == 1)//create a leaf
                    {
                        Node newNode = new Node(new PartOfCity("buidling"+ctr++),i));
                    }
                    else//create a composite
                    {
                        Node newNode = new Node(new CompositeCity("suburb"+ctr2++),i));
                    }

                }
            }
        }
    }

    public void addNode(ICity city, int thisDepth)
    {
        if(thisDepth == 1)
        {
            Node n = new Node(city, k);
            nodes.add(node);
        }
        else
        {
            Node n = new Node(city, k);

            for(Node curr: nodes)
            {
                if(cur)
            }
        }

    }

}