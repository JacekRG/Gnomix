package pl.bony.gnomix.security;

import com.auth0.jwt.algorithms.Algorithm;

public final class SecurityUtils {

    private SecurityUtils() { }

    public static final int TOKEN_TTL = 15 * 60 * 1000;
    public static final int REFRESH_TOKEN_TTL = 120 * 60 * 1000;

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256("qwevcxblkdfssdffsfsgfdgas".getBytes());
    }
}
