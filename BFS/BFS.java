// BFS pathfinding algorithm
import java.util.*;

public class BFS {
    private static Grid grid;
    private static Node startNode;
    private static Node targetNode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter grid height: ");
        int height = scanner.nextInt();

        System.out.print("Enter grid width: ");
        int width = scanner.nextInt();

        grid = new Grid(height, width);

        System.out.print("Enter start node coordinates (x y): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.print("Enter target node coordinates (x y): ");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();

        startNode = grid.getNode(startX, startY);
        targetNode = grid.getNode(targetX, targetY);

        startNode.setStart(true);
        targetNode.setTarget(true);

        System.out.print("Enter wall density (0.0 to 1.0): ");
        double density = scanner.nextDouble();

        grid.resetGrid();
        startNode.setStart(true);
        targetNode.setTarget(true);
        grid.generateRandomWalls(density);

        System.out.println("Initial grid with walls:");
        grid.printGrid();

        System.out.println("Starting BFS search...");
        boolean found = bfs(startNode, targetNode);

        if (found) {
            System.out.println("Path found!");
            markPath(targetNode);
        } else {
            System.out.println("Path not found.");
        }

        System.out.println("Final grid:");
        grid.printGrid();

        scanner.close();
    }

    private static boolean bfs(Node start, Node target) {
        Queue<Node> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == target) {
                return true;
            }

            for (Node neighbor : getNeighbors(current)) {
                if (neighbor != null && !neighbor.visited && !neighbor.wall) {
                    neighbor.visited = true;
                    neighbor.previous = current;
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    private static List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        neighbors.add(node.right);
        neighbors.add(node.bottom);
        neighbors.add(node.left);
        neighbors.add(node.diagonal1);
        neighbors.add(node.diagonal2);
        neighbors.add(node.diagonal3);
        neighbors.add(node.diagonal4);
        neighbors.add(node.top);
        return neighbors;
    }

    private static void markPath(Node target) {
        Node current = target;

        while (current != null) {
            current.setPath(true);
            current = current.previous;
        }
    }
}
