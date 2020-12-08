package ba.codecta.academy.repository;

import ba.codecta.academy.model.ModelObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T extends ModelObject> {
        private Map<Integer, T> objectMap = new HashMap<>();
        public T add(T modelObject){
            Integer key = 1;
            if(!objectMap.isEmpty()) {
                key = objectMap.keySet().stream().max(Integer::compare).get() +1;
            }
            modelObject.setId(key);
            objectMap.put(key, modelObject);
            return modelObject;
        }
        public List<T> findAll(){
            return new ArrayList<>(objectMap.values());
        }
        public T findById(Integer id){
            if(objectMap.containsKey(id)){
                return objectMap.get(id);
            }
            return null;
        }
        public T update(Integer id, T t){
            if(objectMap.containsKey(id)){
                return objectMap.put(id, t);
            }
            return null;
        }
}
