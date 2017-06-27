package spittr.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	private List<Spitter> spitterList;
	
	public SpitterRepositoryImpl() {
		spitterList = new ArrayList<>();
	}
	
	@Override
	public Spitter save(Spitter spitter) {
		spitterList.add(spitter);
		return spitter;
	}

	@Override
	public Spitter findOne(long spitterId) {
		for(Spitter spitter : spitterList){
			if(spitter.getId() == spitterId){
				return spitter;
			}
		}
		return null;
	}

	@Override
	public Spitter findByUsername(String username) {
		for(Spitter spitter : spitterList){
			if(spitter.getUsername().equals(username)){
				return spitter;
			}
		}
		return null;
	}

}
