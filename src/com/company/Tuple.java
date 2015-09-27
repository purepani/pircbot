package com.company;

/**
 * Created by panis on 9/27/15.
 */

/*
*
* To group things together. For grouping roles to a channel, and maybe other things l9r
*
*/

public class Tuple<A, B> {
    public A a;
    public B b;

    public Tuple(A a, B b){
        this.a = a;
        this.b = b;
    }

    public Object showA(){
        return a;
    }

    public Object showB(){
        return b;
    }
}
