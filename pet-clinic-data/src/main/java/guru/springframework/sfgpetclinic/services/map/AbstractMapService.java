package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity > {

	protected Map<Long, T> map = new HashMap<>();
	
	public Set<T> findAll(){
		return new HashSet<>(map.values());
	}
	
	public T findById(Long id) {
		return map.get(id);
	}
	
	public T save(T object) {
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextId());
			}
			map.put(object.getId(), object);
		}
		return object;
	}
	
	public void deleteById(Long id) {
		map.remove(id);
	}
	
	public void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	
	private Long getNextId() {
		if (map.keySet().isEmpty()) {
			return 1L;
		} else {
			return Collections.max(map.keySet()) +1;
		}
	}
	
	
}
