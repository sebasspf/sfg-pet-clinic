package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.services.CrudService;

public class AbstractJpaService<T extends BaseEntity, R extends CrudRepository<T, Long>> implements CrudService<T,Long>{

	protected final R repository;

	public AbstractJpaService(R repository) {
		super();
		this.repository = repository;
	}
	
	@Override
    public Set<T> findAll() {
        Set<T> results = new HashSet<T>();
        repository.findAll().forEach(results::add);
        return results;
    }

	@Override
	public T findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public T save(T object) {
		return repository.save(object);
	}

	@Override
	public void delete(T object) {
		repository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
