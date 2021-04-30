package com.decagon.binchecking.service;

import com.decagon.binchecking.dto.BinDetailedResponse;
import com.decagon.binchecking.dto.BinResponse;
import com.decagon.binchecking.exception.ResourceNotFoundException;
import com.decagon.binchecking.model.Bin;
import com.decagon.binchecking.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BinService {

    @Autowired
    private BinRepository repository;

    public BinResponse getCardInfo(String bin) throws Exception {
        ResponseEntity<BinDetailedResponse> re;
        String sixDigitBin = bin.substring(0,6);

        Map<String, String> uriVariable = new HashMap<>();
        uriVariable.put("bin", bin);
        try{
            re = new RestTemplate().getForEntity("https://lookup.binlist.net/{bin}", BinDetailedResponse.class, uriVariable);
        }catch(Exception ex){
            throw new ResourceNotFoundException("Invalid BIN/IIN");
        }
        Bin b = repository.findByBin(sixDigitBin);
        if(b != null){
            b.setCheckCounter(b.getCheckCounter() + 1);
            b.setTime(ZonedDateTime.now());
            repository.save(b);
        }
        else{
            Bin newBin = new Bin(sixDigitBin, 1, ZonedDateTime.now());
            repository.save(newBin);
        }

        BinResponse br = new BinResponse(re.getBody().getBank().get("name"), re.getBody().getScheme(), re.getBody().getType());
        return br;
    }


    public Map<String, Integer> getCardUsageFrequencies(int start, int limit) {
        List<Bin> binList = repository.findAll();
        int counter = 0;
        Map<String, Integer> payload = new HashMap<>();
        for(Bin bin : binList){
            counter++;
            payload.put(bin.getBin(), bin.getCheckCounter());
            if (counter == limit) break;
        }
        return payload;
    }

}
