package spittr.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.Spittle;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

	private List<Spittle> spittleList;
	
	public SpittleRepositoryImpl() {
		spittleList = new ArrayList<>();
	}
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return null;
	}

	@Override
	public Spittle findOne(long spittleId) {
		return null;
	}

}
