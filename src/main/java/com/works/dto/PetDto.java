package com.works.dto;

import com.works.config.Config;
import com.works.entities.Pet;
import com.works.entities.PetColor;
import com.works.entities.PetRace;
import com.works.repositories.PetColorRepository;
import com.works.repositories.PetRaceRepository;
import com.works.repositories.PetRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetDto {

    final PetRaceRepository prRepo;
    final PetColorRepository pcRepo;
    final PetRepository pRepo;
    public PetDto(PetRaceRepository prRepo, PetColorRepository pcRepo, PetRepository pRepo) {
        this.prRepo = prRepo;
        this.pcRepo = pcRepo;
        this.pRepo = pRepo;
    }

    // petRaceList - start
    public Map<ERest,Object> petRaceList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<PetRace> pageList = prRepo.findByOrderByRidAsc(pageable);
            Long totalcount = prRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sayfalama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sayfalama işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // petRaceList - end

    // petColorList - start
    public Map<ERest,Object> petColorList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<PetColor> pageList = pcRepo.findByOrderByPcidAsc(pageable);
            Long totalcount = prRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sayfalama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sayfalama işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // petColorList - end

    // petRaceAdd - start
    public Map<ERest,Object> petRaceAdd( PetRace petRace){
        Map<ERest, Object> hm = new LinkedHashMap<>();


        try {
            PetRace pRace = prRepo.save(petRace);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, pRace);
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu ırk(" + petRace.getPrace() + ") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }
    // petRaceAdd - end

    // petColorAdd - start
    public Map<ERest,Object> petColorAdd( PetColor petColor){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            PetColor pColor = pcRepo.save(petColor);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, pColor);
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu renk(" + petColor.getPcolor() + ") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }
    // petColorAdd - end


    // petList - start
    public Map<ERest,Object> petList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Pet> pageList = pRepo.findByOrderByPidAsc(pageable);
            Long totalcount = pRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sayfalama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sayfalama işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // petList - end

    // petAdd - start
    public Map<ERest,Object> petAdd( Pet pet){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Pet p = pRepo.save(pet);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, p );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu çip no (" + pet.getCchip() + ") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }
    // petAdd - end

    // updatePet - start
    public Map<ERest, Object> updatePet( Pet pet) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( pet.getPid() != null ){

            Optional<Pet> oPet = pRepo.findById( pet.getPid());
            if( oPet.isPresent() ) {

                try {
                    pRepo.saveAndFlush( pet );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme başarılı");
                    hm.put(ERest.result, pet);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, "Bu çip no (" + pet.getCchip() + ") adresi ile daha önce kayıt yapılmış");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, pet);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, pet);
        }


        return hm;

    }
    // updatePet - end








}
