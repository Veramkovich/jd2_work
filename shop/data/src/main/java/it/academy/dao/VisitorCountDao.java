package it.academy.dao;

import it.academy.model.VisitorCount;

public interface VisitorCountDao {

    VisitorCount readCount(int id);

    int updateCount(VisitorCount visitorCount);
}
