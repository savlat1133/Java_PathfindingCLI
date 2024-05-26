// Node class
class Node {
    int x, y;
    boolean target, visited, start, path, wall;
    Node previous, right, diagonal1, bottom, diagonal2, left, diagonal3, diagonal4, top;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.target = false;
        this.visited = false;
        this.start = false;
        this.path = false;
        this.wall = false;
        this.previous = null;
        this.right = null;
        this.diagonal1 = null;
        this.bottom = null;
        this.diagonal2 = null;
        this.left = null;
        this.diagonal3 = null;
        this.diagonal4 = null;
        this.top = null;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }
}
