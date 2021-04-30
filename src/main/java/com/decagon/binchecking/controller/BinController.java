package com.decagon.binchecking.controller;

import com.decagon.binchecking.apiresponse.ApiResponse;
import com.decagon.binchecking.apiresponse.ApiResponseAdmin;
import com.decagon.binchecking.dto.BinResponse;
import com.decagon.binchecking.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BinController {

    @Autowired
    private BinService service;

    @GetMapping
    public ResponseEntity<?> home(){
        String msg = "Welcome to Decagon Card Bin Checking Platform";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping(value = "/card-scheme/verify/{bin}")
    public ResponseEntity<ApiResponse<BinResponse>> getCardInfo(@PathVariable("bin") String bin) throws Exception {
        ApiResponse<BinResponse> ar = new ApiResponse<BinResponse>();
        BinResponse br = service.getCardInfo(bin);
        ar.setPayload(br);
        ar.setSuccess(true);
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    @GetMapping(value = "/card-scheme/stats")
    public ResponseEntity<?> getCardUsageFrequencies(@RequestParam("start") int start, @RequestParam("limit") int limit){
        ApiResponseAdmin<Map<String, Integer>> ar = new ApiResponseAdmin<>();
        Map<String, Integer> br = service.getCardUsageFrequencies(start, limit);
        ar.setPayload(br);
        ar.setSuccess(true);
        ar.setSize(br.size());
        ar.setLimit(limit);
        ar.setStart(start);
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

}
