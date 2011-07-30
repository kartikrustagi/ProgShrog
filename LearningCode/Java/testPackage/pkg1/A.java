package javaFiles.testPackage.pkg1;

public class A{
    public int i;
    private A(){
        System.out.println("In constructor of A");
        i = 5;
    }
}