// Grid generetor class
import java.util.Random;

class Grid {
    private int height, width;
    private Node[][] nodes;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.nodes = new Node[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes[y][x] = new Node(x, y);
            }
        }
        linkNodes();
    }

    private void linkNodes() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node current = nodes[y][x];
                if (x < width - 1) current.right = nodes[y][x + 1];
                if (y < height - 1) current.bottom = nodes[y + 1][x];
                if (x > 0) current.left = nodes[y][x - 1];
                if (x < width - 1 && y < height - 1) current.diagonal1 = nodes[y + 1][x + 1];
                if (x > 0 && y < height - 1) current.diagonal2 = nodes[y + 1][x - 1];
                if (x > 0 && y > 0) current.diagonal3 = nodes[y - 1][x - 1];
                if (x < width - 1 && y > 0) current.diagonal4 = nodes[y - 1][x + 1];
                if (y > 0) current.top = nodes[y - 1][x];
            }
        }
    }

    public Node getNode(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Invalid node position");
        }
        return nodes[y][x];
    }

    public void resetGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = nodes[y][x];
                node.visited = false;
                node.previous = null;
                node.start = false;
                node.target = false;
                node.path = false;
                node.wall = false;
            }
        }
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = nodes[y][x];
                if (node.start) {
                    System.out.print("S ");
                } else if (node.target) {
                    System.out.print("T ");
                } else if (node.path) {
                    System.out.print("* ");
                } else if (node.wall) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void generateRandomWalls(double density) {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (random.nextDouble() < density && !nodes[y][x].start && !nodes[y][x].target) {
                    nodes[y][x].setWall(true);
                }
            }
        }
    }
}
