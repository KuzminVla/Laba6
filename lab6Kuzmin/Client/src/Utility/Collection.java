package Utility;

import Stuff.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import sun.net.www.protocol.http.AuthCacheValue;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class Collection {
    HashSet<LabWork> collection;
    Gson gson = new Gson();

    public Collection() {
        collection = new HashSet<LabWork>();
        LabWorkCollection.setCollection(collection);
        System.out.println("Коллекция создана. ");
    }

    public void fillCollection(String data){
        if (data==null) System.out.println("Указанный файл не найден.Коллекция пустая.");
        else {
            try {
                Type type = new TypeToken<HashSet>(){}.getType();
                HashSet <LinkedTreeMap> labs = new Gson().fromJson(data,type);
                for (LinkedTreeMap params : labs) {
                    LabWork labWork = new LabWork();
                    labWork.setId(((Double) params.get("id")).longValue());
                    labWork.setName((String) params.get("name"));
                    labWork.setMinimalPoint((Double) params.get("minimalPoint"));
                    labWork.setTunedInWorks(((Double) params.get("tunedInWorks")).longValue());
                    labWork.setDifficulty(Difficulty.valueOf((String) params.get("difficulty")));
                    LinkedTreeMap<String, Double> partsOfDate = (LinkedTreeMap) params.get("creationDate");
                    int year = partsOfDate.get("year").intValue();
                    int month = partsOfDate.get("month").intValue();
                    int day = partsOfDate.get("day").intValue();
                    labWork.setCreationDate(LocalDate.of(year,month,day));
                    LinkedTreeMap coordParams = (LinkedTreeMap) params.get("coordinates");
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(((Double) coordParams.get("x")).floatValue());
                    coordinates.setY(((Double) coordParams.get("y")).floatValue());
                    labWork.setCoordinates(coordinates);
                    LinkedTreeMap dis = (LinkedTreeMap) params.get("discipline");
                    Discipline discipline = new Discipline();
                    discipline.setSelfStudyHours(((Double) dis.get("selfStudyHours")).intValue());
                    discipline.setName((String) dis.get("name"));
                    labWork.setDiscipline(discipline);
                    collection.add(labWork);
                }
                System.out.println("Коллекция успешно заполнена.");
            } catch (JsonSyntaxException e) {
                System.out.println("Ошибка заполнения.Коллекция пустая");
            } catch (NullPointerException e) {
                e.printStackTrace();
            System.out.println("В файле указаны некорретные данные. Коллекция пустая.");
        }

        }
    }
}