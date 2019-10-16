package ua.kolot.filebrowser;

import org.junit.Test;

public class TestJava {
    @Test
    public void test(){
        TestClass p1, p2;
        p1 = new TestClass();
        p2 = p1;
        modify(p1);
        System.out.println("finish p2 = " + p2.i);
    }

    private void modify(TestClass testClass){
        testClass.i+=2;
    }


}
class TestClass{
    public int i =0;
    public TestClass(){
    }

}

