package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {

    @Test
    public void parseJwt(){
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTY3MDcyNjUxOSwiYXV0aG9yaXRpZXMiOlsiYWNjb3VudGFudCIsInVzZXIiLCJzYWxlc21hbiJdLCJqdGkiOiI2M2U2OTE1Yi01NzAyLTQ0NzEtYjVjZS00ZTU5MDNhMjVjNzciLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInVzZXJuYW1lIjoiaGVpbWEifQ.Psiw8dY3c1VH0Vti8dzRPLA84wHI9lyV8CllCff5sQAgClNznIQV5ZO9q4yPrcFqzfrXRdM8vtkBcjv5npOIVWAHgbwgw0dJHVOuTy4ltst-rQCUy-ECU6U4hAomUzYH63iEj6LlDr9bVxoAwjfjdqBVmQoybC2rfY3mSDkcAkr0UO1_gCBYN3J7oujpSK1kfVlDRwIP7Eynb4UPSgegm3EA9t8WF2oJoRRmIRtzpKIJxBxrKrENiuBa5pokqJwau0pFvrNfd2aLWyxmZgPUTRsQi89zzCRBDCCRRSwlj5NW67nz-n5g5wx2QnNhKVwGKuSEwMYXgFo8T6vdzdKXig";

        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkWCTSJjoigxBNvx5pw+ifR7Vvi4LIY3QgFwLjwb32Iv8OZfqdunFaO3O7wYJr4Df5OSOWBJmF0TxLuOiqhxgTYRQgVBdwCliXIGcTwU8W+XmwwI/Mw2JVFrHYjv83AVgn4ToUyr+yRpRKijjKsDPNOndO+X59v8fXOgk6MxOgb1D0nXLKZyqZ8O7AaeqUISAhU/fHL0ZlaWEIhYJguyCEPPBkYx171ENnQ5Bd4Y8wSlAVGrW7ridHZ3rdSOlIx5jkMbOQ3ePLAdf988Lm2KCB7zh3TFLU+sE+kVQBzS27Yld//Efy7lIKiM2P+fY1XwAo+AKe7qDM0Ek750mN2zYTQIDAQAB-----END PUBLIC KEY-----";

        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));

        String claims = token.getClaims();
        System.out.println(claims);
    }
}
