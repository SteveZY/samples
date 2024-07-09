package zyz.steve.snakegame;
//https://www.youtube.com/watch?v=idsPB3vGzLY


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Snakegame implements java.io.Serializable {

    private final int width, height;
    private final Deque<Coordinate> bodyQ;
    private final Queue<Coordinate> foodQ;
    private final Set<Coordinate> body = new HashSet<>();
    private int numOfConsumed=0;
    public Snakegame(int width, int height, int[][] food){

        this.width = width;
        this.height = height;
        foodQ = new LinkedList<>();
        bodyQ = new ArrayDeque<>();
        Coordinate start = new Coordinate(0, 0);
        bodyQ.add(start);
        body.add(start);
        for(int[] f: food){
            foodQ.add(new Coordinate(f[1],f[0]));
        }
    }
    private Coordinate move(Coordinate pos, String direction){
        switch (direction){
            case "U":
                return new Coordinate(pos.x, pos.y-1);
            case "R":
                return new Coordinate(pos.x+1, pos.y);
            case "D":
                return new Coordinate(pos.x, pos.y+1);
            case "L":
                return new Coordinate(pos.x-1, pos.y);
        }
        return null;
    }
    private boolean cantProceedTo(Coordinate pos){
        if(pos.x>width || pos.y > height || pos.x<0 || pos.y<0 ){
            return true;
        }
        //下一个位置 同除了尾巴 的 身体的其他 部位相碰了 ，也是fail
        return !pos.equals(bodyQ.peek()) && body.contains(pos);
    }
    public int move(String direction){
        Coordinate head = bodyQ.peekLast();

        Coordinate nextPos = move(head, direction);
        if(null == nextPos) return numOfConsumed;
        if(cantProceedTo(nextPos)) return -1;

        bodyQ.offer(nextPos);//没撞墙 就 加这个新位置
        //检查是否 有吃到 食物
        if(!foodQ.isEmpty()&& !body.contains(foodQ.peek()) && nextPos.equals(foodQ.peek())){
            foodQ.poll();
            body.add(nextPos);
            numOfConsumed++;
            return numOfConsumed;
        }
        body.remove(bodyQ.poll());//没搞到吃的，就移除尾巴
        body.add(nextPos);
        return numOfConsumed;
    }

    class Coordinate{
        private final int x;

        private final int y;
//        final private int width,height;
        public Coordinate(int x, int y){
            this.x = x;
            this.y=y;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode(){
            return y*width+x;
        }
    }

    public static void main(String[] args) {
        Snakegame s = new Snakegame(3,2,new int[][]{{1,2},{0,1}});
        System.out.println(s.move("R"));
        System.out.println(s.move("D"));
        System.out.println(s.move("R"));
        System.out.println(s.move("U"));
        System.out.println(s.move("L"));
        System.out.println(s.move("U"));

        System.out.println((int)(Math.random()*100000));
        System.out.println((int)Math.random()*100000);
        System.out.println((int)Math.random()*100000);
        System.out.println((int)Math.random()*100000);
        System.out.println((int)Math.random()*100000);
    }

}
