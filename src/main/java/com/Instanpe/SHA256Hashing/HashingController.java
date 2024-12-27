package com.Instanpe.SHA256Hashing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sha256")
public class HashingController {
    @Autowired
    private HashingService hashingService;

    //Endpoint to Generate a hash value
    @PostMapping("/generate")
    public ResponseEntity<String> generateHash(@RequestBody String input){
        log.info("Generating a hash value for the provided input {}",input);
        String hash= hashingService.generateHash(input);
        return ResponseEntity.ok(hash);
    }
    //To Verify a Hash against the input string
    @PostMapping("/verify")
    public ResponseEntity<String> verifyHash(@RequestParam String input,@RequestParam String hash){
        log.info("Verifying a hash with the original string provided");
        boolean isVerified= hashingService.verifyHash(input, hash);
        return ResponseEntity.ok(isVerified?"Hash is Verified.":"Hash does not match.");
    }


}
