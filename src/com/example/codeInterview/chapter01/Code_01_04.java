package com.example.codeInterview.chapter01;

import java.util.LinkedList;
import java.util.Scanner;

public class Code_01_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CatDogQueue catDogQueue = new CatDogQueue();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            if (s.equals("add")) {
                String petType = scanner.next();
                String petNo = scanner.next();
                if (petType.equals("cat")) {
                    catDogQueue.add(new Cat(Integer.valueOf(petNo)));
                } else if (petType.equals("dog")) {
                    catDogQueue.add(new Dog(Integer.valueOf(petNo)));
                } else {
                    throw new RuntimeException("err, not a cat or dog");
                }
            } else if (s.equals("pollAll")) { // 全部弹出。。。
                while (!catDogQueue.isEmpty()){
                    Pet pet = catDogQueue.pollAll();
                    System.out.println(pet.getType() + " " + pet.getNo());
                }
            } else if (s.equals("pollDog")) {
                while (!catDogQueue.isDogEmpty()){
                    Dog dog = (Dog) catDogQueue.pollDog();
                    System.out.println(dog.getType()+" "+dog.getNo());
                }
            } else if (s.equals("pollCat")) {
                while (!catDogQueue.isCatEmpty()){
                    Cat cat = (Cat) catDogQueue.pollCat();
                    System.out.println(cat.getType()+" "+cat.getNo());
                }
            } else if (s.equals("isEmpty")) {
                System.out.println(catDogQueue.isEmpty() ? "yes" : "no");
            } else if (s.equals("isDogEmpty")) {
                System.out.println(catDogQueue.isDogEmpty() ? "yes" : "no");
            } else if (s.equals("isCatEmpty")) {
                System.out.println(catDogQueue.isCatEmpty() ? "yes" : "no");
            } else {
                throw new RuntimeException("err, no such an operation");
            }
        }
    }
}

class Pet {
    private String type;
    private Integer no;

    public Pet(String type, Integer no) {
        this.type = type;
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public Integer getNo() {
        return no;
    }
}
class Dog extends Pet {
    public Dog(Integer no) {
        super("dog", no);
    }
}
class Cat extends Pet {
    public Cat(Integer no) {
        super("cat", no);
    }
}
class PetWrapper {
    private Pet pet;
    private Long time;

    public PetWrapper(Pet pet, Long time) {
        this.pet = pet;
        this.time = time;
    }

    public Pet getPet() {
        return pet;
    }

    public Long getTime() {
        return time;
    }

    public String getType() {
        return this.getPet().getType();
    }

    public Integer getNo() {
        return this.getPet().getNo();
    }
}
class CatDogQueue {
    private LinkedList<PetWrapper> cats;
    private LinkedList<PetWrapper> dogs;

    // 时间戳
    private Long time;

    public CatDogQueue() {
        this.cats = new LinkedList<>();
        this.dogs = new LinkedList<>();
        this.time = 0L;
    }

    public void add(Pet pet) {
        if(pet.getType().equals("cat")) {
            this.cats.add(new PetWrapper(pet, this.time++));
        } else if(pet.getType().equals("dog")) {
            this.dogs.add(new PetWrapper(pet, this.time++));
        }
    }

    public Pet pollAll() {
        if(!cats.isEmpty() && !dogs.isEmpty()) {
            if(cats.peek().getTime() < dogs.peek().getTime()) {
                return cats.poll().getPet();
            } else {
                return dogs.poll().getPet();
            }
        } else if(!cats.isEmpty()) {
            return cats.poll().getPet();
        } else if(!dogs.isEmpty()) {
            return dogs.poll().getPet();
        }
        return null;
    }

    public Pet pollDog() {
        if(!dogs.isEmpty()) {
            return dogs.poll().getPet();
        }
        return null;
    }

    public Pet pollCat() {
        if(!cats.isEmpty()) {
            return cats.poll().getPet();
        }
        return null;
    }

    public boolean isEmpty() {
        return dogs.isEmpty() && cats.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogs.isEmpty();
    }

    public boolean isCatEmpty() {
        return cats.isEmpty();
    }
}
