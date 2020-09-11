/*
package com.demo;

import lombok.Data;
import org.junit.Test;

import java.util.PriorityQueue;

*/
/**
 * @author wangxiaocheng
 *//*

public class PriorityQueueDemo {
    @Test
    public void Test(){
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Person("aaa",11));
        priorityQueue.add(new Person("bbb",12));
        priorityQueue.add(new Person("ccc",13));
        priorityQueue.add(new Person("ddd",14));
        priorityQueue.add(new Person("eee",15));
        priorityQueue.add(new Person("fff",16));
        priorityQueue.add(new Person("ggg",17));
        priorityQueue.add(new Person("hhh",18));
        priorityQueue.add(new Person("iii",19));
        priorityQueue.add(new Person("jjj",20));
        priorityQueue.add(new Person("kkk",21));
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }


}
@Data
class Person implements Comparable<Person>{
    private String name;
    private int age;
    public Person(String name,int age){
        this.age = age;
        this.name = name;
    }
    @Override
    public String toString(){
        return "Person's name is "+this.name+" and age is "+this.age;
    }

    @Override
    public int compareTo(Person o) {
        return o.age-age;
    }
}
*/
