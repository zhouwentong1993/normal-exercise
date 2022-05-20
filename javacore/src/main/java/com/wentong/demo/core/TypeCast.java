package com.wentong.demo.core;

public class TypeCast {

    public static void main(String[] args) {
        Object student = new Student("jack", "100", "北京");
        System.out.println(student);
        Student s = (Student) student;
        change(s);
        System.out.println(student);
        System.out.println(null instanceof Student);
    }

    private static void change(Student s) {
        s.name = "rename";
    }

    static class Student {
        String name;
        String grade;
        String address;

        public Student(String name, String grade, String address) {
            this.name = name;
            this.grade = grade;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade='" + grade + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

}
