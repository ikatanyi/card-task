package com.logicea.card.security.securityOAuthConfig;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.logicea.card.model.dto.Token;
import com.logicea.card.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  @Autowired
  JwtEncoder accessTokenEncoder;

  @Autowired
  @Qualifier("jwtRefreshTokenEncoder")
  JwtEncoder refreshTokenEncoder;

  private String createAccessToken(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("TaskCardApp")
        .issuedAt(now)
        .expiresAt(now.plus(10, ChronoUnit.MINUTES))
        .subject(String.valueOf(user.getId()))
        .build();

    return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  private String createRefreshToken(Authentication authentication) {
    UserEntity user = (UserEntity) authentication.getPrincipal();
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("TaskCardApp")
        .issuedAt(now)
        .expiresAt(now.plus(30, ChronoUnit.DAYS))
        .subject(String.valueOf(user.getId()))
        .build();

    return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }
  public Token createToken(Authentication authentication) {
    if (!(authentication.getPrincipal() instanceof UserEntity user)) {
      throw new BadCredentialsException(
          MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
      );
    }

    Token tokenDTO = new Token();
    tokenDTO.setUserId(String.valueOf(user.getId()));
    tokenDTO.setAccessToken(createAccessToken(authentication));

    String refreshToken;
    if (authentication.getCredentials() instanceof Jwt jwt) {
      Instant now = Instant.now();
      Instant expiresAt = jwt.getExpiresAt();
      Duration duration = Duration.between(now, expiresAt);
      long daysUntilExpired = duration.toDays();
      if (daysUntilExpired < 7) {
        refreshToken = createRefreshToken(authentication);
      } else {
        refreshToken = jwt.getTokenValue();
      }
    } else {
      refreshToken = createRefreshToken(authentication);
    }
    tokenDTO.setRefreshToken(refreshToken);

    return tokenDTO;
  }

}

