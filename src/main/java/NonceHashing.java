import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NonceHashing {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String nickname = "chy";
        int nonce = 0;

        long startTime = System.currentTimeMillis();
        String hash = "";


        while (!hash.startsWith("0000")) {
            hash = calculateHash(nickname, nonce);
            nonce++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("4 Leading Zeros Hash Found: " + hash);
        System.out.println("Time spent: " + (endTime - startTime) + "ms");


        nonce = 0;
        startTime = System.currentTimeMillis();
        hash = "";
        while (!hash.startsWith("00000")) {
            hash = calculateHash(nickname, nonce);
            nonce++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("5 Leading Zeros Hash Found: " + hash);
        System.out.println("Time spent: " + (endTime - startTime) + "ms");
    }

    private static String calculateHash(String nickname, int nonce) throws NoSuchAlgorithmException {
        String data = nickname + nonce;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
