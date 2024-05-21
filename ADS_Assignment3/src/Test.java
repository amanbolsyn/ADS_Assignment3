public class Test{
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();


        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new  MyTestingClass("Key" + (int)(Math.random() * 10000));
            Student value = new Student("Student" + i, (int)(Math.random() * 100));
            table.put(key, value);
        }

    }
}
