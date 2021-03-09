package it.academy.service;

import it.academy.dao.VisitorCountDao;
import it.academy.model.VisitorCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitorCountService {

    @Autowired
    private VisitorCountDao visitorCountDao;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int readCount() {
        final VisitorCount visitorCount = visitorCountDao.readCount(1);
        return visitorCount == null ? 0 : visitorCount.getCount();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateCount() {
        int id = 1;
        VisitorCount visitorCount = visitorCountDao.readCount(id);
        if (visitorCount == null) {
            visitorCount = new VisitorCount();
            visitorCount.setId(id);
            visitorCount.setCount(0);
        }
        int count = visitorCount.getCount();
        count++;
        visitorCount.setCount(count);
        return visitorCountDao.updateCount(visitorCount);
    }
}
