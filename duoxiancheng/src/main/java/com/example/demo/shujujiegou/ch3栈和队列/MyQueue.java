package com.example.demo.shujujiegou.ch3栈和队列;

import java.util.Queue;

/**
 * Created by King on 2017/11/14.
 *
 * Queue是一种很常见的数据结构类型，在Java里面Queue是一个接口，
 * 它只是定义了一个基本的Queue应该有哪些功能规约。
 * 实际上有多个Queue的实现，有的是采用线性表实现，有的基于链表实现。
 * 还有的适用于多线程的环境。java中具有Queue功能的类主要有如下几个：
 * AbstractQueue, ArrayBlockingQueue, ConcurrentLinkedQueue, LinkedBlockingQueue, DelayQueue, LinkedList, PriorityBlockingQueue, PriorityQueue和ArrayDqueue。
 */
public class MyQueue {
    public static void main(String[] args) {
/**
 * 队列是一种特殊的线性表，它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作。进行插入操作的端称为队尾，进行删除操作的端称为队头。队列中没有元素时，称为空队列。

   在队列这种数据结构中，最先插入的元素将是最先被删除的元素；反之最后插入的元素将是最后被删除的元素，因此队列又称为“先进先出”（FIFO—first in first out）的线性表。
 *
 *
 * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 offer       添加一个元素并返回true       如果队列已满，则返回false
 poll         移除并返问队列头部的元素    如果队列为空，则返回null
 peek       返回队列头部的元素             如果队列为空，则返回null
 put         添加一个元素                      如果队列满，则阻塞
 take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
    }
}
