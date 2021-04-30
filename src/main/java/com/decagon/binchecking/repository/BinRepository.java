package com.decagon.binchecking.repository;

import com.decagon.binchecking.model.Bin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BinRepository extends JpaRepository<Bin, Long> {
    Bin findByBin(String substring);
    @Query("select b from Bin b")
    Page<Bin> pagedBin(Pageable pageRequest);
}
