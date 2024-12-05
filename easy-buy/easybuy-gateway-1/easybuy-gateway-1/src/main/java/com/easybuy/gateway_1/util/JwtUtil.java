//package com.easybuy.gateway_1.util;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//    @Component
//    public class JwtUtil {
//    //    @Value("${'secret.key':'secret.key.local'}")
//        private String secretKey="cfglxdmkxcfvgbhnjkewsrdtfokwesrdtfhujikxfdcgnjmkxcfvgbhnjm";
//        @Autowired
//        private EasyBuyUserDetailsService userDetailsService;
//        public String extractUsername(String token) {
//            return extractClaim(token, (claims) -> claims.getSubject());
//        }
//
//        public Claims extractAllClaims(String token) {
//            return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
//        }
//
//        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//            Claims claims = extractAllClaims(token);
//            return claimsResolver.apply(claims);
//        }
//
//
//        public String generateToken(AuthRequest user) {
//            Map<String, Object> additionalClaims = new HashMap<>();
//            return createToken(user.getUsername(), additionalClaims);
//
//        }
//
//        private String createToken(String username, Map<String, Object> additionalClaims) {
//
//            return Jwts.builder()
//                    .claims(additionalClaims)
//                    .expiration(new Date(2023, 11, 6))
//                    .subject(username)
//                    .issuedAt(new Date(2023, 11, 6))
//                    .signWith(SignatureAlgorithm.HS256, secretKey).compact();
//
//
//        }
//
//        public UserDTO validateJwt(String jwt) throws Exception {
//            String username=extractUsername(jwt);
//            UserDTO dto= (UserDTO) userDetailsService.loadUserByUsername(username);
//            if (!dto.isAccountNonExpired()&&!dto.isEnabled()) {
//                throw new Exception("not a valid user; validation failed");
//            }
//                return dto;
//        }
//
//    }
