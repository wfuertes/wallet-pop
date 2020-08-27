package com.wfuertes.earnings;

import java.util.Optional;

public interface EarningsRepository {

    Optional<Earnings> findById(Long id);

    void save(Earnings earnings);

    void update(Earnings earnings);
}
