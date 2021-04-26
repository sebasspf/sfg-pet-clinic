package guru.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;

@Service
public class VisitMapService extends AbstractMapService<Visit> implements VisitService {

	@Override
	public Visit save(Visit visit) {
		if (visit.getPet() == null || visit.getPet().getOwner() == null
				|| visit.getPet().getId() == null || visit.getPet().getOwner().getId() == null) {
			throw new RuntimeException("Invalid visit");
		}
		
		return super.save(visit);
	}
}
