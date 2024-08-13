package com.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class OurMap<K,V>
{
    private class MapNode<K,V>{
        K key;
        V value;
        MapNode<K,V>next;
        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private List<MapNode<K,V>>bucket;
    private int capacity;
    private int size;
    private final int INITIAL_CAPACITY=5;
    public OurMap(){
        bucket=new ArrayList<>();
        capacity=INITIAL_CAPACITY;
        for(int i=0;i<capacity;i++){
            bucket.add(null);
        }
    }
    int getBucketIndex(K key){
        int hashcode=key.hashCode();
        return hashcode%capacity;
    }
    public V get(K key){
        int bucketIndex=getBucketIndex(key);
        MapNode<K,V>head=bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)) return head.value;
            head=head.next;
        }
        return null;
    }
    public void put(K key,V value){
        int bucketIndex=getBucketIndex(key);
        MapNode<K,V>head=bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                head.value=value;
                return;
            }
            head=head.next;
        }
        MapNode<K,V>newHead=new MapNode<>(key,value);
        head=bucket.get(bucketIndex);
        newHead.next=head;
        bucket.set(bucketIndex,newHead);

        double load_factor=(1.0 * size)/capacity;
        System.out.println("Inserting Key "+key);
        System.out.println("Load factor : "+load_factor);
        if(load_factor>0.7) rehash();
    }
    private void rehash(){
        System.out.println("Rehashing buckets");
        List<MapNode<K,V>>temp=bucket;
        bucket=new ArrayList<>();
        for(int i=0;i<capacity;i++){
            bucket.add(null);
        }
        size=0;
        for(int i=0;i<capacity;i++){
            MapNode<K,V>head=temp.get(i);
            while(head!=null){
                put(head.key, head.value);
                head=head.next;
            }
        }
    }
    public void remove(K key){
        int bucketIndex=getBucketIndex(key);
        MapNode<K,V>head=bucket.get(bucketIndex);
        MapNode<K,V>prev=null;
        while(head!=null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    bucket.set(bucketIndex, head.next);
                    size--;
                    break;
                } else {
                    prev.next = head.next;
                    size--;
                    break;
                }
            }
            prev = head;
            head = head.next;
        }
    }
}
