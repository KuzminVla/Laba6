package Stuff;
import java.time.LocalDate;
import java.util.HashSet;

public class LabWorkCollection {
    private static HashSet<LabWork> collection;
    public static HashSet<LabWork> getCollection() {
        return collection;
    }
    public static void setCollection(HashSet<LabWork> collection){
        LabWorkCollection.collection = collection;
    }
    public static  void clear() {
        collection.clear();
    }
    public static void insert(LabWork labWork) {
       collection.add(labWork);
    }
    public static void update(Long ind, LabWork labWork) {
        if (!isKeyFree(ind)) {
            HashSet <LabWork> set = new HashSet<>();
            for (LabWork labWork1 : LabWorkCollection.getCollection()) {
                if (labWork1.getId() != ind) set.add(labWork1);
            }
            set.add(labWork);
            collection=set;
        }
    }

    public static boolean isKeyFree(Long ind) {
        try {
            for (LabWork labWork : LabWorkCollection.getCollection())
                if (labWork.getId()==ind) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    public static void remove(Long id){
        HashSet <LabWork> set = new HashSet<>();
        for (LabWork labWork1 : LabWorkCollection.getCollection()) {
            if (labWork1.getId() != id) set.add(labWork1);
        }
        collection=set;
    }
    public static  int getSize() {
        return collection.size();
    }
    public static String getInfo() {
        return "Тип коллекции: HashSet;\nKоличество элементов коллекции: " + getSize() + ".";
    }
}
