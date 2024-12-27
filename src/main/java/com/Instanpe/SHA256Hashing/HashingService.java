package com.Instanpe.SHA256Hashing;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashingService {
        //Method to generate a hash value.Java provides inbuilt MessageDigest class for SHA-256 hashing
        public String generateHash(String input) {
            try{
                MessageDigest digest=MessageDigest.getInstance("SHA-256");
                byte[] encodeHash= digest.digest(input.getBytes(StandardCharsets.UTF_8));
                return bytesToHex(encodeHash);

            }catch(NoSuchAlgorithmException e){
                throw new RuntimeException("Error generating hash",e);
            }
        }

        //Byte to hex converter to get the hashed value in hexadecimal

         private String bytesToHex(byte[] encodeHash) {
            StringBuilder hexString=new StringBuilder(2*encodeHash.length);
            for(int i=0;i<encodeHash.length;i++){
                String hex=Integer.toHexString(0xff & encodeHash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }

    //Verify if the hash of the input matches the provided hash
    public boolean verifyHash(String input,String encodeHash){
            String computedHash=generateHash(input);
            return computedHash.equals(encodeHash);
    }




}
