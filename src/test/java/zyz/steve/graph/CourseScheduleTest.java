package zyz.steve.graph;

import org.junit.Test;

public class CourseScheduleTest {

    @Test
    public void testCourseSchedule() {
        CourseSchedule cs = new CourseSchedule();
        int[][] p = {{1, 0},{0,1}};
        System.out.println(cs.canFinish(2, p));
    }
    @Test
    public void testTopoSort()
    {
        System.out.println(CourseSchedule.topologicalSort(new int[][] {{1,2}, {2},{}}));
    }

}